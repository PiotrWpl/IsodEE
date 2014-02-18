package pl.pw.isodee.fragments;

import java.util.ArrayList;

import pl.pw.isodee.MainActivity;
import pl.pw.isodee.R;
import pl.pw.isodee.models.TeacherListItem;
import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class TeachersFragment extends ListFragment {
	

	protected MainActivity theActivity;
	private ListView lv;
	private String query;
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);    
	}
	 
    @Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		theActivity = (MainActivity) activity;
	}
    
    public void useFilter(String query) {
    	if (this.query == null || !this.query.equals(query)) {
	    	this.query = query;
	    	
	    	ArrayList<TeacherListItem> teachersFiltered = new ArrayList<TeacherListItem>();
	        for (int i = 0; i < theActivity.theApplication.getContent().getTeachersLength(); i++) {
	        	TeacherListItem teacher = theActivity.theApplication.getContent().getTeacherByPos(i);
	    		if (teacher.getName().toLowerCase().contains(query) || teacher.getDepartment().toLowerCase().contains(query)) {
	    			teachersFiltered.add(teacher);
	    		}
	  		}
	         
	    	((TeacherAdapter)lv.getAdapter()).setData(teachersFiltered);
	    	((TeacherAdapter)lv.getAdapter()).notifyDataSetChanged();
    	}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        	
        View contentView = (View)inflater.inflate(R.layout.teachers_list_view, container, false);
           
        ArrayList<TeacherListItem> teachers = new ArrayList<TeacherListItem>();
        for (int i = 0; i < theActivity.theApplication.getContent().getTeachersLength(); i++) {
        	teachers.add(theActivity.theApplication.getContent().getTeacherByPos(i));
		}
        
        TeacherAdapter mAdapter = new TeacherAdapter(getActivity(), R.layout.teacher_list_item, teachers);
        lv = (ListView)contentView.findViewById(android.R.id.list);
        lv.setAdapter(mAdapter);

        return contentView;
    }

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
//				theActivity.showNews(position);
			}
		});
	}
    
    public class TeacherAdapter extends ArrayAdapter<TeacherListItem> {
    	Context context; 
        int layoutResourceId;    
        ArrayList<TeacherListItem> data = null;
        
        public void setData(ArrayList<TeacherListItem> data) {
        	this.data = data;
        }
        
        public TeacherAdapter(Context context, int layoutResourceId, ArrayList<TeacherListItem> data) {
            super(context, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.context = context;
            this.data = data;
        }
        
        @Override
        public int getCount() {
        	return data.size();
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        	View row = convertView;
        	TeacherHolder holder = null;
        	
        	if (row == null) {
        		LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                row = inflater.inflate(layoutResourceId, parent, false);

                holder = new TeacherHolder();
                holder.position = (TextView)row.findViewById(R.id.teacher_position);
                holder.name = (TextView)row.findViewById(R.id.teacher_name);
                holder.title = (TextView)row.findViewById(R.id.teacher_title);
                holder.department = (TextView)row.findViewById(R.id.teacher_department);
            	row.setTag(holder);
            } else {
                holder = (TeacherHolder)row.getTag();
            }

            TeacherListItem teacher = data.get(position);
            holder.position.setText(teacher.getLp());
            holder.name.setText(teacher.getName());
            holder.title.setText(teacher.getTitle());
            holder.department.setText(teacher.getDepartment());

        	return row;
        }
        
        private class TeacherHolder {
    		TextView position;
        	TextView name;
        	TextView department;
        	TextView title;
        }
    }
}
