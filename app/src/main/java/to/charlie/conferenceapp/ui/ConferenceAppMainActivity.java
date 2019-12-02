package to.charlie.conferenceapp.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import to.charlie.conferenceapp.R;

public class ConferenceAppMainActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conference_app_main);

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

		NavigationUI.setupWithNavController(bottomNavigationView, navController);
		NavigationUI.setupActionBarWithNavController(this, navController);
	}
}