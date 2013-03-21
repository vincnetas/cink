package com.xmedic.cink;

import com.xmedic.cink.util.Util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button playButton;
	
	private Button knotsButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		playButton = (Button) findViewById(R.id.play_button);
		playButton.setTypeface(Util.getCustomFont(this, Util.NOVECENTOWIDE_BOOK));
		knotsButton = (Button) findViewById(R.id.knots_button);
		knotsButton.setTypeface(Util.getCustomFont(this, Util.NOVECENTOWIDE_BOOK));
		
		playButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, ChooseDomainActivity.class));
			}
		});
		
		knotsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
