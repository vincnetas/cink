package com.xmedic.cink;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xmedic.cink.model.Assignment;
import com.xmedic.cink.model.GameScore;
import com.xmedic.cink.util.Util;

public class CheckKnotActivity extends FullScreenActivity {

	private Assignment assignment;
	
	private GameScore gameScore;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_knot);
		
		assignment = (Assignment) getIntent().getExtras().getSerializable(QuestionActivity.ASSIGNMENT);
		gameScore = (GameScore) getIntent().getExtras().getSerializable(QuestionActivity.SCORE);
		
		Button correctButton = (Button) findViewById(R.id.check_correct_button);
		correctButton.setTypeface(Util.getCustomFont(this, Util.NOVECENTOWIDE_BOOK));
		
		Button wrongButton = (Button) findViewById(R.id.check_wrong_button);
		wrongButton.setTypeface(Util.getCustomFont(this, Util.NOVECENTOWIDE_BOOK));
		
		ImageView finalKnot = (ImageView) findViewById(R.id.final_knot);
		finalKnot.setImageDrawable(assignment.getKnot().getFinalKnot(this));

		TextView title = (TextView) findViewById(R.id.chcek_title);
		title.setTypeface(Util.getCustomFont(this, Util.NOVECENTOWIDE_BOOK));
	}
	
	public void goPrepare(View v) {
		if (v.getId() == R.id.check_correct_button) {
			gameScore.scoreCorrect();
		} else {
			gameScore.scoreIncorrect();
		}
		
		if (gameScore.getProgress() == gameScore.getTotalSteps()) {
			Intent intent = new Intent(CheckKnotActivity.this, MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			
			startActivity(intent);
		} else {			
			Intent intent = new Intent(CheckKnotActivity.this, PrepareActivity.class);
			intent.putExtra(QuestionActivity.QUESTION_STYLE, assignment.getDomain());
			intent.putExtra(QuestionActivity.SCORE, gameScore);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	
			startActivity(intent);
		}
	}

}
