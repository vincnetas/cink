package com.xmedic.cink;

import java.util.List;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.xmedic.cink.model.HowToStep;
import com.xmedic.cink.model.Knot;
import com.xmedic.cink.ui.KnotStepLibraryView;

public class KnotInfoActivity extends FullScreenActivity {
	
	public static final String KNOT = "knot";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_knot_info);
		
		LinearLayout scrollView = (LinearLayout) findViewById(R.id.knot_info_scroll_view);
		
		Knot knot = (Knot) getIntent().getExtras().getSerializable(KNOT);
		List<HowToStep> steps = knot.getSteps();
		for (HowToStep step : steps) {
			KnotStepLibraryView stepView = new KnotStepLibraryView(this);
			stepView.setInstructions(step.getDescription());			
			stepView.setImage(step.getDrawable(this));
			scrollView.addView(stepView);
		}
	}

}
