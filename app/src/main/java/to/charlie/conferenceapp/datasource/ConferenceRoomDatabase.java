package to.charlie.conferenceapp.datasource;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import to.charlie.conferenceapp.datasource.util.AssetSQLiteOpenHelperFactory;
import to.charlie.conferenceapp.model.Location;
import to.charlie.conferenceapp.model.LocationDao;
import to.charlie.conferenceapp.model.Session;
import to.charlie.conferenceapp.model.SessionDao;
import to.charlie.conferenceapp.model.Speaker;
import to.charlie.conferenceapp.model.SpeakerDao;

/**
 * The Room database and initialisation class
 *
 * @author Charlie Robinson
 * @version 2/12/19
 */
@Database(entities = {Location.class, Session.class, Speaker.class}, version = 2)
public abstract class ConferenceRoomDatabase extends RoomDatabase
{
	private static ConferenceRoomDatabase INSTANCE;
	private static final String DB_NAME = "conf.db";

	public abstract LocationDao getLocationDao();

	public abstract SessionDao getSessionDao();

	public abstract SpeakerDao getSpeakerDao();

	public static ConferenceRoomDatabase getDatabase(final Context context)
	{
		if (INSTANCE == null)
		{
			synchronized (ConferenceRoomDatabase.class)
			{
				if (INSTANCE == null)
				{
					Log.i("Database", "Creating the database");
					INSTANCE = createDatabase(context);
				}
			}
		}

		return INSTANCE;
	}

	private static ConferenceRoomDatabase createDatabase(Context context)
	{
		RoomDatabase.Builder<ConferenceRoomDatabase> builder = Room.databaseBuilder(context.getApplicationContext(), ConferenceRoomDatabase.class, DB_NAME);

		// The AssetSQLiteOpenHelperFactory does the heavy lifting of the database file
		// from assets/databases to our /data/data/package-name/databases folder
		// It is important that the Room table
		return (builder.openHelperFactory(new AssetSQLiteOpenHelperFactory()).addMigrations(MIGRATION_1_2).build());
	}

	// The following example is needed to copy in an updated sqlite database
	// See https://medium.com/google-developers/understanding-migrations-with-room-f01e04b07929
	private static final Migration MIGRATION_1_2 = new Migration(1, 2)
	{
		@Override
		public void migrate(SupportSQLiteDatabase database)
		{
			//No migration is needed this is just here so the DB copies over
		}
	};
}
