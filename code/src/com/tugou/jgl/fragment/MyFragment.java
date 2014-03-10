package com.tugou.jgl.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.plugin.common.utils.CustomThreadPool;
import com.plugin.internet.InternetUtils;
import com.tugou.jgl.R;
import com.tugou.jgl.activity.GeiLiJuanActivity;
import com.tugou.jgl.activity.MyProfileInfoActivity;
import com.tugou.jgl.api.GetUserInfoRequest;
import com.tugou.jgl.api.GetUserInfoResponse;
import com.tugou.jgl.utils.SettingManager;


public class MyFragment extends Fragment {

    private TextView nickname;
    private TextView balance_value;

    private int mGeilijuanCount;

    private Handler mHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View ret = inflater.inflate(R.layout.my, null);

        nickname = (TextView) ret.findViewById(R.id.nickname);
        balance_value = (TextView) ret.findViewById(R.id.balance_value);

        init(ret);

        return ret;
    }

    @Override
    public void onStart() {
        super.onStart();

        getUserInfo();
    }

    private void init(View root) {
        View my_geilijuan = root.findViewById(R.id.my_geilijuan);
        my_geilijuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getActivity().getApplicationContext(), GeiLiJuanActivity.class);
                i.putExtra(GeiLiJuanActivity.KEY_GEILIJUAN, mGeilijuanCount);
                startActivity(i);
            }
        });

        View myinfo = root.findViewById(R.id.myinfo);
        myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getActivity().getApplicationContext(), MyProfileInfoActivity.class);
                startActivity(i);
            }
        });
    }

    private void getUserInfo() {
        CustomThreadPool.getInstance().excute(new CustomThreadPool.TaskWrapper(new Runnable() {
            @Override
            public void run() {
                try {
                    GetUserInfoRequest request = new GetUserInfoRequest(SettingManager.getInstance().getIntProperty("user_id"));

                    final GetUserInfoResponse response = InternetUtils.request(getActivity().getApplicationContext(), request);
                    if (response != null && response.result != null) {
                        SettingManager.getInstance().setStringProperty("username", response.result.userName);
                        SettingManager.getInstance().setStringProperty("phone", response.result.mobile);

                        mGeilijuanCount = response.result.groupon_ticket_num;
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                nickname.setText(response.result.userName);
                                balance_value.setText(String.valueOf(response.result.balance));
                            }
                        });
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "登录失败", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }

}
