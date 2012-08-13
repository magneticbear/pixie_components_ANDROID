package com.magneticbear.pixie;

import android.content.Context;
import android.util.AttributeSet;

public class PixieUsageIndicator extends PixieEaseControlAnimator {

	protected final static int EASE_STYLE = PixieEaseControlAnimator.EASE_STYLE_SIN_IN_OUT;
	protected final static int EASE_SPEED = 50;
	
	public PixieUsageIndicator(Context context) {
		super(context);
	}
	public PixieUsageIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public PixieUsageIndicator(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void SetUsagePercent(float Percent) {
		EaseToFrameByScalar(Percent, EASE_SPEED, EASE_STYLE);
	}
	
}
