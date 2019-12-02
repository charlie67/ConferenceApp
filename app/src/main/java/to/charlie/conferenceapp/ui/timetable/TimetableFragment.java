package to.charlie.conferenceapp.ui.timetable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import to.charlie.conferenceapp.R;
import to.charlie.conferenceapp.ui.timetableList.NavigationType;

import static to.charlie.conferenceapp.ui.timetableList.TimetableListFragment.NAVIGATION_TYPE_BUNDLE_KEY;

public class TimetableFragment extends Fragment
{
	public TimetableFragment()
	{
		// Required empty public constructor
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_timetable, container, false);

		final NavController navController = Navigation.findNavController((FragmentActivity) view.getContext(),
						R.id.nav_host_fragment);

		Bundle navigationBundle = new Bundle();
		navigationBundle.putString(NAVIGATION_TYPE_BUNDLE_KEY, NavigationType.ALL.toString());
		navController.navigate(R.id.action_timetableFragment_to_timetableListFragment, navigationBundle);

		return view;
	}
}
