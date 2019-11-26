package to.charlie.conferenceapp;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import to.charlie.conferenceapp.datasource.ConferenceRoomDatabase;
import to.charlie.conferenceapp.datasource.Injection;
import to.charlie.conferenceapp.model.Session;
import to.charlie.conferenceapp.model.SessionDao;
import to.charlie.conferenceapp.util.LiveDataTestUtil;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MarkSessionAsFavouriteTest
{
	// This is a JUnit Test Rules that swaps the background executor used by the Architecture
	// Components with one that executes synchronously instead.
	@Rule
	public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

	ConferenceRoomDatabase db;
	SessionDao sessionDao;

	@Before
	public void setup()
	{
		db = Injection.getDatabase(ApplicationProvider.getApplicationContext());
		sessionDao = db.getSessionDao();
	}

	@Test
	public void testThatFavoritingSessionActuallyFavouritesASession() throws Exception
	{
		Session sessionToTest = sessionDao.findSessionById("arkit");
		sessionToTest.setFavourite(false);
		//ensure we are starting from a clean slate
		sessionDao.updateSession(sessionToTest);

		sessionToTest = sessionDao.findSessionById("arkit");
		assertFalse(sessionToTest.isFavourite());

		sessionToTest.setFavourite(true);
		sessionDao.updateSession(sessionToTest);
		sessionToTest = sessionDao.findSessionById("arkit");
		assertTrue(sessionToTest.isFavourite());

		LiveData<List<Session>> favouriteSessionList = sessionDao.getAllFavouriteSessions();
		assertEquals(1, LiveDataTestUtil.getValue(favouriteSessionList).size());

		//need to ensure that you clean up after the test
		sessionToTest.setFavourite(false);
		sessionDao.updateSession(sessionToTest);
	}
}
