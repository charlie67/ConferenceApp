package to.charlie.conferenceapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import to.charlie.conferenceapp.datasource.ConferenceRepository;
import to.charlie.conferenceapp.ui.sessionList.SessionsRecyclerWithListAdapter;

public class SessionListViewModel extends AndroidViewModel
{
	private LiveData<List<Session>> sessionList;
	private SessionsRecyclerWithListAdapter adapter;
	private ConferenceRepository repository;

	/**
	 * This model is used for both the session list and the favourites list, this variable is set
	 * to true when this is being used for the favourites list
	 */
	private boolean favouriteList;

	public SessionListViewModel(@NonNull Application application)
	{
		super(application);

		repository = new ConferenceRepository(application);

		searchForSessions();
	}

	private void searchForSessions()
	{
		if (favouriteList)
		{
			sessionList = repository.getAllFavouriteSessions();
		}
		else
		{
			sessionList = repository.getAllSessions();
		}
	}

	public void setFavourites(boolean favouriteList)
	{
		this.favouriteList = favouriteList;
		searchForSessions();
	}

	public LiveData<List<Session>> getSessionList()
	{
		return sessionList;
	}

	public SessionsRecyclerWithListAdapter getAdapter()
	{
		return adapter;
	}

	public void setAdapter(SessionsRecyclerWithListAdapter adapter)
	{
		this.adapter = adapter;
	}
}
