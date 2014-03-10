package com.tugou.jgl.activity;

import android.os.Bundle;
import com.tugou.jgl.R;
import com.tugou.jgl.fragment.SubListFragment;

/**
 * Created with IntelliJ IDEA.
 * User: michael
 * Date: 13-8-15
 * Time: PM2:47
 * To change this template use File | Settings | File Templates.
 */
public class SubListActivity extends BaseActivity {

    public static final String TITLE = "title";

    public static final String CATEGORY = "category";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.sub_list_activity);

        String title = getIntent().getStringExtra(TITLE);
        int category = getIntent().getIntExtra(CATEGORY, 0);

        mActionbar.setTitle(title);

        fragmentUpdate(R.id.sub_fl, new SubListFragment(title, category));
    }

}