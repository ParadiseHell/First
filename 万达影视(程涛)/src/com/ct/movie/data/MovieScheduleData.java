package com.ct.movie.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieScheduleData {
	private List<String> group;
	private Map<String, List<Map<String, Object>>> child;
	public MovieScheduleData() {
		super();
		initInfo();
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
	private void initInfo() {
		//group
		group = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			group.add("北京万达电影城"+(i+1));
		}
		
		//child
		///
		String time1[][] = new String[4][6];
		String time2[][] = new String[4][6];
		String time3[][] = new String[4][6];
		int color1[][] = new int[4][6];
		int color2[][] = new int[4][6];
		int color3[][] = new int[4][6];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				time1[i][j] = "";
				time2[i][j] = "";
				time3[i][j] = "";
				color1[i][j] = 0;
				color2[i][j] = 0;
				color3[i][j] = 0;
			}
		}
		time1[1][2] = "09:30";
		time1[1][4] = "11:15";
		time1[3][0] = "19:00";
		time1[3][1] = "20:30";
		time1[3][2] = "21:45";		
		color1[1][2] = 1;
		color1[1][4] = 1;
		color1[3][0] = 1;
		color1[3][1] = 1;
		color1[3][2] = 3;
		
		time2[0][0] = "01:15";
		time2[2][0] = "13:55";
		time2[3][1] = "20:15";
		time2[3][3] = "22:15";
		time2[3][5] = "00:15";
		color2[0][0] = 1;
		color2[2][0] = 1;
		color2[3][1] = 3;
		color2[3][3] = 3;
		color2[3][5] = 2;
		
		time3[0][5] = "06:15";
		time3[1][1] = "08:25";
		time3[1][3] = "10:35";
		time3[3][3] = "22:15";
		color3[0][5] = 1;
		color3[1][1] = 1;
		color3[1][3] = 2;
		color3[3][3] = 3;
		/////
		child = new HashMap<String, List<Map<String,Object>>>();
		for (int i = 0; i < 3; i++) {
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			if (i == 0) {
				map.put("time", time1);
				map.put("color", color1);
			} else if (i == 1) {
				map.put("time", time2);
				map.put("color", color2);
			}else if(i == 2){
				map.put("time", time3);
				map.put("color", color3);
			}
			list.add(map);
			child.put(group.get(i), list);
		}
	}
}
