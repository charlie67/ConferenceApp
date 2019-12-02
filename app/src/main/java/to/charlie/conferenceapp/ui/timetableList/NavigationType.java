package to.charlie.conferenceapp.ui.timetableList;

/**
 * The navigation type. What session should be shown.
 *
 * @author Charlie Robinson
 * @version 2/12/19
 */
public enum NavigationType
{
	/**
	 * Show all sessions
	 */
	ALL,

	/**
	 * Show only favourite sessions
	 */
	FAVOURITES,

	/**
	 * Show only sessions from one speaker. If this is set then the bundle also has the speaker ID
	 * stored as key SPEAKER_ID
	 */
	SPEAKER
}
