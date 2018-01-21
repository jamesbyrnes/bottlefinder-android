package net.runcoderun.james.bottlefinder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by james on 06/12/17.
 */

public class SearchFragment extends Fragment {
    private String arg;

    public static SearchFragment newInstance(String arg) {
        SearchFragment searchFragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString("showString", arg);
        searchFragment.setArguments(args);
        return searchFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arg = getArguments().getString("showString", "NOTHING");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_search, container, false);
        TextView textView = (TextView) view.findViewById(R.id.search_text_view);
        textView.setText(arg);
        Log.d("SEARCH_FRAG", arg);
        return view;
    }
}
