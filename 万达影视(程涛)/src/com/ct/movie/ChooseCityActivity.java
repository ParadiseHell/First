package com.ct.movie;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ChooseCityActivity extends Activity implements OnClickListener{
	private ImageView chooseCityBack;
	private ListView cityList;
	
	private List<String> list = new ArrayList<String>();  
	private List<String> listTag = new ArrayList<String>();  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_city);
		
		initView();
		initEvent();
	}
	private void initEvent() {
		chooseCityBack.setOnClickListener(this);
	}
	private void initView() {
		chooseCityBack = (ImageView) findViewById(R.id.tv_choose_city_back);
		
		cityList = (ListView) findViewById(R.id.choose_city_list);
		getData();
		cityList.setAdapter(new MyAdapter(this, list, listTag));
		cityList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
					Intent data = new Intent();
					data.putExtra("city", list.get(position));
					ChooseCityActivity.this.setResult(0, data);
					ChooseCityActivity.this.finish();
			}
		});
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_choose_city_back:
			finish();
			break;

		default:
			break;
		}
	}
	public void getData() {
		String city_name_list[] = ChooseCityActivity.this.getResources()
				.getStringArray(R.array.city_description_list);
		
		String cityTag[] = { "华北区", "东北区", "东南区", "西南区", "西北区"};
		int listsize[] = { 0, 3,3,3,3,3};

		for (int j = 1; j < listsize.length; j++) {
			list.add(cityTag[j - 1]);
			listTag.add(cityTag[j - 1]);
			listsize[j] = listsize[j - 1] + listsize[j];
			for (int i = listsize[j - 1]; i < listsize[j]; i++) {
				list.add(city_name_list[i]);
			}
		}
	}
	@SuppressLint("InflateParams")
	class MyAdapter extends ArrayAdapter<String>{
		private List<String> listTag = null;
		
		public MyAdapter(Context context, List<String> objects, List<String> tags) {  
	        super(context, 0, objects);  
	        this.listTag = tags;  
	    }
		public boolean isEnabled(int position) {  
	        if(listTag.contains(getItem(position))){  
	            return false;  
	        }  
	        return super.isEnabled(position);  
	    }  
	    @SuppressLint("InflateParams")
		@Override  
	    public View getView(int position, View convertView, ViewGroup parent) {  
	        View view = convertView;  
	        if(listTag.contains(getItem(position))){  
	            view = LayoutInflater.from(getContext()).inflate(R.layout.citylist_tag, null);  
	        }else{                      
	            view = LayoutInflater.from(getContext()).inflate(R.layout.citylist_item, null);  
	        }  
	        TextView textView = (TextView) view.findViewById(R.id.group_list_item_text);  
	        textView.setText(getItem(position));  
	        return view;  
	    }  
	}
}
