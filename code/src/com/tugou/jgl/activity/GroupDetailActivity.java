package com.tugou.jgl.activity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.plugin.common.utils.CustomThreadPool;
import com.plugin.common.utils.CustomThreadPool.TaskWrapper;
import com.plugin.common.view.WebImageView;
import com.plugin.internet.InternetUtils;
import com.tugou.jgl.R;
import com.tugou.jgl.adapter.GrouponListAdapter;
import com.tugou.jgl.api.GetGoodDetailRequest;
import com.tugou.jgl.api.GetGoodDetailResponse;
import com.tugou.jgl.api.GetGoodDetailResponse.Result;
import com.tugou.jgl.fragment.SubListFragment;
import com.tugou.jgl.test.TestApiListActivity;
import com.tugou.jgl.utils.Constant;
import com.tugou.jgl.utils.Debug;

public class GroupDetailActivity extends BaseActivity {
	private WebImageView imageViewCover;
	private ImageView imageViewFreeTips;
	private TextView tvPrice;
	private TextView tvYuan;
	private Button buttonBuy;
	private TextView tvGroupTitle;
	private TextView tvDescription;
	private ImageView imageViewStart1;
	private ImageView imageViewStart2;
	private ImageView imageViewStart3;
	private ImageView imageViewStart4;
	private ImageView imageViewStart5;
	private TextView tvGrade;
	private TextView tvGradeNum;
	private TextView tvBuyNum;
	private TextView tvTime;
	private TextView tvMerchantName;
	private TextView tvMerchantAddr;
	private TextView tvMerchantDistance;
	private TextView tvSetName;
	private TextView tvSetPrice;
	private TextView tvSetTips;
	private TextView tvDiscussName1;
	private TextView tvDiscussInfo1;
	private TextView tvDiscussName2;
	private TextView tvDiscussInfo2;
	private TextView tvDiscussName3;
	private TextView tvDiscussInfo3;
	
	private String strId;
	private Result result;
	
    private static final int SET_VIEW = 1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SET_VIEW:
                	DrawDetailView();
                	break;
            }
        }
    };
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.groupon_detail);

        mActionbar.setTitle(R.string.group_detail);
        Bundle bundle = this.getIntent().getExtras();
        strId = bundle.getString("group_id");
//        Debug.LOGD("!!!!!!!!!!!!!!lon = " + JuGeiLiActivity.locationData.longitude);
//        Debug.LOGD("!!!!!!!!!!!!!!lat = " + JuGeiLiActivity.locationData.latitude);
        //fragmentUpdate(R.id.sub_fl, new SubListFragment());
        initUI();
        
    }
    
    private void initUI(){
    	imageViewCover = (WebImageView)findViewById(R.id.cover);
    	imageViewFreeTips = (ImageView)findViewById(R.id.free_tips);
    	tvPrice = (TextView)findViewById(R.id.tv_price);
    	tvYuan = (TextView)findViewById(R.id.tv_yuan);
    	buttonBuy = (Button)findViewById(R.id.get_button);
    	tvGroupTitle = (TextView)findViewById(R.id.title);
    	tvDescription = (TextView)findViewById(R.id.description);
    	imageViewStart1 = (ImageView)findViewById(R.id.star_1);
    	imageViewStart2 = (ImageView)findViewById(R.id.star_2);
    	imageViewStart3 = (ImageView)findViewById(R.id.star_3);
    	imageViewStart4 = (ImageView)findViewById(R.id.star_4);
    	imageViewStart5 = (ImageView)findViewById(R.id.star_5);
    	tvGrade = (TextView)findViewById(R.id.tv_grade);
    	tvGradeNum = (TextView)findViewById(R.id.grade_num);
    	tvBuyNum = (TextView)findViewById(R.id.textview_bought_num);
    	tvTime = (TextView)findViewById(R.id.textview_time);
    	tvMerchantName = (TextView)findViewById(R.id.tv_merchant_name);
    	tvMerchantAddr = (TextView)findViewById(R.id.merchant_address);
    	tvMerchantDistance = (TextView)findViewById(R.id.merchant_distance);
    	tvSetName = (TextView)findViewById(R.id.tv_set_name);
    	tvSetPrice = (TextView)findViewById(R.id.tv_set_price);
    	tvSetTips = (TextView)findViewById(R.id.tv_set_tips);
    	tvDiscussName1 = (TextView)findViewById(R.id.discuss_name_1);
    	tvDiscussInfo1 = (TextView)findViewById(R.id.discuss_info_1);
    	tvDiscussName2 = (TextView)findViewById(R.id.discuss_name_2);
    	tvDiscussInfo2 = (TextView)findViewById(R.id.discuss_info_2);
    	tvDiscussName3 = (TextView)findViewById(R.id.discuss_name_3);
    	tvDiscussInfo3 = (TextView)findViewById(R.id.discuss_info_3);
    	
    	GetGoodDetail(Integer.valueOf(this.strId));
    }
    
    private void GetGoodDetail(final int id) {
        CustomThreadPool.getInstance().excute(new TaskWrapper(new Runnable() {
            @Override
            public void run() {
                try {
					final GetGoodDetailResponse response = InternetUtils.request( 
							GroupDetailActivity.this.getApplicationContext(), new GetGoodDetailRequest(id));
					if (response != null) {
						//if()
						result = response.result;
						mHandler.sendEmptyMessage(SET_VIEW);
                    }else{
                    	
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }
    
    private void DrawDetailView(){
    	Debug.LOGD("!!!!!!!!!!!!!!!" + Constant.BASE_URL + result.cover);
		imageViewCover.setImageURI(new Uri.Builder()
	        .path(Constant.BASE_URL + result.cover).build());
		
		tvGroupTitle.setText(result.name);
		tvPrice.setText(result.price);
		tvDescription.setText(result.description);
		tvGrade.setText(result.grade);
		double grade = Double.valueOf(result.grade);
		drawStart(grade);
		tvBuyNum.setText(result.num_bought);
		tvTime.setText(result.left_time);
		tvMerchantName.setText(result.merchant_name);
		tvMerchantAddr.setText(result.merchant_addr);
		//tvMerchantDistance.setText(result.);
		tvSetName.setText(result.name);
		tvSetPrice.setText(result.price);
		tvSetTips.setText(result.purchase_notes);
		//tvMerchantAddr.setText(result.merchant_addr);
		
    }
    
    private void drawStart(double grade){
    	if((grade >= 1.0) && (grade < 2.0)){
    		//imageViewStart1.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.back_icon));
    		imageViewStart1.setBackground(this.getResources().getDrawable(R.drawable.detail_star_ungray));
    	}else if((grade >= 2.0) && (grade < 3.0)){
    		//imageViewStart1.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.back_icon));
    		imageViewStart1.setBackground(this.getResources().getDrawable(R.drawable.detail_star_ungray));
    		imageViewStart2.setBackground(this.getResources().getDrawable(R.drawable.detail_star_ungray));
    	}else if((grade >= 3.0) && (grade < 4.0)){
    		//imageViewStart1.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.back_icon));
    		imageViewStart1.setBackground(this.getResources().getDrawable(R.drawable.detail_star_ungray));
    		imageViewStart2.setBackground(this.getResources().getDrawable(R.drawable.detail_star_ungray));
    		imageViewStart3.setBackground(this.getResources().getDrawable(R.drawable.detail_star_ungray));
    	}else if((grade >= 4.0) && (grade < 5.0)){
    		//imageViewStart1.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.back_icon));
    		imageViewStart1.setBackground(this.getResources().getDrawable(R.drawable.detail_star_ungray));
    		imageViewStart2.setBackground(this.getResources().getDrawable(R.drawable.detail_star_ungray));
    		imageViewStart3.setBackground(this.getResources().getDrawable(R.drawable.detail_star_ungray));
    		imageViewStart4.setBackground(this.getResources().getDrawable(R.drawable.detail_star_ungray));
    	}else if((grade >= 5.0)){
    		//imageViewStart1.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.back_icon));
    		imageViewStart1.setBackground(this.getResources().getDrawable(R.drawable.detail_star_ungray));
    		imageViewStart2.setBackground(this.getResources().getDrawable(R.drawable.detail_star_ungray));
    		imageViewStart3.setBackground(this.getResources().getDrawable(R.drawable.detail_star_ungray));
    		imageViewStart4.setBackground(this.getResources().getDrawable(R.drawable.detail_star_ungray));
    		imageViewStart5.setBackground(this.getResources().getDrawable(R.drawable.detail_star_ungray));
    	}
    }
}
