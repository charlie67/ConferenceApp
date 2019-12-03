package to.charlie.conferenceapp.espresso.search;

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
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CheckNoSessionsToDisplayIsShownOnSearch
{
	@Rule
	public ActivityTestRule<ConferenceAppMainActivity> activityActivityTestRule =
					new ActivityTestRule<>(ConferenceAppMainActivity.class);

	@Test
	public void seeThatNoSessionsToDisplayIsShown()
	{
		//click on the search tab
		onView(withId(R.id.searchFragment)).perform(click());

		onView(withId(R.id.nav_host_fragment)).check((view, noViewFoundException) ->
		{
			TextView textView = view.findViewById(R.id.search_no_sessions_text);
			assertEquals(textView.getVisibility(), View.VISIBLE);
			assertEquals(textView.getText(), "There are no sessions to display.");
		});
	}
}
