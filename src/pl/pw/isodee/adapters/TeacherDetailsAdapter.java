package pl.pw.isodee.adapters;

import android.content.Context;
import pl.pw.isodee.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import pl.pw.isodee.fragments.TeacherDetailsFragment;
import pl.pw.isodee.fragments.TeacherMainDetailsFragment;

public class TeacherDetailsAdapter extends FragmentPagerAdapter {
    protected static String[] CONTENT = null;
    Context context;

    private int mCount = 0;

    public TeacherDetailsAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        CONTENT = this.context.getResources().getStringArray(R.array.teacher_details_pages);
        mCount = CONTENT.length;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TeacherMainDetailsFragment.newInstance(CONTENT[position % CONTENT.length]);
        }

        return TeacherMainDetailsFragment.newInstance(CONTENT[position % CONTENT.length]);
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TeacherDetailsAdapter.CONTENT[position % CONTENT.length];
    }

    public void setCount(int count) {
        if (count > 0) {
            mCount = count;
            notifyDataSetChanged();
        }
    }
}