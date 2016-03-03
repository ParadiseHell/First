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
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.ct.movie.data.MovieData;

@SuppressWarnings("deprecation")
public class GalleryActivity extends Activity implements OnClickListener{
	private ImageView galleryImage;
	private Gallery gallery;
	private List<Map<String, Object>> lists;
	private LinearLayout grallerDrop;
	private ImageView grallerdot[];
	private TextView city;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);
		initView();
		initEvent();

	}

	private void initEvent() {
		//addData();
		// TODO Auto-generated method stub
		galleryImage.setOnClickListener(this);
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				ChangeColor(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

	protected void ChangeColor(int position) {
		for (int i = 0; i < lists.size(); i++) {
			grallerdot[i].setBackgroundResource(R.drawable.home_point_pic);
		}
		grallerdot[position].setBackgroundResource(R.drawable.home_point_selected_pic);		
	}

	private void initView() {
		
		// TODO Auto-generated method stub
		city = (TextView) findViewById(R.id.tv_choose_city);
		Intent intent = getIntent();
		city.setText(intent.getStringExtra("city"));
		
		gallery = (Gallery) findViewById(R.id.movie_gallery);
		galleryImage = (ImageView) findViewById(R.id.iv_main_top_slide);

		getData();
		gallery.setAdapter(new SimpleAdapter(this, lists,
				R.layout.gallery_item, new String[] { "movieImage", "movieName",
						"movieSimpleContent", "movieCommentNumber", "movieScore" }, new int[] {
						R.id.gallery_image, R.id.gallery_title,
						R.id.gallery_content, R.id.gallery_comment,
						R.id.gallery_score }));
		
		grallerDrop = (LinearLayout) findViewById(R.id.gallery_drop);
		grallerdot = new ImageView[lists.size()];
		for (int i = 0; i < lists.size(); i++) {
			grallerdot[i] = new ImageView(this);
			grallerdot[i].setBackgroundResource(R.drawable.home_point_pic);
			grallerDrop.addView(grallerdot[i]);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_main_top_slide:
			finish();
			break;

		default:
			break;
		}
	}
	
	private void getData(){
		lists = new ArrayList<Map<String,Object>>();
		MovieData data = new MovieData();
		for (int i = 0; i < data.getMovieName().length; i++) {
			HashMap<String, Object>map = new HashMap<String, Object>();
			map.put("movieName", data.getMovieName()[i]);
			map.put("movieCommentNumber", data.getMovieCommentNumber()[i]);
			map.put("movieImage", data.getMovieImage()[i]);
			map.put("movieScore", data.getMovieScore()[i]);
			map.put("movieSimpleContent", data.getMovieSimpleContent()[i]);
			lists.add(map);
		}
	}
}
