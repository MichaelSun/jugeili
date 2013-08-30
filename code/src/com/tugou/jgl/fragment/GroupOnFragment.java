package com.tugou.jgl.fragment;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tugou.jgl.R;
import com.tugou.jgl.adapter.SubListAdater;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class GroupOnFragment extends Fragment {
    private PullToRefreshListView mPullToRefreshListView;
    private ListView mlistView;

    private static final int SET_LIST = 1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SET_LIST:
                    mlistView.setAdapter(new SubListAdater(getActivity().getLayoutInflater()));
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.groupon_list, null);

        mPullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.fragment_list);
        mlistView = mPullToRefreshListView.getRefreshableView();

        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshListView.setShowIndicator(false);
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
            }
        });

        mHandler.sendEmptyMessage(SET_LIST);

        return view;
    }
}
