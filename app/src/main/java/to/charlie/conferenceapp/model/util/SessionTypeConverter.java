package to.charlie.conferenceapp.model.util;

import androidx.room.TypeConverter;

import to.charlie.conferenceapp.model.SessionType;

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
		return sessionType == null ? null : SessionType.valueOf(sessionType);
	}
}
