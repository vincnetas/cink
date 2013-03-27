/**
 * 
 */
package com.xmedic.cink.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xmedic.cink.R;
import com.xmedic.cink.util.Util;

/**
 * @author vincent
 *
 */
public class KnotStepLibraryView extends RelativeLayout {
	
	private TextView instruction;
	
	private ImageView image;
	
	public KnotStepLibraryView(Context context) {
		super(context);
		
		View view = inflate(context, R.layout.layout_knot_step_library, this);
		instruction = (TextView) view.findViewById(R.id.knot_step_instruction);
		instruction.setTypeface(Util.getCustomFont(context, Util.NOVECENTOWIDE_BOOK));
		image = (ImageView) findViewById(R.id.knot_library_step_image);
	}
	
	public void setInstructions(String text) {
		instruction.setText(text);
	}
	
	public void setImage(Drawable drawable) {
		image.setImageDrawable(drawable);
	}
}
