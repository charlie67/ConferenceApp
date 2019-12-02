package to.charlie.conferenceapp.espresso.session;

import android.view.View;
import android.widget.TextView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import to.charlie.conferenceapp.R;
import to.charlie.conferenceapp.ui.ConferenceAppMainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static to.charlie.conferenceapp.util.EspressoMatcherUtil.withIndex;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SessionDisplaysCorrectInformation
{
	@Rule
	public ActivityTestRule<ConferenceAppMainActivity> activityActivityTestRule =
					new ActivityTestRule<>(ConferenceAppMainActivity.class);

	@Test
	public void checkSessionInfoDisplayedCorrectly()
	{
		//click on the timetable tab
		onView(withIndex(withText(R.string.timetable_tab), 1)).perform(click());
		//now click on the session
		onView(withText("Using ARKit with SpriteKit")).perform(click());

		onView(withId(R.id.nav_host_fragment)).check((view, noViewFoundException) ->
		{
			TextView speakerName = view.findViewById(R.id.session_view_speaker_name);
			TextView sessionTitle = view.findViewById(R.id.session_view_title);
			TextView sessionType = view.findViewById(R.id.session_view_session_type);
			TextView sessionTime = view.findViewById(R.id.session_view_date_time);
			TextView sessionPlace = view.findViewById(R.id.session_view_location_name);

			assertEquals(speakerName.getText(), "Speaker: Janie Clayton");
			assertEquals(speakerName.getVisibility(), View.VISIBLE);

			assertEquals(sessionTitle.getText(), "Using ARKit with SpriteKit");
			assertEquals(sessionTitle.getVisibility(), View.VISIBLE);

			assertEquals(sessionType.getText(), "Workshop");
			assertEquals(sessionType.getVisibility(), View.VISIBLE);

			assertEquals(sessionTime.getText(), "Tuesday 10th December, 16:00 - 18:00");
			assertEquals(sessionTime.getVisibility(), View.VISIBLE);

			assertEquals(sessionPlace.getText(), "Llandinam B23");
			assertEquals(sessionPlace.getVisibility(), View.VISIBLE);
		});
	}
}
