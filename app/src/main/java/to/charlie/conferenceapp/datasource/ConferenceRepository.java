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

	public void setSessionAsFavourite(Session session)
	{
		new SetSessionFavouriteAsyncTask(sessionDao).execute(session);
	}

	public LiveData<List<Session>> getAllSessions()
	{
		return sessionDao.getAllSessions();
	}

	public LiveData<List<Session>> getAllFavouriteSessions()
	{
		return sessionDao.getAllFavouriteSessions();
	}

	public LiveData<List<Speaker>> getAllSpeakers()
	{
		return speakerDao.getAllSpeakers();
	}

	public LiveData<Speaker> getSpeakerWithId(String speakerId)
	{
		return speakerDao.findSpeakerById(speakerId);
	}

	public LiveData<Location> getLocationWithId(String locationId)
	{
		return locationDao.findLocationById(locationId);
	}

	private static class SetSessionFavouriteAsyncTask extends AsyncTask<Session, Void, Void>
	{
		SessionDao mAsyncTaskDao;

		SetSessionFavouriteAsyncTask(SessionDao mAsyncTaskDao)
		{
			this.mAsyncTaskDao = mAsyncTaskDao;
		}

		protected Void doInBackground(final Session... sessions)
		{
			for (Session session : sessions)
			{
				// get the current favourite state and invert it. Then insert the new session into the
				// database
				boolean currentFavouriteState = session.isFavourite();
				session.setFavourite(!currentFavouriteState);
				mAsyncTaskDao.updateSession(session);
			}
			return null;
		}


	}
}
