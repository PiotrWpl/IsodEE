package pl.pw.isodee.fragments;

import pl.pw.isodee.MainActivity;
import pl.pw.isodee.R;
import pl.pw.isodee.models.NewsListItem;
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
    
    @Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		theActivity = (MainActivity) activity;
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        	
        lv = (ListView)inflater.inflate(R.layout.teachers_list, container, false);
//        lv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
           
        Teacher fromNews[] = new Teacher[500];
        for (int i = 0; i < 500; i++) {
			fromNews[i] = new Teacher(i + 1, "Profesor " + i, "Wydział " + i);
		}

        TeacherAdapter mAdapter = new TeacherAdapter(getActivity(), R.layout.teacher_fragment, fromNews);

        lv.setAdapter(mAdapter);

        return lv;
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
    
    public class TeacherAdapter extends ArrayAdapter<Teacher> {
    	Context context; 
        int layoutResourceId;    
        Teacher data[] = null;
        
        public TeacherAdapter(Context context, int layoutResourceId, Teacher[] data) {
            super(context, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.context = context;
            this.data = data;
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
                holder.department = (TextView)row.findViewById(R.id.teacher_department);
            	row.setTag(holder);
            } else {
                holder = (TeacherHolder)row.getTag();
            }

            Teacher teacher = data[position];
            holder.position.setText(teacher.position);
            holder.name.setText(teacher.name);
            holder.department.setText(teacher.department);
            
        	return row;
        }
        
        private class TeacherHolder {
    		TextView position;
        	TextView name;
        	TextView department;
        }
    }
    
    public class Teacher {
    	private String position;
        private String name;
        private String department;
        
        public Teacher(){
            super();
        }
    	    
        public Teacher(int position, String name, String department) {
            super();
            this.position = "" + position;
            this.name = name;
            this.department = department;
        }
    }
}
