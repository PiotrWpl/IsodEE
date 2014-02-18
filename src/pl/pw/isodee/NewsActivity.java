package pl.pw.isodee;

import pl.pw.isodee.models.NewsContent;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

public class NewsActivity extends Activity {
	
	public static final String KEY_NEWS = "news_pos";
	
	private IsodEEApplication theApplication;
	private NewsContent news;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		theApplication = (IsodEEApplication) getApplication();
		setContentView(R.layout.activity_news);
    
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    
	    int pos = 0;//getIntent().getExtras().getInt(KEY_NEWS);
	    news = theApplication.getContent().getNewsContentByPos(pos);
	    showNews();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		getActionBar().setTitle(getString(R.string.news_title));
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
	
	private void showNews () {
		TextView title = (TextView) findViewById(R.id.news_title);
		title.setText(news.getTitle());
		
		TextView date = (TextView) findViewById(R.id.news_date);
		date.setText(getString(R.string.news_publication_date) + news.getDate());
		
		TextView updated = (TextView) findViewById(R.id.news_updated);
		updated.setText(getString(R.string.news_last_modified) + news.getUpdateDate() + getString(R.string.news_last_modified_link) + news.getAuthor());
		
		WebView content = (WebView) findViewById(R.id.news_content);
		String htmlContent = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body style=\"margin: 0 10px; padding: 0;\">" + news.getHtmlContent() + "</body></html>";
		content.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null);
		content.setBackgroundColor(Color.TRANSPARENT);

	}

}
