package com.xmedic.cink;


import java.lang.reflect.Field;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xmedic.cink.model.Assignment;
import com.xmedic.cink.ui.QuestionFragment;
import com.xmedic.cink.util.FixedSpeedScroller;
import com.xmedic.cink.util.Timer;
import com.xmedic.cink.util.Util;

public class QuestionActivity extends FragmentActivity {

	public static final String QUESTION_STYLE = "question style";

	private ImageView questionStyleImage;

	private ViewPager pager;
	
	private TextView timerText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);
		
		int questionStyle = getIntent().getExtras().getInt(QUESTION_STYLE);
		questionStyleImage = (ImageView) findViewById(R.id.question_style_image);
		questionStyleImage.setImageResource(questionStyle);

		Assignment assignemnt = Util.getAssignemnt(questionStyle, getAssets());
		ScreenSlidePagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), assignemnt);
		
		pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(pagerAdapter);
		
		try {
            Field mScroller;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true); 
            FixedSpeedScroller scroller = new FixedSpeedScroller(this, 1000);
            mScroller.set(pager, scroller);
        } catch (NoSuchFieldException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
		
		timerText = (TextView) findViewById(R.id.time_text);
		timerText.setTypeface(Util.getCustomFont(this, Util.NOVECENTOWIDE_BOOK), Typeface.BOLD);
		timerText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(QuestionActivity.this, KnotGameActivity.class);
				intent.putExtra(KnotGameActivity.QUESTION_STYLE, getIntent().getExtras().getInt(QUESTION_STYLE));
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
		timerTask = new Timer(timerText);		
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
		
		public ScreenSlidePagerAdapter(FragmentManager fm, Assignment assignment) {
			super(fm);
			this.assignment = assignment;
		}

		@Override
		public Fragment getItem(int position) {
			QuestionFragment fragment = new QuestionFragment();
			fragment.setAssignemt(assignment, position);			
			return fragment;
		}

		@Override
		public int getCount() {
			return assignment.getSteps();
		}
	}

}
