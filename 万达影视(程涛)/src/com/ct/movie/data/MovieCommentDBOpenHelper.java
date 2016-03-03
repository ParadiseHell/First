package com.ct.movie.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieCommentDBOpenHelper extends SQLiteOpenHelper {

	public MovieCommentDBOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public MovieCommentDBOpenHelper(Context context) {
		super(context, "comment.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE comment(_id integer primary key autoincrement,title varchar(255),content varchar(255),rating varchar(255),like varchar(255),dislike varchar(255),tag varchar(255),image varchar(255))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
