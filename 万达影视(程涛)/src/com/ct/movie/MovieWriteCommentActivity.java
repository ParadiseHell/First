package com.ct.movie;

import com.ct.movie.data.MovieComment;
import com.ct.movie.data.MovieCommentDBOpenHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

public class MovieWriteCommentActivity extends Activity implements OnClickListener{
	private ImageView btnExit;
	private EditText commentContent;
	private EditText commentTitle;
	private Button commentDone;
	private RatingBar commentRatingBar;
	
	private SQLiteOpenHelper helper;
	private String tag;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_write_comment);
		
		initView();
		initEvent();
	}
	private void initEvent() {
		tag = this.getIntent().getStringExtra("tag");
		
		btnExit.setOnClickListener(this);
		commentDone.setOnClickListener(this);
		
	}
	private void initView() {
		commentRatingBar = (RatingBar) findViewById(R.id.moive_write_comment_ratingbar);
		commentContent = (EditText) findViewById(R.id.moive_write_comment_content);
		commentTitle = (EditText) findViewById(R.id.moive_write_comment_content_title);
		btnExit = (ImageView) findViewById(R.id.moive_write_comment_back);
		commentDone = (Button) findViewById(R.id.moive_write_comment_done);
	}
	@Override
	public void onClick(View v) {
switch (v.getId()) {
case R.id.moive_write_comment_back:
	finish();
	break;
case R.id.moive_write_comment_done:
	saveComment();
	break;
default:
	break;
}		
	}
	private void saveComment() {
		MovieComment comment = new MovieComment();
		helper = new MovieCommentDBOpenHelper(this);
		SQLiteDatabase db = helper.getWritableDatabase();
		String title = commentTitle.getText().toString();
		String content = commentContent.getText().toString();
		String rating = commentRatingBar.getRating()+"";
		ContentValues values = new ContentValues();
		values.put("title", title);
		values.put("content", content);
		values.put("rating", rating);
		values.put("like", 0);
		values.put("dislike", 0);
		values.put("tag", tag);
		values.put("image", comment.getImages()[(int) ((Math.random()*10)%4)]);
		long result = db.insert("comment", null, values);
		if (result == -1) {
			Toast.makeText(getApplicationContext(), "评论失败", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(getApplicationContext(), "评论成功", Toast.LENGTH_SHORT).show();
		}
		db.close();
		finish();
	}
}
