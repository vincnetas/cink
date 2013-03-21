package com.xmedic.cink;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class StartupActivity extends FullScreenActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_startup);
		
		View background = findViewById(R.id.startup_background);
		background.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(StartupActivity.this, MainActivity.class));
			}
		});
	}
}
