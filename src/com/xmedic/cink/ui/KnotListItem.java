/**
 * 
 */
package com.xmedic.cink.ui;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xmedic.cink.R;
import com.xmedic.cink.model.Domain;
import com.xmedic.cink.model.Knot;
import com.xmedic.cink.util.Util;

/**
 * @author vincentas
 *
 */
public class KnotListItem extends RelativeLayout {

	private TextView knotName;
	
	private LinearLayout styleContainer;
	
	private Context context;
	
	public KnotListItem(Context context) {
		super(context);
		View view = inflate(context, R.layout.layout_knot_item, this);
		knotName = (TextView) view.findViewById(R.id.knot_item_name);
		knotName.setTypeface(Util.getCustomFont(context, Util.NOVECENTOWIDE_BOOK));
		styleContainer = (LinearLayout) findViewById(R.id.knot_style_container);
		this.context = context;
	}
	
	public void setKnot(Knot knot) {
		knotName.setText(knot.getName());
		styleContainer.removeAllViews();

		for (Domain domain : knot.getDomains()) {
			ImageView imageView = new ImageView(context);
			imageView.setImageResource(domain.iconDrawable);
			styleContainer.addView(imageView);
		}
	}

	
}
