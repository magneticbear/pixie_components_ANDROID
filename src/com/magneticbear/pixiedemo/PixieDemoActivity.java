package com.magneticbear.pixiedemo;



import com.magneticbear.pixie.PixieEaseControlAnimator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class PixieDemoActivity extends Activity {

	private PixieEaseControlAnimator pixie;
	private final int ANIM_SPEED = 10;
	private final int ANIM_STYLE = PixieEaseControlAnimator.EASE_STYLE_SIN_IN_OUT;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixie_demo);
        
        // Grab our pixie
        pixie = (PixieEaseControlAnimator)findViewById(R.id.pixieEaseControlAnimator1);
        //pixie.EaseToFrameByScalar(1, 500, PixieEaseControlAnimator.EASE_STYLE_SIN_IN_OUT);
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_pixie_demo, menu);
        return true;
    }
    
    public void onClick_0(View view) {
    	pixie.EaseToFrameByScalar(0.00f,  ANIM_SPEED, ANIM_STYLE);
    }
    public void onClick_15(View view) {
    	pixie.EaseToFrameByScalar(0.15f,  ANIM_SPEED, ANIM_STYLE);
    }
    public void onClick_50(View view) {
    	pixie.EaseToFrameByScalar(0.50f,  ANIM_SPEED, ANIM_STYLE);
    }
    public void onClick_85(View view) {
    	pixie.EaseToFrameByScalar(0.85f,  ANIM_SPEED, ANIM_STYLE);
    }
    public void onClick_100(View view) {
    	pixie.EaseToFrameByScalar(1.00f,  ANIM_SPEED, ANIM_STYLE);
    }
}
