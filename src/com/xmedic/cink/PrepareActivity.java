package com.xmedic.cink;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xmedic.cink.model.Assignment;
import com.xmedic.cink.model.Domain;
import com.xmedic.cink.model.GameScore;
import com.xmedic.cink.util.Util;

public class PrepareActivity extends FullScreenActivity {

	private Assignment assignment;
	
	private GameScore gameScore;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prepare);
		
		Bundle extras = getIntent().getExtras();
		Domain domain = Domain.valueOf(extras.getString(QuestionActivity.QUESTION_STYLE));
		if (extras.containsKey(QuestionActivity.SCORE)) {
			gameScore = (GameScore) extras.getSerializable(QuestionActivity.SCORE);
		} else {
			gameScore = new GameScore();
		}
		assignment = Util.getAssignemnt(domain);
		
		TextView currentStatus = (TextView) findViewById(R.id.prepare_current_status);
		currentStatus.setTypeface(Util.getCustomFont(this, Util.NOVECENTOWIDE_BOOK));
		currentStatus.setText("KNOT " + (gameScore.getProgress() + 1) + "/" + gameScore.getTotalSteps());
		
		TextView knotNameText = (TextView) findViewById(R.id.prepare_knot_name_text);
		knotNameText.setTypeface(Util.getCustomFont(this, Util.NOVECENTOWIDE_BOOK));
		knotNameText.setText(assignment.getKnot().getName());
				
		View view = findViewById(R.id.prepare_layout);
		view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PrepareActivity.this, QuestionActivity.class);
				intent.putExtra(QuestionActivity.ASSIGNMENT, assignment);
				intent.putExtra(QuestionActivity.SCORE, gameScore);
				startActivity(intent);
			}
		});

	}

}
