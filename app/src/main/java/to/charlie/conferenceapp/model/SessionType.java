package to.charlie.conferenceapp.model;

/**
 * Session types.
 *
 * @author Charlie Robinson
 * @version 2/12/19
 */
public enum SessionType
{
	TALK("Talk"), WORKSHOP("Workshop"), COFFEE("Coffee"), LUNCH("Lunch"), DINNER("Dinnner"), REGISTRATION("Registration");

	private String typeName;

	SessionType(String type)
	{
		typeName = type;
	}

	/**
	 * Get the human readable name for the session.
	 *
	 * @return The human readable name for the session.
	 */
	public String getTypeName()
	{
		return typeName;
	}
}
