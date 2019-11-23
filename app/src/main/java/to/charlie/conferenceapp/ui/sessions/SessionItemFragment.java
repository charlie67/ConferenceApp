package to.charlie.conferenceapp.ui.sessions;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import to.charlie.conferenceapp.R;
import to.charlie.conferenceapp.model.Location;
import to.charlie.conferenceapp.model.Session;
import to.charlie.conferenceapp.model.SessionViewModel;
import to.charlie.conferenceapp.model.Speaker;

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

		speakerName = view.findViewById(R.id.session_view_speaker_name);

		Bundle arguments = getArguments();
		if (arguments != null)
		{
			Session session = Session.SessionFromBundle(arguments);
			Log.i("id", "id is " + session.getId());
			sessionTitle.setText(session.getTitle());
			sessionDate.setText(session.getSessionDate());
			sessionTime.setText(session.getTimeStart());
			sessionDescription.setText(session.getContent());

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

	private void setSpeakerInfo(Speaker speaker)
	{
		speakerName.setText(speaker.getName());
	}
}