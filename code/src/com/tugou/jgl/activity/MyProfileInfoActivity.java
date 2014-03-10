package com.tugou.jgl.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.tugou.jgl.R;
import com.tugou.jgl.utils.SettingManager;

/**
 * Created by michael on 14-3-9.
 */
public class MyProfileInfoActivity extends Activity {

    private TextView mName;
    private TextView mPhone;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.myprofileinfo_activity);

        mName = (TextView) findViewById(R.id.name);
        mPhone = (TextView) findViewById(R.id.phone);

        mName.setText(SettingManager.getInstance().getStringProperty("username"));
        mPhone.setText("已绑定" + SettingManager.getInstance().getStringProperty("phone"));

        View logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingManager.getInstance().setBooleanProperty("login", false);
                finish();
            }
        });
    }

}