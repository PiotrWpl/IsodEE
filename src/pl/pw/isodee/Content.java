package pl.pw.isodee;

import java.util.Hashtable;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import pl.pw.isodee.models.NewsFullContent;
import pl.pw.isodee.models.NewsListItem;
//import pl.pw.isodmobile.model.Article;
//import pl.pw.isodmobile.model.LecturerInfo;
//import pl.pw.isodmobile.model.LecturersList;
//import pl.pw.isodmobile.model.NewsList;
import pl.pw.isodee.models.TeacherListItem;


public class Content {
	private Hashtable<String, NewsFullContent> newsContents;
//	private Hashtable<String, LecturersList> lecturers;
//	private Hashtable<String, LecturerInfo> lecturersInfo;
	private Hashtable<String, NewsListItem> newsList;
	private Hashtable<String, TeacherListItem> teachersList;

	public Content() {
		newsContents = new Hashtable<String, NewsFullContent>();
		newsList = new Hashtable<String, NewsListItem>();
		teachersList = new Hashtable<String, TeacherListItem>();
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
	public void putNewsContent(int pos, NewsFullContent article) {
		newsContents.put("" + pos, article);
	}
//	
	public NewsFullContent getNewsContentByPos(int pos) {
		return newsContents.get("" + pos);
	}
	
	public NewsListItem getNewsByPos(int pos) {
		return newsList.get("" + pos);
	}
	
	public TeacherListItem getTeacherByPos(int pos) {
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

	public void putTeacher(int idx, TeacherListItem teacher) {
		teachersList.put("" + idx, teacher);
	}
	
}
