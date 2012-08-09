package com.magneticbear.pixiedemo;

import com.magneticbear.pixie.PixieComponent;
import com.magneticbear.pixie.PixieHeader;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

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
    
    public void onClick_debug_button(View v) {
    	// Load the resource
        Bitmap demobmp = BitmapFactory.decodeResource(getResources(), R.drawable.demo_anim);
    	
        // Build a pixie component
        PixieComponent px = new PixieComponent(getBaseContext(), demobmp);
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        this.addContentView(px, lp);
        
        String output = px.header.getHumanReadableData();        
        TextView debug_out = (TextView)findViewById(R.id.debug_output_textview);
        debug_out.setText(output);
    }
}
