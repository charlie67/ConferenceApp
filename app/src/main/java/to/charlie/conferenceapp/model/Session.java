package to.charlie.conferenceapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import to.charlie.conferenceapp.model.util.SessionTypeConverter;

@Entity(tableName = "sessions")
@TypeConverters({SessionTypeConverter.class})
public class Session
{
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
