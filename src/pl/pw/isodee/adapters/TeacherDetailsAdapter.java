package pl.pw.isodee.adapters;

import pl.pw.isodee.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.viewpagerindicator.IconPagerAdapter;
import pl.pw.isodee.fragments.TeacherDetailsFragment;

public class TeacherDetailsAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
    protected static final String[] CONTENT = new String[] { "dr inż. Robert Szmurło", "Konsultacje w 2014L:", "Przedmioty prowadzone w semestrze 2014L:", "Plan na studiach stacjonarnych w semestrze 2014L:", "Plan na studiach niestacjonarnych w semestrze 2014L:", "Dostępne propozycje tematów prac dyplomowych i projektów:", "Obronione prace dyplomowe i projekty:"};
    protected static final int[] ICONS = new int[] {
            R.drawable.news_icon,
            R.drawable.schedule_icon,
            R.drawable.syllabuses_icon,
            R.drawable.teachers_icon,
            R.drawable.syllabuses_icon,
            R.drawable.teachers_icon,
            R.drawable.defended_icon
    };

    private int mCount = CONTENT.length;

    public TeacherDetailsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return TeacherDetailsFragment.newInstance(CONTENT[position % CONTENT.length]);
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TeacherDetailsAdapter.CONTENT[position % CONTENT.length];
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