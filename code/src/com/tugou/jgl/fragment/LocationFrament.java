package com.tugou.jgl.fragment;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.*;
import android.widget.*;

import com.plugin.common.view.WebImageView;
import com.tugou.jgl.R;
import com.tugou.jgl.activity.GroupDetailActivity;
import com.tugou.jgl.activity.SubListActivity;
import com.tugou.jgl.utils.AppRuntime;
import com.tugou.jgl.utils.Constant;
import com.tugou.jgl.utils.Debug;

/**
 * Created with IntelliJ IDEA.
 * User: michael
 * Date: 13-8-15
 * Time: PM4:23
 * To change this template use File | Settings | File Templates.
 */
public class LocationFrament extends Fragment {

    private LayoutInflater mInflater;

    private ViewPager mCoverVP;

    private Handler mHandler = new Handler();

    public static final String ACTION_UPDATE = "com.cover.upate";
    private BroadcastReceiver mCoverBRC = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ACTION_UPDATE.equals(intent.getAction())) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCoverVP.setAdapter(new CoverPagerAdapter());
                    }
                });
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(getActivity().getApplicationContext());
        lbm.registerReceiver(mCoverBRC, new IntentFilter(ACTION_UPDATE));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(getActivity().getApplicationContext());
        lbm.unregisterReceiver(mCoverBRC);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mInflater = inflater;
        View ret = inflater.inflate(R.layout.location, null);

        //test
//        ret.findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent();
//                i.setClass(getActivity().getApplicationContext(), SubListActivity.class);
//                getActivity().startActivity(i);
//            }
//        });
        GridView gv = (GridView) ret.findViewById(R.id.category);
        gv.setAdapter(new CategoryAdapter());

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Debug.LOGD("---------------positon = " + position);

                String title = null;
                switch (position) {
                    case 0:
                        title = "新单";
                        break;
                    case 1:
                        title = "美食";
                        break;
                    case 2:
                        title = "娱乐";
                        break;
                    case 3:
                        title = "电影";
                        break;
                    case 4:
                        title = "KTV";
                        break;
                    case 5:
                        title = "洗浴";
                        break;
                    case 6:
                        title = "酒店";
                        break;
                    case 7:
                        title = "积分";
                        break;
                    case 8:
                        title = "手机";
                        break;
                }

                Intent i = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt(SubListActivity.CATEGORY, position);
                bundle.putString(SubListActivity.TITLE, title);
                i.putExtras(bundle);
                i.setClass(getActivity().getApplicationContext(), SubListActivity.class);
                getActivity().startActivity(i);

                AppRuntime.makeCrash();
            }
        });

        initCover(ret);

        return ret;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

    }

    private void initCover(View root) {
        mCoverVP = (ViewPager) root.findViewById(R.id.cover);
        mCoverVP.setAdapter(new CoverPagerAdapter());
    }

    private class CoverPagerAdapter extends PagerAdapter {

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            WebImageView view = (WebImageView) object;
            container.removeView(view);
            view.setImageURI(null);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            WebImageView ret = (WebImageView) mInflater.inflate(R.layout.location_cover, null);

            //test code
//            String url = "http://ww4.sinaimg.cn/bmiddle/6aadc52dgw1e827avbgzyj21kw11x129.jpg";
//            switch (position) {
//                case 0:
//                    url = "http://ww4.sinaimg.cn/bmiddle/989f2751jw1e82awic3imj20m80m840e.jpg";
//                    break;
//                case 1:
//                    url = "http://ww3.sinaimg.cn/bmiddle/61add7e3jw1e827hs67ksj20zk0nsdjc.jpg";
//                    break;
//                case 2:
//                    url = "http://ww4.sinaimg.cn/bmiddle/7d3f0ccdjw1e82krnxvrgj20fa0akmxx.jpg";
//                    break;
//            }
            String url = null;
            String id = null;
            if (AppRuntime.groupInfo != null && AppRuntime.groupInfo.length > (position + 3)) {
                url = AppRuntime.groupInfo[position + 3].cover;
                id = AppRuntime.groupInfo[position + 3].id;
            }

            final String idInfo = id;

            if (!TextUtils.isEmpty(url)) {
                ret.setImageURI(new Uri.Builder().path(Constant.BASE_URL + url).build());
            }

            ret.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), GroupDetailActivity.class);
                    Bundle boundle = new Bundle();
                    boundle.putString("group_id", idInfo);
                    i.putExtras(boundle);
                    startActivity(i);
                }
            });

            container.addView(ret);
            return ret;
        }

        @Override
        public int getCount() {
            if (AppRuntime.groupInfo != null) {
                if (AppRuntime.groupInfo.length > 7) {
                    return 4;
                }
            }

            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
    }

    private class CategoryAdapter extends BaseAdapter {

        private Context context;
        private Integer[] imgs = {
                                     R.drawable.nearby_newbook, R.drawable.nearby_food, R.drawable.nearby_play,
                                     R.drawable.nearby_movie, R.drawable.nearby_ktv, R.drawable.nearby_wash,
                                     R.drawable.nearby_hotel, R.drawable.nearby_rewards, R.drawable.nearby_phone
        };

        private Integer[] names = {
                                      R.string.new_book, R.string.food, R.string.play,
                                      R.string.movie, R.string.ktv, R.string.wash,
                                      R.string.hotel, R.string.rewards, R.string.phone
        };

//        CategoryAdapter(Context context){
//            this.context = context;	        	
//        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return imgs.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.category_grid_item, null);
                holder = new ViewHolder();
                holder.icon = (ImageView) convertView.findViewById(R.id.icon);
                holder.name = (TextView) convertView.findViewById(R.id.name);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.icon.setImageResource(imgs[position]);
            holder.name.setText(names[position]);

            return convertView;
        }

        private class ViewHolder {
            ImageView icon;
            TextView name;
        }

    }
}
