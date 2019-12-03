package to.charlie.conferenceapp.espresso.speakers;

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
import static to.charlie.conferenceapp.util.EspressoChecker.checkSessionTitleTextView;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CheckThatSpeakersTabDisplaySessionsFromOneSpeaker
{
	@Rule
	public ActivityTestRule<ConferenceAppMainActivity> activityActivityTestRule =
					new ActivityTestRule<>(ConferenceAppMainActivity.class);

	@Test
	public void clickingOnSpeakerShowsSessionsForThatSpeakerOnly()
	{
		//click on the speakers tab
		onView(withId(R.id.speakersFragment)).perform(click());

		onView(withText("Adam Rush")).perform(click());
		onView(withId(R.id.session_title_text_view)).check(checkSessionTitleTextView("Continuous " +
						"Delivery"));

		//click on the session and validate the speaker
		onView(withText("Continuous Delivery")).perform(click());
		//validate the speaker name is correct
		onView(withId(R.id.nav_host_fragment)).check((view, noViewFoundException) ->
		{
			TextView textView = view.findViewById(R.id.session_view_speaker_name);

			assertEquals(textView.getText(), "Speaker: Adam Rush");
		});
	}
}
