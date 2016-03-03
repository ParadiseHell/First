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
		group.add("�ҵ��˻�");
		group.add("�˻�����");
		
		
		child = new HashMap<String, List<String>>();
		
		List<String> list1 = new ArrayList<String>();
		list1.add("ע��\\��½");
		list1.add("�ҵĹ�Ʊ��¼");
		list1.add("�ҵĻ���");
		list1.add("�ҵ���Ʒ��");
		list1.add("VIP��Ա����");
		child.put(group.get(0), list1);
		
		List<String> list2 = new ArrayList<String>();
		list2.add("�޸�����");
		list2.add("�����޸�");
		child.put(group.get(1), list2);
	}
	
}
