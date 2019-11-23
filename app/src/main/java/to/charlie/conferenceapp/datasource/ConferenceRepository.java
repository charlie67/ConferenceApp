package to.charlie.conferenceapp.datasource;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import to.charlie.conferenceapp.model.Location;
import to.charlie.conferenceapp.model.LocationDao;
import to.charlie.conferenceapp.model.Session;
import to.charlie.conferenceapp.model.SessionDao;
import to.charlie.conferenceapp.model.Speaker;
import to.charlie.conferenceapp.model.SpeakerDao;

public class ConferenceRepository
{
	private LocationDao locationDao;
	private SessionDao sessionDao;
	private SpeakerDao speakerDao;

	public ConferenceRepository(Application application)
	{
		//create the database and get the DAO objects from it
		ConferenceRoomDatabase db = ConferenceRoomDatabase.getDatabase(application);
		locationDao = db.getLocationDao();
		sessionDao = db.getSessionDao();
		speakerDao = db.getSpeakerDao();
	}

	public LiveData<List<Session>> getAllSessions()
	{
		return sessionDao.getAllSessions();
	}

	public LiveData<List<Session>> getAllFavouriteSessions()
	{
		return sessionDao.getAllFavouriteSessions();
	}

	public LiveData<Speaker> getSpeakerWithId(String speakerId)
	{
		return speakerDao.findSpeakerById(speakerId);
	}

	public LiveData<Location> getLocationWithId(String locationId)
	{
		return locationDao.findLocationById(locationId);
	}

	private static class getSpeakerAsyncTask extends AsyncTask<String, Void, Speaker>
	{
		SpeakerDao mAsyncTaskDao;

		getSpeakerAsyncTask(SpeakerDao mAsyncTaskDao)
		{
			this.mAsyncTaskDao = mAsyncTaskDao;
		}

		protected Speaker doInBackground(final String... id)
		{
			mAsyncTaskDao.findSpeakerById(id[0]);
			return null;
		}


	}
}
