/**
 * 
 */
package com.xmedic.cink.model;

import com.xmedic.cink.R;

/**
 * @author vincentas
 *
 */
public enum Domain {
	MOUNTAINEERING(R.drawable.climbing),
	SAILING(R.drawable.survival),
	SURVIVAL(R.drawable.sailing);

	public int drawable;
	
	Domain(int drawable) {
		this.drawable = drawable;
	}

}
