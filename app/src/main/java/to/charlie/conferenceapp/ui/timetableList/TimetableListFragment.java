package to.charlie.conferenceapp.ui.timetableList;

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

public class TimetableListFragment extends Fragment
{
	private TimetableRecyclerWithListAdapter sessionsRecyclerAdapter;

	public static String NAVIGATION_TYPE_BUNDLE_KEY = "NAVIGATION_TYPE";
	public static String SPEAKER_ID_BUNDLE_KEY = "SPEAKER_ID";

	public TimetableListFragment()
	{
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		final View view = inflater.inflate(R.layout.fragment_timetable_list, container, false);

		SessionListViewModel sessionListViewModel = ViewModelProviders.of(this).get(SessionListViewModel.class);

		NavigationType navigationType;
		if (getArguments() == null)
		{
			navigationType = NavigationType.ALL;
		}
		else
		{
			String navigationTypeString = getArguments().getString(NAVIGATION_TYPE_BUNDLE_KEY);

			navigationType = NavigationType.valueOf(navigationTypeString.toUpperCase());
		}

		switch (navigationType)
		{
			case SPEAKER:
				String speakerId = getArguments().getString(SPEAKER_ID_BUNDLE_KEY);
				sessionListViewModel.setSearchCriteria(false, speakerId);
				break;

			case FAVOURITES:
				sessionListViewModel.setSearchCriteria(true, null);
				break;

			case ALL:
				sessionListViewModel.setSearchCriteria(false, null);
				break;
		}

		sessionsRecyclerAdapter = sessionListViewModel.getAdapter();
		if (sessionsRecyclerAdapter == null)
		{
			sessionsRecyclerAdapter = new TimetableRecyclerWithListAdapter();
			sessionListViewModel.getSessionList().observe(this, sessions -> sessionsRecyclerAdapter.changeDataSet(sessions));
			sessionListViewModel.setAdapter(sessionsRecyclerAdapter);
		}

		RecyclerView listSessions = view.findViewById(R.id.session_list);
		listSessions.setLayoutManager(new LinearLayoutManager(getContext()));
		listSessions.setAdapter(sessionsRecyclerAdapter);

		return view;
	}
}
