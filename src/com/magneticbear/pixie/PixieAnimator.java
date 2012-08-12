package com.magneticbear.pixie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class PixieAnimator extends ImageView {

	public PixieHeader header;
	public Bitmap 	   pixieSheet;
	public int 		   currentFrame;
	public int 		   currentFrameTick;
	public int 		   currentFrameTickTo;
	public Rect 	   drawingSourceRect;
	public Rect 	   drawingDestRect;
	
	public boolean 	   isPlaying;
	
	public PixieAnimator(Context context) {
		super(context);
		init();
	}
	public PixieAnimator(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	public PixieAnimator(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	private void init() {
		// Convert to bitmap for easy access
		pixieSheet = ((BitmapDrawable)this.getDrawable()).getBitmap();
		// Read header data
		header = PixieHeader.ReadHeaderFromBitmap(pixieSheet);
		
		// Initialize rects so we don't create them every frame (save allocations)
		drawingSourceRect = new Rect();
		drawingDestRect   = new Rect();
		
		// Setup frame data
		currentFrame       = 0;
		currentFrameTick   = 0;
		currentFrameTickTo = header.info_FrameTickTo;
		
		// Set source rect to initial frame
		setSourceRectToCurrentFrame();
		
		// Set to initially playing
		isPlaying = true;
	}
	
	@Override
	protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
		// CONTRACT: When overriding this method, you must call setMeasuredDimension(int, int) 
		// 			 to store the measured width and height of this view. Failure to do so will 
		//			 trigger an IllegalStateException, thrown by measure(int, int). Calling the 
		//			 superclass' onMeasure(int, int) is a valid use. 
		
		// A MeasureSpec can be in one of three modes:
		//
	    // 	UNSPECIFIED: This is used by a parent to determine the desired dimension of a child 
		//				 View. For example, a LinearLayout may call measure() on its child with 
		//			     the height set to UNSPECIFIED and a width of EXACTLY 240 to find out how 
		//				 tall the child View wants to be given a width of 240 pixels.
	    // 	EXACTLY: 	 This is used by the parent to impose an exact size on the child. The 
		//				 child must use this size, and guarantee that all of its descendants will 
		//			 	 fit within this size.
	    // 	AT_MOST: 	 This is used by the parent to impose a maximum size on the child. The 
		//				 child must guarantee that it and all of its descendants will fit within 
		//				 this size.

		int width;
		int height;

		switch(MeasureSpec.getMode(widthMeasureSpec)){
			default:
			case MeasureSpec.UNSPECIFIED:
				// Parent wants to know how wide we want to be given unbounded measure
				// in this case we want to be the width of our animation
				width = header.info_FrameWidth;
				break;
				
			case MeasureSpec.EXACTLY:
				// Parent says we need to be exactly this wide
				width = MeasureSpec.getSize(widthMeasureSpec);
				break;
			
			case MeasureSpec.AT_MOST:
				// Parent says we can be at most this wide
				width = MeasureSpec.getSize(widthMeasureSpec);
				// If we are smaller, shrink
				if(header.info_FrameWidth < width) width = header.info_FrameWidth;
				break;
		}
		
		switch(MeasureSpec.getMode(heightMeasureSpec)){
			default:
			case MeasureSpec.UNSPECIFIED:
				// Parent wants to know how tall we want to be given unbounded measure
				// in this case we want to be the height of our animation
				height = header.info_FrameHeight;
				break;
				
			case MeasureSpec.EXACTLY:
				// Parent says we need to be exactly this tall, cool
				height = MeasureSpec.getSize(heightMeasureSpec);
				break;
			
			case MeasureSpec.AT_MOST:
				// Parent says we can be at most this tall
				height = MeasureSpec.getSize(heightMeasureSpec);
				// If we are smaller, shrink
				if(header.info_FrameHeight < height) height = header.info_FrameHeight;
				break;
		}
		
		
		// This call is CONTRACTED and must be called before onMeasure returns
		this.setMeasuredDimension(width, height);
		
		// Build dest rect
		drawingDestRect.left   = 0;
		drawingDestRect.top    = 0;
		drawingDestRect.right  = width;
		drawingDestRect.bottom = height;
	}
	@Override
    protected void onDraw(Canvas canvas) {
		// Blit current frame of animation
		canvas.drawBitmap(pixieSheet, drawingSourceRect, drawingDestRect, null);
		
		// We only update the frame jog if isPlaying == true
		if(isPlaying) {
			// Have we been on this frame for enough draw calls?
			currentFrameTick++;
			if(currentFrameTick >= currentFrameTickTo) {
				// We have!
				// Reset ticker
				currentFrameTick = 0;
				
				// Move to next frame
				currentFrame++;
				// Loop at end
				if(currentFrame >= header.info_FrameCountTotal) currentFrame = 0;
				
				// Update source rect to coincide with frame change
				setSourceRectToCurrentFrame();
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
	
	protected void setSourceRectToCurrentFrame() {
		setSourceRectToFrame(currentFrame); 
	}
	protected void setSourceRectToFrame(int FrameIndex) {
		// Ensure frame is within limits
		if(FrameIndex >= header.info_FrameCountTotal)
		{
			// Out of bounds
			throw new Error("Trying to setSourceRectToFrame to a frame index that is out of bounds!");
		}
		
		// Update current frame
		currentFrame = FrameIndex;
		
		// Currently pixie uses the first vertical row of pixels as it's header stream
		// which means when we grab frames we need to offset past that
		int x_header_offset = 1;
		int y_header_offset = 0;
		
		// Calculate where the frame is on the sheet by its index
		int frame_y_index = (int) (Math.floor((double)(FrameIndex) / (double)(header.info_FrameCountAcross)));
		int frame_x_index = FrameIndex % header.info_FrameCountAcross;
		
		// Build and set source rect
		drawingSourceRect.left   = x_header_offset + (frame_x_index * header.info_FrameWidth);
		drawingSourceRect.top    = y_header_offset + (frame_y_index * header.info_FrameHeight);
		drawingSourceRect.right  = x_header_offset + (frame_x_index * header.info_FrameWidth)  + header.info_FrameWidth;
		drawingSourceRect.bottom = y_header_offset + (frame_y_index * header.info_FrameHeight) + header.info_FrameHeight;
	}
	
	public void SetTickTo(int TickTo) {
		// Tick to must be >= 1
		if(TickTo >= 1) {
			currentFrameTick   = 0;
			currentFrameTickTo = TickTo;
		}
		else {
			throw new Error("TickTo in SetTickTo must be >= 1.");
		}
	}
	public void SetTickTo_FastestPossible() {
		SetTickTo(1);
	}
	public void SetTickTo_FiveTicks() {
		SetTickTo(5);
	}
	public void SetTickTo_TenTicks() {
		SetTickTo(10);
	}
	public void SetTickTo_TwentyTicks() {
		SetTickTo(20);
	}
	public void SetTickTo_FiftyTicks() {
		SetTickTo(50);
	}
	public void SetTickTo_HundredTicks() {
		SetTickTo(100);
	}
	public void Play() {
		isPlaying = true;
	}
	public void Stop() {
		isPlaying = false;
		currentFrameTick = 0;
	}
	public void GoToFrameByIndex(int FrameIndex) {
		setSourceRectToFrame(FrameIndex);
		currentFrameTick = 0;
	}
	public void GoToFrameByScalar(float Scalar) {
		// Wtf is a scalar? A percent value without unit!
		// This value must be inclusively top and bottom between 0 and 1
		if(Scalar >= 0 && Scalar <= 1) {
			// Yay good values, no one dies today (:
			
			// So lets figure out what frame that is
			int frameIndexByScalar = (int)((float)(header.info_FrameCountTotal - 1) * Scalar);
			
			// Go there!
			GoToFrameByIndex(frameIndexByScalar);
			
			// Peace
			return;
		}
		else{
			// You can't go to 110% or -20% or NaN% or inf% or "my string"%
			// If you did that, this error will catch you like a safety net,
			// like a safety net made of electric eels telling you to go fix
			// your shit.
			throw new Error("Scalar in GoToFrameByScalar must be (inclusively) between 0 and 1.");
		}
	}
	public void GoToStart() {
		GoToFrameByIndex(0);
	}
	public void GoToEnd() {
		GoToFrameByIndex(header.info_FrameCountTotal - 1);
	}
}
