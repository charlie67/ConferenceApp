package to.charlie.conferenceapp.espresso.favourite;

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
import static to.charlie.conferenceapp.util.EspressoChecker.checkNoSessionTextIsCorrectAndVisible;

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
		onView(withId(R.id.favouritesFragment)).perform(click());

		onView(withText(R.string.no_sessions)).check(checkNoSessionTextIsCorrectAndVisible());
	}
}
