/**
 * 
 */
package com.xmedic.cink.model.handler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.xmedic.cink.model.Knot;

/**
 * @author vincent
 *
 */
public class KnotHandler extends DefaultHandler {

	private Knot knot;
	
	private List<String> steps;
	
	public Knot getKnot() {
		return knot;
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		stringBuilder.setLength(0);
		
		if (isKnot(localName, qName)) {
			knot = new Knot(attributes.getValue("name"), attributes.getValue("style"));
			steps = new ArrayList<String>();
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (isStep(localName, qName)) {
			steps.add(getText());
		} else if (isKnot(localName, qName)) {
			knot.setStepDescriptions(steps);
		} 
	}
	
	private boolean isStep(String localName, String qName) {
		return "step".equals(localName);
	}

	private boolean isKnot(String localName, String qName) {
		return "knot".equals(localName);
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
