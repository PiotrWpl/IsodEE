package pl.pw.isodee.adapters;

import pl.pw.isodee.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.viewpagerindicator.IconPagerAdapter;
import pl.pw.isodee.fragments.TestFragment;

public class TestFragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
    protected static final String[] CONTENT = new String[] { "Podstawowe informacje", "Konsultacje w 2014L", "Przedmioty w 2014L", "Plan zajęć", "Tematy prac dyplomowych"};
    protected static final int[] ICONS = new int[] {
            R.drawable.news_icon,
            R.drawable.schedule_icon,
            R.drawable.syllabuses_icon,
            R.drawable.teachers_icon,
            R.drawable.defended_icon
    };

    private int mCount = CONTENT.length;

    public TestFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return TestFragment.newInstance(CONTENT[position % CONTENT.length]);
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TestFragmentAdapter.CONTENT[position % CONTENT.length];
    }

    @Override
    public int getIconResId(int index) {
        return ICONS[index % ICONS.length];
    }

    public void setCount(int count) {
        if (count > 0 && count <= 10) {
            mCount = count;
            notifyDataSetChanged();
        }
    }
}