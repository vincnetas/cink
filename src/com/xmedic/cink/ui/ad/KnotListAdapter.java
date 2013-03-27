/**
 * 
 */
package com.xmedic.cink.ui.ad;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xmedic.cink.model.Knot;
import com.xmedic.cink.ui.KnotListItem;

/**
 * @author vincentas
 * 
 */
public class KnotListAdapter extends BaseAdapter {
	
	private List<Knot> knots;
	
	public KnotListAdapter(List<Knot> knots) {
		this.knots = knots;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		KnotListItem result = (KnotListItem) convertView;
		if (result == null) {
			result = new KnotListItem(parent.getContext());
		}

		result.setKnot((Knot) getItem(position));

		return result;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return knots.get(position);
	}

	@Override
	public int getCount() {
		return knots.size();
	}

}
