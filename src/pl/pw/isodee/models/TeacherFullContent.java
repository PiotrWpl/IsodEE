package pl.pw.isodee.models;

import org.json.JSONException;
import org.json.JSONObject;

public class TeacherFullContent {

	private String lp;
	private String title;
	private String department;
	private String name;
	
	public TeacherFullContent(JSONObject o) throws JSONException {
		title = o.getString("t");
	}
	
	public TeacherFullContent (int lp, String name, String title, String department) {
		this.lp = "" + lp;
		this.title = title;
		this.name = name;
		this.department = department;
	}

	public String getTitle() {
		return title;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public String getLp() {
		return lp;
	}
	
	public String getName() {
		return name;
	}
}
