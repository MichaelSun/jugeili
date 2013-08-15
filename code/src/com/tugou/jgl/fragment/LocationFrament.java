package com.tugou.jgl.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tugou.jgl.R;
import com.tugou.jgl.activity.SubListActivity;

/**
 * Created with IntelliJ IDEA.
 * User: michael
 * Date: 13-8-15
 * Time: PM4:23
 * To change this template use File | Settings | File Templates.
 */
public class LocationFrament extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View ret = inflater.inflate(R.layout.location, null);

        //test
        ret.findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getActivity().getApplicationContext(), SubListActivity.class);
                getActivity().startActivity(i);
            }
        });

        return ret;
    }
}
