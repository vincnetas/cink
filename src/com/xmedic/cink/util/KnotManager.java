/**
 * 
 */
package com.xmedic.cink.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.SAXParserFactory;

import com.xmedic.cink.model.Knot;
import com.xmedic.cink.model.handler.KnotHandler;

import android.content.res.AssetManager;

/**
 * @author vincent
 *
 */
public class KnotManager {

	private final AssetManager assetManager;
	
	public KnotManager(AssetManager assetManager) {
		this.assetManager = assetManager;
	}
	
	private String[] getKnotPaths(int domain) {		
		List<String> result = new ArrayList<String>();
		
		try {
			String knotsPath = Util.getDomainFolder(domain) + File.separator + "knots";
			String[] knots = assetManager.list(knotsPath);
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
	
	public Knot getKnot(int domain) {
		String[] knotPaths = getKnotPaths(domain);
		return loadKnot(domain, knotPaths[new Random().nextInt(knotPaths.length)]);		
	}
	
	private Knot loadKnot(int domain, String path) {
		Knot result;
		
		try {		    		    
		    InputStream inputStream = assetManager.open(path + File.separator + "knot.xml");
		    KnotHandler handler = new KnotHandler();
			SAXParserFactory.newInstance().newSAXParser().parse(inputStream, handler);
			result = handler.getKnot();
			result.setKnotPath(path);
			result.setDomain(domain);
		} catch (Exception e ) {
			throw new Error(e);
		}
		
		return result;
	}
	
	public List<Knot> getAllKnots(int domain) {
		List<Knot> result = new ArrayList<Knot>();
		String[] knotPaths = getKnotPaths(domain);
		for (String knotPath : knotPaths) {
			result.add(loadKnot(domain, knotPath));
		}
		
		return result;		
	}
}
