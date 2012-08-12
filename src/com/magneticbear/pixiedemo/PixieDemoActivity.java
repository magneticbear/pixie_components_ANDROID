package com.magneticbear.pixiedemo;



import com.magneticbear.pixie.PixieEaseControlAnimator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PixieDemoActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixie_demo);
        
        PixieEaseControlAnimator pixie = (PixieEaseControlAnimator)findViewById(R.id.pixieEaseControlAnimator1);
        pixie.EaseToFrameByScalar(1, 500, PixieEaseControlAnimator.EASE_STYLE_SIN_IN_OUT);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_pixie_demo, menu);
        return true;
    }
}
