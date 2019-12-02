package to.charlie.conferenceapp.model.util;

import androidx.room.TypeConverter;

import to.charlie.conferenceapp.model.SessionType;

/**
 * Utility class to convert SQLite text session types to the session type enum and vice versa
 *
 * @author Charlie Robinson
 * @version 2/12/19
 */
public class SessionTypeConverter
{
	@TypeConverter
	public static String toString(SessionType sessionType)
	{
		return sessionType == null ? null : sessionType.toString();
	}

	@TypeConverter
	public static SessionType toSessionType(String sessionType)
	{
		return sessionType == null ? null : SessionType.valueOf(sessionType.toUpperCase());
	}
}
