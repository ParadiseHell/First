package com.ct.movie.adapter;

import java.util.List;
import java.util.Map;
import com.ct.movie.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class UserAdapter extends BaseExpandableListAdapter {
	private LayoutInflater mlayoutInflater;
	private List<String> mGroup;
	private Map<String, List<String>> mChild;

	public UserAdapter(Context context, List<String> mGroup,
			Map<String, List<String>> mChild) {
		super();
		this.mlayoutInflater = LayoutInflater.from(context);
		this.mGroup = mGroup;
		this.mChild = mChild;
	}
	
	class GroupViewHolder{
		TextView title;
	}
	class ChildViewHolder{
		TextView content;
	}
	
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return mGroup.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
			convertView = mlayoutInflater.inflate(R.layout.user_title, null);
			groupViewHolder.title = (TextView) convertView.findViewById(R.id.user_title);
			convertView.setTag(groupViewHolder);
		}else {
			groupViewHolder = (GroupViewHolder) convertView.getTag();
		}
		groupViewHolder.title.setText((String)mGroup.get(groupPosition));
		return convertView;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		String key = mGroup.get(groupPosition);
		ChildViewHolder childViewHolder = null;
		if (convertView == null) {
			childViewHolder = new ChildViewHolder();
			convertView = mlayoutInflater.inflate(R.layout.user_content, null);
			childViewHolder.content = (TextView) convertView.findViewById(R.id.user_content);
			convertView.setTag(childViewHolder);
		} else {
			childViewHolder = (ChildViewHolder) convertView.getTag();
		}
		childViewHolder.content.setText((String)mChild.get(key).get(childPosition));
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
