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

import com.xmedic.cink.model.Domain;
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
	
	private List<Question> getDomainQuestions(Domain domain) {
		List<Question> result = new ArrayList<Question>();
		
		try {		    		    
		    InputStream inputStream = assetManager.open(getDomainFolder(domain) + File.separator + "questions.xml");
		    QuestionHander handler = new QuestionHander(this);
			SAXParserFactory.newInstance().newSAXParser().parse(inputStream, handler);
			result = handler.getQuestions();
		} catch (Exception e ) {
			throw new Error(e);
		}
		
		return result;
	}
	
	private static String getDomainFolder(Domain domain) {
		if (domain.equals(Domain.MOUNTAINEERING)) return "questions" + File.separator + "climbing";
		if (domain.equals(Domain.SAILING)) return "questions" + File.separator + "sailing";
		if (domain.equals(Domain.SURVIVAL)) return "questions" + File.separator + "survival";
		throw new Error("Unknowd domain " + domain);
	}
	
	public List<Question> getQuestions(Domain domain, int number) {
		List<Question> domainQuestions = getDomainQuestions(domain);
		
		Random random = new Random();
		List<Question> result = new ArrayList<Question>();
		while (result.size() < number && !domainQuestions.isEmpty()) {
			Question question = domainQuestions.remove(random.nextInt(domainQuestions.size()));
			result.add(question);
		}
		
		return result;
	} 
}
