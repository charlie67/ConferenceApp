package to.charlie.conferenceapp.datasource;

import android.content.Context;

/**
 * This is only used for creating the database for tests
 *
 * @author Charlie Robinson
 * @version 2/12/19
 */
public class Injection
{
	public static ConferenceRoomDatabase getDatabase(Context context)
	{
		return ConferenceRoomDatabase.getDatabase(context);
	}
}
