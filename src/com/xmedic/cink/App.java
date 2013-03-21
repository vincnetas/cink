package com.xmedic.cink;

import com.xmedic.cink.util.KnotManager;
import com.xmedic.cink.util.QuestionManager;

public class App extends android.app.Application {
	
	public static QuestionManager questionManager;
	
	public static KnotManager knotManager;
	
	@Override
	public void onCreate() {		
		questionManager = new QuestionManager(getAssets());
		knotManager = new KnotManager(getAssets());
	}
}
