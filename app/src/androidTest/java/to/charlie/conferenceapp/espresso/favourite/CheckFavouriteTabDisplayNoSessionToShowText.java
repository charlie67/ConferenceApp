package to.charlie.conferenceapp.espresso.favourite;

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
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static to.charlie.conferenceapp.util.EspressoMatcherUtil.withIndex;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CheckFavouriteTabDisplayNoSessionToShowText
{
	@Rule
	public ActivityTestRule<ConferenceAppMainActivity> activityActivityTestRule =
					new ActivityTestRule<>(ConferenceAppMainActivity.class);

	@Test
	public void seeThatFavouriteTabDisplaysTextWhenNoSessionsToShow()
	{
		//click on the favourites tab
		onView(withIndex(withText(R.string.favourites_tab), 0)).perform(click());

		onView(withText(R.string.no_sessions)).check((view, noViewFoundException) ->
		{
			TextView textView = view.findViewById(R.id.timetable_no_sessions_text);
			assertEquals(textView.getText(), "There are no sessions to display.");
			assertEquals(textView.getVisibility(), View.VISIBLE);
		});
	}
}
