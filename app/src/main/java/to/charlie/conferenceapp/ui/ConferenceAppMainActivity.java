package to.charlie.conferenceapp.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import to.charlie.conferenceapp.R;
import to.charlie.conferenceapp.ui.favourites.FavouritesFragment;
import to.charlie.conferenceapp.ui.speakers.SpeakersFragment;
import to.charlie.conferenceapp.ui.timetable.TimetableFragment;

public class ConferenceAppMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conference_app_main);

		toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		// Get the navigation drawer
		DrawerLayout drawer = findViewById(R.id.drawer_layout);

		// Create ActionBar toggle (burger icon) for the navigation drawer
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_open_drawer, R.string.nav_close_drawer);

		drawer.addDrawerListener(toggle);
		toggle.syncState(); // sync the state of the icon with drawer state

		// Find the navigation drawer
		NavigationView navigationView = findViewById(R.id.nav_view);

		// Handle navigation drawer option selections
		navigationView.setNavigationItemSelectedListener(this);

		SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
		ViewPager pager = findViewById(R.id.pager);
		pager.setAdapter(pagerAdapter);

		TabLayout tabLayout = findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(pager);

		pager.addOnPageChangeListener(addPageChangeListener());
	}

	/**
	 * Called when an item in the navigation drawer is selected
	 *
	 * @param item The item that was selected
	 * @return true if te item was handled
	 */
	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item)
	{

		Toast.makeText(this, "Item id: " + item.getItemId(), Toast.LENGTH_LONG).show();
		return true;
	}

	private ViewPager.OnPageChangeListener addPageChangeListener()
	{
		return new ViewPager.OnPageChangeListener()
		{

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
			{
			}

			@Override
			public void onPageSelected(int position)
			{

				AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
				toolbar.setLayoutParams(layoutParams);
			}

			@Override
			public void onPageScrollStateChanged(int state)
			{
			}
		};
	}

	private class SectionsPagerAdapter extends FragmentPagerAdapter
	{
		SectionsPagerAdapter(@NonNull FragmentManager fm, int behavior)
		{
			super(fm, behavior);
		}

		@NonNull
		@Override
		public Fragment getItem(int position)
		{
			switch (position)
			{
				case 0:
					return new TimetableFragment();
				case 1:
					return new FavouritesFragment();
				case 2:
					return new SpeakersFragment();
			}
			return null;
		}

		@Override
		public int getCount()
		{
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position)
		{
			switch (position)
			{
				case 0:
					return getText(R.string.timetable_tab);
				case 1:
					return getText(R.string.favourites_tab);
				case 2:
					return getText(R.string.speakers_tab);
			}

			return null;
		}
	}
}
