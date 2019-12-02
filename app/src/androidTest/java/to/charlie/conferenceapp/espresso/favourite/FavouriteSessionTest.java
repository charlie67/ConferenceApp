package to.charlie.conferenceapp.espresso.favourite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.ViewAssertion;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.nio.ByteBuffer;

import to.charlie.conferenceapp.R;
import to.charlie.conferenceapp.datasource.ConferenceRoomDatabase;
import to.charlie.conferenceapp.datasource.Injection;
import to.charlie.conferenceapp.model.SessionDao;
import to.charlie.conferenceapp.ui.ConferenceAppMainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static to.charlie.conferenceapp.util.EspressoMatcherUtil.withIndex;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FavouriteSessionTest
{
	private SessionDao sessionDao;
	private Context context;

	@Rule
	public ActivityTestRule<ConferenceAppMainActivity> activityActivityTestRule =
					new ActivityTestRule<>(ConferenceAppMainActivity.class);

	@Before
	public void setup()
	{
		context = ApplicationProvider.getApplicationContext();
		ConferenceRoomDatabase db = Injection.getDatabase(context);
		sessionDao = db.getSessionDao();
	}

	@Test
	public void seeThatFavouriteSessionRemainsFavourite()
	{
		//click on the timetable tab
		onView(withIndex(withText(R.string.timetable_tab), 1)).perform(click());

		//now click on the session
		onView(withText("Using ARKit with SpriteKit")).perform(click());

		//set the session as a favourite
		onView(withId(R.id.session_view_set_favourite_button)).perform(click());
		onView(withId(R.id.session_view_set_favourite_button)).check(checkIconCorrectLambda(context.getResources().getDrawable(R.drawable.ic_favorite_black_24dp, null)));


		assertTrue(sessionDao.findSessionById("arkit").isFavourite());

		//navigate back to the timetable
		onView(withIndex(withText(R.string.timetable_tab), 1)).perform(click());
		//now click on the session
		onView(withText("Using ARKit with SpriteKit")).perform(click());
		//check the correct icon is used
		onView(withId(R.id.session_view_set_favourite_button)).check(checkIconCorrectLambda(context.getResources().getDrawable(R.drawable.ic_favorite_black_24dp, null)));

		//unfavourite the session
		onView(withId(R.id.session_view_set_favourite_button)).perform(click());
		//check the correct icon is used
		onView(withId(R.id.session_view_set_favourite_button)).check(checkIconCorrectLambda(context.getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp, null)));

		assertFalse(sessionDao.findSessionById("arkit").isFavourite());
	}

	@NonNull
	private ViewAssertion checkIconCorrectLambda(Drawable correctDrawable)
	{
		//this returns the lambda that the espresso icon check uses
		return (view, noViewFoundException) ->
		{
			ImageButton imageButton = view.findViewById(R.id.session_view_set_favourite_button);
			VectorDrawable iconDrawable = ((VectorDrawable) imageButton.getDrawable());

			DrawableCompat.setTint(correctDrawable,
							context.getResources().getColor(R.color.primaryTextColor, null));

			assertTrue(compareTwoDrawable(correctDrawable, iconDrawable));
		};
	}

	private boolean compareTwoDrawable(Drawable drawable1, Drawable drawable2)
	{
		//turn them both into bitmaps and then compare the bytes
		Bitmap bitmap1 = Bitmap.createBitmap(drawable1.getIntrinsicWidth(),
						drawable1.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

		Canvas canvas1 = new Canvas(bitmap1);
		drawable1.setBounds(0, 0, canvas1.getWidth(), canvas1.getHeight());
		drawable1.draw(canvas1);


		Bitmap bitmap2 = Bitmap.createBitmap(drawable2.getIntrinsicWidth(),
						drawable1.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

		Canvas canvas2 = new Canvas(bitmap2);
		drawable2.setBounds(0, 0, canvas2.getWidth(), canvas2.getHeight());
		drawable2.draw(canvas2);

		int size = bitmap1.getRowBytes() * bitmap1.getHeight();
		ByteBuffer byteBuffer1 = ByteBuffer.allocate(size);
		bitmap1.copyPixelsToBuffer(byteBuffer1);

		size = bitmap2.getRowBytes() * bitmap2.getHeight();
		ByteBuffer byteBuffer2 = ByteBuffer.allocate(size);
		bitmap2.copyPixelsToBuffer(byteBuffer2);

		return byteBuffer1.equals(byteBuffer2);
	}
}
