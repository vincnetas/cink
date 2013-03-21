package com.xmedic.cink;

import java.lang.reflect.Field;
import java.util.List;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.xmedic.cink.model.Question;
import com.xmedic.cink.ui.KnotStepFragment;
import com.xmedic.cink.ui.QuestionFragment;
import com.xmedic.cink.util.FixedSpeedScroller;
import com.xmedic.cink.util.Util;

public class KnotGameActivity extends FragmentActivity {
	
	public static final String QUESTION_STYLE = "question style";

	public static final int STYLE_CLIMBING = R.drawable.climbing;

	public static final int STYLE_SURVIVAL = R.drawable.survival;

	public static final int STYLE_SAILING = R.drawable.sailing;

	private ImageView questionStyleImage;

	private ViewPager pager;
	
	private TextView timerText;
	
	private TextView progressText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_knot_game);

		questionStyleImage = (ImageView) findViewById(R.id.question_style_image);
		questionStyleImage.setImageResource(getIntent().getExtras().getInt(QUESTION_STYLE));

		pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(new ScreenSlidePagerAdapter(getSupportFragmentManager(), null));
		
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
		
		progressText = (TextView) findViewById(R.id.progress_text);
		progressText.setTypeface(Util.getCustomFont(this, Util.NOVECENTOWIDE_BOOK));
	}

	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		
		private List<Question> questions;
		
		public ScreenSlidePagerAdapter(FragmentManager fm, List<Question> questions) {
			super(fm);
			this.questions = questions;
		}

		@Override
		public Fragment getItem(int position) {
			QuestionFragment fragment = new QuestionFragment();
//			fragment.setAssignemt(questions.get(position));			
			return new KnotStepFragment();
		}

		@Override
		public int getCount() {
			return questions.size();
		}
	}
}
