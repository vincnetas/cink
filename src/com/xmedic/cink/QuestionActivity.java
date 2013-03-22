package com.xmedic.cink;


import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.xmedic.cink.model.Assignment;
import com.xmedic.cink.model.StepInfo;
import com.xmedic.cink.ui.StepFragment;
import com.xmedic.cink.util.Timer;
import com.xmedic.cink.util.Util;

public class QuestionActivity extends FragmentActivity {

	public static final String QUESTION_STYLE = "question style";

	public static final String ASSIGNMENT = "assignment";

	private ImageView questionStyleImage;

	private ViewPager pager;
	
	private TextView timerText;
	
	private Assignment assignment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_question);
		
		assignment = (Assignment) getIntent().getExtras().getSerializable(ASSIGNMENT);
		ScreenSlidePagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), assignment);

		questionStyleImage = (ImageView) findViewById(R.id.question_style_image);
		questionStyleImage.setImageResource(assignment.getDomain());

		pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(pagerAdapter);
		
		timerText = (TextView) findViewById(R.id.time_text);
		timerText.setTypeface(Util.getCustomFont(this, Util.NOVECENTOWIDE_BOOK), Typeface.BOLD);
		timerText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				Intent intent = new Intent(QuestionActivity.this, CheckKnotActivity.class);
				intent.putExtra(QuestionActivity.ASSIGNMENT, assignment);
				startActivity(intent);				
			}
		});
	}
	
	private Timer timerTask;
	
	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		
		long time = 2 * 60 * 1000;
		timerTask = new Timer(timerText) {

			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				
				Intent intent = new Intent(QuestionActivity.this, CheckKnotActivity.class);
				intent.putExtra(QuestionActivity.ASSIGNMENT, assignment);
				startActivity(intent);
			}
			
		};		
		timerTask.execute(time);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		
		timerTask.cancel(true);
	}




	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		
		private Assignment assignment;
		
		private List<StepInfo> stepInfoList = new ArrayList<StepInfo>();		
		
		public ScreenSlidePagerAdapter(FragmentManager fm, Assignment assignment) {
			super(fm);
			this.assignment = assignment;
			for (int i = 0; i < assignment.getSteps(); i++) {
				stepInfoList.add(new StepInfo(i, assignment));
			}
		}
		
		@Override
		public Fragment getItem(int position) {
			StepFragment fragment = new StepFragment();
			fragment.setStepInfo(stepInfoList.get(position));
			
			return fragment;
		}

		@Override
		public int getCount() {
			return assignment.getSteps();
		}
	}

}
