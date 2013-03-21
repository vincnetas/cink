package com.xmedic.cink;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.xmedic.cink.model.Assignment;
import com.xmedic.cink.ui.TwoClickButton;

public class ChooseDomainActivity extends FullScreenActivity {

	private TwoClickButton climbingButton;
	
	private TwoClickButton survivalButton;
	
	private TwoClickButton sailingButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_choose_domain);
		
		climbingButton = (TwoClickButton) findViewById(R.id.climbing_button);
		survivalButton = (TwoClickButton) findViewById(R.id.survival_button);
		sailingButton = (TwoClickButton) findViewById(R.id.sailing_button);
		
		climbingButton.setOnSecondClickListener(new ClickListenerWithType(Assignment.STYLE_CLIMBING));
		survivalButton.setOnSecondClickListener(new ClickListenerWithType(Assignment.STYLE_SURVIVAL));
		sailingButton.setOnSecondClickListener(new ClickListenerWithType(Assignment.STYLE_SAILING));
	}
	
	private class ClickListenerWithType implements View.OnClickListener {

		private final int style;
		
		public ClickListenerWithType(int style) {
			this.style = style;
		}
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(ChooseDomainActivity.this, QuestionActivity.class);
			intent.putExtra(QuestionActivity.QUESTION_STYLE, style);
			startActivity(intent);
		}
		
	}

}
