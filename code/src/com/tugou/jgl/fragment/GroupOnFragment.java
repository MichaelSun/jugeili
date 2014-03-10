package com.tugou.jgl.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.plugin.common.utils.CustomThreadPool;
import com.plugin.common.utils.CustomThreadPool.TaskWrapper;
import com.plugin.internet.InternetUtils;
import com.tugou.jgl.R;
import com.tugou.jgl.activity.GroupDetailActivity;
import com.tugou.jgl.adapter.GrouponListAdapter;
import com.tugou.jgl.api.*;
import com.tugou.jgl.api.GetAreaListResponse.GroupAreaInfo;
import com.tugou.jgl.api.GetCategoryListResponse.GroupCategoryInfo;
import com.tugou.jgl.api.GetListGroupResponse.GroupInfo;
import com.tugou.jgl.popupmenu.RRMenuItem;
import com.tugou.jgl.popupmenu.RRPopupMenu;
import com.tugou.jgl.popupmenu.RRPopupMenu.OnRRMenuItemClickListener;
import com.tugou.jgl.utils.AppRuntime;
import com.tugou.jgl.utils.Constant;

public class GroupOnFragment extends Fragment {
    private PullToRefreshListView mPullToRefreshListView;
    private ListView mlistView;
    private RRPopupMenu mPopupMenuCategory;
    private RRPopupMenu mPopupMenuLocation;
    private RRPopupMenu mPopupMenuOrder;
    private GroupInfo[] groupInfo;
    private GroupCategoryInfo[] groupCategoryInfo;
    private GroupAreaInfo[] groupAreaInfo;

    private TextView mCategoryTitle;

    private TextView mLocationTitle;

    private TextView mOrderTitle;

    private static final int SET_LIST = 1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SET_LIST:
                    mPullToRefreshListView.onRefreshComplete();
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
                getListGroup();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
            }
        });

        //mHandler.sendEmptyMessage(SET_LIST);
        initPopupMenu();
        getListGroup();
        GetAreaList();
        GetCategoryList();
        initPopupMenuOrder();
        //initPopupMenuLocation();
        initUIView(view);

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

//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mPullToRefreshListView.setRefreshing();
//            }
//        }, 1000);

        return view;
    }

    private void initPopupMenu() {
        mPopupMenuOrder = new RRPopupMenu(getActivity().getApplicationContext(), RRPopupMenu.RRMenuType.LOCATION);
        mPopupMenuCategory = new RRPopupMenu(getActivity().getApplicationContext(), RRPopupMenu.RRMenuType.LOCATION);
        mPopupMenuLocation = new RRPopupMenu(getActivity().getApplicationContext(), RRPopupMenu.RRMenuType.LOCATION);
    }

    private void initPopupMenuOrder() {
        //mPopupMenuOrder = new RRPopupMenu(getActivity().getApplicationContext(), RRPopupMenu.RRMenuType.DROPDOWN);
        mPopupMenuOrder.addItem(new RRMenuItem(getActivity().getApplicationContext(), 1000, getString(R.string.order_default), RRMenuItem.RRMenuItemStyle.STYLE_NORMAL));
        mPopupMenuOrder.addItem(new RRMenuItem(getActivity().getApplicationContext(), 1001, getString(R.string.order_near), RRMenuItem.RRMenuItemStyle.STYLE_NORMAL));
        mPopupMenuOrder.addItem(new RRMenuItem(getActivity().getApplicationContext(), 1002, getString(R.string.order_comment), RRMenuItem.RRMenuItemStyle.STYLE_NORMAL));
        mPopupMenuOrder.addItem(new RRMenuItem(getActivity().getApplicationContext(), 1003, getString(R.string.order_new), RRMenuItem.RRMenuItemStyle.STYLE_NORMAL));
        mPopupMenuOrder.addItem(new RRMenuItem(getActivity().getApplicationContext(), 1004, getString(R.string.order_popu), RRMenuItem.RRMenuItemStyle.STYLE_NORMAL));
        mPopupMenuOrder.addItem(new RRMenuItem(getActivity().getApplicationContext(), 1005, getString(R.string.order_low_price), RRMenuItem.RRMenuItemStyle.STYLE_NORMAL));
        mPopupMenuOrder.addItem(new RRMenuItem(getActivity().getApplicationContext(), 1006, getString(R.string.order_high_price), RRMenuItem.RRMenuItemStyle.STYLE_NORMAL));

        mPopupMenuOrder.setOnRRMenuItemClickListener(new OnRRMenuItemClickListener() {
            @Override
            public void onItemClick(RRMenuItem menuItem) {
                mOrderTitle.setText(menuItem.getTitle());
                switch (menuItem.getId()) {
                    case 1000:
                        break;
                    case 1001:
                        break;
                    case 1002:
                        break;
                    case 1003:
                        break;
                    case 1004:
                        break;
                    case 1005:
                        break;
                    case 1006:
                        break;
                }
            }
        });
    }

    private void initPopupMenuCategory(GroupCategoryInfo[] groupCategoryInfo) {
        //mPopupMenuCategory = new RRPopupMenu(getActivity().getApplicationContext(), RRPopupMenu.RRMenuType.DROPDOWN);
        int iLen = groupCategoryInfo.length;
        for (int i = 0; i < iLen; i++) {
            mPopupMenuCategory.addItem(new RRMenuItem(getActivity().getApplicationContext(), 1000 + i, groupCategoryInfo[i].category_name, RRMenuItem.RRMenuItemStyle.STYLE_NORMAL));
        }

        mPopupMenuCategory.setOnRRMenuItemClickListener(new OnRRMenuItemClickListener() {
            @Override
            public void onItemClick(RRMenuItem menuItem) {
                mCategoryTitle.setText(menuItem.getTitle());
            }
        });
    }

    private void initPopupMenuLocation(GroupAreaInfo[] groupAreaInfo) {
        int iLen = groupAreaInfo.length;
        for (int i = 0; i < iLen; i++) {
            mPopupMenuLocation.addItem(new RRMenuItem(getActivity().getApplicationContext(), 1000 + i, groupAreaInfo[i].area_name, RRMenuItem.RRMenuItemStyle.STYLE_NORMAL));
        }

        mPopupMenuLocation.setOnRRMenuItemClickListener(new OnRRMenuItemClickListener() {

            @Override
            public void onItemClick(RRMenuItem menuItem) {
                mLocationTitle.setText(menuItem.getTitle());
            }
        });
    }

    private void initUIView(View root) {
        mCategoryTitle = (TextView) root.findViewById(R.id.category);
        mCategoryTitle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mPopupMenuCategory.show(mCategoryTitle);
            }
        });

        mLocationTitle = (TextView) root.findViewById(R.id.location);
        mLocationTitle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mPopupMenuLocation.show(mLocationTitle);
            }
        });

        mOrderTitle = (TextView) root.findViewById(R.id.order);
        mOrderTitle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mPopupMenuOrder.show(mOrderTitle);
            }
        });

        mlistView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent i = new Intent(GroupOnFragment.this.getActivity(), GroupDetailActivity.class);
                Bundle boundle = new Bundle();
                boundle.putString("group_id", groupInfo[position - 1].id);
                i.putExtras(boundle);
                startActivity(i);
            }
        });
    }

    private void getListGroup() {
        CustomThreadPool.getInstance().excute(new TaskWrapper(new Runnable() {
            @Override
            public void run() {
                try {

                    GetListGroupRequest request = new GetListGroupRequest(Constant.CATEGORY_ALL,
                                                                             Constant.AREA_ALL,
                                                                             Constant.ORDER_DEFAULT,
                                                                             Constant.CITY_SHANGHAI,
                                                                             Constant.IS_NO_ORDER_CLOSE,
                                                                             Constant.IS_HOLIDAY_CAN_USE_CLOSE);
                    final GetListGroupResponse response = InternetUtils.request(getActivity().getApplicationContext(), request);
                    if (response != null) {
//                    	if(response.result.groupInfo.length != 0){
//                    		groupInfo = response.result.groupInfo
//                    	}
                        groupInfo = response.groupInfo;
                        AppRuntime.groupInfo = groupInfo;

                        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(getActivity().getApplicationContext());
                        Intent i = new Intent();
                        i.setAction(LocationFrament.ACTION_UPDATE);
                        lbm.sendBroadcast(i);

                        //GetCategoryList();
                        mHandler.sendEmptyMessage(SET_LIST);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    private void GetCategoryList() {
        CustomThreadPool.getInstance().excute(new TaskWrapper(new Runnable() {
            @Override
            public void run() {
                try {
                    final GetCategoryListResponse response = InternetUtils.request(
                                                                                      GroupOnFragment.this.getActivity().getApplicationContext(), new GetCategoryListRequest(0,
                                                                                                                                                                                1));
                    if (response != null) {
//                    	groupInfo = response.groupInfo;
//                    	mHandler.sendEmptyMessage(SET_LIST);
                        groupCategoryInfo = response.groupCategoryInfo;
                        initPopupMenuCategory(groupCategoryInfo);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    private void GetAreaList() {
        CustomThreadPool.getInstance().excute(new TaskWrapper(new Runnable() {
            @Override
            public void run() {
                try {
                    final GetAreaListResponse response = InternetUtils.request(
                                                                                  GroupOnFragment.this.getActivity().getApplicationContext(), new GetAreaListRequest(0,
                                                                                                                                                                        1));
                    if (response != null) {
//                    	groupInfo = response.groupInfo;
//                    	mHandler.sendEmptyMessage(SET_LIST);
                        groupAreaInfo = response.groupAreaInfo;
                        initPopupMenuLocation(groupAreaInfo);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }


}
