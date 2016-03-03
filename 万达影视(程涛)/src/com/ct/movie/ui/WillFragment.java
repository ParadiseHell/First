package com.ct.movie.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.ct.movie.MoveInfoActivity;
import com.ct.movie.R;
import com.ct.movie.data.MovieDBOpenHelper;
import com.ct.movie.data.MovieData;

public class WillFragment extends Fragment implements OnItemClickListener{
	private ListView willList;
	private Context context;
	private List<Map<String, Object>> infos;

	public WillFragment() {
		super();
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		context = activity.getApplicationContext();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.acitivity_main_tab3,container, false);
		willList = (ListView) view.findViewById(R.id.list_view_tab3);
		getData();
		if (infos.size() == 0) {
			Toast.makeText(getActivity(), "没有数据,去添加一些数据吧", Toast.LENGTH_SHORT).show();
		} else {
			willList.setAdapter(new SimpleAdapter(context, infos,
					R.layout.list_item_info, new String[] { "movieImage", "movieName",
				"movieSimpleContent", "movieCommentNumber", "movieScore" }, new int[] {
				R.id.list_item_title_image, R.id.list_item_title,
				R.id.list_item_content, R.id.list_item_comment_text,
				R.id.list_item_comment_score }));
			willList.setOnItemClickListener(this);
		}		
		return view;		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(context, MoveInfoActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("movie", (Serializable) infos.get(position));
		intent.putExtras(bundle);
		startActivity(intent);	
	}
	private void getData(){		
		infos = new ArrayList<Map<String,Object>>();
		MovieDBOpenHelper helper = new MovieDBOpenHelper(context);
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.query("movie", null, null, null, null, null, "_id DESC");
		MovieData data = new MovieData(cursor.getCount());
		cursor.moveToNext();
		for (int i = 0; i < cursor.getCount(); i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			data.getMovieName()[i] = cursor.getString(cursor.getColumnIndex("movieName"));
			data.getMovieImage()[i] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("movieImage")));
			data.getMovieSimpleContent()[i] = cursor.getString(cursor.getColumnIndex("movieSimpleContent"));
			data.getMovieCommentNumber()[i] = cursor.getString(cursor.getColumnIndex("movieCommentNumber"));
			data.getMovieScore()[i] = cursor.getString(cursor.getColumnIndex("movieScore"));
			data.getMovieExactContent()[i] = cursor.getString(cursor.getColumnIndex("movieExactContent"));
			data.getMovieInfoContent()[i] = cursor.getString(cursor.getColumnIndex("movieInfoContent"));
			data.getMovieInfoTime()[i] = cursor.getString(cursor.getColumnIndex("movieInfoTime"));
			data.getMovieTag()[i] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("movieTag")));
			
			map.put("movieName", data.getMovieName()[i]);
			map.put("movieImage", data.getMovieImage()[i]);
			map.put("movieSimpleContent", data.getMovieSimpleContent()[i]);
			map.put("movieCommentNumber", data.getMovieCommentNumber()[i]);
			map.put("movieScore", data.getMovieScore()[i]);
			map.put("movieExactContent", data.getMovieExactContent()[i]);
			map.put("movieInfoContent", data.getMovieInfoContent()[i]);
			map.put("movieInfoTime", data.getMovieInfoTime()[i]);
			map.put("movieTag", data.getMovieTag()[i]);
			infos.add(map);
			cursor.moveToNext();
		}
	}
}
