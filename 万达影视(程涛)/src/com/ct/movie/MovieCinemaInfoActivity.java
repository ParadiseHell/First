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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.ct.movie.data.MovieData;

@SuppressWarnings("deprecation")
public class MovieCinemaInfoActivity extends Activity implements OnClickListener{
	private Button backButton;
	private TextView cinemaName;
	private ImageView[] drop;
	private ImageView imageViewA;
	private ImageView imageViewB;
	private Gallery galley;
	private LinearLayout linearLayout;
	private Button cinemaSchedule;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_movie_cinema_info);
	
	initView();
	initGallery();
	initInfo();
	initEvent();
	
}
private void initEvent() {
	// TODO Auto-generated method stub
	backButton.setOnClickListener(this);
	cinemaSchedule.setOnClickListener(this);
	galley.setOnItemSelectedListener(new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			colorChange(position);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
	});
}
private void colorChange(int position) {
	// TODO Auto-generated method stub
	for (int i = 0; i < drop.length; i++) {
		drop[i].setBackgroundResource(R.drawable.home_point_pic);
	}
	drop[position].setBackgroundResource(R.drawable.home_point_selected_pic);
}
private void initView() {
	// TODO Auto-generated method stub
	cinemaName = (TextView) findViewById(R.id.moive_cinema_info_title);
	backButton = (Button) findViewById(R.id.moive_cinema_info_back);
	galley = (Gallery) findViewById(R.id.movie_cinema_info_gallery);
	linearLayout = (LinearLayout) findViewById(R.id.movie_cinema_info_drop);
	imageViewA = (ImageView) findViewById(R.id.im_a);
	imageViewB = (ImageView) findViewById(R.id.im_b);
	cinemaSchedule = (Button) findViewById(R.id.movie_cinema_info_seat);
}
@Override
public void onClick(View v) {
	switch (v.getId()) {
	case R.id.moive_cinema_info_back:
		finish();
		break;
	case R.id.movie_cinema_info_seat:
		toMovieSchedule();
		break;
	default:
		break;
	}
}
private void toMovieSchedule() {
	// TODO Auto-generated method stub
	Intent intent = new Intent(getApplicationContext(), MovieCinemaScheduleActivity.class);
	intent.putExtra("cinemaName", cinemaName.getText().toString());
	startActivity(intent);
}
private void initGallery() {
	MovieData data = new MovieData();
	drop = new ImageView[data.getMovieContentImages().length];
	for (int i = 0; i < data.getMovieContentImages().length; i++) {
		drop[i] = new ImageView(this);
		drop[i].setBackgroundResource(R.drawable.home_point_pic);
		linearLayout.addView(drop[i]);
	}
	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	for (int i = 0; i < data.getMovieContentImages().length; i++) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("image", data.getMovieContentImages()[i]);
		list.add(map);
	}
	galley.setAdapter(new SimpleAdapter(this, list, R.layout.cinema_info_gallery, new String[]{"image"}, new int[]{R.id.cinema_info_gallery_image}));
}
@SuppressWarnings("unchecked")
private void initInfo() {
	Intent intent = new Intent();
	intent = this.getIntent();
	Map<String, Object> map = new HashMap<String, Object>();
	map = (Map<String, Object>) intent.getSerializableExtra("cinemaInfo");
	cinemaName.setText((CharSequence) map.get("movieCinemaName"));
	imageViewA.setImageResource((int) map.get("movieCinemaImageViewA"));
	imageViewB.setImageResource((int) map.get("movieCinemaImageViewB"));
}
}
