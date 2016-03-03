package com.ct.movie.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieTimeDate {
	private List<Map<String, Object>> lists;
	private String[] dateHinit;
	private String[] monthHinit;

	public MovieTimeDate() {
		super();
		GetTime();
	}

	public List<Map<String, Object>> getLists() {
		return lists;
	}

	public void setLists(List<Map<String, Object>> lists) {
		this.lists = lists;
	}
	private void GetTime() {
		dateHinit = new String[7];
		monthHinit = new String[7];
		Calendar c = Calendar.getInstance();		
		lists = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 7; i++) {
			monthHinit[i] = c.get(Calendar.MONTH)+1+"月"+c.get(Calendar.DATE)+"日";
			dateHinit[i] = getUpperDay(c.get(Calendar.DAY_OF_WEEK)-1);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("month", monthHinit[i]);
			map.put("day", dateHinit[i]);
			lists.add(map);	
		}
	}
	private String getUpperDay(int n) {
		String[] days = new String[] {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六","星期日"};
		String day = days[n-1];
		return day;
	}
}
