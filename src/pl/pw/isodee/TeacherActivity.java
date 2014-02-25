package pl.pw.isodee;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;
import pl.pw.isodee.models.NewsFullContent;
import com.viewpagerindicator.LinePageIndicator;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import pl.pw.isodee.adapters.*;
import android.support.v4.app.FragmentActivity;


public class TeacherActivity extends FragmentActivity {
	
	public static final String KEY_NEWS = "news_pos";
	
	private IsodEEApplication theApplication;
	private NewsFullContent news;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		theApplication = (IsodEEApplication) getApplication();
		setContentView(R.layout.teacher_full_content);
    
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    
	    int pos = 0;//getIntent().getExtras().getInt(KEY_NEWS);
	    news = theApplication.getContent().getNewsContentByPos(pos);
	    showTeacher();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		getActionBar().setTitle(getString(R.string.teachers_title));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
		    // Respond to the action bar's Up/Home button
		    case android.R.id.home:
		        NavUtils.navigateUpFromSameTask(this);
		        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	private void showTeacher () {
        //Set the pager with an adapter
        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new TestFragmentAdapter(getSupportFragmentManager()));

        //Bind the title indicator to the adapter
        LinePageIndicator titleIndicator = (LinePageIndicator)findViewById(R.id.indicator);
        titleIndicator.setViewPager(pager);
	}

}
