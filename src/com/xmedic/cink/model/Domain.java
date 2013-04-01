/**
 * 
 */
package com.xmedic.cink.model;

import java.io.Serializable;

import com.xmedic.cink.R;

/**
 * @author vincentas
 *
 */
public class Domain implements Serializable {
	
	private static final long serialVersionUID = 4327995181231311889L;
	
	public static final Domain MOUNTAINEERING = new Domain(R.drawable.climbing, R.drawable.ic_climbing_active);
	
	public static final Domain SAILING = new Domain(R.drawable.sailing, R.drawable.ic_sailing_active);
	
	public static final Domain SURVIVAL = new Domain(R.drawable.survival, R.drawable.ic_survival_active);

	public int backgroundDrawable;
	
	public int iconDrawable;
	
	private Domain(int backgroundDrawable, int iconDrawable) {
		this.backgroundDrawable = backgroundDrawable;
		this.iconDrawable = iconDrawable;
	}

	public static Domain valueOf(String string) {
		if (string.equalsIgnoreCase("MOUNTAINEERING")) return MOUNTAINEERING;
		if (string.equalsIgnoreCase("SAILING")) return SAILING;
		if (string.equalsIgnoreCase("SURVIVAL")) return SURVIVAL;
		throw new Error("Unknown value");
	}

	@Override
	public String toString() {
		if (this.equals(MOUNTAINEERING)) return "MOUNTAINEERING";
		if (this.equals(SAILING)) return "SAILING";
		if (this.equals(SURVIVAL)) return "SURVIVAL";
		throw new Error("Unknown type");
	}

	@Override
	public boolean equals(Object o) {
		return backgroundDrawable == ((Domain)o).backgroundDrawable;
	}

	@Override
	public int hashCode() {
		return backgroundDrawable;
	}
	
	
	
	

}
