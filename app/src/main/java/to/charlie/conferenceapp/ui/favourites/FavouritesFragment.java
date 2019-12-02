package to.charlie.conferenceapp.ui.favourites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import to.charlie.conferenceapp.R;
import to.charlie.conferenceapp.ui.timetableList.NavigationType;

import static to.charlie.conferenceapp.ui.timetableList.TimetableListFragment.NAVIGATION_TYPE_BUNDLE_KEY;

public class FavouritesFragment extends Fragment
{
	public FavouritesFragment()
	{
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_favourites, container, false);

		final NavController navController = Navigation.findNavController((FragmentActivity) view.getContext(),
						R.id.nav_host_fragment);

		Bundle navigationBundle = new Bundle();
		navigationBundle.putString(NAVIGATION_TYPE_BUNDLE_KEY, NavigationType.FAVOURITES.toString());
		navController.navigate(R.id.action_favouritesFragment_to_timetableListFragment, navigationBundle);

		return view;
	}
}
