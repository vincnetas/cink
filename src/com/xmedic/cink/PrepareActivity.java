package com.xmedic.cink;

import com.xmedic.cink.model.Assignment;
import com.xmedic.cink.util.Util;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PrepareActivity extends FullScreenActivity {

	private Assignment assignment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prepare);
		
		int domain = getIntent().getExtras().getInt(QuestionActivity.QUESTION_STYLE);
		assignment = Util.getAssignemnt(domain);
		
		TextView knotNameText = (TextView) findViewById(R.id.prepare_knot_name_text);
		knotNameText.setTypeface(Util.getCustomFont(this, Util.NOVECENTOWIDE_BOOK));
		knotNameText.setText(assignment.getKnot().getName());
		
		Button goButton = (Button) findViewById(R.id.prepare_go_buttom);
		goButton.setTypeface(Util.getCustomFont(this, Util.NOVECENTOWIDE_BOOK));
		goButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PrepareActivity.this, QuestionActivity.class);
				intent.putExtra(QuestionActivity.ASSIGNMENT, assignment);
				startActivity(intent);
			}
		});

	}

}
