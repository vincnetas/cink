/**
 * 
 */
package com.xmedic.cink.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xmedic.cink.R;
import com.xmedic.cink.model.StepInfo;
import com.xmedic.cink.util.Util;

/**
 * @author vincent
 *
 */
public class KnotStepView extends RelativeLayout {

	public KnotStepView(Context context, StepInfo stepInfo) {
		super(context);
		
		View knotView = LayoutInflater.from(context).inflate(R.layout.layout_knot_step, this);

		TextView instructiontext = (TextView) knotView.findViewById(R.id.instruction_text);
		instructiontext.setTypeface(Util.getCustomFont(context, Util.NOVECENTOWIDE_BOOK));
		instructiontext.setText(stepInfo.getStepDescription());

		ImageView imageView = (ImageView) knotView.findViewById(R.id.instruction_image);
		imageView.setImageDrawable(stepInfo.getStepDrawable(context));
	}
}
