package com.techgig.thirstycrow;

import java.util.Arrays;

public class CandidateCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] array={1,3,4,67,23,12,0,23};
		Arrays.sort(array);
		System.out.println(ThirstyCrowProblem(array,8,2));

	}
	
	public static int ThirstyCrowProblem(int [] input1, int input2, int input3)
	{
		int noOfStonesNeeded=0;
		Arrays.sort(input1);
		int minOfStoneForFilling=input1[input3-1];
		noOfStonesNeeded=minOfStoneForFilling* input2;
		for(int i=0;i<input3;i++)
		{
			if(input1[i]<minOfStoneForFilling)
			{
				noOfStonesNeeded= noOfStonesNeeded-(minOfStoneForFilling-input1[i]);
			}
		}
		return noOfStonesNeeded;
	}

}
