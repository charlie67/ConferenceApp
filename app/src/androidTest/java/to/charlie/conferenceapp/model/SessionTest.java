package to.charlie.conferenceapp.model;

import android.os.Bundle;

import org.junit.Test;

import to.charlie.conferenceapp.model.util.SessionTypeConverter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SessionTest
{
	@Test
	public void testSessionToBundleConverter()
	{
		Session sessionToTest = new Session("id", "title", "content", "locationId", "sessionDate",
						1, "timeStart", "timeEnd", SessionType.TALK, "speakerID", false);

		Bundle bundle = sessionToTest.toBundle();

		assertEquals(bundle.getString("SESSION_ID"), "id");
		assertEquals(bundle.getString("SESSION_TITLE"), "title");
		assertEquals(bundle.getString("SESSION_CONTENT"), "content");
		assertEquals(bundle.getString("SESSION_LOCATION_ID"), "locationId");
		assertEquals(bundle.getString("SESSION_DATE"), "sessionDate");
		assertEquals(bundle.getInt("SESSION_ORDER"), 1);
		assertEquals(bundle.getString("SESSION_TIME_START"), "timeStart");
		assertEquals(bundle.getString("SESSION_TIME_END"), "timeEnd");
		assertEquals(bundle.getString("SESSION_TYPE"), SessionTypeConverter.toString(SessionType.TALK));
		assertEquals(bundle.getString("SESSION_SPEAKER_ID"), "speakerID");
		assertFalse(bundle.getBoolean("SESSION_FAVOURITE"));
	}

	@Test
	public void testSessionFromBundleConverter()
	{
		Bundle bundle = new Bundle();
		bundle.putString("SESSION_ID", "id");
		bundle.putString("SESSION_TITLE", "title");
		bundle.putString("SESSION_CONTENT", "content");
		bundle.putString("SESSION_LOCATION_ID", "locationId");
		bundle.putString("SESSION_DATE", "sessionDate");
		bundle.putInt("SESSION_ORDER", 1);
		bundle.putString("SESSION_TIME_START", "timeStart");
		bundle.putString("SESSION_TIME_END", "timeEnd");
		bundle.putString("SESSION_TYPE", SessionTypeConverter.toString(SessionType.TALK));
		bundle.putString("SESSION_SPEAKER_ID", "speakerID");
		bundle.putBoolean("SESSION_FAVOURITE", false);

		Session sessionToTest = Session.SessionFromBundle(bundle);

		assertEquals(sessionToTest.getId(), "id");
		assertEquals(sessionToTest.getTitle(), "title");
		assertEquals(sessionToTest.getContent(), "content");
		assertEquals(sessionToTest.getLocationId(), "locationId");
		assertEquals(sessionToTest.getSessionDate(), "sessionDate");
		assertEquals(sessionToTest.getSessionOrder(), 1);
		assertEquals(sessionToTest.getTimeStart(), "timeStart");
		assertEquals(sessionToTest.getTimeEnd(), "timeEnd");
		assertEquals(sessionToTest.getSessionType().toString(), SessionType.TALK.toString());
		assertEquals(sessionToTest.getSpeakerId(), "speakerID");
		assertFalse(sessionToTest.isFavourite());

	}

}
