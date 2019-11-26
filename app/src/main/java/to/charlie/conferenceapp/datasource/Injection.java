package to.charlie.conferenceapp.datasource;

import android.content.Context;

public class Injection
{
	public static ConferenceRoomDatabase getDatabase(Context context)
	{
		return ConferenceRoomDatabase.getDatabase(context);
	}
}
