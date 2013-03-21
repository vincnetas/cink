package com.xmedic.cink.ui;

import com.xmedic.cink.R;
import com.xmedic.cink.util.Util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class FontTextView extends android.widget.TextView {
	
	public static final String TAG = FontTextView.class.getSimpleName();	

	public FontTextView(Context context) {
		super(context);
		init(context, null);
	}

	public FontTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public FontTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {
		String fontStyle = null;
		
		if (attrs != null) {	
			TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FontTextView, 0, 0);		
			
			try {			
				fontStyle = a.getString(R.styleable.FontTextView_font);	
			} finally {
				a.recycle();
			}
		}
		
		Typeface typeface = Util.getCustomFont(context, fontStyle);
		if (typeface != null) {
			setTypeface(typeface);
		}
	}
}
