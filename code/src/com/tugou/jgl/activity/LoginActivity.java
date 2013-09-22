package com.tugou.jgl.activity;

import android.os.Bundle;
import android.view.Menu;

import com.tugou.jgl.R;

public class LoginActivity extends BaseActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ju_gei_li, menu);
        return true;
    }
}
