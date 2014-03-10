package com.tugou.jgl.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import com.plugin.common.utils.CustomThreadPool;
import com.plugin.common.utils.CustomThreadPool.TaskWrapper;
import com.plugin.internet.InternetUtils;
import com.tugou.jgl.R;
import com.tugou.jgl.activity.BaseActivity;
import com.tugou.jgl.api.*;
import com.tugou.jgl.utils.Constant;

public class TestApiListActivity extends BaseActivity {
	
	private ListView listViewTest;
	private LayoutInflater mInflater;
	private TestItemAdapter testItemAdapter;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_list);
        mInflater = getLayoutInflater();
        
        listViewTest = (ListView)findViewById(R.id.list);
        testItemAdapter = new TestItemAdapter();
        listViewTest.setAdapter(testItemAdapter);
        
        listViewTest.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
	        	switch(position){
	        	case TestConstant.GetCategoryListRequest: 
	        		GetCategoryList();
	        		break;
	        	case TestConstant.GetListGroupRequest:
	        		getListGroup();
	        		break;
	        	case TestConstant.LoginRequest:
	        		//Login("user", StringUtils.MD5Encode("123456"));
	        		Login("user", "123456");
	        		break;
	        	case TestConstant.NearbyListRequest:
	        		getNearbyList(100, 0, 0, 1, 0, 0, 0);
	        		break;
	        	case TestConstant.GetAreaListRequest:
	        		getAreaList(0, 1);	
	        		break;
	        	case TestConstant.GetAllCityListRequest:
	        		getCityAllList();	
	        		break;	
	        	case TestConstant.GetCityListRequest:
	        		getCityList(18);	
	        		break;	
	        	case TestConstant.searchGoodsRequest:
	        		SearchGoods("a");	
	        		break;
	        	case TestConstant.GetGoodDetailRequest:
	        		GetGoodDetail(94);	
	        		break;
	        	case TestConstant.GetRelativeGroupListRequest:
	        		GetRelativeGroupList(94);
	        		break;
	        	case TestConstant.GetCommentsRequest:
	        		getCommentList();
	        		break;
	        	case TestConstant.GetUserInfo:
	        		getUserInfo();
	        		break;
	        	case TestConstant.GetOrders:
	        		getOrders();
	        		break;
	        	case TestConstant.GetGroupbond:
	        		getGroupbond();
	        		break;
	        	default:
	        		break;
	        	}
				
			}
        	
        });
    }
	
    private class TestItemAdapter extends BaseAdapter {
        @Override
        public int getCount() {// total num
            return 100;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.test_list_item, null);
                holder = new ViewHolder();
                holder.textViewReq = (TextView) convertView.findViewById(R.id.req_url);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            
            setApiList(holder, position);
            return convertView;
        }
        
        private void setApiList(ViewHolder holder, int position){
        	switch(position){
        	case TestConstant.GetCategoryListRequest: 
        		holder.textViewReq.setText("GetCategoryListRequest");
        		break;
        	case TestConstant.GetListGroupRequest:
        		holder.textViewReq.setText("GetListGroupRequest");
        		break;
        	case TestConstant.LoginRequest:
        		holder.textViewReq.setText("LoginRequest");
        		break;
        	case TestConstant.NearbyListRequest:
        		holder.textViewReq.setText("NearbyListRequest");
        		break;
        	case TestConstant.GetAreaListRequest:
        		holder.textViewReq.setText("GetAreaListRequest");
        		break;
        	case TestConstant.GetAllCityListRequest:
        		holder.textViewReq.setText("GetAllCityListRequest");
        		break;  		
        	case TestConstant.GetCityListRequest:
        		holder.textViewReq.setText("GetCityListRequest");
        		break;       		
        	case TestConstant.searchGoodsRequest:
        		holder.textViewReq.setText("searchGoodsRequest");
        		break;
        	case TestConstant.GetGoodDetailRequest:
        		holder.textViewReq.setText("GetGoodDetailRequest");
        		break;
        	case TestConstant.GetRelativeGroupListRequest:
        		holder.textViewReq.setText("GetRelativeGroupListRequest");
        		break;
        	case TestConstant.GetCommentsRequest:
        		holder.textViewReq.setText("GetCommentsRequest");
        		break;
        	case TestConstant.GetUserInfo:
        		holder.textViewReq.setText("GetUserInfo");
        		break;
        	case TestConstant.GetOrders:
        		holder.textViewReq.setText("GetOrders");
        		break;
        	case TestConstant.GetGroupbond:
        		holder.textViewReq.setText("GetGroupbond");
        		break;
        	default:
        		break;
        	}
        }
        
        private class ViewHolder {
            TextView textViewReq;
        }
    }
    
    private void ToTextView(final String response){
    	runOnUiThread(new Runnable() {
    	public void run() {
    	Intent i = new Intent(TestApiListActivity.this, TestRespActivity.class);
    	Bundle bundle = new Bundle();
    	bundle.putString("response", response);
    	i.putExtras(bundle);
        startActivity(i);
    	}
    	});
    }
    
    private void GetCategoryList() {
        CustomThreadPool.getInstance().excute(new TaskWrapper(new Runnable() {
            @Override
            public void run() {
                try {
					final GetCategoryListResponse response = InternetUtils.request( 
							TestApiListActivity.this.getApplicationContext(), new GetCategoryListRequest(0, 
									1));
                    if (response != null) {
                    	ToTextView(response.toString());
                    }else{
                    	ToTextView("null");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }
    
    private void getListGroup() {
        CustomThreadPool.getInstance().excute(new TaskWrapper(new Runnable() {
            @Override
            public void run() {
                try {
					final GetListGroupResponse response = InternetUtils.request( 
							TestApiListActivity.this.getApplicationContext(), new GetListGroupRequest(Constant.CATEGORY_ALL, 
									Constant.AREA_ALL, 
									Constant.ORDER_DEFAULT, 
									Constant.CITY_SHANGHAI, 
									Constant.IS_NO_ORDER_CLOSE, 
									Constant.IS_HOLIDAY_CAN_USE_CLOSE));
                    if (response != null) {
                    	ToTextView(response.toString());
                    }else{
                    	ToTextView("null");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }
    
	private void Login(final String email, final String pwd) {
        CustomThreadPool.getInstance().excute(new TaskWrapper(new Runnable() {
            @Override
            public void run() {
                try {                	
					final LoginResponse response = InternetUtils.request( 
							TestApiListActivity.this.getApplicationContext(), new LoginRequest(pwd, email));//versionCode));
                    if (response != null) {
                    	ToTextView(response.toString());
                    }else{
                    	ToTextView("null");
                    }
                } catch (Exception e) {
                	runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        	Toast.makeText(TestApiListActivity.this.getApplicationContext(), getString(R.string.network_error_tips),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                    e.printStackTrace();
                }
            }
        }));
    }
	
    private void getNearbyList(final float dest, final int category, final int order, final int distance, final int city, final int is_no_order, final int is_holiday_can_use) {
        CustomThreadPool.getInstance().excute(new TaskWrapper(new Runnable() {
            @Override
            public void run() {
                try {
					final NearbyListResponse response = InternetUtils.request( 
							TestApiListActivity.this.getApplicationContext(), new NearbyListRequest(39.92, 
									116.46, 
									dest, 
									category, 
									order,
                                    distance,
									city,
									is_no_order,
									is_holiday_can_use));
					if (response != null) {
                    	ToTextView(response.toString());
                    }else{
                    	ToTextView("null");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }
    
    private void getAreaList(final int area, final int city) {
        CustomThreadPool.getInstance().excute(new TaskWrapper(new Runnable() {
            @Override
            public void run() {
                try {
					final GetAreaListResponse response = InternetUtils.request( 
							TestApiListActivity.this.getApplicationContext(), new GetAreaListRequest(area, city));
					if (response != null) {
                    	ToTextView(response.toString());
                    }else{
                    	ToTextView("null");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }
    
    private void getCityAllList() {
        CustomThreadPool.getInstance().excute(new TaskWrapper(new Runnable() {
            @Override
            public void run() {
                try {
					final GetCityListResponse response = InternetUtils.request( 
							TestApiListActivity.this.getApplicationContext(), new GetCityListRequest());
					if (response != null) {
                    	ToTextView(response.toString());
                    }else{
                    	ToTextView("null");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }
    
    private void getCityList(final int city) {
        CustomThreadPool.getInstance().excute(new TaskWrapper(new Runnable() {
            @Override
            public void run() {
                try {
					final GetCityListResponse response = InternetUtils.request( 
							TestApiListActivity.this.getApplicationContext(), new GetCityListRequest(city));
					if (response != null) {
                    	ToTextView(response.toString());
                    }else{
                    	ToTextView("null");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }
    
    private void SearchGoods(final String keyword) {
        CustomThreadPool.getInstance().excute(new TaskWrapper(new Runnable() {
            @Override
            public void run() {
                try {
					final SearchGoodsResponse response = InternetUtils.request( 
							TestApiListActivity.this.getApplicationContext(), new SearchGoodsRequest(keyword));
					if (response != null) {
                    	ToTextView(response.toString());
                    }else{
                    	ToTextView("null");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }
    
    private void GetGoodDetail(final int id) {
        CustomThreadPool.getInstance().excute(new TaskWrapper(new Runnable() {
            @Override
            public void run() {
                try {
					final GetGoodDetailResponse response = InternetUtils.request( 
							TestApiListActivity.this.getApplicationContext(), new GetGoodDetailRequest(id));
					if (response != null) {
                    	ToTextView(response.toString());
                    }else{
                    	ToTextView("null");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }
    
    private void GetRelativeGroupList(final int groupId) {
        CustomThreadPool.getInstance().excute(new TaskWrapper(new Runnable() {
            @Override
            public void run() {
                try {
					final GetRelativeGroupListResponse response = InternetUtils.request( 
							TestApiListActivity.this.getApplicationContext(), new GetRelativeGroupListRequest(groupId));
					if (response != null) {
                    	ToTextView(response.toString());
                    }else{
                    	ToTextView("null");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }
    
    private void getCommentList() {
        CustomThreadPool.getInstance().excute(new TaskWrapper(new Runnable() {
            @Override
            public void run() {
                try {
					final getCommentsResponse response = InternetUtils.request( 
							TestApiListActivity.this.getApplicationContext(), new getCommentsRequest(94, 1));
                    if (response != null) {
                    	ToTextView(response.toString());
                    }else{
                    	ToTextView("null");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }
    
    private void getUserInfo() {
        CustomThreadPool.getInstance().excute(new TaskWrapper(new Runnable() {
            @Override
            public void run() {
                try {
					final GetUserInfoResponse response = InternetUtils.request( 
							TestApiListActivity.this.getApplicationContext(), new GetUserInfoRequest(52));
                    if (response != null) {
                    	ToTextView(response.toString());
                    }else{
                    	ToTextView("null");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }
    
    private void getOrders() {
        CustomThreadPool.getInstance().excute(new TaskWrapper(new Runnable() {
            @Override
            public void run() {
                try {
					final GetOrdersResponse response = InternetUtils.request( 
							TestApiListActivity.this.getApplicationContext(), new GetOrdersRequest(1, 1));
                    if (response != null) {
                    	ToTextView(response.toString());
                    }else{
                    	ToTextView("null");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }
    
    private void getGroupbond() {
        CustomThreadPool.getInstance().excute(new TaskWrapper(new Runnable() {
            @Override
            public void run() {
                try {
					final GetGroupbondResponse response = InternetUtils.request( 
							TestApiListActivity.this.getApplicationContext(), new GetGroupbondRequest(1, 1));
                    if (response != null) {
                    	ToTextView(response.toString());
                    }else{
                    	ToTextView("null");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }
}
