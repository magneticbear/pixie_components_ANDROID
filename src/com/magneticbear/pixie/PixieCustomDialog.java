package com.magneticbear.pixie;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class PixieCustomDialog extends Dialog {

	public View inflatedLayout;
	private int BackgroundResID;
	
	public PixieCustomDialog(Context context, int layoutResID, int backgroundResID, boolean ShowTitle, String TitleIfShown) {
		super(context);
		
		//  Inflate the view from the resource provided
		inflatedLayout = getLayoutInflater().inflate(layoutResID, null);
		
		// Display it!
		setContentView(inflatedLayout);
		
		// Save the background resource for later when we need it
		BackgroundResID = backgroundResID;
		
		// If the user wants a title give them a title
		if(ShowTitle) {
			setTitle(TitleIfShown);
		}
	
	}
	
	@Override
	public void show() {
		// Want to do something before or after the dialog appears?
		
		// Do stuff right before it appears here
		super.show();
		// Do stuff right after it appears here
		makeDialogFullScreen();
		customizeDialogStyle();
		
	}

	@Override
	public void hide() {
		// Want to do something before or after the dialog hides?
		
		// Do stuff right before it hides here
		super.hide();
		// Do stuff right after it hides here
	}
	
	private void makeDialogFullScreen() {
		// We're going to do a hotswap of the window parameters
		// WINDOW MUST BE CREATED ALREADY WITH Dialog.show();
		
		// Get the layout parameters from the window of the dialog
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		
		// Set params to fill parent
		lp.width  = WindowManager.LayoutParams.FILL_PARENT;
		lp.height = WindowManager.LayoutParams.FILL_PARENT;
		
		// Set the layout parameters into the window of the dialog
		getWindow().setAttributes(lp);
	}
	
	private void customizeDialogStyle() {
		// Set the background
		getWindow().setBackgroundDrawableResource(BackgroundResID);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// We need to intercept menu and back
		if(keyCode == KeyEvent.KEYCODE_MENU ||
		   keyCode == KeyEvent.KEYCODE_BACK) {
			
			// Hide the menu on back and down
			dismiss();
			
			// Return that this event was handled
			return true;
		} else {
			// It's some other button... and we don't care, let someone else deal with it
			return super.onKeyDown(keyCode, event);
		}
	}
	
	

}
