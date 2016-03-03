package com.ct.movie.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ct.movie.R;

public class MovieCinemaData {
	private List<String> group;
	private Map<String, List<Map<String, Object>>> child;

	public MovieCinemaData() {
		super();
		getGroupInfo();
		getChildInfo();
	}

	public List<String> getGroup() {
		return group;
	}

	public void setGroup(List<String> group) {
		this.group = group;
	}

	public Map<String, List<Map<String, Object>>> getChild() {
		return child;
	}

	public void setChild(Map<String, List<Map<String, Object>>> child) {
		this.child = child;
	}

	private void getChildInfo() {
		int [] images=new int[]{R.drawable.d3_pic,R.drawable.imax_pic,R.drawable.vip_pic};
		String []address =  new String[]{"����","�Ϻ�","����","�ɶ�","���"};
		child = new HashMap<String, List<Map<String,Object>>>();
		int temp = 0;
		for (int i = 0; i < address.length; i++) {
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			for (int j = 0; j < 4; j++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("movieCinemaName", address[i]+"���Ӱ��"+(j+1)+"��");
				map.put("movieCinemaAddress", address[i]+"��XX��XX·");
				map.put("movieCinemaImageViewA", images[temp]);
				if (temp == 2) {
					temp = 0;
					map.put("movieCinemaImageViewB", images[temp]);
				}else {
					map.put("movieCinemaImageViewB", images[temp+1]);
				}								
				list.add(map);
				temp++;	
			}
			child.put(group.get(i),list);
		}
		
	}

	private void getGroupInfo() {
		group = new ArrayList<String>();
		group.add("����");
		group.add("�Ϻ�");
		group.add("����");
		group.add("�ɶ�");
		group.add("���");
	}
	
}
