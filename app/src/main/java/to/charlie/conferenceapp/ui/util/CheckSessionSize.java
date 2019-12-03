package to.charlie.conferenceapp.ui.util;

import android.view.View;
import android.widget.TextView;

public class CheckSessionSize
{
	public static void checkSessionSize(View view, int size, int viewId)
	{
		//check if the no sessions to show text should be removed.
		TextView noSessionsText = view.findViewById(viewId);
		if (size > 0)
		{
			noSessionsText.setVisibility(View.GONE);
		}
		else
		{
			noSessionsText.setVisibility(View.VISIBLE);
		}
	}
}
