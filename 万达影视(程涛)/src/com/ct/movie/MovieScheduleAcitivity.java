package com.ct.movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.ct.movie.data.MovieScheduleData;
import com.ct.movie.data.MovieTimeDate;

@SuppressWarnings("deprecation")
public class MovieScheduleAcitivity extends Activity implements OnClickListener {
	private Button backButton;
	private Button refreshButton;
	private TextView movieName;
	private String movieNameString;
	private ExpandableListView expandableListView;

	private Gallery gallery;
	private List<Map<String, Object>> lists;
	private List<String> group;
	private Map<String, List<Map<String, Object>>> child;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_schedule);
		initView();
		initEvent();
	}

	private void initEvent() {
		// TODO Auto-generated method stub

		backButton.setOnClickListener(this);
		refreshButton.setOnClickListener(this);
		showStyle();
		
	}

	private void initView() {
		// TODO Auto-generated method stub
		backButton = (Button) findViewById(R.id.movie_schedule_back);
		refreshButton = (Button) findViewById(R.id.movie_schedule_refresh);
		movieName = (TextView) findViewById(R.id.movie_schedule_movie_name);
		gallery = (Gallery) findViewById(R.id.movie_schedule_gallery);
		expandableListView = (ExpandableListView) findViewById(R.id.movie_schedule_list_view);
		initInfo();
		initGallery();
		initListView();
	}

	private void showStyle() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("选择版本");
		final String[] version = new String[] { "IMAX 3D 版", "IMAX 版", "3D 版",
				"2D 版" };
		builder.setItems(version, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				String name = movieNameString + " " + version[which];
				movieName.setText(name);
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		builder.show();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.movie_schedule_back:
			finish();
			break;
		case R.id.movie_schedule_refresh:
			showStyle();
			break;
		default:
			break;
		}
	}
private void initGallery() {
	MovieTimeDate date = new MovieTimeDate();
	lists = date.getLists();
	gallery.setAdapter(new SimpleAdapter(this, lists, R.layout.movie_schedule_time, new String[]{"month", "day"},new int[]{R.id.schedule_time_month,R.id.schedule_time_date}));
}
	private void initInfo() {
		Intent intent = this.getIntent();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) intent
				.getSerializableExtra("movie");
		movieNameString = (String) map.get("movieName");
		movieName.setText(movieNameString);
	}
	
	private void initListView() {
		MovieScheduleData data = new MovieScheduleData();
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
