package com.techgig.monkeyproblem;

public class CandiateCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] treeSizeList = { 1,2, 3, 4};
		int noOfTree = 4;
		int maxTime = getMaxTravleTime(treeSizeList, noOfTree);

		System.out.println("Max Time will be :=" + maxTime);
		//System.out.println("Max Time will be :=" + indexOfLogestTree(treeSizeList));

	}

	public static int getMaxTravleTime(int[] treeSizeArray, int noOfTree) {
		
		int sourceIndex=indexOfLogestTree(treeSizeArray);
		int maxDistance=findMaxDistance(treeSizeArray,sourceIndex);
		return maxDistance;
	}

	private static int findMaxDistance(int[] treeSizeArray, int sourceIndex) {
		int noOfTree=treeSizeArray.length;
		int currentIndex=sourceIndex;
		int tempDistance=1;
		for (int i=0;i<noOfTree-1;i++)
		{
			currentIndex=(currentIndex+1)%noOfTree;
			int clockwiseDistance=findClockWiseDistance(sourceIndex,currentIndex,treeSizeArray);
			int anitClockwiseDistance=findAntiClockWiseDistance(sourceIndex,currentIndex,treeSizeArray);
			int currentDistance=(clockwiseDistance<anitClockwiseDistance)?clockwiseDistance:anitClockwiseDistance;
			if (currentDistance>tempDistance)
			{
				tempDistance=currentDistance;
			}
		}
		return tempDistance;
	}
	
	public static int findClockWiseDistance(int sourceIndex,int destinationIndex,int [] treeSizeArray)
	{
		int paddestalDistance=java.lang.Math.abs(destinationIndex-sourceIndex);
		int monkeyDownTravel=treeSizeArray[sourceIndex];
		int monkeyUpTravel=treeSizeArray[destinationIndex];
		return paddestalDistance+monkeyDownTravel+monkeyUpTravel;
	}
	public static int findAntiClockWiseDistance(int sourceIndex,int destinationIndex,int [] treeSizeArray)
	{
		int tottalPaddestalLength=treeSizeArray.length;
		int paddestalDistance=tottalPaddestalLength-java.lang.Math.abs(destinationIndex-sourceIndex);
		int monkeyDownTravel=treeSizeArray[sourceIndex];
		int monkeyUpTravel=treeSizeArray[destinationIndex];
		return paddestalDistance+monkeyDownTravel+monkeyUpTravel;
	}
	

	public static int indexOfLogestTree(int[] treeSizeArray) {
		int tottalTree = treeSizeArray.length;
		int index = 0;
		int tempMax=treeSizeArray[0];
		for (int current = 1; current < tottalTree; current++) {
			
			if(treeSizeArray[current]>tempMax)
			{
				tempMax=treeSizeArray[current];
				index=current;
			}
		}
		return index;
	}

}
