package com.xmedic.cink;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.xmedic.cink.util.Util;

public class MainActivity extends FullScreenActivity {

	private Button playButton;
	
	private Button knotsButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

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
}
