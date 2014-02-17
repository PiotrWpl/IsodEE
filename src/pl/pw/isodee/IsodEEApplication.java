package pl.pw.isodee;

import java.util.Locale;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.util.Log;
public class IsodEEApplication extends Application {

	private Content content;
    private Locale locale = null;

	public IsodEEApplication() {
		super();
	}

	@Override
	public void onCreate() {
		super.onCreate();
		setLanguage();
	}
	
	@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (locale != null) {
        	saveLocale(locale, newConfig);
        }
    }
	
	public void restartApplication() {
	    Intent i = new Intent(this, MainActivity.class);
	    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    this.startActivity(i);
	}
	
	
	private void setLanguage () {
		String savedLanguage = getLanguage();
		
		if (!"".equals(savedLanguage)) {
			changeLanguage(savedLanguage);
		}
	}
	
	public void changeLanguage(String lang) {
		Configuration config = getBaseContext().getResources().getConfiguration();
        
		if (!"".equals(lang) && !config.locale.getLanguage().equals(lang)) {
			
            Editor ed = PreferenceManager.getDefaultSharedPreferences(this).edit();
            ed.putString(getString(R.string.KEY_PREF_SELECTED_LANGUAGE), lang);
            ed.commit();

            locale = new Locale(lang);
            saveLocale(locale, config);
        }
    }
	
	public void saveLocale (Locale locale, Configuration config) {
        Locale.setDefault(locale);
        Configuration conf = new Configuration(config);
        conf.locale = locale;
        getBaseContext().getResources().updateConfiguration(conf, getBaseContext().getResources().getDisplayMetrics());
	}

    public String getLanguage(){
        return PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.KEY_PREF_SELECTED_LANGUAGE), "");
    }

	public Content getContent() {
		if (content == null) {
			content = new Content();
		}
		return content;
	}
}
