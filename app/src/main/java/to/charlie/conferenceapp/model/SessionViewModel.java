package to.charlie.conferenceapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import to.charlie.conferenceapp.datasource.ConferenceRepository;
import to.charlie.conferenceapp.ui.sessions.SessionsRecyclerWithListAdapter;

public class SessionViewModel extends AndroidViewModel
{
	private ConferenceRepository repository;
	private LiveData<List<Session>> sessionList;
	private SessionsRecyclerWithListAdapter adapter;

	public SessionViewModel(@NonNull Application application)
	{
		super(application);

		repository = new ConferenceRepository(application);

		sessionList = repository.getAllSessions();

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
