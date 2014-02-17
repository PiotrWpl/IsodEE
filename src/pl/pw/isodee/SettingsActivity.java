package pl.pw.isodee;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;

import pl.pw.isodee.fragments.SettingsFragment;

public class SettingsActivity extends Activity implements OnSharedPreferenceChangeListener {
	
	SharedPreferences pref;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		pref.registerOnSharedPreferenceChangeListener(this);
		getActionBar().setTitle(getString(R.string.settings_title));
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		pref.unregisterOnSharedPreferenceChangeListener(this);
	}
    
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.KEY_PREF_SELECTED_LANGUAGE))) {
           ((IsodEEApplication) getApplication()).changeLanguage(sharedPreferences.getString(key, ""));
           ((IsodEEApplication) getApplication()).restartApplication();
        }
    }
}