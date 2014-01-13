package com.tugou.jgl.fragment;

import com.tugou.jgl.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class MoreFragment extends Fragment {
    @Override  
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //mInflater = inflater;
        View ret = inflater.inflate(R.layout.more, null);
        //GridView gv = (GridView)ret.findViewById(R.id.category);

        return ret;
    }
}
