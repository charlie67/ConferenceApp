package to.charlie.conferenceapp.ui.sessions;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import to.charlie.conferenceapp.R;
import to.charlie.conferenceapp.model.Location;
import to.charlie.conferenceapp.model.Session;
import to.charlie.conferenceapp.model.SessionViewModel;
import to.charlie.conferenceapp.model.Speaker;

import static androidx.core.util.Preconditions.checkArgument;

public class SessionItemFragment extends Fragment
{
	TextView speakerName;


	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		final View view = inflater.inflate(R.layout.fragment_session_item, container, false);
		SessionViewModel sessionViewModel = ViewModelProviders.of(this).get(SessionViewModel.class);

		TextView sessionTitle = view.findViewById(R.id.session_view_title);
		TextView sessionDate = view.findViewById(R.id.session_view_date);
		TextView sessionTime = view.findViewById(R.id.session_view_time);
		TextView sessionDescription = view.findViewById(R.id.session_view_session_description);
		TextView sessionType = view.findViewById(R.id.session_view_session_type);

		speakerName = view.findViewById(R.id.session_view_speaker_name);

		Bundle arguments = getArguments();
		if (arguments != null)
		{
			Session session = Session.SessionFromBundle(arguments);

			LocalDate sessionInLocalDate = LocalDate.parse(session.getSessionDate());
			Locale locale = view.getResources().getConfiguration().getLocales().get(0);

			String dayOfWeek = sessionInLocalDate.getDayOfWeek().getDisplayName(TextStyle.FULL, locale);
			String monthName = sessionInLocalDate.getMonth().getDisplayName(TextStyle.FULL, locale);

			sessionTitle.setText(session.getTitle());
			sessionDate.setText(session.getSessionDate());
			sessionTime.setText(session.getTimeStart());
			sessionDescription.setText(session.getContent());
			sessionType.setText(session.getSessionType().getTypeName());

			LiveData<Location> locationLiveData = sessionViewModel.getLocationWithId(session.getLocationId());

			//Not every session has a speaker e.g. registration so check if there is a speaker id and
			// if so load it and set the views
			if (!TextUtils.isEmpty(session.getSpeakerId()))
			{
				LiveData<Speaker> speakerLiveData = sessionViewModel.getSpeakerWithId(session.getSpeakerId());
				speakerLiveData.observe(this, this::setSpeakerInfo);
			}
//			else
//			{
//				speakerName.setHeight(0);
//			}

		}

		return view;
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
		speakerName.setText(speaker.getName());
	}
}