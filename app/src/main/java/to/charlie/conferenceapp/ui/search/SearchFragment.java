package to.charlie.conferenceapp.ui.search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import to.charlie.conferenceapp.R;
import to.charlie.conferenceapp.model.SessionListViewModel;
import to.charlie.conferenceapp.ui.timetableList.TimetableRecyclerWithListAdapter;

public class SearchFragment extends Fragment
{
	private SessionListViewModel sessionListViewModel;
	private TimetableRecyclerWithListAdapter sessionsRecyclerAdapter;
	private Fragment observerOwner;

	public SearchFragment()
	{
		//Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_search, container, false);
		observerOwner = this;

		SearchView searchView = view.findViewById(R.id.search);
		sessionListViewModel = ViewModelProviders.of(this).get(SessionListViewModel.class);
		//at the start you don't want any sessions to be found so have an empty search
		sessionListViewModel.setSearchCriteria(false, null, null, true);
		sessionsRecyclerAdapter = sessionListViewModel.getAdapter();
		if (sessionsRecyclerAdapter == null)
		{
			sessionsRecyclerAdapter = new TimetableRecyclerWithListAdapter();
			sessionListViewModel.getSessionList().observe(this, sessions -> sessionsRecyclerAdapter.changeDataSet(sessions));
			sessionListViewModel.setAdapter(sessionsRecyclerAdapter);
		}

		sessionsRecyclerAdapter.setUsedForSearch(true);

		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
		{
			@Override
			public boolean onQueryTextSubmit(String query)
			{
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText)
			{
				Log.i("SEARCH", "search query is " + newText);
				//same as above if then have an empty search
				if ("".equals(newText))
				{
					sessionListViewModel.setSearchCriteria(false, null, null, true);
				}
				else
				{
					sessionListViewModel.setSearchCriteria(false, null, newText, false);
				}
				sessionListViewModel.getSessionList().observe(observerOwner, sessions -> sessionsRecyclerAdapter.changeDataSet(sessions));
				return false;
			}
		});

		RecyclerView listSessions = view.findViewById(R.id.search_session_list);
		listSessions.setLayoutManager(new LinearLayoutManager(getContext()));
		listSessions.setAdapter(sessionsRecyclerAdapter);

		return view;
	}
}
