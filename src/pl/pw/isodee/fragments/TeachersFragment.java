package pl.pw.isodee.fragments;

import java.util.ArrayList;

import pl.pw.isodee.IsodEEApplication;
import pl.pw.isodee.MainActivity;
import pl.pw.isodee.R;
import pl.pw.isodee.models.NewsListItem;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class TeachersFragment extends ListFragment {
	

	protected MainActivity theActivity;
	private ListView lv;
	private String query;
    
	 public static TeachersFragment newInstance(String query) {
		TeachersFragment fragment = new TeachersFragment();
	    Bundle args = new Bundle();
	    args.putString("searchQuery", query);
	    fragment.setArguments(args);
	    return fragment;
	}
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);    

		Bundle arguments = getArguments();
		if (arguments != null) {
			this.query = arguments.getString("searchQuery", "");
		}
		setHasOptionsMenu(true);
	}
	 
    @Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		theActivity = (MainActivity) activity;
	}
    
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
        inflater.inflate(R.menu.teachers, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        	
        View contentView = (View)inflater.inflate(R.layout.teachers_list, container, false);
//        lv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
           
        Teacher fromNews[] = new Teacher[theActivity.theApplication.getContent().getTeachersLength()];
        for (int i = 0; i < theActivity.theApplication.getContent().getTeachersLength(); i++) {
        	pl.pw.isodee.models.Teacher teacher = theActivity.theApplication.getContent().getTeacherByPos(i);
			fromNews[i] = new Teacher(teacher.getLp(), teacher.getName(), teacher.getTitle(), teacher.getDepartment());
		}
        
        TeacherAdapter mAdapter = null;
        
        if (query != null) {
        	Log.i("query", query);
        	ArrayList<Teacher> list = searchNames(fromNews, query);
        	Teacher queredNews[] = list.toArray(new Teacher[list.size()]);
            mAdapter = new TeacherAdapter(getActivity(), R.layout.teacher_fragment, queredNews);
        } else {
            mAdapter = new TeacherAdapter(getActivity(), R.layout.teacher_fragment, fromNews);
        }
        
        lv = (ListView)contentView.findViewById(android.R.id.list);
        lv.setAdapter(mAdapter);

        return contentView;
    }
    
    private ArrayList<Teacher> searchNames(Teacher[] fromNews, String query) {
		// TODO Auto-generated method stub
    	ArrayList<Teacher> temp = new ArrayList<Teacher>();
    	for (int i = 0; i < fromNews.length; i++) {
    		if (fromNews[i].name.contains(query)) {
    			temp.add(fromNews[i]);
    		}
    	}
		return temp;
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
                holder.title = (TextView)row.findViewById(R.id.teacher_title);
                holder.department = (TextView)row.findViewById(R.id.teacher_department);
            	row.setTag(holder);
            } else {
                holder = (TeacherHolder)row.getTag();
            }

            Teacher teacher = data[position];
            holder.position.setText(teacher.position);
            holder.name.setText(teacher.name);
            holder.title.setText(teacher.title);
            holder.department.setText(teacher.department);
            
        	return row;
        }
        
        private class TeacherHolder {
    		TextView position;
        	TextView name;
        	TextView department;
        	TextView title;
        }
    }
    
    public class Teacher {
    	private String position;
        private String name;
        private String title;
        private String department;
        
        public Teacher(){
            super();
        }
    	    
        public Teacher(int position, String name, String title, String department) {
            super();
            this.position = "" + position;
            this.name = name;
            this.title = title;
            this.department = department;
        }
    }
}
