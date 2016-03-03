package com.ct.movie.adapter;

import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ct.movie.R;

public class MovieCinemaListAdapter extends BaseExpandableListAdapter {
	private Map<String, List<Map<String, Object>>> mChild;
	private List<String> mGroup;
	private LayoutInflater layoutInflater;
	
	class  GroupViewHolder{
		TextView movieCinemaCity;
	}
	
	class ChildViewHolder {
		TextView movieCinemaName;
		TextView movieCinemaAddress;
		ImageView movieCinemaImageViewA;
		ImageView movieCinemaImageViewB;
	}

	public MovieCinemaListAdapter(Context context,List<String> group,Map<String, List<Map<String, Object>>> child) {
		mChild = child;
		mGroup = group;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return mGroup.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		String key = mGroup.get(groupPosition);
		return mChild.get(key).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return mGroup.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		String key = mGroup.get(groupPosition);
		return mChild.get(key).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		GroupViewHolder groupViewHolder = null;
		if (convertView == null) {
			groupViewHolder = new GroupViewHolder();
			convertView = layoutInflater.inflate(R.layout.movie_cinema_list_city, null);
			groupViewHolder.movieCinemaCity = (TextView) convertView.findViewById(R.id.movie_cinema_list_city_name);
			convertView.setTag(groupViewHolder);
		} else {
			groupViewHolder = (GroupViewHolder) convertView.getTag();
		}
		groupViewHolder.movieCinemaCity.setText((String)mGroup.get(groupPosition));
		return convertView;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ChildViewHolder childViewHolder = null;
		String key = mGroup.get(groupPosition);
		if (convertView == null) {
			childViewHolder = new ChildViewHolder();
			convertView = layoutInflater.inflate(R.layout.movie_cinema_list_content, null);
			childViewHolder.movieCinemaName= (TextView) convertView.findViewById(R.id.movie_cinema_name);
			childViewHolder.movieCinemaAddress=(TextView) convertView.findViewById(R.id.movie_cinema_address);
			childViewHolder.movieCinemaImageViewA=(ImageView) convertView.findViewById(R.id.movie_cinema_style_one);
			childViewHolder.movieCinemaImageViewB=(ImageView) convertView.findViewById(R.id.movie_cinema_style_two);
			
			convertView.setTag(childViewHolder);
		} else {
			childViewHolder = (ChildViewHolder) convertView.getTag();
			
		}
		childViewHolder.movieCinemaName.setText((String)mChild.get(key).get(childPosition).get("movieCinemaName"));
		childViewHolder.movieCinemaAddress.setText((String)mChild.get(key).get(childPosition).get("movieCinemaAddress"));
		childViewHolder.movieCinemaImageViewA.setImageResource((int) mChild.get(key).get(childPosition).get("movieCinemaImageViewA"));
		childViewHolder.movieCinemaImageViewB.setImageResource((int) mChild.get(key).get(childPosition).get("movieCinemaImageViewB"));
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
