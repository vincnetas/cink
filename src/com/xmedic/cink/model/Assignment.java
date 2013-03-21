/**
 * 
 */
package com.xmedic.cink.model;

import java.util.List;

import com.xmedic.cink.R;

/**
 * @author vincent
 *
 */
public class Assignment {
	
	public static final int STYLE_CLIMBING = R.drawable.climbing;

	public static final int STYLE_SURVIVAL = R.drawable.survival;

	public static final int STYLE_SAILING = R.drawable.sailing;
	
	private Knot knot;
	
	private List<Question> questions;	
	
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	/**
	 * @return the knot
	 */
	public Knot getKnot() {
		return knot;
	}

	/**
	 * @param knot the knot to set
	 */
	public void setKnot(Knot knot) {
		this.knot = knot;
	}

	public Question getQuestion(int step) {
		return questions.get(step);
	}
		
	/**
	 * @return the steps
	 */
	public int getSteps() {
		return knot.getStepDescriptions().size();
	}
}
