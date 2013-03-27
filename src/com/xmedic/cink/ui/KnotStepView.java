/**
 * 
 */
package com.xmedic.cink.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
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
	
	private ImageButton previousStepButton;
	
	private ImageButton nextStepButton;
	
	private StepInfo stepInfo;
	
	private ImageView imageView;
	
	public KnotStepView(Context context, StepInfo stepInfo, final ViewPager viewPager) {
		super(context);
		
		View knotView = LayoutInflater.from(context).inflate(R.layout.layout_knot_step, this);

		nextStepButton = (ImageButton) knotView.findViewById(R.id.button_next_step);
		previousStepButton = (ImageButton) knotView.findViewById(R.id.button_previous_step);
		
		if (stepInfo.isFirstStep()) {
			previousStepButton.setVisibility(INVISIBLE);
		}
		
		if (stepInfo.isLastStep()) {
			nextStepButton.setVisibility(INVISIBLE);
		}
		
		nextStepButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);		
			}
		});
		
		previousStepButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);				
			}
		});
		
		TextView instructiontext = (TextView) knotView.findViewById(R.id.instruction_text);
		instructiontext.setTypeface(Util.getCustomFont(context, Util.NOVECENTOWIDE_BOOK));
		instructiontext.setText(stepInfo.getStepDescription());

		imageView = (ImageView) knotView.findViewById(R.id.instruction_image);
		if (stepInfo.isMistake()) {
			hideAnswer();
		} else {
			imageView.setImageDrawable(stepInfo.getStepDrawable(context));
		}
		
		this.stepInfo = stepInfo;
	}
	
	public void hideAnswer() {
		imageView.setImageResource(R.drawable.wrong_answer);
	}
}
