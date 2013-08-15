package com.tugou.jgl.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

/**
 * Created with IntelliJ IDEA.
 * User: michael
 * Date: 13-8-15
 * Time: PM3:22
 * To change this template use File | Settings | File Templates.
 */
public class BaseActivity extends Activity {

    protected ActionBar mActionbar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    protected void init() {
        mActionbar = getActionBar();
        mActionbar.setDisplayHomeAsUpEnabled(true);
        mActionbar.setDisplayShowTitleEnabled(true);
        mActionbar.setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item != null) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    finish();
                    return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}