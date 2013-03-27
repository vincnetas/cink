/**
 * 
 */
package com.xmedic.cink.model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * @author vincentas
 *
 */
public class HowToStep implements Serializable {

	private static final long serialVersionUID = 7610459856933172266L;
	
	private Knot knot;
	
	private int step;
	
	public HowToStep(Knot knot, int step) {
		this.knot = knot;
		this.step = step;
	}
	
	public String getDescription() {
		return knot.getStepDescriptions().get(step);
	}
	
	public Drawable getDrawable(Context context) {
		try {
			String imagePath = knot.getKnotPath() + File.separator + Integer.toString(step + 1) + ".png";
			return Drawable.createFromStream(context.getAssets().open(imagePath), null);
		} catch (IOException e) {
			throw new Error(e);
		}
	}

}
