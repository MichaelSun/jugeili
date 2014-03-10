package com.tugou.jgl.activity;

import android.os.Bundle;
import android.widget.TextView;
import com.tugou.jgl.R;

/**
 * Created by michael on 14-3-9.
 */
public class GeiLiJuanActivity extends BaseActivity {

    public static final String KEY_GEILIJUAN = "geilijuan";

    private TextView mEmptyTips;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.geilijuan_activity);

        mEmptyTips = (TextView) findViewById(R.id.empty_tips);

        int count = getIntent().getIntExtra(KEY_GEILIJUAN, 0);
        if (count == 0) {
            mEmptyTips.setText("当前没有给力卷");
        }

        mActionbar.setTitle("我个给力卷");
    }

}