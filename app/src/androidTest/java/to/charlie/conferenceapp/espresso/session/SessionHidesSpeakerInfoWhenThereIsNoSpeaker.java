package to.charlie.conferenceapp.espresso.session;

import android.view.View;
import android.widget.ImageView;
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
public class SessionHidesSpeakerInfoWhenThereIsNoSpeaker
{
	@Rule
	public ActivityTestRule<ConferenceAppMainActivity> activityActivityTestRule =
					new ActivityTestRule<>(ConferenceAppMainActivity.class);

	@Test
	public void noSpeakerInformationIsShownWhenItIsNotAvailable()
	{
		//click on the timetable tab
		onView(withIndex(withText(R.string.timetable_tab), 1)).perform(click());
		//now click on the session
		onView(withText("Optional Social")).perform(click());

		onView(withId(R.id.nav_host_fragment)).check((view, noViewFoundException) ->
		{
			TextView speakerName = view.findViewById(R.id.session_view_speaker_name);
			TextView speakerBiography = view.findViewById(R.id.session_view_speaker_biography);
			ImageView speakerImage = view.findViewById(R.id.session_view_speaker_picture);

			assertEquals(speakerName.getText(), "");
			assertEquals(speakerName.getVisibility(), View.GONE);

			assertEquals(speakerBiography.getText(), "");
			assertEquals(speakerBiography.getVisibility(), View.GONE);

			assertEquals(speakerImage.getVisibility(), View.GONE);
		});
	}
}
