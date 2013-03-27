/**
 * 
 */
package com.xmedic.cink.model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * @author vincent
 *
 */
public class Knot implements Serializable {

	private static final long serialVersionUID = 2836308267072333089L;

	private List<String> stepDescriptions;
	
	private String knotPath;
	
	private String name;
	
	private String style;
	
	private int domain;
	
	public Knot(String name, String style) {
		this.name = name;
		this.style = style;
	}

	/**
	 * @return the stepDescriptions
	 */
	public List<String> getStepDescriptions() {
		return stepDescriptions;
	}

	/**
	 * @param stepDescriptions the stepDescriptions to set
	 */
	public void setStepDescriptions(List<String> stepDescriptions) {
		this.stepDescriptions = stepDescriptions;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the style
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * @param style the style to set
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * @return the knotPath
	 */
	public String getKnotPath() {
		return knotPath;
	}

	/**
	 * @param knotPath the knotPath to set
	 */
	public void setKnotPath(String knotPath) {
		this.knotPath = knotPath;
	}

	public int getDomain() {
		return domain;
	}

	public void setDomain(int domain) {
		this.domain = domain;
	}
	
	public Drawable getFinalKnot(Context context) {
		try {
			String imagePath = getKnotPath() + File.separator + Integer.toString(stepDescriptions.size()) + ".png";
			return Drawable.createFromStream(context.getAssets().open(imagePath), null);
		} catch (IOException e) {
			throw new Error(e);
		}
	}
}
