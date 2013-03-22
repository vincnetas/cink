package com.xmedic.cink.util;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;

import com.xmedic.cink.App;
import com.xmedic.cink.model.Assignment;
import com.xmedic.cink.model.Knot;

public class Util {
	
	private static final String DEFAULT_FONT_EXTENSION = ".otf";
	
	public static final Map<String, Typeface> tfCache = new WeakHashMap<String, Typeface>();
		
	public static Typeface getCustomFont(Context ctx, String asset) {
		if (asset == null) {
			return null;
		}
		
		Typeface result = tfCache.get(asset);
		
		if (result == null) {
			try {				
				result = Typeface.createFromAsset(ctx.getAssets(), "fonts/" + asset + DEFAULT_FONT_EXTENSION);
				tfCache.put(asset, result);
			} catch (Exception e) {
				Log.e(Util.class.getName(), "Could not get typeface (" + asset + "): " + e.getMessage());
			}
		}

		return result;
	}

	public static final String NOVECENTOWIDE_BOOK = "Novecentowide-Book";
	
	public static String getDomainFolder(int domain) {
		switch (domain) {
		case Assignment.STYLE_CLIMBING: return "domain" + File.separator + "climbing";
		case Assignment.STYLE_SAILING: return "domain" + File.separator + "sailing";
		case Assignment.STYLE_SURVIVAL: return "domain" + File.separator + "survival";
		default: throw new Error("Unknowd domain " + domain);
		}
	}
	
	public static Assignment getAssignemnt(int domain) {
		Knot knot = App.knotManager.getKnot(domain);
		Assignment assignment = new Assignment();
		assignment.setKnot(knot);
		assignment.setQuestions(App.questionManager.getQuestions(domain, knot.getStepDescriptions().size()));
		assignment.setDomain(domain);
		
		return assignment;
	}
}

 
