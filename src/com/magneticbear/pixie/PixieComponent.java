package com.magneticbear.pixie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class PixieComponent extends View {

	public PixieHeader header;
	public Bitmap 	   pixieSheet;
	public int 		   currentFrame;
	public int 		   currentFrameTick;
	public int 		   currentFrameTickTo;
	
	public PixieComponent(Context context, Bitmap PixieSheet) {
		super(context);
		SetPixieSheet(PixieSheet);
	}
	
	private void SetPixieSheet(Bitmap PixieSheet) {
		pixieSheet 		   = PixieSheet;
		header 			   = PixieHeader.ReadHeaderFromBitmap(pixieSheet);
		currentFrame       = 0;
		currentFrameTick   = 0;
		currentFrameTickTo = 20;
	}
	
	@Override
	protected void onFinishInflate(){
		super.onFinishInflate();
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
		
		
		// This call is contracted and must be called before onMeasure returns
		this.setMeasuredDimension(width, height);
	}
	
	
	@Override
	protected void onSizeChanged (int w, int h, int oldw, int oldh) {
		// This is called during layout when the size of this view has changed. If you were just 
		// added to the view hierarchy, you're called with the old values of 0.
	}
	
	@Override
	protected void onDraw (Canvas canvas) {
		// Blit
		Rect src = getCurrentFrameSrcRect();
		Rect dst = new Rect(0, 0, src.width(), src.height());
		canvas.drawBitmap(pixieSheet, src, dst, null);
		
		// Have we been on this frame for enough calls?
		currentFrameTick++;
		if(currentFrameTick >= currentFrameTickTo) {
			// We have!
			// Reset ticker
			currentFrameTick = 0;
			
			// Move to next frame
			currentFrame++;
			// Loop at end
			if(currentFrame >= header.info_FrameCountTotal) currentFrame = 0;
		}
		
		// Mark invalid for redraw
		invalidate();		
	}
	
	private Rect getCurrentFrameSrcRect() {
		return getFrameSrcRect(currentFrame); 
	}
	private Rect getFrameSrcRect(int FrameIndex) {
		// Ensure frame is within limits
		if(FrameIndex >= header.info_FrameCountTotal)
		{
			// Out of bounds
			assert(false);
		}
		
		// Currently pixie uses the first vertical row of pixels as it's header stream
		// which means when we grab frames we need to offset past that
		int x_header_offset = 1;
		int y_header_offset = 0;
		
		// Calculate where the frame is on the sheet by its index
		int frame_y_index = (int) (Math.floor((double)(FrameIndex) / (double)(header.info_FrameCountAcross)));
		int frame_x_index = FrameIndex % header.info_FrameCountAcross;
		
		// Build and return rect
		return new Rect(x_header_offset + (frame_x_index * header.info_FrameWidth),
						y_header_offset + (frame_y_index * header.info_FrameHeight),
						x_header_offset + (frame_x_index * header.info_FrameWidth) + header.info_FrameWidth,
						y_header_offset + (frame_y_index * header.info_FrameHeight) + header.info_FrameHeight);
	}

}
