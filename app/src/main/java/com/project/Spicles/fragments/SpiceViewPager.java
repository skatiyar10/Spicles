package com.project.Spicles.fragments;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class SpiceViewPager extends ViewPager {

	private boolean isLocked;
	
    public SpiceViewPager(Context context) {
        super(context);
        isLocked = false;
    }

    public SpiceViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        isLocked = false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
    	if (!isLocked) {
	        try {
	            return super.onInterceptTouchEvent(ev);
	        } catch (IllegalArgumentException e) {
	            return false;
	        }
    	}
    	return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return !isLocked && super.onTouchEvent(event);
    }
    
	public void toggleLock() {
		isLocked = !isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public boolean isLocked() {
		return isLocked;
	}
	
}
