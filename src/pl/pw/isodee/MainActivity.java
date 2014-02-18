package pl.pw.isodee;

import pl.pw.isodee.fragments.NewsListFragment;
import pl.pw.isodee.fragments.TeachersFragment;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

public class MainActivity extends Activity {
	public static final int VIEW_NEWS = 0;
	public static final int VIEW_SCHEDULE = 1;
	public static final int VIEW_SYLLABUSES = 2;
	public static final int VIEW_TEACHERS= 3;
	public static final int VIEW_DEFENDED = 4;
	public static final int VIEW_TOPIC_PROPOSITIONS = 5;
	public static final int VIEW_STUDY_PROGRAMMES = 5;
	public static final int VIEW_DOCUMENTS = 5;
	
	private String[] mNavigationItems;
	private String[] mNavigationItemsIcons;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
	public IsodEEApplication theApplication;
	private SearchView searchView;
	private int activeView;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        theApplication = (IsodEEApplication) getApplication();
        
        setContentView(R.layout.activity_main);
        
        ContentGenerator.newsListItems(theApplication.getContent());
        ContentGenerator.newsContentItems(theApplication.getContent());
        ContentGenerator.newTeacherItems(theApplication.getContent());
        
        mTitle = mDrawerTitle = getTitle();
        mNavigationItems = getResources().getStringArray(R.array.navigation_items);
        mNavigationItemsIcons = getResources().getStringArray(R.array.navigation_items_icons);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
        initNavigationDrawer();
        
        if (savedInstanceState == null) {
            selectItem(VIEW_NEWS);
            activeView = VIEW_NEWS;
        }
	}

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    private void onQuery(String query) {
    	Fragment currentFragment = (Fragment) getFragmentManager().findFragmentById(R.id.content_frame);
    	if (currentFragment instanceof TeachersFragment) {
    		((TeachersFragment)currentFragment).useFilter(query);
    	}
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        // Get the SearchView and set the searchable configuration
	    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	    searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
	    // Assumes current activity is the searchable activity
	    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
	    
	    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
	        @Override
	        public boolean onQueryTextSubmit(String query) {
	            return false;
	        }
	
	        @Override
	        public boolean onQueryTextChange(String newText) {
	        	Log.i("QUERY", newText);
	        	onQuery(newText);
	            return true;
	        }
	    });
		    
        return super.onCreateOptionsMenu(menu);
	}
	/* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        if (!drawerOpen && activeView == VIEW_TEACHERS) {
        	menu.findItem(R.id.action_search).setVisible(true);
        } else {
        	menu.findItem(R.id.action_search).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // The action bar home/up action should open or close the drawer.
         // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        
        // Handle action buttons
        switch(item.getItemId()) {
	        case R.id.action_settings:
	        	showSettings();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
        }
    }
    
    private void showSettings() {
    	Intent myIntent = new Intent(this, SettingsActivity.class);
		this.startActivity(myIntent);
    }
    
    public void showNews (int position) {
    	Intent myIntent = new Intent(this, NewsActivity.class);
		myIntent.putExtra(NewsActivity.KEY_NEWS, position);
		this.startActivity(myIntent);
    }
    
    private void clearSearchView () {
    	if (searchView != null) {
        	searchView.setQuery("", false);
        	searchView.clearFocus();
        }
    }
    
    private void initNavigationDrawer () {
    	// set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new DrawerAdapter(this,
                R.layout.drawer_list_item, mNavigationItems));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                clearSearchView();
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                clearSearchView();
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }
    
    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
    	Fragment fragment = null;
    	
    	switch (position) {
			case VIEW_NEWS:
				fragment = new NewsListFragment();
				activeView = VIEW_NEWS;
				break;
			case VIEW_TEACHERS:
				fragment = new TeachersFragment();
				activeView = VIEW_TEACHERS;
				break;
			default:
				fragment = new NewsListFragment();
				activeView = VIEW_NEWS;
				break;
		}

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.content_frame, fragment).commit();
        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mNavigationItems[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }
    
    public class DrawerAdapter extends ArrayAdapter<String> {
    	Context context; 
        int layoutResourceId;    
        String data[] = null;
        
        public DrawerAdapter(Context context, int layoutResourceId, String[] data) {
            super(context, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.context = context;
            this.data = data;
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        	View row = convertView;
        	DrawerHolder holder = null;
        	
        	if (row == null) {
        		LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                row = inflater.inflate(layoutResourceId, parent, false);

                holder = new DrawerHolder();
                holder.title = (TextView)row.findViewById(R.id.drawer_title);
                holder.icon = (ImageView)row.findViewById(R.id.drawer_icon);
            	row.setTag(holder);
            } else {
                holder = (DrawerHolder)row.getTag();
            }

            String item = data[position];
            holder.title.setText(item);
            holder.icon.setImageResource(getResources().getIdentifier(mNavigationItemsIcons[position], "drawable", getContext().getPackageName()));
            
        	return row;
        }
        
        private class DrawerHolder {
    		TextView title;
        	ImageView icon;
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }
}
