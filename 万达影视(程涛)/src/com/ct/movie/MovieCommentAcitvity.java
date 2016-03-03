package com.ct.movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.ct.movie.data.MovieCommentDBOpenHelper;

public class MovieCommentAcitvity extends Activity implements OnClickListener {
	private ImageView editImage;
	private ImageView btnBack;
	private ListView movieCommentListView;
	private List<Map<String, Object>> lists;
	private SQLiteOpenHelper helper;

	private String tag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_comment);
		MovieCommentDBOpenHelper helper = new MovieCommentDBOpenHelper(this);
		helper.getWritableDatabase();
		tag = this.getIntent().getStringExtra("tag");
		initView();
		initEvent();
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		initListView();
	}

	private void initEvent() {
		// TODO Auto-generated method stub

		editImage.setOnClickListener(this);
		btnBack.setOnClickListener(this);

	}

	private void initView() {
		// TODO Auto-generated method stub
		btnBack = (ImageView) findViewById(R.id.moive_comment_back);
		movieCommentListView = (ListView) findViewById(R.id.movie_comment_list_view);
		editImage = (ImageView) findViewById(R.id.moive_comment_edit);
		initListView();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.moive_comment_edit:
			goToWriteComment();
			break;
		case R.id.moive_comment_back:
			finish();
			break;
		default:
			break;
		}
	}

	private void goToWriteComment() {
		Intent intent = new Intent(getApplicationContext(),
				MovieWriteCommentActivity.class);
		intent.putExtra("tag", tag);
		startActivity(intent);
	}

	private void getLists() {
		helper = new MovieCommentDBOpenHelper(this);
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.query("comment", null, "tag=?", new String[]{tag},
				null, null, "_id DESC");
		lists = new ArrayList<Map<String, Object>>();
		cursor.moveToNext();
		for (int i = 0; i < cursor.getCount(); i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("image", Integer.parseInt(cursor.getString(cursor
					.getColumnIndex("image"))));
			map.put("title", cursor.getString(cursor.getColumnIndex("title")));
			map.put("content",
					cursor.getString(cursor.getColumnIndex("content")));
			lists.add(map);
			cursor.moveToNext();
		}
		db.close();
	}

	private void initListView() {
		getLists();
		if (lists.size() == 0) {
			Toast.makeText(getApplicationContext(), "没有评论,给电影写一些评论吧", Toast.LENGTH_SHORT).show();
		} else {
		movieCommentListView.setAdapter(new SimpleAdapter(
				getApplicationContext(), lists, R.layout.comment_list,
				new String[] { "image", "title", "content" }, new int[] {
						R.id.comment_image, R.id.comment_title,
						R.id.comment_content }));}
	}

}
