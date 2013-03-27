/**
 * 
 */
package com.xmedic.cink.model;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * @author vincent
 *
 */
public class StepInfo {

	private Assignment assignment;

	private int step;
	
	private boolean revealed;
	
	public StepInfo(int step, Assignment assignment) {
		this.assignment = assignment;
		this.step = step;
	}
	
	public Question getQuestion() {
		return assignment.getQuestion(step);
	}
	
	public String getStepDescription() {
		return assignment.getKnot().getStepDescriptions().get(step);
	}
	
	public Drawable getStepDrawable(Context context) {
		try {
			String imagePath = assignment.getKnot().getKnotPath() + File.separator + Integer.toString(step + 1) + ".png";
			return Drawable.createFromStream(context.getAssets().open(imagePath), null);
		} catch (IOException e) {
			throw new Error(e);
		}	
	}
	
	public boolean isRevealed() {
		return revealed;
	}

	public void setRevealed(boolean revealed) {
		this.revealed = revealed;
	}	
	
	public boolean isFirstStep() {
		return step == 0;
	}
	
	public boolean isLastStep() {
		return step == assignment.getSteps() - 1;
	}
}
