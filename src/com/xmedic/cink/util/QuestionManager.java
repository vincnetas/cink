/**
 * 
 */
package com.xmedic.cink.util;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.SAXParserFactory;

import android.content.res.AssetManager;

import com.xmedic.cink.model.Question;
import com.xmedic.cink.model.handler.QuestionHander;

/**
 * @author vincent
 *
 */
public class QuestionManager {

	private final AssetManager assetManager;
	
	public QuestionManager(AssetManager assetManager) {
		this.assetManager = assetManager;
	}
	
	private List<Question> getDomainQuestions(int domain) {
		List<Question> result = new ArrayList<Question>();
		
		try {		    		    
		    InputStream inputStream = assetManager.open(Util.getDomainFolder(domain) + File.separator + "questions.xml");
		    QuestionHander handler = new QuestionHander();
			SAXParserFactory.newInstance().newSAXParser().parse(inputStream, handler);
			result = handler.getQuestions();
		} catch (Exception e ) {
			throw new Error(e);
		}
		
		return result;
	}
	
	public List<Question> getQuestions(int domain, int number) {
		List<Question> domainQuestions = getDomainQuestions(domain);
		List<Question> result = new ArrayList<Question>();
		while (result.size() < number) {
			result.add(domainQuestions.get(new Random().nextInt(domainQuestions.size())));
		}
		
		return result;
	}
}
