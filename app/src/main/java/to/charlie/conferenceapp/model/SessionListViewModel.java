package to.charlie.conferenceapp.model;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import to.charlie.conferenceapp.datasource.ConferenceRepository;
import to.charlie.conferenceapp.ui.timetableList.TimetableRecyclerWithListAdapter;

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

	public void setSearchCriteria(boolean favourites, String speakerId)
	{
		if (favourites)
		{
			sessionList = repository.getAllFavouriteSessions();
		}
		else if (!TextUtils.isEmpty(speakerId))
		{
			sessionList = repository.getAllSessionsWhereSpeakerHasId(speakerId);
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
