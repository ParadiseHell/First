package com.ct.movie.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDBOpenHelper extends SQLiteOpenHelper {

	public MovieDBOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public MovieDBOpenHelper(Context context) {
		super(context, "movie.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table movie(_id integer primary key autoincrement,movieName varchar(255),movieSimpleContent varchar(255),movieCommentNumber varchar(255),movieScore varchar(255),movieExactContent varchar(255),movieInfoContent varchar(255),movieInfoTime varchar(255),movieImage integer(255),movieTag integer(255))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
