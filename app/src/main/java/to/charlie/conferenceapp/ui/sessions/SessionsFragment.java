package to.charlie.conferenceapp.ui.sessions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import to.charlie.conferenceapp.R;
import to.charlie.conferenceapp.model.SessionViewModel;

public class SessionsFragment extends Fragment
{
	private SessionViewModel sessionViewModel;
	private SessionsRecyclerWithListAdapter sessionsRecyclerAdapter;

	public SessionsFragment()
	{
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		final View view = inflater.inflate(R.layout.fragment_sessions, container, false);

		sessionViewModel = ViewModelProviders.of(this).get(SessionViewModel.class);
		sessionsRecyclerAdapter = sessionViewModel.getAdapter();
		if (sessionsRecyclerAdapter == null)
		{
			sessionsRecyclerAdapter = new SessionsRecyclerWithListAdapter();
			sessionViewModel.getSessionList().observe(this, sessions -> sessionsRecyclerAdapter.changeDataSet(sessions));
			sessionViewModel.setAdapter(sessionsRecyclerAdapter);
		}

		RecyclerView listSessions = view.findViewById(R.id.session_list);
		listSessions.setLayoutManager(new LinearLayoutManager(getContext()));
		listSessions.setAdapter(sessionsRecyclerAdapter);

		return view;
	}


}
