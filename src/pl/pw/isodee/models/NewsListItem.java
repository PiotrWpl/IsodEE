package pl.pw.isodee.models;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class NewsListItem {

	private int index;
	private String title;
	private String date;
	private long timestamp;
	private long updateTimestamp;
	private String author;
	private String htmlContent;
	
	public NewsListItem(JSONObject o) throws JSONException {
//		index = o.getInt("x");
		title = capitalizeFirstLetter(o.getString("t"));
		date = o.getString("d");
//		timestamp = o.getLong("d");
//		updateTimestamp = o.optLong("ud", 0);
//		author = o.getString("a");
//		if (!o.isNull("c")) {
//			htmlContent = o.getString("c");
//		}
		
	}
	
	public NewsListItem(String title, String date){
//		index = o.getInt("x");
		this.title = capitalizeFirstLetter(title);
		this.date = date;
//		timestamp = o.getLong("d");
//		updateTimestamp = o.optLong("ud", 0);
//		author = o.getString("a");
//		if (!o.isNull("c")) {
//			htmlContent = o.getString("c");
//		}
		
	}
	
	public String getDate() {
		return date;
	}

	public int getIndex() {
		return index;
	}

	public String getTitle() {
		return title;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public long getUpdateTimestamp() {
		return updateTimestamp;
	}

	public String getAuthor() {
		return author;
	}

	public String getHtmlContent() {
		return htmlContent;
	}
	
	private String capitalizeFirstLetter(String text) {
		if (text.length() > 0) {
            text = String.valueOf(text.charAt(0)).toUpperCase() + text.subSequence(1, text.length());
            return text;
		}
		return "";
	}
}
