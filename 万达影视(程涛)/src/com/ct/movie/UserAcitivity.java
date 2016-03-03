package com.ct.movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ct.movie.adapter.UserAdapter;
import com.ct.movie.data.UserData;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;

public class UserAcitivity extends Activity implements OnClickListener{
	private Button userBack;
	private ExpandableListView expandableListView;
	private List<String> group;
	private Map<String, List<String>> child;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_user);
	initView();
	initListView();
	initEvent();
}

private void initEvent() {
	// TODO Auto-generated method stub
	userBack.setOnClickListener(this);
}

private void initView() {
	// TODO Auto-generated method stub
	userBack= (Button) findViewById(R.id.user_back);
	expandableListView= (ExpandableListView) findViewById(R.id.user_expandable_list_view);
}

@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	switch (v.getId()) {
	case R.id.user_back:
		finish();
		break;

	default:
		break;
	}
}
private void initListView() {
	group = new ArrayList<String>();
	child = new HashMap<String, List<String>>();
	UserData data = new UserData();
	group = data.getGroup();
	child = data.getChild();
	UserAdapter adapter = new UserAdapter(this, group, child);
	expandableListView.setAdapter(adapter);
	expandableListView.setGroupIndicator(null);
	expandableListView.expandGroup(0);
	expandableListView.expandGroup(1);
}
}
