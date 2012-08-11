package com.magneticbear.pixie;

import android.graphics.Bitmap;
import android.graphics.Color;

public class PixieHeader {

	public int info_FrameWidth;	
	public int info_FrameHeight;	
	public int info_FrameCountTotal;
	public int info_FrameCountAcross;
	public int info_FrameCountDown;	
	public int info_FrameTickTo;
	
	public static PixieHeader ReadHeaderFromBitmap(Bitmap PixieSheet){
		PixieHeader header = new PixieHeader(PixieSheet);
		return header;
	}
	public static PixieHeader CreateEmptyHeader() {
		PixieHeader header = new PixieHeader(null);
		return header;
	}
	private PixieHeader(Bitmap PixieSheet) {
		// Create empty header
		if(PixieSheet == null){
			info_FrameWidth 	  = 50;
			info_FrameHeight      = 50;
			info_FrameCountTotal  = 1;
			info_FrameCountAcross = 1;
			info_FrameCountDown   = 1;
			info_FrameTickTo      = 10;
		}
		// Create packed header
		else {
		info_FrameWidth  	  = readFrameWidth(PixieSheet);
		info_FrameHeight      = readFrameHeight(PixieSheet);
		info_FrameCountTotal  = readFrameCountTotal(PixieSheet);
		info_FrameCountAcross = readFrameCountAcross(PixieSheet);
		info_FrameCountDown   = readFrameCountDown(PixieSheet);
		info_FrameTickTo 	  = readFrameTickTo(PixieSheet);
		}
	}
	
	private int readFrameWidth(Bitmap PixieSheet) {
		return readIntAtPixel(0, 0, PixieSheet);
	}
	private int readFrameHeight(Bitmap PixieSheet) {
		return readIntAtPixel(0, 1, PixieSheet);	
	}
	private int readFrameCountTotal(Bitmap PixieSheet) {
		return readIntAtPixel(0, 2, PixieSheet);
	}
	private int readFrameCountAcross(Bitmap PixieSheet) {
		return readIntAtPixel(0, 3, PixieSheet);
	}
	private int readFrameCountDown(Bitmap PixieSheet) {
		return readIntAtPixel(0, 4, PixieSheet);
	}
	private int readFrameTickTo(Bitmap PixieSheet) {
		return readIntAtPixel(0, 5, PixieSheet);
	}
	private int readIntAtPixel(int X, int Y, Bitmap SourceBitmap){
		return readTrueValueAtPixel(X, Y, SourceBitmap);
	}
	private int readTrueValueAtPixel(int X, int Y, Bitmap SourceBitmap) {
		// Pull pixel
    	int pixelData = SourceBitmap.getPixel(X, Y);
    	
    	// Pull color components from pixel
    	int redData = Color.red(pixelData);
    	int greenData = Color.green(pixelData);
    	int blueData = Color.blue(pixelData);
    	
    	// Create 'true' value by converting from base 256
    	int trueValue = blueData + (greenData * (256)) + (redData * (256 * 256));
    	
    	// Return the value as an int
    	return trueValue;
	}
	
	public String getHumanReadableData() {
		String output = "";
		
		output = output.concat("Frame Width: " 		  + info_FrameWidth 	  + " px\n");
		output = output.concat("Frame Height: " 	  + info_FrameHeight 	  + " px\n");
		output = output.concat("Frame Count Total: "  + info_FrameCountTotal  + " frames\n");
		output = output.concat("Frame Count Across: " + info_FrameCountAcross + " frames\n");
		output = output.concat("Frame Count Down: "   + info_FrameCountDown   + " frames\n");
		
		return output;
	}

}
