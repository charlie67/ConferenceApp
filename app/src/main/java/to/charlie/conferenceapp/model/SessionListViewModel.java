package to.charlie.conferenceapp.model;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import to.charlie.conferenceapp.datasource.ConferenceRepository;
import to.charlie.conferenceapp.ui.timetableList.TimetableRecyclerWithListAdapter;

/**
 * Session view model. Holds UI data for sessions. This is used to display a list of sessions
 * anywhere in the app that requires it.
 *
 * @author Charlie Robinson
 * @version 2/12/19
 */
public class SessionListViewModel extends AndroidViewModel
{
	private LiveData<List<Session>> sessionList;
	private TimetableRecyclerWithListAdapter adapter;
	private ConferenceRepository repository;

	public SessionListViewModel(@NonNull Application application)
	{
		super(application);

		repository = new ConferenceRepository(application);
	}

	/**
	 * Set the search criteria for the sessions to display. This is used anywhere in the app that
	 * requires a list of sessions and so needs to be able to know what sessions to find.
	 *
	 * @param favourites     Should favourites be shown.
	 * @param speakerId      The speaker ID to find for sessions. NULL if not to search by speaker
	 * @param searchCriteria The search criteria that the title should contain
	 */
	public void setSearchCriteria(boolean favourites, String speakerId, String searchCriteria,
																boolean emptySearch)
	{
		if (favourites)
		{
			sessionList = repository.getAllFavouriteSessions();
		}
		else if (!TextUtils.isEmpty(speakerId))
		{
			sessionList = repository.getAllSessionsWhereSpeakerHasId(speakerId);
		}
		else if (!TextUtils.isEmpty(searchCriteria))
		{
			Log.i("TEST", "new session list search for");
			sessionList = repository.searchForSessionWithTitle(searchCriteria);
		}
		else if (emptySearch)
		{
			sessionList = repository.getSessionWithId(null);
		}
		else
		{
			sessionList = repository.getAllSessions();
		}
	}

	public LiveData<List<Session>> getSessionList()
	{
		return sessionList;
	}

	public TimetableRecyclerWithListAdapter getAdapter()
	{
		return adapter;
	}

	public void setAdapter(TimetableRecyclerWithListAdapter adapter)
	{
		this.adapter = adapter;
	}
}
