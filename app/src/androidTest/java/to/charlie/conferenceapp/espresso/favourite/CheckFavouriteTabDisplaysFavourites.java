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
import static to.charlie.conferenceapp.util.EspressoChecker.checkSessionTitleTextView;
import static to.charlie.conferenceapp.util.EspressoMatcherUtil.withIndex;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CheckFavouriteTabDisplaysFavourites
{
	@Rule
	public ActivityTestRule<ConferenceAppMainActivity> activityActivityTestRule =
					new ActivityTestRule<>(ConferenceAppMainActivity.class);

	@Test
	public void seeThatSessionsMarkedAsFavouriteAreShownInFavouriteTab()
	{
		//click on the timetable tab
		onView(withIndex(withText(R.string.timetable_tab), 1)).perform(click());
		//now click on the session
		onView(withText("Using ARKit with SpriteKit")).perform(click());
		//set the session as a favourite
		onView(withId(R.id.session_view_set_favourite_button)).perform(click());

		onView(withIndex(withText(R.string.favourites_tab), 0)).perform(click());
		onView(withIndex(withId(R.id.session_title_text_view), 0)).check(checkSessionTitleTextView("Using ARKit " +
						"with SpriteKit"));

		//click on the timetable tab
		onView(withIndex(withText(R.string.timetable_tab), 0)).perform(click());
		//now click on the session
		onView(withText("Welcome / Introduction")).perform(click());
		//set the session as a favourite
		onView(withId(R.id.session_view_set_favourite_button)).perform(click());

		//click on the favourites tab
		onView(withIndex(withText(R.string.favourites_tab), 0)).perform(click());
		onView(withIndex(withId(R.id.session_title_text_view), 0)).check(checkSessionTitleTextView("Using ARKit " +
						"with SpriteKit"));
		onView(withIndex(withId(R.id.session_title_text_view), 1)).check(checkSessionTitleTextView("Welcome / " +
						"Introduction"));

		//click on the timetable tab
		onView(withIndex(withText(R.string.timetable_tab), 0)).perform(click());
		//now click on the session
		onView(withText("I'll tell you what you can do with Core ML")).perform(click());
		//set the session as a favourite
		onView(withId(R.id.session_view_set_favourite_button)).perform(click());

		//click on the favourites tab
		onView(withIndex(withText(R.string.favourites_tab), 0)).perform(click());
		onView(withIndex(withId(R.id.session_title_text_view), 0)).check(checkSessionTitleTextView("Using ARKit " +
						"with SpriteKit"));
		onView(withIndex(withId(R.id.session_title_text_view), 1)).check(checkSessionTitleTextView("Welcome / " +
						"Introduction"));
		onView(withIndex(withId(R.id.session_title_text_view), 2)).check(checkSessionTitleTextView("I'll tell you" +
						" what you can do with Core ML"));

		//click on the 1st session
		onView(withText("Using ARKit with SpriteKit")).perform(click());
		//unfavourite the session
		onView(withId(R.id.session_view_set_favourite_button)).perform(click());

		//click on the favourites tab
		onView(withIndex(withText(R.string.favourites_tab), 1)).perform(click());
		onView(withIndex(withId(R.id.session_title_text_view), 0)).check(checkSessionTitleTextView("Welcome / " +
						"Introduction"));
		onView(withIndex(withId(R.id.session_title_text_view), 1)).check(checkSessionTitleTextView("I'll tell you" +
						" what you can do with Core ML"));

		//click on the 2nd session
		onView(withText("Welcome / Introduction")).perform(click());
		//unfavourite the session
		onView(withId(R.id.session_view_set_favourite_button)).perform(click());

		//click on the favourites tab
		onView(withIndex(withText(R.string.favourites_tab), 1)).perform(click());
		onView(withIndex(withId(R.id.session_title_text_view), 0)).check(checkSessionTitleTextView("I'll tell you" +
						" what you can do with Core ML"));

		//now click on the last session
		onView(withText("I'll tell you what you can do with Core ML")).perform(click());
		//unfavourite the session
		onView(withId(R.id.session_view_set_favourite_button)).perform(click());

		//click on the favourites tab
		onView(withIndex(withText(R.string.favourites_tab), 1)).perform(click());
		//check that the no session to display text is shown
		onView(withText(R.string.no_sessions)).check(checkNoSessionTextIsCorrectAndVisible());
	}
}
