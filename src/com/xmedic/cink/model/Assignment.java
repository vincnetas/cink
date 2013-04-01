/**
 * 
 */
package com.xmedic.cink.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author vincent
 *
 */
public class Assignment implements Serializable {
	
	private static final long serialVersionUID = -2265358542471172247L;
	
	private Knot knot;
	
	private List<Question> questions;	
	
	private int domain;
	
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

	public int getDomain() {
		return domain;
	}

	public void setDomain(int domain) {
		this.domain = domain;
	}
	
	
}
