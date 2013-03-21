/**
 * 
 */
package com.xmedic.cink.ui;

import java.io.File;
import java.io.IOException;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import com.xmedic.cink.R;
import com.xmedic.cink.model.Assignment;
import com.xmedic.cink.model.Knot;
import com.xmedic.cink.model.Question;
import com.xmedic.cink.util.Util;

/**
 * @author vincentas
 * 
 */
public class QuestionFragment extends Fragment {

	private Button button1;
	
	private Button button2;
	
	private Button button3;
	
	private Button button4;
	
	private TextView questionText;
	
	private Assignment assignment;
	
	private Question question;
	
	private Knot knot;
	
	private String imagePath;
	
	private int step;

	public void setAssignemt(Assignment assignment, int step) {
		this.assignment = assignment;
		question = assignment.getQuestion(step);
		knot = assignment.getKnot();
		this.step = step;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
		final ViewSwitcher viewSwitcher = new ViewSwitcher(container.getContext());
		
		ViewGroup questionView = (ViewGroup) inflater.inflate(R.layout.layout_question, container, false);

		button1 = (Button) questionView.findViewById(R.id.answer_1_button);
		button2 = (Button) questionView.findViewById(R.id.answer_2_button);
		button3 = (Button) questionView.findViewById(R.id.answer_3_button);
		button4 = (Button) questionView.findViewById(R.id.answer_4_button);

		button1.setTypeface(Util.getCustomFont(container.getContext(), Util.NOVECENTOWIDE_BOOK));
		button2.setTypeface(Util.getCustomFont(container.getContext(), Util.NOVECENTOWIDE_BOOK));
		button3.setTypeface(Util.getCustomFont(container.getContext(), Util.NOVECENTOWIDE_BOOK));
		button4.setTypeface(Util.getCustomFont(container.getContext(), Util.NOVECENTOWIDE_BOOK));
		
		ButtonListener listener = new ButtonListener((ViewPager) container);
		button1.setOnClickListener(listener);
		button2.setOnClickListener(listener);
		button3.setOnClickListener(listener);
		button4.setOnClickListener(listener);

		questionText = (TextView) questionView.findViewById(R.id.instruction_text);
		questionText.setTypeface(Util.getCustomFont(container.getContext(), Util.NOVECENTOWIDE_BOOK));
		
		questionText.setText(question.getQuestion());
		if (question.getOptions().length > 0) button1.setText(question.getOptions()[0]);
		if (question.getOptions().length > 1) button2.setText(question.getOptions()[1]);
		if (question.getOptions().length > 2) button3.setText(question.getOptions()[2]);
		if (question.getOptions().length > 3) button4.setText(question.getOptions()[3]);
		
		
		
		ViewGroup knotView = (ViewGroup) inflater.inflate(R.layout.layout_knot_step, container, false);

		TextView instructiontext = (TextView) knotView.findViewById(R.id.instruction_text);
		ImageView imageView = (ImageView) knotView.findViewById(R.id.instruction_image);
		
		instructiontext.setText(knot.getStepDescriptions().get(step));
		try {
			String imagePath = knot.getKnotPath() + File.separator + Integer.toString(step) + ".png";
			Drawable d = Drawable.createFromStream(container.getContext().getAssets().open(imagePath), null);
			imageView.setImageDrawable(d);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		viewSwitcher.addView(questionView);
		viewSwitcher.addView(knotView);
		viewSwitcher.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				viewSwitcher.showNext();
				
			}
		});
		
		viewSwitcher.setInAnimation(container.getContext(), android.R.anim.fade_in);
		viewSwitcher.setOutAnimation(container.getContext(), android.R.anim.fade_out);
		
		return viewSwitcher;
	}
	
	private class ButtonListener implements OnClickListener {

		private ViewPager pager;
		
		public ButtonListener(ViewPager pager) {
			this.pager = pager;
		}
		
		@Override
		public void onClick(View v) {
			Button a = (Button) v;
			if (a.getText().equals(question.getAnswer())) {
				a.setTextColor(Color.GREEN);
			} else {
				a.setTextColor(Color.RED);
			}
			
			a.setTypeface(a.getTypeface(), Typeface.BOLD);	
			pager.setCurrentItem(pager.getCurrentItem() + 1, true);
		}
		
	}
	
}
