package com.ct.movie;

import java.util.ArrayList;
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
import android.widget.Gallery;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.ct.movie.adapter.MovieScheduleAdapter;
import com.ct.movie.data.MovieCinemaScheduleData;
import com.ct.movie.data.MovieTimeDate;

@SuppressWarnings("deprecation")
public class MovieCinemaScheduleActivity extends Activity implements
		OnClickListener {
	private Button backButton;
	private Button chooseCinemaButton;
	private Gallery gallery;
	private ExpandableListView expandableListView;
	private List<Map<String, Object>> lists;
	private List<String> group;
	private Map<String, List<Map<String, Object>>> child;
	private TextView cinemaName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_cinema_schedule);
		initView();
		initEvent();
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		backButton.setOnClickListener(this);
		chooseCinemaButton.setOnClickListener(this);
	}

	private void initView() {
		// TODO Auto-generated method stub
		backButton = (Button) findViewById(R.id.movie_cinema_schedule_back);
		chooseCinemaButton = (Button) findViewById(R.id.movie_cinema_schedule_where);
		gallery = (Gallery) findViewById(R.id.movie_cinema_schedule_gallery);
		expandableListView = (ExpandableListView) findViewById(R.id.movie_cinema_schedule_list_view);
		cinemaName = (TextView) findViewById(R.id.movie_cinema_schedule_title);
		initInfo();
		initGallery();
		initListView();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.movie_cinema_schedule_back:
			finish();
			break;
		case R.id.movie_cinema_schedule_where:
			chooseCinema();
			break;
		default:
			break;
		}
	}
	private void chooseCinema() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getApplicationContext(), MovieCinemaListAcitivity.class);
		startActivity(intent);
	}

	private void initInfo() {
		Intent intent = this.getIntent();		
		cinemaName.setText(intent.getStringExtra("cinemaName"));
	}
	private void initGallery() {
		MovieTimeDate date = new MovieTimeDate();
		lists = date.getLists();
		gallery.setAdapter(new SimpleAdapter(this, lists, R.layout.movie_schedule_time, new String[]{"month", "day"},new int[]{R.id.schedule_time_month,R.id.schedule_time_date}));
	}
	private void initListView() {
		MovieCinemaScheduleData data = new MovieCinemaScheduleData();
		group = new ArrayList<String>();
		group = data.getGroup();
		child = new HashMap<String, List<Map<String,Object>>>();
		child = data.getChild();
		MovieScheduleAdapter adapter = new MovieScheduleAdapter(this, group, child);
		expandableListView.setAdapter(adapter);
		expandableListView.setGroupIndicator(null);
		for (int i = 0; i < group.size(); i++) {
			expandableListView.expandGroup(i);
		}
		
	}
}
