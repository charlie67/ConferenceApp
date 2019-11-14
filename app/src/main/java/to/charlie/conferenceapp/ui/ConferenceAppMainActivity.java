package to.charlie.conferenceapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import to.charlie.conferenceapp.R;
import to.charlie.conferenceapp.ui.favourites.FavouritesFragment;
import to.charlie.conferenceapp.ui.sessions.SessionsFragment;
import to.charlie.conferenceapp.ui.speakers.SpeakersFragment;

public class ConferenceAppMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
	private int displayedViewId;
	private final static String VIEW_ID_KEY = "view_id_key";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conference_app_main);

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
		bottomNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

		int viewToDisplay = R.id.nav_timetable;

		if (savedInstanceState != null)
		{
			viewToDisplay = savedInstanceState.getInt(VIEW_ID_KEY);
		}
		displayView(viewToDisplay);
	}

	@Override
	public void onSaveInstanceState(@NonNull Bundle outState)
	{
		super.onSaveInstanceState(outState);

		outState.putInt(VIEW_ID_KEY, displayedViewId);
	}


	/**
	 * Called when an item in the navigation drawer is selected
	 *
	 * @param item The item that was selected
	 * @return true if the item was handled
	 */
	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item)
	{
		int viewId = item.getItemId();
		return displayView(viewId);
	}

	private boolean displayView(int viewId)
	{
		Fragment fragment = null;

		switch (viewId)
		{
			case R.id.nav_timetable:
				fragment = new SessionsFragment();
				break;

			case R.id.nav_favourites:
				fragment = new FavouritesFragment();
				break;

			case R.id.nav_speakers:
				fragment = new SpeakersFragment();
				break;
		}

		if (fragment == null)
		{
			Log.e("Nav error", "nav item id was not in switch statement");
			return false;
		}
		displayedViewId = viewId;

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.content_frame, fragment);
		ft.commit();
		return true;
	}
}
