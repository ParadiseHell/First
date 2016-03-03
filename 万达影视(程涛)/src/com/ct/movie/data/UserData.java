package com.ct.movie.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserData {
	private List<String> group;
	private Map<String, List<String>> child;
	public UserData() {
		super();
		initInfo();
	}
	public List<String> getGroup() {
		return group;
	}
	public void setGroup(List<String> group) {
		this.group = group;
	}
	public Map<String, List<String>> getChild() {
		return child;
	}
	public void setChild(Map<String, List<String>> child) {
		this.child = child;
	}
	private void initInfo() {
		//
		group = new ArrayList<String>();
		group.add("我的账户");
		group.add("账户管理");
		
		
		child = new HashMap<String, List<String>>();
		
		List<String> list1 = new ArrayList<String>();
		list1.add("注册\\登陆");
		list1.add("我的购票记录");
		list1.add("我的积分");
		list1.add("我的礼品盒");
		list1.add("VIP会员介绍");
		child.put(group.get(0), list1);
		
		List<String> list2 = new ArrayList<String>();
		list2.add("修改密码");
		list2.add("资料修改");
		child.put(group.get(1), list2);
	}
	
}
