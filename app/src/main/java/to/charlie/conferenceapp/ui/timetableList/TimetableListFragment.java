package to.charlie.conferenceapp.ui.timetableList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import to.charlie.conferenceapp.R;
import to.charlie.conferenceapp.model.SessionListViewModel;

/**
 * Timetable list fragment. This fragment is navigated to from one of the main, timetable,
 * favourites or speakers fragment. In the bundle that does the navigation a navigation type is
 * provided.
 *
 * @author Charlie Robinson
 * @version 2/12/19
 */
public class TimetableListFragment extends Fragment
{
	private TimetableRecyclerWithListAdapter sessionsRecyclerAdapter;
	private SessionListViewModel sessionListViewModel;

	public static String NAVIGATION_TYPE_BUNDLE_KEY = "NAVIGATION_TYPE";
	public static String SPEAKER_ID_BUNDLE_KEY = "SPEAKER_ID";

	public TimetableListFragment()
	{
		// Required empty public constructor
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		sessionListViewModel.setAdapter(null);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		final View view = inflater.inflate(R.layout.fragment_timetable_list, container, false);

		sessionListViewModel = ViewModelProviders.of(this).get(SessionListViewModel.class);

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

			sessionListViewModel.getSessionList().observe(this,
							sessions ->
							{
								sessionsRecyclerAdapter.changeDataSet(sessions);
								//if there are sessions then remove the no sessions to show text
								checkSessionSize(view, sessions.size());
							});

			sessionListViewModel.setAdapter(sessionsRecyclerAdapter);
		}

		//if there are sessions then remove the no sessions to show text
		//this is needed here as well because this n
		checkSessionSize(view, sessionsRecyclerAdapter.getItemCount());

		RecyclerView listSessions = view.findViewById(R.id.session_list);
		listSessions.setLayoutManager(new LinearLayoutManager(getContext()));
		listSessions.setAdapter(sessionsRecyclerAdapter);

		return view;
	}

	private void checkSessionSize(View view, int size)
	{
		//check if the no sessions to show text should be removed.
		if (size > 0)
		{
			TextView noSessionsText = view.findViewById(R.id.timetable_no_sessions_text);
			noSessionsText.setVisibility(View.GONE);
		}
	}
}
