package to.charlie.conferenceapp.ui.sessions;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import to.charlie.conferenceapp.R;
import to.charlie.conferenceapp.model.Location;
import to.charlie.conferenceapp.model.Session;
import to.charlie.conferenceapp.model.SessionViewModel;
import to.charlie.conferenceapp.model.Speaker;

import static androidx.core.util.Preconditions.checkArgument;

public class SessionItemFragment extends Fragment implements OnMapReadyCallback
{
	private SessionViewModel sessionViewModel;

	private TextView speakerName;
	private TextView speakerBiography;
	private ImageView speakerImage;

	private TextView locationNameHeader;
	private TextView locationName;
	private TextView locationDescription;

	private MapView locationMap;

	private ImageButton favouriteButton;

	private Location location;
	private Session session;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		final View view = inflater.inflate(R.layout.fragment_session_item, container, false);
		sessionViewModel = ViewModelProviders.of(this).get(SessionViewModel.class);

		TextView sessionTitle = view.findViewById(R.id.session_view_title);
		TextView sessionDateTime = view.findViewById(R.id.session_view_date_time);
		TextView sessionDescription = view.findViewById(R.id.session_view_session_description);
		TextView sessionType = view.findViewById(R.id.session_view_session_type);

		favouriteButton = view.findViewById(R.id.session_view_set_favourite_button);

		speakerName = view.findViewById(R.id.session_view_speaker_name);
		speakerImage = view.findViewById(R.id.session_view_speaker_picture);
		speakerBiography = view.findViewById(R.id.session_view_speaker_biography);

		locationName = view.findViewById(R.id.session_view_location);
		locationNameHeader = view.findViewById(R.id.session_view_location_name);
		locationDescription = view.findViewById(R.id.session_view_location_description);

		locationMap = view.findViewById(R.id.session_view_location_map);
		locationMap.onCreate(savedInstanceState);

		Bundle arguments = getArguments();
		if (arguments != null)
		{
			session = Session.SessionFromBundle(arguments);

			LocalDate sessionInLocalDate = LocalDate.parse(session.getSessionDate());
			Locale locale = view.getResources().getConfiguration().getLocales().get(0);

			//want to display the date as e.g. Wednesday 11th December 2019
			String dayOfWeek = sessionInLocalDate.getDayOfWeek().getDisplayName(TextStyle.FULL, locale);
			String dayOfMonth = String.valueOf(sessionInLocalDate.getDayOfMonth());
			String daySuffix = getDayOfMonthSuffix(sessionInLocalDate.getDayOfMonth());
			String monthName = sessionInLocalDate.getMonth().getDisplayName(TextStyle.FULL, locale);

			String wholeDateString = dayOfWeek + " " + dayOfMonth + daySuffix + " " + monthName;

			String dateTimeString = wholeDateString + ", " + session.getTimeStart() + " - "
							+ session.getTimeEnd();

			sessionTitle.setText(session.getTitle());
			sessionDateTime.setText(dateTimeString);
			sessionDescription.setText(session.getContent());
			sessionType.setText(session.getSessionType().getTypeName());

			// by default the icon is set to the not favourite state so only needs setting if it is
			// actually a favourite
			if (session.isFavourite())
			{
				favouriteButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_black_24dp, null));
			}

			favouriteButton.setOnClickListener(this::favouriteButtonOnClickListener);

			LiveData<Location> locationLiveData = sessionViewModel.getLocationWithId(session.getLocationId());
			locationLiveData.observe(this, this::setLocationInfo);

			//Not every session has a speaker e.g. registration so check if there is a speaker id and
			// if so load it and set the views
			if (!TextUtils.isEmpty(session.getSpeakerId()))
			{
				LiveData<Speaker> speakerLiveData = sessionViewModel.getSpeakerWithId(session.getSpeakerId());
				speakerLiveData.observe(this, this::setSpeakerInfo);
			}
			else
			{
				//if there isn't a speaker then these views need to be gone because otherwise there's
				// just a huge empty space
				speakerBiography.setVisibility(View.GONE);
				speakerImage.setVisibility(View.GONE);
				speakerName.setVisibility(View.GONE);
			}

		}

		return view;
	}

	private void favouriteButtonOnClickListener(View v)
	{
		// get the current favourite state and invert that because it is being updated to the opposite
		// of what it was
		boolean favourite = !session.isFavourite();
		sessionViewModel.setSessionAsFavourite(session);

		if (favourite)
		{
			favouriteButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_black_24dp, null));
		}
		else
		{
			favouriteButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp, null));
		}
	}

	String getDayOfMonthSuffix(final int n)
	{
		//check if the day of the month is at most 31 and at least 1
		checkArgument(n >= 1 && n <= 31, "illegal day of month: " + n);
		//11th 12th and 13th
		if (n >= 11 && n <= 13)
		{
			return "th";
		}
		switch (n % 10)
		{
			case 1:
				return "st";
			case 2:
				return "nd";
			case 3:
				return "rd";
			default:
				return "th";
		}
	}

	private void setSpeakerInfo(Speaker speaker)
	{
		String nameHeaderString =
						getResources().getString(R.string.speaker_name_first_part) + " " + speaker.getName();
		speakerName.setText(nameHeaderString);
		speakerBiography.setText(speaker.getBiography());

		AssetManager assetManager = getResources().getAssets();
		try (InputStream is = assetManager.open("images/" + speaker.getId() + ".jpg"))
		{
			Bitmap bitmap = BitmapFactory.decodeStream(is);
			speakerImage.setImageBitmap(bitmap);
		} catch (IOException e)
		{
			Log.e("Speaker Image", "Error opening speaker image file", e);
		}

	}

	private void setLocationInfo(Location location)
	{
		this.location = location;

		locationNameHeader.setText(location.getName());
		locationName.setText(location.getName());
		locationDescription.setText(location.getDescription());

		locationMap.getMapAsync(this);
	}

	public boolean onMapTouchEvent(MotionEvent ev)
	{
		int action = ev.getAction();
		switch (action)
		{
			case MotionEvent.ACTION_DOWN:
				// Disallow ScrollView to intercept touch events.
				getView().getParent().requestDisallowInterceptTouchEvent(true);
				break;

			case MotionEvent.ACTION_UP:
				// Allow ScrollView to intercept touch events.
				getView().getParent().requestDisallowInterceptTouchEvent(false);
				break;
		}

		// Handle MapView's touch events.
		locationMap.onTouchEvent(ev);
		return true;
	}

	@Override
	public void onMapReady(GoogleMap googleMap)
	{
		LatLng locationMarker = new LatLng(location.getLatitude(), location.getLongitude());
		googleMap.addMarker(new MarkerOptions().position(locationMarker).title(location.getName()));
		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationMarker, 15.0f));

//		googleMap.setOnMapLongClickListener(this::onMapTouchEvent);
	}

	@Override
	public void onResume()
	{
		super.onResume();
		//need to call the method on the map to ensure it correctly works
		locationMap.onResume();
	}

	@Override
	public void onPause()
	{
		super.onPause();
		//need to call the method on the map to ensure it correctly works
		locationMap.onPause();
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		//need to call the method on the map to ensure it correctly works
		locationMap.onDestroy();
	}

	@Override
	public void onLowMemory()
	{
		super.onLowMemory();
		//need to call the method on the map to ensure it correctly works
		locationMap.onLowMemory();
	}
}