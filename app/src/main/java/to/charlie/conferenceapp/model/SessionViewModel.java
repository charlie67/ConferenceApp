package to.charlie.conferenceapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import to.charlie.conferenceapp.datasource.ConferenceRepository;

/**
 * Session view model. Holds all the UI data to show the individual session info screen.
 *
 * @author Charlie Robinson
 * @version 2/12/19
 */
public class SessionViewModel extends AndroidViewModel
{
	private ConferenceRepository repository;

	public SessionViewModel(@NonNull Application application)
	{
		super(application);

		repository = new ConferenceRepository(application);
	}

	public LiveData<Speaker> getSpeakerWithId(String id)
	{
		return repository.getSpeakerWithId(id);
	}

	public LiveData<Location> getLocationWithId(String id)
	{
		return repository.getLocationWithId(id);
	}

	public void setSessionAsFavourite(Session sessionToSetAsFavourite)
	{
		repository.setSessionAsFavourite(sessionToSetAsFavourite);
	}

}
