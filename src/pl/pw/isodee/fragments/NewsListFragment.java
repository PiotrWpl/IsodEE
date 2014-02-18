package pl.pw.isodee.fragments;

import android.app.ListFragment;
import pl.pw.isodee.MainActivity;
import pl.pw.isodee.R;
import pl.pw.isodee.models.NewsListItem;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
        
        int newsListSize = theActivity.theApplication.getContent().getNewsLength();
        
        NewsListItem fromNews[] = new NewsListItem[newsListSize];
        for (int i = 0; i < newsListSize; i++) {
			fromNews[i] = theActivity.theApplication.getContent().getNewsByPos(i);
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
    
    public class NewsAdapter extends ArrayAdapter<NewsListItem> {
    	Context context; 
        int layoutResourceId;    
        NewsListItem data[] = null;
        
        public NewsAdapter(Context context, int layoutResourceId, NewsListItem[] data) {
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

            NewsListItem news = data[position];
            holder.title.setText(news.getTitle());
            holder.date.setText(news.getDate());
            
            if (news.getNotAnimated()) {
            	news.setNotAnimated(false);
	            startAnimation(row);
            }
        	return row;
        }
        
        private class NewsHolder {
    		TextView title;
        	TextView date;
        }
    }
    
    private void startAnimation (View row) {
    	AlphaAnimation alphaAnimation = new AlphaAnimation(0.6f, 1.0f);
        alphaAnimation.setDuration(300);
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(alphaAnimation);
        
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 200, 0);
        translateAnimation.setDuration(400);
        animationSet.addAnimation(translateAnimation);
        row.startAnimation(animationSet);
    }
}