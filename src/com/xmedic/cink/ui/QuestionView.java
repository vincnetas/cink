package com.xmedic.cink.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xmedic.cink.R;
import com.xmedic.cink.model.Question;
import com.xmedic.cink.model.StepInfo;
import com.xmedic.cink.util.Util;

public abstract class QuestionView extends RelativeLayout {

	private Button button1;
	
	private Button button2;
	
	private Button button3;
	
	private Button button4;
	
	private TextView questionText;
		
	public QuestionView(Context context, StepInfo stepInfo) {
		super(context);
		
		View questionView = LayoutInflater.from(context).inflate(R.layout.layout_question, this);

		button1 = (Button) questionView.findViewById(R.id.answer_1_button);
		button2 = (Button) questionView.findViewById(R.id.answer_2_button);
		button3 = (Button) questionView.findViewById(R.id.answer_3_button);
		button4 = (Button) questionView.findViewById(R.id.answer_4_button);

		button1.setTypeface(Util.getCustomFont(context, Util.NOVECENTOWIDE_BOOK));
		button2.setTypeface(Util.getCustomFont(context, Util.NOVECENTOWIDE_BOOK));
		button3.setTypeface(Util.getCustomFont(context, Util.NOVECENTOWIDE_BOOK));
		button4.setTypeface(Util.getCustomFont(context, Util.NOVECENTOWIDE_BOOK));
		
		ButtonListener listener = new ButtonListener(stepInfo);
		button1.setOnClickListener(listener);
		button2.setOnClickListener(listener);
		button3.setOnClickListener(listener);
		button4.setOnClickListener(listener);

		questionText = (TextView) questionView.findViewById(R.id.instruction_text);
		questionText.setTypeface(Util.getCustomFont(context, Util.NOVECENTOWIDE_BOOK));
		
		Question question = stepInfo.getQuestion();
		questionText.setText(question.getQuestion());
		button1.setText(question.getOptions()[0]);
		button2.setText(question.getOptions()[1]);
		button3.setText(question.getOptions()[2]);
		button4.setText(question.getOptions()[3]);		
	}
	
	protected abstract void onCorrectAnswer();
	
	private class ButtonListener implements OnClickListener {
		
		private StepInfo stepInfo;

		public ButtonListener(StepInfo stepInfo) {
			this.stepInfo = stepInfo;			
		}
		
		@Override
		public void onClick(View v) {
			Button a = (Button) v;
			if (a.getText().equals(stepInfo.getQuestion().getAnswer())) {
				a.setTextColor(Color.GREEN);
				onCorrectAnswer();
			} else {
				a.setTextColor(Color.RED);
			}
			
//			a.setTypeface(a.getTypeface(), Typeface.BOLD);	
//			pager.setCurrentItem(pager.getCurrentItem() + 1, true);
//			viewSwitcher.showNext();
		}		
	}

}
