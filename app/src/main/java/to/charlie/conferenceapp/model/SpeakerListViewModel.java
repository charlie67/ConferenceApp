package to.charlie.conferenceapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import to.charlie.conferenceapp.datasource.ConferenceRepository;
import to.charlie.conferenceapp.ui.speakers.SpeakersRecyclerWithListAdapter;

/**
 * Speaker list view model. Holds all the information for the speaker list view.
 *
 * @author Charlie Robinson
 * @version 2/12/19
 */
public class SpeakerListViewModel extends AndroidViewModel
{
	private SpeakersRecyclerWithListAdapter adapter;
	private LiveData<List<Speaker>> speakerList;

	public SpeakerListViewModel(@NonNull Application application)
	{
		super(application);

		ConferenceRepository repository = new ConferenceRepository(application);
		speakerList = repository.getAllSpeakers();
	}

	public LiveData<List<Speaker>> getSpeakerList()
	{
		return speakerList;
	}

	public SpeakersRecyclerWithListAdapter getAdapter()
	{
		return adapter;
	}

	public void setAdapter(SpeakersRecyclerWithListAdapter adapter)
	{
		this.adapter = adapter;
	}
}
