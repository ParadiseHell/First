package com.ct.movie;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.ct.movie.adapter.MovieCinemaListAdapter;
import com.ct.movie.data.MovieCinemaData;

public class MovieCinemaListAcitivity extends Activity implements OnClickListener,OnChildClickListener{
	private Button backButton;
	private ExpandableListView expandableListView;
	
	private List<String> group;
	private Map<String, List<Map<String, Object>>> child;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_cinema_list);
		
		initView();
		initEvent();
	}
	private void initEvent() {
		// TODO Auto-generated method stub
		backButton.setOnClickListener(this);
		expandableListView.setOnChildClickListener(this);
		
	}
	private void initView() {
		// TODO Auto-generated method stub
		backButton = (Button) findViewById(R.id.moive_cinema_back);
		expandableListView = (ExpandableListView) findViewById(R.id.movie_cinema_expandable_list_view);
		initListView();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.moive_cinema_back:
			finish();
			break;

		default:
			break;
		}
		
	}
	private void initListView() {
		MovieCinemaData data = new MovieCinemaData();
		group = data.getGroup();
		child = data.getChild();
		MovieCinemaListAdapter adapter = new MovieCinemaListAdapter(this, group, child);
		expandableListView.setAdapter(adapter);
		expandableListView.setGroupIndicator(null);
		expandableListView.expandGroup(0);
	}
	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		String key = group.get(groupPosition);
		List<Map<String, Object>> list = child.get(key);
		Map<String, Object> cinemaMap = new HashMap<String, Object>();
		cinemaMap = list.get(childPosition);
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), MovieCinemaInfoActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("cinemaInfo", (Serializable) cinemaMap);
		intent.putExtras(bundle);
		startActivity(intent);
		return true;
	}
	
}
