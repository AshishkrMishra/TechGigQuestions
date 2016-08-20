package com.techgig.cadbury;

public class CandidateCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(distributeCadbury(5, 6, 3, 4));
	}
	public static int distributeCadbury(int input1,int input2,int input3,int input4)
	{
	
		int noOfChild=0;
		for(int xDimension=input1;xDimension<=input2;xDimension++)
		{
			for(int yDimension=input3;yDimension<=input4;yDimension++)
			{
				noOfChild+=getNoOfChildToBeFeed(xDimension,yDimension);
			}
		}
		return noOfChild;
	}
	private static int getNoOfChildToBeFeed(int xDimension, int yDimension) {
		// TODO Auto-generated method stub
		int noOfChild=1;
		if(xDimension!=yDimension)
		{
			int newXDimension=(xDimension<yDimension)?xDimension:(xDimension-yDimension);
			int newYDimension=(yDimension<xDimension)?yDimension:(yDimension-xDimension);
			noOfChild =noOfChild+getNoOfChildToBeFeed(newXDimension, newYDimension);
		}
		return noOfChild;
	}
	public static boolean isValid(int xDimension, int yDimension)
	{
		boolean isValid=true;
		if( xDimension<=0 || yDimension<=0 )
		{
			isValid=false;
		}
		return isValid;
	}
}
