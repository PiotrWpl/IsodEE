package pl.pw.isodee.fragments;

import android.app.ListFragment;
import pl.pw.isodee.MainActivity;
import pl.pw.isodee.NewsActivity;
import pl.pw.isodee.R;
import pl.pw.isodee.models.NewsListItem;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class NewsListFragment extends ListFragment {
	
	protected MainActivity theActivity;
	private ListView lv;
    
    @Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		theActivity = (MainActivity) activity;
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        	
        lv = (ListView)inflater.inflate(R.layout.news_list, container, false);
//        lv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        
        int newsListSize = theActivity.theApplication.getContent().getNewsLength();
        
        News fromNews[] = new News[newsListSize];
        for (int i = 0; i < newsListSize; i++) {
        	NewsListItem news = theActivity.theApplication.getContent().getNewsByPos(i);
			fromNews[i] = new News(news.getTitle(), news.getDate());
		}

        NewsAdapter mAdapter = new NewsAdapter(getActivity(), R.layout.news_fragment, fromNews);

        lv.setAdapter(mAdapter);

        return lv;
    }
    
    @Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
				theActivity.showNews(position);
			}
		});
	}
    
    public class NewsAdapter extends ArrayAdapter<News> {
    	Context context; 
        int layoutResourceId;    
        News data[] = null;
        
        public NewsAdapter(Context context, int layoutResourceId, News[] data) {
            super(context, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.context = context;
            this.data = data;
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        	View row = convertView;
        	NewsHolder holder = null;
        	
        	if (row == null) {
        		LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                row = inflater.inflate(layoutResourceId, parent, false);

                holder = new NewsHolder();
                holder.title = (TextView)row.findViewById(R.id.news_title);
                holder.date = (TextView)row.findViewById(R.id.news_date);
            	row.setTag(holder);
            } else {
                holder = (NewsHolder)row.getTag();
            }

            News news = data[position];
            holder.title.setText(news.title);
            holder.date.setText(news.date);
            
        	return row;
        }
        
        private class NewsHolder {
    		TextView title;
        	TextView date;
        }
    }
    
    public class News {
    	private String title;
        private String date;
        
        public News(){
            super();
        }
    	    
        public News(String title, String date) {
            super();
            this.date = date;
            this.title = title;
        }
    }
}