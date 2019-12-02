package to.charlie.conferenceapp.ui.speakers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import to.charlie.conferenceapp.R;
import to.charlie.conferenceapp.model.SessionListViewModel;
import to.charlie.conferenceapp.ui.sessionList.SessionsRecyclerWithListAdapter;

public class SpeakerSessionListFragment extends Fragment
{
	private SessionsRecyclerWithListAdapter sessionsRecyclerAdapter;

	public SpeakerSessionListFragment()
	{
		//required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		final View view = inflater.inflate(R.layout.fragment_sessions, container, false);

		SessionListViewModel sessionListViewModel = ViewModelProviders.of(this).get(SessionListViewModel.class);

		sessionListViewModel.setSearchCriteria(false, getArguments().getString("SPEAKER_ID"));
		sessionsRecyclerAdapter = sessionListViewModel.getAdapter();
		if (sessionsRecyclerAdapter == null)
		{
			sessionsRecyclerAdapter = new SessionsRecyclerWithListAdapter();
			sessionListViewModel.getSessionList().observe(this, sessions -> sessionsRecyclerAdapter.changeDataSet(sessions));
			sessionListViewModel.setAdapter(sessionsRecyclerAdapter);
		}
		//tell the recycler view that it is being used for the speakers list fragment
		sessionsRecyclerAdapter.setListDisplayType(false, true);

		RecyclerView listSessions = view.findViewById(R.id.session_list);
		listSessions.setLayoutManager(new LinearLayoutManager(getContext()));
		listSessions.setAdapter(sessionsRecyclerAdapter);

		return view;
	}
}
