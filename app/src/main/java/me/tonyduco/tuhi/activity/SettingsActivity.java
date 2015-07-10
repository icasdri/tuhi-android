package me.tonyduco.tuhi.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import me.tonyduco.tuhi.R;

public class SettingsActivity extends ActionBarActivity {


    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();

        setContentView(R.layout.fragment_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        if (toolbar != null) {
            toolbar.setTitle(getTitle());
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
//            toolbar.setTitle(R.string.title_settings);
//            setSupportActionBar(toolbar);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


    }

}