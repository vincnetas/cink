/**
 * 
 */
package com.xmedic.cink.model.handler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.xmedic.cink.model.Question;
import com.xmedic.cink.util.QuestionManager;

/**
 * @author vincentas
 *
 */
public class QuestionHander extends DefaultHandler {

	private QuestionManager questionManager;
	
	private List<Question> questions = new ArrayList<Question>();
	
	private Question question;
	
	private List<String> options = new ArrayList<String>();
	
	public List<Question> getQuestions() {
		return questions;
	}
	
	public QuestionHander(QuestionManager questionManager) {
		this.questionManager = questionManager;
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		stringBuilder.setLength(0);
		
		if (isQuestion(localName, qName)) {
			question = new Question();
		} if (isOptions(localName, qName)) {
			options.clear();
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (isQuestion(localName, qName)) {
			questions.add(question);
		} else if (isOptions(localName, qName)) {
			question.setOptions(options.toArray(new String[options.size()]));
		} else if (isOption(localName, qName)) {
			options.add(getText());
		} else if (isAnswer(localName,qName)) {
			question.setAnswer(getText());
		} else if (isText(localName, qName)) {
			question.setQuestion(getText());
		}
	}

	private boolean isText(String localName, String qName) {
		return "text".equals(localName);
	}

	private boolean isAnswer(String localName, String qName) {
		return "answer".equals(localName);
	}

	private boolean isOption(String localName, String qName) {
		return "option".equals(localName);
	}
	
	private boolean isOptions(String localName, String qName) {
		return "options".equals(localName);
	}

	private boolean isQuestion(String localName, String qName) {
		return "question".equals(localName);
	}


	private StringBuilder stringBuilder = new StringBuilder();
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		stringBuilder.append(ch, start, length);
	}
	
	private String getText() {
		try {
			return stringBuilder.toString();
		} finally {
			stringBuilder.setLength(0);
		}
	}
}
