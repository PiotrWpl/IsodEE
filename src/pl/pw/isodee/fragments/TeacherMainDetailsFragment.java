package pl.pw.isodee.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import pl.pw.isodee.R;

public final class TeacherMainDetailsFragment extends Fragment {
    private static final String KEY_CONTENT = "TeacherDetailsFragment:Content";

    public static TeacherMainDetailsFragment newInstance(String content) {
        TeacherMainDetailsFragment fragment = new TeacherMainDetailsFragment();
//        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < 20; i++) {
//            builder.append(content).append(" ");
//        }
//        builder.deleteCharAt(builder.length() - 1);
        if (content.contains("%NAME%")) {
            content = content.replace("%NAME%", "dr inż. Robert Szmurło");
        } else if (content.contains("%SEMESTER%")) {
            content = content.replace("%SEMESTER%", "2014L");
        }
        fragment.mContent = content;

        return fragment;
    }

    private String mContent = "???";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getString(KEY_CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

//        if (row == null) {
            View view = inflater.inflate(R.layout.teacher_details_main, parent, false);
            TextView text = (TextView)view.findViewById(R.id.teacher_name);
        ImageView image = (ImageView)view.findViewById(R.id.teacher_photo);
//        }
        UrlImageViewHelper.setUrlDrawable(image, "https://isod.ee.pw.edu.pl/isod-portal/photo/key/DpGrhShTODKoUGYeYD2A.dat");

//        TextView text = new TextView(getActivity());
//        text.setGravity(Gravity.CENTER);
//        text.setText(mContent);
//        text.setTextSize(14*getResources().getDisplayMetrics().density);
//        text.setPadding(20, 20, 20, 20);
    text.setText(mContent);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, mContent);
    }
}