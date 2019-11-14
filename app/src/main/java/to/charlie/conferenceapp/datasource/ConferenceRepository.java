package to.charlie.conferenceapp.datasource;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import to.charlie.conferenceapp.model.LocationDao;
import to.charlie.conferenceapp.model.Session;
import to.charlie.conferenceapp.model.SessionDao;
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
}
