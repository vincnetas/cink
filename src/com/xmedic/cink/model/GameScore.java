/**
 * 
 */
package com.xmedic.cink.model;

import java.io.Serializable;

/**
 * @author vincentas
 *
 */
public class GameScore implements Serializable {

	private static final long serialVersionUID = -5166058539649452936L;

	private int progress = 0;
	
	public int getProgress() {
		return progress;
	}
	
	public int getTotalSteps() {
		return 10;
	}
	
	public void scoreIncorrect() {
		progress++;
	}
	
	public void scoreCorrect() {
		progress++;
	}
	
}
