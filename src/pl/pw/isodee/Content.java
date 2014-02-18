package pl.pw.isodee;

import java.util.Hashtable;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import pl.pw.isodee.models.NewsContent;
import pl.pw.isodee.models.NewsListItem;
//import pl.pw.isodmobile.model.Article;
//import pl.pw.isodmobile.model.LecturerInfo;
//import pl.pw.isodmobile.model.LecturersList;
//import pl.pw.isodmobile.model.NewsList;
import pl.pw.isodee.models.Teacher;


public class Content {
	private Hashtable<String, NewsContent> newsContents;
//	private Hashtable<String, LecturersList> lecturers;
//	private Hashtable<String, LecturerInfo> lecturersInfo;
	private Hashtable<String, NewsListItem> newsList;
	private Hashtable<String, Teacher> teachersList;

	public Content() {
		newsContents = new Hashtable<String, NewsContent>();
		newsList = new Hashtable<String, NewsListItem>();
		teachersList = new Hashtable<String, Teacher>();
//		lecturers = new Hashtable<String, LecturersList>();
//		lecturersInfo = new Hashtable<String, LecturerInfo>();
	}
	
	public void clearAllData() {
		newsContents.clear();
		newsList.clear();
//		lecturers.clear();	
//		lecturersInfo.clear();	
	}
//	
	public void putNewsContent(int pos, NewsContent article) {
		newsContents.put("" + pos, article);
	}
//	
	public NewsContent getNewsContentByPos(int pos) {
		return newsContents.get("" + pos);
	}
	
	public NewsListItem getNewsByPos(int pos) {
		return newsList.get("" + pos);
	}
	
	public Teacher getTeacherByPos(int pos) {
		return teachersList.get("" + pos);
	}
	
	public int getTeachersLength() {
		return teachersList.size();
	}
	
	public int getNewsLength () {
		return newsList.size();
	}
	
	public void putNewsToPos(int pos, JSONObject obj) {
		try {
			newsList.put("" + pos, new NewsListItem(obj));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public void putNewsToPos(int pos, NewsListItem item) {
		newsList.put("" + pos, item);
	}
//	
//	public void putLecturer(String id, LecturerInfo lecturer) {
//		lecturersInfo.put(id, lecturer);
//	}
//	
//	public void putLecturerToPos(String id, LecturersList lecturer) {
//		lecturers.put(id, lecturer);
//	}
//	
//	public LecturersList getLecturerByPos(int lecturerId) {
//		return lecturers.get("" + lecturerId);
//	}

	public void putTeacher(int idx, Teacher teacher) {
		teachersList.put("" + idx, teacher);
	}
	
}
