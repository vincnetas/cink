/**
 * 
 */
package com.xmedic.cink.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewSwitcher;

import com.xmedic.cink.model.StepInfo;

/**
 * @author vincentas
 * 
 */
public class StepFragment extends Fragment {
	
	private StepInfo stepInfo;
	
	private KnotStepView knotStepView;
	
	private QuestionView questionView;
	
	public void setStepInfo(StepInfo stepInfo) {
		this.stepInfo = stepInfo;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
		ViewPager viewPager = (ViewPager) container;
		
		final ViewSwitcher viewSwitcher = new ViewSwitcher(container.getContext());
		
		questionView = new QuestionView(container.getContext(), stepInfo, viewPager) {

			@Override
			protected void onCorrectAnswer() {
				stepInfo.setRevealed(true);
				viewSwitcher.showNext();
			}
			
			protected void onIncorrectAnswer() {
				stepInfo.setRevealed(true);
				stepInfo.setMistake(true);
				knotStepView.hideAnswer();
				viewSwitcher.showNext();
			}
			
		};
		
		knotStepView = new KnotStepView(container.getContext(), stepInfo, viewPager);

		viewSwitcher.addView(questionView);
		viewSwitcher.addView(knotStepView);
		
		viewSwitcher.setInAnimation(container.getContext(), android.R.anim.fade_in);
		viewSwitcher.setOutAnimation(container.getContext(), android.R.anim.fade_out);
		
		if (stepInfo.isRevealed()) {
			viewSwitcher.showNext();
		}
		
		return viewSwitcher;
	}

}
