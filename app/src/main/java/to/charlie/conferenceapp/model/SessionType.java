package to.charlie.conferenceapp.model;

public enum SessionType
{
	TALK("Talk"), WORKSHOP("Workshop"), COFFEE("Coffee"), LUNCH("Lunch"), DINNER("Dinnner"), REGISTRATION("Registration");

	private String typeName;

	SessionType(String type)
	{
		typeName = type;
	}

	public String getTypeName()
	{
		return typeName;
	}
}
