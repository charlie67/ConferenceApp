package to.charlie.conferenceapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import to.charlie.conferenceapp.datasource.ConferenceRepository;

public class SessionViewModel extends AndroidViewModel
{
	private ConferenceRepository repository;
	private LiveData<List<Session>> sessionList;

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
}
