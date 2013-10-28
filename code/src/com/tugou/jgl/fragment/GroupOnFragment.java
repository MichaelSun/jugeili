package com.tugou.jgl.fragment;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.plugin.common.utils.CustomThreadPool;
import com.plugin.common.utils.CustomThreadPool.TaskWrapper;
import com.plugin.internet.InternetUtils;
import com.tugou.jgl.R;
import com.tugou.jgl.activity.LoginActivity;
import com.tugou.jgl.adapter.GrouponListAdapter;
import com.tugou.jgl.adapter.SubListAdater;
import com.tugou.jgl.api.GetListGroupRequest;
import com.tugou.jgl.api.GetListGroupResponse;
import com.tugou.jgl.api.GetListGroupResponse.GroupInfo;
import com.tugou.jgl.utils.Constant;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

public class GroupOnFragment extends Fragment {
    private PullToRefreshListView mPullToRefreshListView;
    private ListView mlistView;
    private GroupInfo[] groupInfo;

    private static final int SET_LIST = 1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SET_LIST:
                    mlistView.setAdapter(new GrouponListAdapter(getActivity().getLayoutInflater(), groupInfo));
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

        //mHandler.sendEmptyMessage(SET_LIST);
        getListGroup();
        
        
//        mPullToRefreshListView.setOnClickListener(new OnClickListener(){
//
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent();
//				intent.setClass(null, LoginActivity.class);
//				startActivity(intent);
//				
//			}
//        	
//        });

        return view;
    }
    
    private void getListGroup() {
        CustomThreadPool.getInstance().excute(new TaskWrapper(new Runnable() {
            @Override
            public void run() {
                try {
					final GetListGroupResponse response = InternetUtils.request( 
							GroupOnFragment.this.getActivity().getApplicationContext(), new GetListGroupRequest(Constant.CATEGORY_ALL, 
									Constant.AREA_ALL, 
									Constant.ORDER_DEFAULT, 
									Constant.CITY_SHANGHAI, 
									Constant.IS_NO_ORDER_CLOSE, 
									Constant.IS_HOLIDAY_CAN_USE_CLOSE));
                    if (response != null) {
//                    	if(response.result.groupInfo.length != 0){
//                    		groupInfo = response.result.groupInfo
//                    	}
                    	groupInfo = response.result.groupInfo;
                    	mHandler.sendEmptyMessage(SET_LIST);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }
    
}
