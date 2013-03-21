/**
 * 
 */
package com.xmedic.cink.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xmedic.cink.R;
import com.xmedic.cink.util.Util;

/**
 * @author vincentas
 * 
 */
public class KnotStepFragment extends Fragment {
	
	private TextView instructionText;
	
	public void setQuestion() {

	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.layout_knot_step, container, false);

		instructionText = (TextView) rootView.findViewById(R.id.instruction_text);
		instructionText.setTypeface(Util.getCustomFont(container.getContext(), Util.NOVECENTOWIDE_BOOK));		
		instructionText.setText("ASasfasdasdasda as da a F A FA FAG A AD  S SD SDA");
		
		return rootView;
	}

}
