package com.tugou.jgl.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.plugin.common.utils.CustomThreadPool;
import com.plugin.common.utils.CustomThreadPool.TaskWrapper;
import com.plugin.internet.InternetUtils;
import com.tugou.jgl.R;
import com.tugou.jgl.adapter.SubListAdater;
import com.tugou.jgl.api.NearbyListRequest;
import com.tugou.jgl.api.NearbyListResponse;
import com.tugou.jgl.api.NearbyListResponse.GroupInfo;
import com.tugou.jgl.popupmenu.RRMenuItem;
import com.tugou.jgl.popupmenu.RRPopupMenu;

/**
 * Created with IntelliJ IDEA.
 * User: michael
 * Date: 13-8-28
 * Time: AM11:04
 * To change this template use File | Settings | File Templates.
 */
public class SubListFragment extends Fragment {

    private PullToRefreshListView mPullToRefreshListView;
    private ListView mlistView;

    private RRPopupMenu mPopupMenu;

    private RRPopupMenu mDestPM;

    private int subListPositon;

    private GroupInfo[] groupInfo;

    private String mTitle;

    private TextView mCateTitleTV;

    private TextView mDestTitleTV;

    private int mCategory;

    private String mDest = "3千米";

    private static final int SET_LIST = 1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SET_LIST:
                    mlistView.setAdapter(new SubListAdater(getActivity().getLayoutInflater(), groupInfo));
                    break;
            }
        }
    };

    public SubListFragment(String title, int category) {
        mTitle = title;
        mCategory = category;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sublist, null);

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
        getSubListReq();

        Bundle bundle = getActivity().getIntent().getExtras();
        subListPositon = bundle.getInt("position");

        initPopupMenu();
        initUIView(view);

        return view;
    }

    private void initPopupMenu() {
        mPopupMenu = new RRPopupMenu(getActivity().getApplicationContext(), RRPopupMenu.RRMenuType.LOCATION);
        mPopupMenu.addItem(new RRMenuItem(getActivity().getApplicationContext(), 1000, "全部", RRMenuItem.RRMenuItemStyle.STYLE_NORMAL));
        mPopupMenu.addItem(new RRMenuItem(getActivity().getApplicationContext(), 1001, "热门", RRMenuItem.RRMenuItemStyle.STYLE_NORMAL));

        mPopupMenu.setOnRRMenuItemClickListener(new RRPopupMenu.OnRRMenuItemClickListener() {

            @Override
            public void onItemClick(RRMenuItem menuItem) {
                switch (menuItem.getId()) {
                    case 1000:
                        mCateTitleTV.setText("全部");
                        break;
                    case 1001:
                        mCateTitleTV.setText("热门");
                        break;
                }
            }
        });

        mDestPM = new RRPopupMenu(getActivity().getApplicationContext(), RRPopupMenu.RRMenuType.LOCATION);
        mDestPM.addItem(new RRMenuItem(getActivity().getApplicationContext(), 1002, "1千米", RRMenuItem.RRMenuItemStyle.STYLE_NORMAL));
        mDestPM.addItem(new RRMenuItem(getActivity().getApplicationContext(), 1003, "2千米", RRMenuItem.RRMenuItemStyle.STYLE_NORMAL));
        mDestPM.addItem(new RRMenuItem(getActivity().getApplicationContext(), 1004, "3千米", RRMenuItem.RRMenuItemStyle.STYLE_NORMAL));
        mDestPM.addItem(new RRMenuItem(getActivity().getApplicationContext(), 1005, "5千米", RRMenuItem.RRMenuItemStyle.STYLE_NORMAL));

        mDestPM.setOnRRMenuItemClickListener(new RRPopupMenu.OnRRMenuItemClickListener() {

            @Override
            public void onItemClick(RRMenuItem menuItem) {
                switch (menuItem.getId()) {
                    case 1002:
                        mDestTitleTV.setText("1千米");
                        break;
                    case 1003:
                        mDestTitleTV.setText("2千米");
                        break;
                    case 1004:
                        mDestTitleTV.setText("3千米");
                        break;
                    case 1005:
                        mDestTitleTV.setText("5千米");
                        break;
                }
            }
        });
    }

    private void initUIView(View root) {
        mCateTitleTV = (TextView) root.findViewById(R.id.category);
        mCateTitleTV.setText("全部");
        mCateTitleTV.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mPopupMenu.show(mCateTitleTV);
            }
        });

        mDestTitleTV = (TextView) root.findViewById(R.id.dest);
        mDestTitleTV.setText(mDest);
        mDestTitleTV.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mDestPM.show(mDestTitleTV);
            }
        });
    }

    private void getSubListReq() {
        switch (subListPositon) {
            case 0:
                getNearbyList(10, mCategory, 0, 30, 0, 0, 0);
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }
    }

    private void getNearbyList(final float dest, final int category, final int order, final int distance, final int city, final int is_no_order, final int is_holiday_can_use) {
        CustomThreadPool.getInstance().excute(new TaskWrapper(new Runnable() {
            @Override
            public void run() {
                try {
                    final NearbyListResponse response = InternetUtils.request(
                                                                                 SubListFragment.this.getActivity().getApplicationContext(), new NearbyListRequest(39.92,
                                                                                                                                                                      116.46,
                                                                                                                                                                      dest,
                                                                                                                                                                      category,
                                                                                                                                                                      order,
                                                                                                                                                                      distance,
                                                                                                                                                                      city,
                                                                                                                                                                      is_no_order,
                                                                                                                                                                      is_holiday_can_use));
                    if (response != null) {
//                    	if(response.result.groupInfo.length != 0){
//                    		groupInfo = response.result.groupInfo
//                    	}
                        groupInfo = response.groupInfo;
                        //GetCategoryList();
                        mHandler.sendEmptyMessage(SET_LIST);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }

}
