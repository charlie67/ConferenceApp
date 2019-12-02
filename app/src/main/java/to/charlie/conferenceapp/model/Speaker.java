package to.charlie.conferenceapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Defines a speaker.
 *
 * @author Charlie Robinson
 * @version 2/12/19
 */
@Entity(tableName = "speakers")
public class Speaker
{
	@PrimaryKey()
	@NonNull
	private String id;

	private String name;

	private String biography;

	private String twitter;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getBiography()
	{
		return biography;
	}

	public void setBiography(String biography)
	{
		this.biography = biography;
	}

	public String getTwitter()
	{
		return twitter;
	}

	public void setTwitter(String twitter)
	{
		this.twitter = twitter;
	}
}
