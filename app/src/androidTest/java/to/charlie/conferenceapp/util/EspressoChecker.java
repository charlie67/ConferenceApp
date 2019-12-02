package to.charlie.conferenceapp.util;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.test.espresso.ViewAssertion;

import to.charlie.conferenceapp.R;

import static org.junit.Assert.assertEquals;

public class EspressoChecker
{

	@NonNull
	public static ViewAssertion checkSessionTitleTextView(String textToCheck)
	{
		return (view, noViewFoundException) ->
		{
			TextView textView = view.findViewById(R.id.session_title_text_view);
			assertEquals(textView.getText(), textToCheck);
		};
	}

	@NonNull
	public static ViewAssertion checkNoSessionTextIsCorrectAndVisible()
	{
		return (view, noViewFoundException) ->
		{
			TextView textView = view.findViewById(R.id.timetable_no_sessions_text);
			assertEquals(textView.getText(), "There are no sessions to display.");
			assertEquals(textView.getVisibility(), View.VISIBLE);
		};
	}
}
