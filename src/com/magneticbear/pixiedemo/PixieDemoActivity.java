package com.magneticbear.pixiedemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PixieDemoActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixie_demo);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_pixie_demo, menu);
        return true;
    }
}
