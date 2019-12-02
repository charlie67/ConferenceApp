package to.charlie.conferenceapp.model;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.security.InvalidParameterException;

import to.charlie.conferenceapp.model.util.SessionTypeConverter;

/**
 * Defines a session
 *
 * @author Charlie Robinson
 * @version 2/12/19
 */
@Entity(tableName = "sessions")
@TypeConverters({SessionTypeConverter.class})
public class Session
{
	private static final String SESSION_ID_BUNDLE_KEY = "SESSION_ID";

	private static final String SESSION_TITLE_BUNDLE_KEY = "SESSION_TITLE";

	private static final String SESSION_CONTENT_BUNDLE_KEY = "SESSION_CONTENT";

	private static final String SESSION_LOCATION_ID_BUNDLE_KEY = "SESSION_LOCATION_ID";

	private static final String SESSION_DATE_BUNDLE_KEY = "SESSION_DATE";

	private static final String SESSION_ORDER_BUNDLE_KEY = "SESSION_ORDER";

	private static final String SESSION_TIME_START_BUNDLE_KEY = "SESSION_TIME_START";

	private static final String SESSION_TIME_END_BUNDLE_KEY = "SESSION_TIME_END";

	private static final String SESSION_TYPE_BUNDLE_KEY = "SESSION_TYPE";

	private static final String SESSION_SPEAKER_ID_BUNDLE_KEY = "SESSION_SPEAKER_ID";

	private static final String SESSION_FAVOURITE_BUNDLE_KEY = "SESSION_FAVOURITE";

	/**
	 * Extract the stored information from the bundle and create a session from it.
	 *
	 * @param bundle The bundle that contains the information to extract
	 * @return A session object that holds the information contained in the bundle
	 * @throws InvalidParameterException If the ID was not included in the bundle
	 */
	public static Session SessionFromBundle(Bundle bundle) throws InvalidParameterException
	{
		String id = bundle.getString(SESSION_ID_BUNDLE_KEY);

		if (id == null)
		{
			throw new InvalidParameterException("ID is null");
		}

		return new Session(id, bundle.getString(SESSION_TITLE_BUNDLE_KEY),
						bundle.getString(SESSION_CONTENT_BUNDLE_KEY), bundle.getString(SESSION_LOCATION_ID_BUNDLE_KEY),
						bundle.getString(SESSION_DATE_BUNDLE_KEY), bundle.getInt(SESSION_ORDER_BUNDLE_KEY),
						bundle.getString(SESSION_TIME_START_BUNDLE_KEY), bundle.getString(SESSION_TIME_END_BUNDLE_KEY),
						SessionTypeConverter.toSessionType(bundle.getString(SESSION_TYPE_BUNDLE_KEY)),
						bundle.getString(SESSION_SPEAKER_ID_BUNDLE_KEY), bundle.getBoolean(SESSION_FAVOURITE_BUNDLE_KEY));
	}

	Session(@NonNull String id, String title, String content, String locationId, String sessionDate, int sessionOrder, String timeStart, String timeEnd, SessionType sessionType, String speakerId, boolean favourite)
	{
		this.id = id;
		this.title = title;
		this.content = content;
		this.locationId = locationId;
		this.sessionDate = sessionDate;
		this.sessionOrder = sessionOrder;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.sessionType = sessionType;
		this.speakerId = speakerId;
		this.favourite = favourite;
	}

	/**
	 * Turn the information from this session into a bundle so it can be navigated between sessions
	 *
	 * @return A bundle that contains all the information from this session
	 */
	public Bundle toBundle()
	{
		Bundle bundle = new Bundle();
		bundle.putString(SESSION_ID_BUNDLE_KEY, id);
		bundle.putString(SESSION_TITLE_BUNDLE_KEY, title);
		bundle.putString(SESSION_CONTENT_BUNDLE_KEY, content);
		bundle.putString(SESSION_LOCATION_ID_BUNDLE_KEY, locationId);
		bundle.putString(SESSION_DATE_BUNDLE_KEY, sessionDate);
		bundle.putInt(SESSION_ORDER_BUNDLE_KEY, sessionOrder);
		bundle.putString(SESSION_TIME_START_BUNDLE_KEY, timeStart);
		bundle.putString(SESSION_TIME_END_BUNDLE_KEY, timeEnd);
		bundle.putString(SESSION_TYPE_BUNDLE_KEY, SessionTypeConverter.toString(sessionType));
		bundle.putString(SESSION_SPEAKER_ID_BUNDLE_KEY, speakerId);
		bundle.putBoolean(SESSION_FAVOURITE_BUNDLE_KEY, favourite);


		return bundle;
	}

	@PrimaryKey()
	@NonNull
	private String id;

	private String title;

	private String content;

	private String locationId;

	private String sessionDate;

	private int sessionOrder;

	private String timeStart;

	private String timeEnd;

	private SessionType sessionType;

	private String speakerId;

	private boolean favourite;

	@NonNull
	public String getId()
	{
		return id;
	}

	public void setId(@NonNull String id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getLocationId()
	{
		return locationId;
	}

	public void setLocationId(String locationId)
	{
		this.locationId = locationId;
	}

	public String getSessionDate()
	{
		return sessionDate;
	}

	public void setSessionDate(String sessionDate)
	{
		this.sessionDate = sessionDate;
	}

	public int getSessionOrder()
	{
		return sessionOrder;
	}

	public void setSessionOrder(int sessionOrder)
	{
		this.sessionOrder = sessionOrder;
	}

	public String getTimeStart()
	{
		return timeStart;
	}

	public void setTimeStart(String timeStart)
	{
		this.timeStart = timeStart;
	}

	public String getTimeEnd()
	{
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd)
	{
		this.timeEnd = timeEnd;
	}

	public SessionType getSessionType()
	{
		return sessionType;
	}

	public void setSessionType(SessionType sessionType)
	{
		this.sessionType = sessionType;
	}

	public String getSpeakerId()
	{
		return speakerId;
	}

	public void setSpeakerId(String speakerId)
	{
		this.speakerId = speakerId;
	}

	public boolean isFavourite()
	{
		return favourite;
	}

	public void setFavourite(boolean favourite)
	{
		this.favourite = favourite;
	}
}
