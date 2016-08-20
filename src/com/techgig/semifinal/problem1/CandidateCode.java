package com.techgig.semifinal.problem1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CandidateCode {
	
	
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> inList= new ArrayList<Integer>();
		inList.add(new Integer(2));
		inList.add(new Integer(3));
		inList.add(new Integer(5));
		List<Integer> outList= new ArrayList<Integer>();
		getPipe(inList, outList);
		System.out.println(outList);
		

	}
	
	public static void getPipe(List<Integer> inList,List<Integer> outList)
	{
		if(0==inList.size())
		{
			return;
			
		}
		if(1==inList.size())
		{
			outList.add(inList.get(0));
			inList.remove(0);
		}
		Collections.sort(inList);
		int first= inList.get(0);
		int second=inList.get(1);
		int sum=first+second;
		inList.remove(0);
		inList.remove(1);
		outList.add(sum);
		inList.add(sum);
		getPipe(inList, outList);
	}

}
