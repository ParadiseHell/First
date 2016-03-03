package com.ct.movie.adapter;

import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ct.movie.R;

public class MovieCinemaScheduleAdapter extends BaseExpandableListAdapter {
	private Context context;
	private LayoutInflater layoutInflater;
	private List<String> group;
	private Map<String, List<Map<String, Object>>> child;
	private String[] keys;

	class GroupViewHolder {
		TextView title;
	}

	class ChildViewHolder {
		LinearLayout linearLayout;
	}

	public MovieCinemaScheduleAdapter(Context context, List<String> group,
			Map<String, List<Map<String, Object>>> child) {
		super();
		this.context = context;
		this.group = group;
		this.child = child;
		this.layoutInflater = LayoutInflater.from(context);
		keys = new String[group.size()];
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return group.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		String key = group.get(groupPosition);
		return child.get(key).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return group.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		String key = group.get(groupPosition);
		return child.get(key).get(childPosition);
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
			convertView = layoutInflater.inflate(R.layout.schedule_list_title,
					null);
			groupViewHolder.title = (TextView) convertView
					.findViewById(R.id.schedule_list_title);
			convertView.setTag(groupViewHolder);
		} else {
			groupViewHolder = (GroupViewHolder) convertView.getTag();
		}
		groupViewHolder.title.setText((String) group.get(groupPosition));
		return convertView;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		String key = group.get(groupPosition);
		ChildViewHolder childViewHolder = null;
		if (convertView == null) {
			childViewHolder = new ChildViewHolder();
			convertView = layoutInflater.inflate(
					R.layout.schedule_list_content, null);
			childViewHolder.linearLayout = (LinearLayout) convertView
					.findViewById(R.id.schedule_list_content);
			convertView.setTag(childViewHolder);
		} else {
			childViewHolder = (ChildViewHolder) convertView.getTag();
		}
		if (keys[groupPosition] == null) {
			childViewHolder.linearLayout.setBackgroundColor(Color.BLACK);
			LinearLayout[] verticaLinearLayouts = new LinearLayout[4];
			String time[][] = new String[][] {};
			int color[][] = new int[][] {};
			time = (String[][]) child.get(key).get(childPosition).get("time");
			color = (int[][]) child.get(key).get(childPosition).get("color");
			for (int i = 0; i < verticaLinearLayouts.length; i++) {
				verticaLinearLayouts[i] = new LinearLayout(context);
				verticaLinearLayouts[i].setOrientation(LinearLayout.HORIZONTAL);
				for (int j = 0; j < 6; j++) {
					Button button = new Button(context);
					LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
							0, 40, 0.1f);
					layoutParams.setMargins(1, 1, 1, 1);
					button.setLayoutParams(layoutParams);
					button.setTextSize(12);
					setBackGround(color[i][j], button);
					button.setText(time[i][j]);
					button.setTextColor(Color.WHITE);
					verticaLinearLayouts[i].addView(button);
				}
				childViewHolder.linearLayout.addView(verticaLinearLayouts[i]);
			}
			keys[groupPosition] = key;
		}
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	private void setBackGround(int n, Button button) {
		switch (n) {
		case 0:
			button.setBackgroundColor(Color.rgb(26, 26, 26));
			;
			break;
		case 1:
			button.setBackgroundColor(Color.rgb(1, 177, 14));
			break;
		case 2:
			button.setBackgroundColor(Color.rgb(180, 96, 4));
			break;
		case 3:
			button.setBackgroundColor(Color.RED);
			break;

		default:
			break;
		}
	}

}
