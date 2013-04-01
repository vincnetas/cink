package com.xmedic.cink.ui;

import java.util.Random;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
	
	private ImageButton previousQusetionButton;
	
	private ImageButton nextQuestionButton;
	
	private TextView questionText;
		
	public QuestionView(Context context, StepInfo stepInfo, final ViewPager viewPager) {
		super(context);
		
		View questionView = LayoutInflater.from(context).inflate(R.layout.layout_question, this);

		previousQusetionButton = (ImageButton) findViewById(R.id.button_previous_question);
		nextQuestionButton = (ImageButton) findViewById(R.id.button_next_question);
		
		if (stepInfo.isFirstStep()) {
			previousQusetionButton.setVisibility(INVISIBLE);
		}
		
		if (stepInfo.isLastStep()) {
			nextQuestionButton.setVisibility(INVISIBLE);
		}
		
		nextQuestionButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);		
			}
		});
		
		previousQusetionButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);				
			}
		});
		
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
		String[] options = shuffleAnswers(question.getOptions());
		questionText.setText(question.getQuestion());
		button1.setText(options[0]);
		button2.setText(options[1]);
		button3.setText(options[2]);
		button4.setText(options[3]);		
	}
	
	private String[] shuffleAnswers(String[] answers) {
		String[] result = new String[answers.length];
		
		Random random = new Random();
		for (int i = 0; i < answers.length; i++) {
			int index = random.nextInt(answers.length);
			while (result[index] != null) {
				index = (index + 1) % answers.length;
			}
			
			result[index] = answers[i];
		}
		
		return result;
	}
	
	protected abstract void onCorrectAnswer();
	
	protected abstract void onIncorrectAnswer();
	
	private class ButtonListener implements OnClickListener {
		
		private StepInfo stepInfo;

		public ButtonListener(StepInfo stepInfo) {
			this.stepInfo = stepInfo;			
		}
		
		@Override
		public void onClick(View v) {
			Button a = (Button) v;
			if (a.getText().equals(stepInfo.getQuestion().getAnswer())) {
				onCorrectAnswer();
			} else {
				onIncorrectAnswer();
			}
		}		
	}

}
