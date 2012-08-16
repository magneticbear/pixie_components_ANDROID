package com.magneticbear.pixiedemo;



import com.magneticbear.pixie.PixieCustomDialog;
import com.magneticbear.pixie.PixieEaseControlAnimator;
import com.magneticbear.pixie.PixieMultiColouredUsageIndicator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PixieDemoActivity extends Activity {

	private PixieEaseControlAnimator pixie;
	private final int ANIM_SPEED = 10;
	private final int ANIM_STYLE = PixieEaseControlAnimator.EASE_STYLE_SIN_IN_OUT;
	
	private PixieCustomDialog         menu_custom;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixie_demo);
        
        createMenu();
        
        pixie = (PixieEaseControlAnimator)findViewById(R.id.pixieEaseControlAnimator1);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        showMenu();
        
        // We always suppress the call the says we opened the options menu, so the actual sys menu doesnt get inflated
    	// We should probably have a functioning menu justtttt in case
        return false;
    }
    
    private void createMenu() {
        menu_custom = new PixieCustomDialog(this, R.layout.menu_custom, R.drawable.dark, false, "Durp");
    }
    
    private void hideMenu() {
    	menu_custom.dismiss();
    }
    private void showMenu() {
    	menu_custom.show();
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
