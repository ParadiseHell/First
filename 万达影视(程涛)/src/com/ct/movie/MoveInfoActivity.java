package com.ct.movie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ct.movie.data.MovieData;

@SuppressWarnings({ "unused", "deprecation" })
public class MoveInfoActivity extends Activity implements OnClickListener {
	private ImageView back;
	private ImageView share;
	private TextView slectScence;
	private TextView prevue;
	private Gallery movieInfoGallery;
	private LinearLayout galleryDrop;
	private ImageView grallerdot[];
	private List<Map<String, Object>> lists;
	private ListView shareListView;
	
	private TextView movieScore;
	private TextView movieTitle;
	private TextView movieComment;
	private TextView movieExactContent;
	private TextView movieInfoContent;
	private TextView movieInfoTime;
	
	private LinearLayout startLinearLayout;
	private ImageView startImageView[];
	
	private String tag;
	private Map<String, Object> movieMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_info);

		initView();
		initEvent();
	}

	private void initEvent() {
		movieScore.setOnClickListener(this);
		back.setOnClickListener(this);
		share.setOnClickListener(this);
		slectScence.setOnClickListener(this);
		prevue.setOnClickListener(this);
		movieInfoGallery
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						ChangeColor(position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub

					}
				});
		;
	}

	private void initView() {
		back = (ImageView) findViewById(R.id.moive_info_back);
		share = (ImageView) findViewById(R.id.moive_info_share);
		slectScence = (TextView) findViewById(R.id.movie_info_slect_sence);
		prevue = (TextView) findViewById(R.id.movie_info_prevue);

		movieInfoGallery = (Gallery) findViewById(R.id.movie_info_gallery);
		galleryDrop = (LinearLayout) findViewById(R.id.moive_info_gallery_drop);
		getContentImages();
		movieInfoGallery.setAdapter(new SimpleAdapter(this, lists,
				R.layout.movie_info_list, new String[] { "image" },
				new int[] { R.id.movie_info_list_image }));
		grallerdot = new ImageView[lists.size()];
		for (int i = 0; i < lists.size(); i++) {
			grallerdot[i] = new ImageView(this);
			grallerdot[i].setBackgroundResource(R.drawable.home_point_pic);
			galleryDrop.addView(grallerdot[i]);
		}
		
		
		movieScore = (TextView) findViewById(R.id.movie_info_score);
		movieTitle = (TextView) findViewById(R.id.moive_info_title);
		movieComment = (TextView) findViewById(R.id.movie_info_comment);
		movieExactContent = (TextView) findViewById(R.id.movie_info_detail);
		movieInfoContent = (TextView) findViewById(R.id.moive_info_detail_direct);
		movieInfoTime = (TextView) findViewById(R.id.moive_info_detail_time);
		
		startLinearLayout = (LinearLayout) findViewById(R.id.movie_info_ratingbar);
		startImageView = new ImageView[5];
		initInfo();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.moive_info_back:
			finish();
			break;
		case R.id.moive_info_share:
			shareMovie();
			break;
		case R.id.movie_info_slect_sence:
			seeMovieSchedule();
			break;
		case R.id.movie_info_prevue:

			break;
		case R.id.movie_info_score:
			toWriteComment();
			break;
		default:
			break;
		}
	}

	private void toWriteComment() {
		Intent intent = new Intent(getApplicationContext(), MovieCommentAcitvity.class);
		intent.putExtra("name", movieTitle.getText());
		intent.putExtra("tag", tag);
		startActivity(intent);
	}
	
	private void seeMovieSchedule() {
		Intent intent = new Intent(getApplicationContext(),MovieScheduleAcitivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("movie", (Serializable) movieMap);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	protected void ChangeColor(int position) {
		for (int i = 0; i < lists.size(); i++) {
			grallerdot[i].setBackgroundResource(R.drawable.home_point_pic);
		}
		grallerdot[position]
				.setBackgroundResource(R.drawable.home_point_selected_pic);
	}

	@SuppressLint("InflateParams")
	private void shareMovie() {
		Intent info = this.getIntent();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) info.getSerializableExtra("movie");
		Intent intent=new Intent(Intent.ACTION_SEND);   
        intent.setType("image/*");   
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");   
        intent.putExtra(Intent.EXTRA_TEXT, "电影:<<"+map.get("movieName")+">>超级好看~~");    
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
        startActivity(Intent.createChooser(intent, "分享")); 
	}

	private List<Map<String, Object>> getShareData() {
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		int imges[] = new int[] { R.drawable.dialog_sina,
				R.drawable.dialog_kxw, R.drawable.dialog_renren,
				R.drawable.dialog_douban };
		for (int i = 0; i < imges.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("image", imges[i]);
			lists.add(map);
		}
		return lists;

	}
	
	private void initInfo(){
		Intent intent = this.getIntent();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) intent.getSerializableExtra("movie");
		movieMap = new HashMap<String, Object>();
		movieMap = map;
		movieTitle.setText((CharSequence) map.get("movieName"));
		movieScore.setText((CharSequence) map.get("movieScore"));
		movieComment.setText((CharSequence) map.get("movieCommentNumber"));
		movieExactContent.setText((CharSequence) map.get("movieExactContent"));
		movieInfoContent.setText((CharSequence) map.get("movieInfoContent"));
		movieInfoTime.setText((CharSequence) map.get("movieInfoTime"));
		
		tag = map.get("movieTag").toString();
		
		int temp = (int) Math.round(Double.parseDouble(map.get("movieScore").toString()));
		for (int i = 0; i < temp; i++) {
			startImageView[i]= new ImageView(this);
			startImageView[i].setBackgroundResource(R.drawable.star_big_fill);
			startLinearLayout.addView(startImageView[i]);
		}
		for (int i = temp; i < 5; i++) {
			startImageView[i]= new ImageView(this);
			startImageView[i].setBackgroundResource(R.drawable.star_big_empty);
			startLinearLayout.addView(startImageView[i]);
		}
		
	}
	
	private void getContentImages() {
		MovieData data = new MovieData();
		lists = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < data.getMovieContentImages().length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("image", data.getMovieContentImages()[i]);
			lists.add(map);
		}
	}
}
