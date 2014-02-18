package pl.pw.isodee.models;

import org.json.JSONException;
import org.json.JSONObject;

public class NewsFullContent {

	private int index;
	private String title;
	private long timestamp;
	private long updateTimestamp;
	private String author;
	private String date;
	private String htmlContent;
	private String updateDate;
	
	public NewsFullContent(JSONObject o) throws JSONException {
		index = o.getInt("x");
		title = o.getString("t");
		timestamp = o.getLong("d");
		updateTimestamp = o.optLong("ud", 0);
		author = o.getString("a");
		if (!o.isNull("c")) {
			htmlContent = o.getString("c");
		}
	}
	
	public NewsFullContent (String title, String date, String htmlContent, String updateDate, String author) {
		this.title = title;
		this.date = date;
		this.htmlContent = htmlContent;
		this.updateDate = updateDate;
		this.author = author;
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
	
	public String getUpdateDate() {
		return updateDate;
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
	
	public String getDate() {
		return date;
	}
}
