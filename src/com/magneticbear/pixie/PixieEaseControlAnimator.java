package com.magneticbear.pixie;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
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

	public boolean isEasing;
	public int 	   ease_startFrame;
	public int 	   ease_endFrame;
	public int 	   ease_tick;
	public int 	   ease_tickTo;
	public int 	   ease_style;
	
	public boolean isFading;
	public Paint   paint;
	public int 	   alpha_ease_target;
	public int 	   alpha_ease_start;
	public int 	   alpha_ease_tick;
	public int 	   alpha_ease_tickTo;
	public int     alpha_ease_style;

	public PixieEaseControlAnimator(Context context) {
		super(context);
		easerInit();
	}
	public PixieEaseControlAnimator(Context context, AttributeSet attrs) {
		super(context, attrs);
		easerInit();
	}
	public PixieEaseControlAnimator(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		easerInit();
	}
	private void easerInit() {
		isEasing = false;
		isFading = false;
		
		// Setup the paint object (used for alpha)
		alpha_ease_target = 255;
		paint = new Paint();
		paint.setAlpha(alpha_ease_target);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// Blit current frame of animation
		canvas.drawBitmap(pixieSheet, drawingSourceRect, drawingDestRect, paint);

		// We only update alpha if we are fading
		if(isFading) {
			// Create alpha target holder
			int alpha_target;
			
			// Calculate delta
			int alpha_delta = alpha_ease_target - alpha_ease_start;
			
			// Update fade
			switch(ease_style) {
			case EASE_STYLE_CIRCULAR_IN:
				alpha_target = (int)PixieEasers.CIRCULAR_IN(alpha_ease_tick, alpha_ease_tickTo, alpha_ease_start, alpha_delta);
				break;
			case EASE_STYLE_CIRCULAR_OUT:
				alpha_target = (int)PixieEasers.CIRCULAR_OUT(alpha_ease_tick, alpha_ease_tickTo, alpha_ease_start, alpha_delta);
				break;
			case EASE_STYLE_CUBIC_IN:
				alpha_target = (int)PixieEasers.CUBIC_IN(alpha_ease_tick, alpha_ease_tickTo, alpha_ease_start, alpha_delta);
				break;
			case EASE_STYLE_CUBIC_IN_OUT:
				alpha_target = (int)PixieEasers.CUBIC_IN_OUT(alpha_ease_tick, alpha_ease_tickTo, alpha_ease_start, alpha_delta);
				break;
			case EASE_STYLE_CUBIC_OUT:
				alpha_target = (int)PixieEasers.CUBIC_OUT(alpha_ease_tick, alpha_ease_tickTo, alpha_ease_start, alpha_delta);
				break;
			case EASE_STYLE_EXPONENTIAL_IN:
				alpha_target = (int)PixieEasers.EXPONENTIAL_IN(alpha_ease_tick, alpha_ease_tickTo, alpha_ease_start, alpha_delta);
				break;
			case EASE_STYLE_EXPONENTIAL_IN_OUT:
				alpha_target = (int)PixieEasers.EXPONENTIAL_IN_OUT(alpha_ease_tick, alpha_ease_tickTo, alpha_ease_start, alpha_delta);
				break;
			case EASE_STYLE_EXPONENTIAL_OUT:
				alpha_target = (int)PixieEasers.EXPONENTIAL_OUT(alpha_ease_tick, alpha_ease_tickTo, alpha_ease_start, alpha_delta);
				break;
			case EASE_STYLE_LINEAR:
				alpha_target = (int)PixieEasers.LINEAR(alpha_ease_tick, alpha_ease_tickTo, alpha_ease_start, alpha_delta);
				break;
			case EASE_STYLE_QUADRATIC_IN:
				alpha_target = (int)PixieEasers.QUADRATIC_IN(alpha_ease_tick, alpha_ease_tickTo, alpha_ease_start, alpha_delta);
				break;
			case EASE_STYLE_QUADRATIC_IN_OUT:
				alpha_target = (int)PixieEasers.QUADRATIC_IN_OUT(alpha_ease_tick, alpha_ease_tickTo, alpha_ease_start, alpha_delta);
				break;
			case EASE_STYLE_QUADRATIC_OUT:
				alpha_target = (int)PixieEasers.QUADRATIC_OUT(alpha_ease_tick, alpha_ease_tickTo, alpha_ease_start, alpha_delta);
				break;
			case EASE_STYLE_QUARTIC_IN:
				alpha_target = (int)PixieEasers.QUARTIC_IN(alpha_ease_tick, alpha_ease_tickTo, alpha_ease_start, alpha_delta);
				break;
			case EASE_STYLE_QUARTIC_IN_OUT:
				alpha_target = (int)PixieEasers.QUARTIC_IN_OUT(alpha_ease_tick, alpha_ease_tickTo, alpha_ease_start, alpha_delta);
				break;
			case EASE_STYLE_QUARTIC_OUT:
				alpha_target = (int)PixieEasers.QUARTIC_OUT(alpha_ease_tick, alpha_ease_tickTo, alpha_ease_start, alpha_delta);
				break;
			case EASE_STYLE_QUINTIC_IN:
				alpha_target = (int)PixieEasers.QUINTIC_IN(alpha_ease_tick, alpha_ease_tickTo, alpha_ease_start, alpha_delta);
				break;
			case EASE_STYLE_QUINTIC_IN_OUT:
				alpha_target = (int)PixieEasers.QUINTIC_IN_OUT(alpha_ease_tick, alpha_ease_tickTo, alpha_ease_start, alpha_delta);
				break;
			case EASE_STYLE_QUINTIC_OUT:
				alpha_target = (int)PixieEasers.QUINTIC_OUT(alpha_ease_tick, alpha_ease_tickTo, alpha_ease_start, alpha_delta);
				break;
			case EASE_STYLE_SIN_IN:
				alpha_target = (int)PixieEasers.SIN_IN(alpha_ease_tick, alpha_ease_tickTo, alpha_ease_start, alpha_delta);
				break;
			case EASE_STYLE_SIN_IN_OUT:
				alpha_target = (int)PixieEasers.SIN_IN_OUT(alpha_ease_tick, alpha_ease_tickTo, alpha_ease_start, alpha_delta);
				break;
			case EASE_STYLE_SIN_OUT:
				alpha_target = (int)PixieEasers.SIN_OUT(alpha_ease_tick, alpha_ease_tickTo, alpha_ease_start, alpha_delta);
				break;
			default:
				throw new Error("Trying to do an ease in a PixieEaseControlAnimator with an unknown ease style, please use the constants provided in the class to choose an ease style (:");
			}

			// Set alpha
 			this.paint.setAlpha(alpha_target);

			// Update tick
			alpha_ease_tick++;

			// Check for completion
			if(alpha_ease_tick > alpha_ease_tickTo) {
				// Complete!
				isFading = false;
			}
		}
		
		// We only update the frame number if we are easing
		if(isEasing) {

			// Create a variable to hold the frame we should be on
			int newFrame = currentFrame;

			// Calculate frame delta
			int frameDelta = ease_endFrame - ease_startFrame;

			// Update ease
			switch(ease_style) {
			case EASE_STYLE_CIRCULAR_IN:
				newFrame = (int)PixieEasers.CIRCULAR_IN(ease_tick, ease_tickTo, ease_startFrame, frameDelta);
				break;
			case EASE_STYLE_CIRCULAR_OUT:
				newFrame = (int)PixieEasers.CIRCULAR_OUT(ease_tick, ease_tickTo, ease_startFrame, frameDelta);
				break;
			case EASE_STYLE_CUBIC_IN:
				newFrame = (int)PixieEasers.CUBIC_IN(ease_tick, ease_tickTo, ease_startFrame, frameDelta);
				break;
			case EASE_STYLE_CUBIC_IN_OUT:
				newFrame = (int)PixieEasers.CUBIC_IN_OUT(ease_tick, ease_tickTo, ease_startFrame, frameDelta);
				break;
			case EASE_STYLE_CUBIC_OUT:
				newFrame = (int)PixieEasers.CUBIC_OUT(ease_tick, ease_tickTo, ease_startFrame, frameDelta);
				break;
			case EASE_STYLE_EXPONENTIAL_IN:
				newFrame = (int)PixieEasers.EXPONENTIAL_IN(ease_tick, ease_tickTo, ease_startFrame, frameDelta);
				break;
			case EASE_STYLE_EXPONENTIAL_IN_OUT:
				newFrame = (int)PixieEasers.EXPONENTIAL_IN_OUT(ease_tick, ease_tickTo, ease_startFrame, frameDelta);
				break;
			case EASE_STYLE_EXPONENTIAL_OUT:
				newFrame = (int)PixieEasers.EXPONENTIAL_OUT(ease_tick, ease_tickTo, ease_startFrame, frameDelta);
				break;
			case EASE_STYLE_LINEAR:
				newFrame = (int)PixieEasers.LINEAR(ease_tick, ease_tickTo, ease_startFrame, frameDelta);
				break;
			case EASE_STYLE_QUADRATIC_IN:
				newFrame = (int)PixieEasers.QUADRATIC_IN(ease_tick, ease_tickTo, ease_startFrame, frameDelta);
				break;
			case EASE_STYLE_QUADRATIC_IN_OUT:
				newFrame = (int)PixieEasers.QUADRATIC_IN_OUT(ease_tick, ease_tickTo, ease_startFrame, frameDelta);
				break;
			case EASE_STYLE_QUADRATIC_OUT:
				newFrame = (int)PixieEasers.QUADRATIC_OUT(ease_tick, ease_tickTo, ease_startFrame, frameDelta);
				break;
			case EASE_STYLE_QUARTIC_IN:
				newFrame = (int)PixieEasers.QUARTIC_IN(ease_tick, ease_tickTo, ease_startFrame, frameDelta);
				break;
			case EASE_STYLE_QUARTIC_IN_OUT:
				newFrame = (int)PixieEasers.QUARTIC_IN_OUT(ease_tick, ease_tickTo, ease_startFrame, frameDelta);
				break;
			case EASE_STYLE_QUARTIC_OUT:
				newFrame = (int)PixieEasers.QUARTIC_OUT(ease_tick, ease_tickTo, ease_startFrame, frameDelta);
				break;
			case EASE_STYLE_QUINTIC_IN:
				newFrame = (int)PixieEasers.QUINTIC_IN(ease_tick, ease_tickTo, ease_startFrame, frameDelta);
				break;
			case EASE_STYLE_QUINTIC_IN_OUT:
				newFrame = (int)PixieEasers.QUINTIC_IN_OUT(ease_tick, ease_tickTo, ease_startFrame, frameDelta);
				break;
			case EASE_STYLE_QUINTIC_OUT:
				newFrame = (int)PixieEasers.QUINTIC_OUT(ease_tick, ease_tickTo, ease_startFrame, frameDelta);
				break;
			case EASE_STYLE_SIN_IN:
				newFrame = (int)PixieEasers.SIN_IN(ease_tick, ease_tickTo, ease_startFrame, frameDelta);
				break;
			case EASE_STYLE_SIN_IN_OUT:
				newFrame = (int)PixieEasers.SIN_IN_OUT(ease_tick, ease_tickTo, ease_startFrame, frameDelta);
				break;
			case EASE_STYLE_SIN_OUT:
				newFrame = (int)PixieEasers.SIN_OUT(ease_tick, ease_tickTo, ease_startFrame, frameDelta);
				break;
			default:
				throw new Error("Trying to do an ease in a PixieEaseControlAnimator with an unknown ease style, please use the constants provided in the class to choose an ease style (:");
			}

			// Check if we need a frame change (optimization)
			if(currentFrame != newFrame) {
				// We need a frame change, make the change
				setSourceRectToFrame(newFrame);
			}

			// Update tick
			ease_tick++;

			// Check for completion
			if(ease_tick > ease_tickTo) {
				// Complete!
				isEasing = false;
			}
		}

		// Mark invalid for redraw (we use this as a faux-update cycle)
		// 		Note: Normally MVC dictates we separate interests of drawing and updating.
		//			  However the slow nature of mobile means we may receive 5 or 6 true updates on our thread
		//			  before we receive even one draw call. To combat this, animations can only move forward during
		//			  their own draw cycle.
		//		tl;dr: why update an animation if its not being drawn, thats why we update here using invalidate to ensure we get another draw
		invalidate();	
	}

	public void EaseToAlpha(int AlphaTarget, int EaseAlphaOverThisManyTicks, int EaseStyle) {
		// Set up fade params
		alpha_ease_target = AlphaTarget;
		alpha_ease_start  = paint.getAlpha();
		alpha_ease_tick   = 0;
		alpha_ease_tickTo = EaseAlphaOverThisManyTicks;
		alpha_ease_style  = EaseStyle;
		
		// Flag fade as go
		isFading 	      = true;
	}
	public void EaseToFrameByIndex(int FrameIndex, int EaseOverThisManyTicks, int EaseStyle) {
		// Set up ease params
		ease_tick   	= 0;
		ease_tickTo 	= EaseOverThisManyTicks;
		ease_startFrame = currentFrame;
		ease_endFrame   = FrameIndex;
		ease_style      = EaseStyle;
		
		// Flag ease as go
		isEasing 		= true;
	}
	public void EaseToFrameByScalar(float Scalar, int EaseOverThisManyTicks, int EaseStyle) {
		// Wtf is a scalar? A percent value without unit!
		// This value must be inclusively top and bottom between 0 and 1
		if(Scalar >= 0 && Scalar <= 1) {
			// Yay good values, no one dies today (:

			// So lets figure out what frame that is
			int frameIndexByScalar = (int)((float)(header.info_FrameCountTotal - 1) * Scalar);

			// Go there!
			EaseToFrameByIndex(frameIndexByScalar, EaseOverThisManyTicks, EaseStyle);

			// Peace
			return;
		}
		else{
			// You can't go to 110% or -20% or NaN% or inf% or "my string"%
			// If you did that, this error will catch you like a safety net,
			// like a safety net made of electric eels telling you to go fix
			// your shit.
			throw new Error("Scalar in EaseToFrameByScalar must be (inclusively) between 0 and 1.");
		}
	}
}
