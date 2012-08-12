package com.magneticbear.pixie;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

public class PixieEaseControlAnimator extends PixieAnimator {

	public static final int EASE_STYLE_LINEAR  		      = 0;            
	public static final int EASE_STYLE_SIN_IN  		      = 1;        
	public static final int EASE_STYLE_SIN_OUT 		      = 2;          
	public static final int EASE_STYLE_SIN_IN_OUT 		  = 3;        
	public static final int EASE_STYLE_QUADRATIC_IN  	  = 4;    
	public static final int EASE_STYLE_QUADRATIC_OUT      = 5;  
	public static final int EASE_STYLE_QUADRATIC_IN_OUT   = 6;  
	public static final int EASE_STYLE_CUBIC_IN           = 7;
	public static final int EASE_STYLE_CUBIC_OUT          = 8;
	public static final int EASE_STYLE_CUBIC_IN_OUT       = 9;
	public static final int EASE_STYLE_QUARTIC_IN         = 10;
	public static final int EASE_STYLE_QUARTIC_OUT        = 11;
	public static final int EASE_STYLE_QUARTIC_IN_OUT     = 12;
	public static final int EASE_STYLE_QUINTIC_IN         = 13;
	public static final int EASE_STYLE_QUINTIC_OUT        = 14;
	public static final int EASE_STYLE_QUINTIC_IN_OUT     = 15;
	public static final int EASE_STYLE_EXPONENTIAL_IN     = 16;
	public static final int EASE_STYLE_EXPONENTIAL_OUT    = 17;
	public static final int EASE_STYLE_EXPONENTIAL_IN_OUT = 18;
	public static final int EASE_STYLE_CIRCULAR_IN        = 19;
	public static final int EASE_STYLE_CIRCULAR_OUT       = 20;

	
	public int ease_startFrame;
	public int ease_endFrame;
	public int ease_tick;
	public int ease_tickTo;
	public int ease_style;
	
	public PixieEaseControlAnimator(Context context) {
		super(context);
	}
	public PixieEaseControlAnimator(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public PixieEaseControlAnimator(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
    protected void onDraw(Canvas canvas) {
		// Blit current frame of animation
		canvas.drawBitmap(pixieSheet, drawingSourceRect, drawingDestRect, null);
		
		// We use isPlaying to represent if the animation is currently moving towards a target
		
		
		// Mark invalid for redraw (we use this as a faux-update cycle)
		// 		Note: Normally MVC dictates we separate interests of drawing and updating.
		//			  However the slow nature of mobile means we may receive 5 or 6 true updates on our thread
		//			  before we receive even one draw call. To combat this, animations can only move forward during
		//			  their own draw cycle.
		//		tl;dr: why update an animation if its not being drawn, thats why we update here using invalidate to ensure we get another draw
		invalidate();	
	}
	
	 
}
