package com.xmedic.cink.util;

import java.util.Map;
import java.util.WeakHashMap;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import com.xmedic.cink.App;
import com.xmedic.cink.model.Assignment;
import com.xmedic.cink.model.Domain;
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
		
	public static Assignment getAssignemnt(Domain domain) {
		Knot knot = App.knotManager.getKnot(domain);
		Assignment assignment = new Assignment();
		assignment.setKnot(knot);
		assignment.setQuestions(App.questionManager.getQuestions(domain, knot.getStepDescriptions().size()));
		
		return assignment;
	}
}

 
