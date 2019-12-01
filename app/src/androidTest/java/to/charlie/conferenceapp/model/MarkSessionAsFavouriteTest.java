package to.charlie.conferenceapp.model;

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
import to.charlie.conferenceapp.util.LiveDataTestUtil;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MarkSessionAsFavouriteTest
{
	// This is a JUnit Test Rule that swaps the background executor used by the Architecture
	// Components with one that executes synchronously instead.
	@Rule
	public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

	private SessionDao sessionDao;

	@Before
	public void setup()
	{
		ConferenceRoomDatabase db = Injection.getDatabase(ApplicationProvider.getApplicationContext());
		sessionDao = db.getSessionDao();
	}

	@Test
	public void testThatFavoritingSessionActuallyFavouritesASession() throws Exception
	{
		Session sessionToTest = sessionDao.findSessionById("arkit");
		assertFalse(sessionToTest.isFavourite());

		sessionToTest.setFavourite(true);
		sessionDao.updateSession(sessionToTest);
		sessionToTest = sessionDao.findSessionById("arkit");
		assertTrue(sessionToTest.isFavourite());

		LiveData<List<Session>> favouriteSessionList = sessionDao.getAllFavouriteSessions();
		assertEquals(1, LiveDataTestUtil.getValue(favouriteSessionList).size());
		assertEquals("arkit", LiveDataTestUtil.getValue(favouriteSessionList).get(0).getId());

		//need to ensure that the data is the same at the end of the test and the start of the test
		//in case other tests need this data
		sessionToTest.setFavourite(false);
		sessionDao.updateSession(sessionToTest);
	}

	@Test
	public void testThatByDefaultNoFavouritesAreSet() throws InterruptedException
	{
		LiveData<List<Session>> favouriteSessionList = sessionDao.getAllFavouriteSessions();
		assertEquals(0, LiveDataTestUtil.getValue(favouriteSessionList).size());
	}
}
