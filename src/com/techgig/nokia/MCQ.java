package com.techgig.nokia;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MCQ {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<String> list= new LinkedList<String>();
		list.add(" x");
		list.add("xx");
		list.add("Xx");
		Collections.reverseOrder();
		Collections.sort(list);
		System.out.println(list);
	}

}
