package com.xmedic.cink;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xmedic.cink.model.Assignment;
import com.xmedic.cink.util.Util;

public class CheckKnotActivity extends FullScreenActivity {

	public Assignment assignment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_knot);
		
		assignment = (Assignment) getIntent().getExtras().getSerializable(QuestionActivity.ASSIGNMENT);
		
		Button correctButton = (Button) findViewById(R.id.check_correct_button);
		correctButton.setTypeface(Util.getCustomFont(this, Util.NOVECENTOWIDE_BOOK));
		correctButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CheckKnotActivity.this, PrepareActivity.class);
				intent.putExtra(QuestionActivity.QUESTION_STYLE, assignment.getDomain());
				startActivity(intent);
			}
		});
		
		Button wrongButton = (Button) findViewById(R.id.check_wrong_button);
		wrongButton.setTypeface(Util.getCustomFont(this, Util.NOVECENTOWIDE_BOOK));
		wrongButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CheckKnotActivity.this, PrepareActivity.class);
				intent.putExtra(QuestionActivity.QUESTION_STYLE, assignment.getDomain());
				startActivity(intent);
			}
		});

		TextView title = (TextView) findViewById(R.id.chcek_title);
		title.setTypeface(Util.getCustomFont(this, Util.NOVECENTOWIDE_BOOK));
	}

}
