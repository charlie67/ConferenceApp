package to.charlie.conferenceapp.ui.sessions;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import to.charlie.conferenceapp.R;

public class SessionItemFragment extends Fragment
{
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		final View view = inflater.inflate(R.layout.fragment_session_item, container, false);

		Bundle arguments = getArguments();

		if (arguments != null)
		{
			String sessionId = arguments.getString("session_id");
			Log.i("id", "id is " + sessionId);
		}

		return view;
	}
}