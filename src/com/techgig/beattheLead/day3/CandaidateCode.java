package com.techgig.beattheLead.day3;

import java.util.ArrayList;
import java.util.List;

public class CandaidateCode {
	
	public static void main(String[] args) {
		
		String[] input1={"1#1","2#5","3#3","6#3"};
		//String[] input2={"2#6#8#6#-7","2#5#-5#-5#0","-1#3#-8#8#7","3#2#0#6#9","2#1#-4#5#8","-5#6#7#4#7"};
		String[]input2={
				"2#6#8#6#-7",
				"2#5#-5#-5#0",
				"-1#3#-8#8#7",
				"3#2#0#6#9",
				"2#1#-4#5#8",
				"-5#6#7#4#7"};
		int input3=3;
		String [] resStrings=RatsPostions(input1, input2, input3);
		for(String str:resStrings)
		{
			System.out.println(str+" ");
		}
		
	}
	
	public static String[] RatsPostions(String[] input1,String[] input2,int input3)
    {
		String [] finalPositon=input1;
		if(input3==0)
		{
			return finalPositon;
		}
		int [] [] temparaturebrick=prepareTempBricks(input2);
		finalPositon=getmousesFinalPositin(temparaturebrick,finalPositon,input3);
		return finalPositon;
    }
	
	private static String[] getmousesFinalPositin(int[][] temparaturebrick,
			String [] initialPosition, int noOfMoves) {
		
		for(int i=0;i<noOfMoves;i++)
		{
			moveAllMouse(temparaturebrick,initialPosition);
		}
		return initialPosition;
	}
	
	

	private static void moveAllMouse(int[][] temparaturebrick,
			String [] currentPosition) {
		
		for(int i=0;i<currentPosition.length;i++)
		{
			moveMouse(temparaturebrick, currentPosition[i],currentPosition,i);
		}
		
	}
	
	private static void moveMouse(int[][] temparaturebrick,
			String currentPosition, String [] positionOfAll,int mousetype) {
		
		String [] position=currentPosition.split("#");
		int x=Integer.parseInt(position[0])-1;
		int y=Integer.parseInt(position[1])-1;
		String  newPositon=takeAMove(temparaturebrick,x,y);
		if(!newPositon.equals(currentPosition))
		{
			positionOfAll[mousetype]=newPositon;
		}
		
	}

	private static String takeAMove(int[][] temparaturebrick, int x, int y) {
		// TODO Auto-generated method stubx
		//upper, right, lower, left.
		int currentMin=-1;
		String nextMove=getPosInStr(x, y);
		int tempmin=0;
		int currentTemp=temparaturebrick[x][y];
		// Move LEFT
		if(isSafeForMove(temparaturebrick,x,y-1))
		{
			if(currentMin ==-1)
			{
				currentMin=Math.abs(temparaturebrick[x][y-1]-currentTemp);
				nextMove=getPosInStr(x, y-1);
			}
			else
			{
				tempmin=Math.abs(temparaturebrick[x][y-1]-currentTemp);
				if(tempmin<currentMin)
				{
					currentMin=tempmin;
					nextMove=getPosInStr(x, y-1);
				}else if(tempmin==currentMin)
				{
					nextMove=getPosInStr(x, y-1);
				}
			}
		}
		// Move DOWN
		if(isSafeForMove(temparaturebrick,x-1,y))
		{
			if(currentMin ==-1)
			{
				currentMin=Math.abs(temparaturebrick[x-1][y]-currentTemp);
				nextMove=getPosInStr(x-1, y);
			}
			else
			{
				tempmin=Math.abs(temparaturebrick[x-1][y]-currentTemp);
				if(tempmin<currentMin)
				{
					currentMin=tempmin;
					nextMove=getPosInStr(x-1, y);
				}else if(tempmin==currentMin)
				{
					nextMove=getPosInStr(x-1, y);
				}
			}
		}

		// Move RIGHT
		if(isSafeForMove(temparaturebrick,x,y+1))
		{
			if(currentMin ==-1)
			{
				currentMin=Math.abs(temparaturebrick[x][y+1]-currentTemp);
				nextMove=getPosInStr(x, y+1);
			}
			else
			{
				tempmin=Math.abs(temparaturebrick[x][y+1]-currentTemp);
				if(tempmin<currentMin)
				{
					currentMin=tempmin;
					nextMove=getPosInStr(x, y+1);
				}else if(tempmin==currentMin)
				{
					nextMove=getPosInStr(x, y+1);
				}
			}
		}
		// Move UPPER

		if(isSafeForMove(temparaturebrick,x+1,y))
		{
			if(currentMin ==-1)
			{
				currentMin=Math.abs(temparaturebrick[x+1][y]-currentTemp);
				nextMove=getPosInStr(x+1, y);
			}
			else
			{
				tempmin=Math.abs(temparaturebrick[x+1][y]-currentTemp);
				if(tempmin<currentMin)
				{
					currentMin=tempmin;
					nextMove=getPosInStr(x+1, y);
				}else if(tempmin==currentMin)
				{
					nextMove=getPosInStr(x+1, y);
				}
			}
		}
		return nextMove;
	}
	
	public static String getPosInStr(int x, int y)
	{
		StringBuilder str= new StringBuilder();
		str.append(x+1);
		str.append("#");
		str.append(y+1);
		return str.toString();
	}
	private static boolean isSafeForMove(int[][] area,int futurX,int futurY)
	{
		boolean isSafe=true;
		int rows=area.length;
		int cols=area[0].length;
		if( futurX<0 || futurX>rows-1)
		{
			isSafe=false;
		}
		if( futurY<0 || futurY>cols-1)
		{
			isSafe=false;
		}
		return isSafe;
	}
	

	public static int [][] prepareTempBricks(String [] mazeTempList)
	{
		int rows=mazeTempList.length;
		int [][] tempBricks=new int [rows][];
		for(int i=0;i<rows;i++)
		{
			tempBricks[i]=strinToIntArray(mazeTempList[i]);
		}
		return tempBricks;
	}
	public static int[] strinToIntArray(String str)
	{
		String [] temps=str.split("#");
		int length=temps.length;
		int [] array= new int [length];
		for(int i=0;i<length;i++)
		{
			array[i]=Integer.parseInt(temps[i]);
		}
		return array;
	}
	
	public static List<String> initialPosition(String [] inititalPositon)
	{
		List<String> initialPositon= new ArrayList<String>();
		for(String mousePosition: inititalPositon)
		{
			initialPositon.add(mousePosition);
		}
		return initialPositon;
	}
	public static String [] convertListToArray(List<String> position)
	{
		String [] positionArray=new String [position.size()];
		position.toArray(positionArray);
		return positionArray;
	}

}
