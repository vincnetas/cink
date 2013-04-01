/**
 * 
 */
package com.xmedic.cink.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.parsers.SAXParserFactory;

import android.content.res.AssetManager;

import com.xmedic.cink.model.Domain;
import com.xmedic.cink.model.Knot;
import com.xmedic.cink.model.handler.KnotHandler;

/**
 * @author vincent
 *
 */
public class KnotManager {
	
	private Map<Domain, List<Knot>> knots;

	private final AssetManager assetManager;
	
	public KnotManager(AssetManager assetManager) {
		this.assetManager = assetManager;
	}
	
	private synchronized Map<Domain, List<Knot>> getKnots() {
		if (knots == null) {
			knots = new HashMap<Domain, List<Knot>>();
			
			for (String path : getKnotPaths()) {
				Knot knot = loadKnot(path);
				for (Domain domain : knot.getDomains()) {
					List<Knot> list = knots.get(domain);
					if (list == null) {
						list = new ArrayList<Knot>();
						knots.put(domain, list);
					}
					
					list.add(knot);
				}
			}
		}
		
		return knots;
	}
	
	private String[] getKnotPaths() {		
		List<String> result = new ArrayList<String>();
		
		try {
			String knotsPath = "knots";
			String[] knots = assetManager.list("knots");
			for (String knot : knots) {
				String[] list = assetManager.list(knotsPath + File.separator + knot);
				for (String item : list) {
					if (item.equals("knot.xml")) {
						result.add(knotsPath + File.separator + knot);
					}
				}
			}
		} catch (IOException e) {
			throw new Error(e);
		}
		
		return result.toArray(new String[result.size()]);
	}
	
	public Knot getKnot(Domain domain) {
		List<Knot> list = getKnots().get(domain);
		return list.get(new Random().nextInt(list.size()));	
	}
	
	private Knot loadKnot(String path) {
		Knot result;
		
		try {		    		    
		    InputStream inputStream = assetManager.open(path + File.separator + "knot.xml");
		    KnotHandler handler = new KnotHandler();
			SAXParserFactory.newInstance().newSAXParser().parse(inputStream, handler);
			result = handler.getKnot();
			result.setKnotPath(path);
		} catch (Exception e ) {
			throw new Error(e);
		}
		
		return result;
	}
	
	public List<Knot> getAllKnots() {
		SortedSet<Knot> allKnots = new TreeSet<Knot>(new Comparator<Knot>() {

			@Override
			public int compare(Knot lhs, Knot rhs) {
				return lhs.getName().compareTo(rhs.getName());
			}
		});
		
		for (Entry<Domain, List<Knot>> entry : getKnots().entrySet()) {
			allKnots.addAll(entry.getValue());
		}
		
		return new ArrayList<Knot>(allKnots);
	}
	
	public List<Knot> getAllKnots(Domain domain) {
		return getKnots().get(domain);
	}
}
