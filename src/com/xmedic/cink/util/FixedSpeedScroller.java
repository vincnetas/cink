package com.xmedic.cink.util;

import android.content.Context;
import android.widget.Scroller;

public class FixedSpeedScroller extends Scroller {

    private int duration = 5000;

    public FixedSpeedScroller(Context context, int duration) {
        super(context);
        this.duration = duration;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
	        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, this.duration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, duration);
    }
}