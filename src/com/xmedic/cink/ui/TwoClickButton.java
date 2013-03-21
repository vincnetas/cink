/**
 * 
 */
package com.xmedic.cink.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xmedic.cink.R;

/**
 * @author vincentas
 * 
 */
public class TwoClickButton extends RelativeLayout {

	private OnClickListener secondClickListener;
	
	private boolean clicked = false;
	
	private int activeImageResource;
	
	private int inactiveImageResource;
	
	private ImageView image;
	
	private TextView text;

	public TwoClickButton(Context context) {
		super(context);
		init(context, null);
	}

	public TwoClickButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public TwoClickButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}

	public void init(Context context, AttributeSet attrs) {
		View view = LayoutInflater.from(context).inflate(R.layout.layout_two_click_button, this);
		
		image = (ImageView) view.findViewById(R.id.button_image);
		text = (TextView) view.findViewById(R.id.button_text);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TwoClickButton, 0, 0);
		try {
			activeImageResource = a.getResourceId(R.styleable.TwoClickButton_active, 0);
			inactiveImageResource = a.getResourceId(R.styleable.TwoClickButton_inactive, 0);
			
			image.setImageResource(inactiveImageResource);
			text.setText(a.getString(R.styleable.TwoClickButton_text));
		} finally {
			a.recycle();
		}
		
		setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ViewGroup parent = (ViewGroup) getParent();
				for(int i = 0; i < parent.getChildCount(); i++) {
					TwoClickButton button = (TwoClickButton) parent.getChildAt(i);
					if (button != TwoClickButton.this) {
						button.setClicked(false);
					} else {
						if (clicked) {
							if (secondClickListener != null) {
								secondClickListener.onClick(TwoClickButton.this);
							}
						} else {
							setClicked(true);
						}
					}
				}
			}
		});
		
		setClicked(clicked);
	}
	
	public void setClicked(boolean active) {
		clicked = active;
		if (active) {
			image.setImageResource(activeImageResource);
			text.setVisibility(View.VISIBLE);
		} else {
			image.setImageResource(inactiveImageResource);
			text.setVisibility(View.INVISIBLE);
		}
	}
	 
	

	/* (non-Javadoc)
	 * @see android.view.View#setOnClickListener(android.view.View.OnClickListener)
	 */
	public void setOnSecondClickListener(OnClickListener l) {
		secondClickListener = l;
	}
	
	

}
