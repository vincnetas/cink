/**
 * 
 */
package com.xmedic.cink.model;

import java.io.Serializable;

import com.xmedic.cink.util.QuestionManager;

/**
 * @author vincentas
 *
 */
public class Question implements Serializable {
	
	private static final long serialVersionUID = -3638858799013155259L;

	private String question;
	
	private String[] options;
	
	private String answer;
	
	private QuestionManager questionManager;
	
	public Question(QuestionManager questionManager) {
		this.questionManager = questionManager;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the options`
	 */
	public String[] getOptions() {
		return options;
	}

	/**
	 * @param options the options to set
	 */
	public void setOptions(String[] options) {
		this.options = options;
	}

	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public void setAnswered(boolean correctly) {
		
	}

	@Override
	public boolean equals(Object o) {
		return question.equals(((Question)o).question);
	}

	@Override
	public int hashCode() {
		return question.hashCode();
	}
}
