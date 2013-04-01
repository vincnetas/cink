package com.xmedic.cink;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xmedic.cink.model.Domain;
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
		
		climbingButton.setOnSecondClickListener(new ClickListenerWithType(Domain.MOUNTAINEERING));
		survivalButton.setOnSecondClickListener(new ClickListenerWithType(Domain.SURVIVAL));
		sailingButton.setOnSecondClickListener(new ClickListenerWithType(Domain.SAILING));
	}
	
	private class ClickListenerWithType implements View.OnClickListener {

		private final Domain domain;
		
		public ClickListenerWithType(Domain domain) {
			this.domain = domain;
		}
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(ChooseDomainActivity.this, PrepareActivity.class);
			intent.putExtra(QuestionActivity.QUESTION_STYLE, domain.toString());
			startActivity(intent);
		}
		
	}

}
