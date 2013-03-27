package com.xmedic.cink;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xmedic.cink.model.Assignment;
import com.xmedic.cink.model.Knot;
import com.xmedic.cink.ui.ad.KnotListAdapter;

public class KnotLibraryActivity extends FullScreenActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_knot_library);
		
		final List<Knot> knots = App.knotManager.getAllKnots(Assignment.STYLE_CLIMBING);
		ListView listView = (ListView) findViewById(R.id.knots_list_view);
		listView.setAdapter(new KnotListAdapter(knots));
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Knot knot = (Knot) arg0.getItemAtPosition(arg2);
				Intent intent = new Intent(KnotLibraryActivity.this, KnotInfoActivity.class);
				intent.putExtra(KnotInfoActivity.KNOT, knot);
				
				startActivity(intent);
			}
		});
	}
}
