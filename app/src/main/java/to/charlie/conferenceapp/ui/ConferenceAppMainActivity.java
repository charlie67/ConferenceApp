package to.charlie.conferenceapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import to.charlie.conferenceapp.R;
import to.charlie.conferenceapp.ui.favourites.FavouritesFragment;
import to.charlie.conferenceapp.ui.speakers.SpeakersFragment;
import to.charlie.conferenceapp.ui.timetable.TimetableFragment;

public class ConferenceAppMainActivity extends AppCompatActivity
{
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conference_app_main);

		toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
		ViewPager pager = findViewById(R.id.pager);
		pager.setAdapter(pagerAdapter);

		TabLayout tabLayout = findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(pager);

		pager.addOnPageChangeListener(addPageChangeListener());
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
