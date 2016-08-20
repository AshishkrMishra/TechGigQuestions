package com.techgig.waterlevelprblm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CandidateCode {

	public static class Vertex {
		int x, y;

		Vertex(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public String toString() {
			return new StringBuilder().append(x).append(',').append(y)
					.toString();

		}
	}

	public static int GetWaterLevel(int input1, int input2, int[] input3) {
		int[][] orignalCube = new int[input1][input2];
		prepareCubeFromInput(input1, input2, input3, orignalCube);
		int[][] cubeToFill = new int[input1][input2];
		prepareCubeFromInput(input1, input2, input3, cubeToFill);
		for (int i = 1; i < input1-1; i++) {
			for (int j = 1; j < input2-1; j++) {
					fillWater(cubeToFill, i, j);
			}
		}
		return calCulateIncreasedLevel(orignalCube, cubeToFill);

	}

	private static int calCulateIncreasedLevel(int[][] orignalCube,
			int[][] cubeToFill) {
		int input1 = orignalCube.length;
		int input2 = orignalCube[0].length;
		int increasedLevel = 0;
		for (int i = 1; i < input1 - 1; i++) {
			for (int j = 1; j < input2 - 1; j++) {
				increasedLevel += (cubeToFill[i][j] - orignalCube[i][j]);
			}
		}
		return increasedLevel;
	}

	public static void prepareCubeFromInput(int row, int col, int[] input3,
			int[][] target) {
		int count = 0;
		for (int rows = 0; rows < row; rows++) {
			for (int cols = 0; cols < col; cols++) {
				target[rows][cols] = input3[count];
				count++;
			}
		}
	}

	public static void fillWater(int[][] cubes, int i, int j) {
		
		// Find Min in Surrounding if Water Level Greater than Increase
		int minSrroundingVal = minOfSurrounding(cubes, i, j);
		if (minSrroundingVal > cubes[i][j]) {
			cubes[i][j] = minSrroundingVal;
			return;
		}
		// Getting List of Vertices to Be filled before
		List<String> listOfPriorFillerBlock = findMinLevelListinSurrrounding(
				cubes, i, j);
		// Filiing the Block which are more depth
		if (null != listOfPriorFillerBlock
				&& listOfPriorFillerBlock.size() != 0) {
			// Fill IN ordered way
			Collections.sort(listOfPriorFillerBlock);
			for (String vertex : listOfPriorFillerBlock) {
				fillWater(cubes, Integer.parseInt(vertex.split(",")[0]),
						Integer.parseInt(vertex.split(",")[1]));
			}
		
			// Get The Area to be Filled with Water
			// Getting List of Vertices to Be filled before
			List<String> areaToBFilled = new ArrayList<String>();
			findAreaToBeFilled(cubes, cubes[i][j], i, j, areaToBFilled);
			// No Block Smaller Became Area
			if (areaToBFilled.size()!=0) {
				fillTheWaterInArea(cubes, areaToBFilled, cubes[i][j]);
			}
		}

	}

	// Write Code for Filling Area

	private static void fillTheWaterInArea(int[][] cubes,
			List<String> areaToBFilled, int currentWaterLevel) {
		int waterLevelForArea = findMinWaterLevelForArea(cubes, areaToBFilled,
				currentWaterLevel);
		if (waterLevelForArea > currentWaterLevel) {
			for (String vertex : areaToBFilled) {
				String[] location = vertex.split(",");
				int x = Integer.parseInt(location[0]);
				int y = Integer.parseInt(location[1]);
				if (waterLevelForArea > cubes[x][y]) {
					cubes[x][y] = waterLevelForArea;
				}
			}
		}

	}

	// find minLevel Among Area

	private static int findMinWaterLevelForArea(int[][] cubes,
			List<String> areaToBFilled, int currentWaterLevel) {
		String[] vertex = areaToBFilled.get(0).split(",");
		int minOfSurroundingbyArea = minOfSurroundingbyArea(cubes, Integer
				.parseInt(vertex[0]), Integer.parseInt(vertex[1]),
				areaToBFilled);
		for (String currentVertex : areaToBFilled) {
			vertex = currentVertex.split(",");
			int tempVal = minOfSurroundingbyArea(cubes, Integer
					.parseInt(vertex[0]), Integer.parseInt(vertex[1]),
					areaToBFilled);
			if (tempVal < minOfSurroundingbyArea) {
				minOfSurroundingbyArea = tempVal;
			}
		}
		return minOfSurroundingbyArea;

	}

	public static void findAreaToBeFilled(int[][] cubes, int valueToBeMatch,
			int currentX, int currentY, List<String> areaTobeFilled) {
		if (areaTobeFilled.contains(new Vertex(currentX, currentY).toString())) {
			return;
		}
		if (cubes[currentX][currentY] != valueToBeMatch) {
			return;
		}
		areaTobeFilled.add(new Vertex(currentX, currentY).toString());
		// TOP
		if (isSafetoFillWater(cubes, currentX, currentY - 1)
				&& valueToBeMatch == cubes[currentX][currentY - 1]) {
			findAreaToBeFilled(cubes, valueToBeMatch, currentX, currentY - 1,
					areaTobeFilled);
		}
		// TOP-RIGHT
		if (isSafetoFillWater(cubes, currentX + 1, currentY - 1)
				&& valueToBeMatch == cubes[currentX + 1][currentY - 1]) {
			findAreaToBeFilled(cubes, valueToBeMatch, currentX + 1,
					currentY - 1, areaTobeFilled);
		}
		// TOP-LEFT
		if (isSafetoFillWater(cubes, currentX - 1, currentY - 1)
				&& valueToBeMatch == cubes[currentX - 1][currentY - 1]) {
			findAreaToBeFilled(cubes, valueToBeMatch, currentX - 1,
					currentY - 1, areaTobeFilled);
		}
		// BOTTOM
		if (isSafetoFillWater(cubes, currentX, currentY + 1)
				&& valueToBeMatch == cubes[currentX][currentY + 1]) {
			findAreaToBeFilled(cubes, valueToBeMatch, currentX, currentY + 1,
					areaTobeFilled);
		}
		// BOTTOM-LEFT
		if (isSafetoFillWater(cubes, currentX - 1, currentY + 1)
				&& valueToBeMatch == cubes[currentX - 1][currentY + 1]) {
			findAreaToBeFilled(cubes, valueToBeMatch, currentX - 1,
					currentY + 1, areaTobeFilled);
		}
		// BOTTOM-RIGHT
		if (isSafetoFillWater(cubes, currentX + 1, currentY + 1)
				&& valueToBeMatch == cubes[currentX + 1][currentY + 1]) {
			findAreaToBeFilled(cubes, valueToBeMatch, currentX + 1,
					currentY + 1, areaTobeFilled);
		}
		// LEFT
		if (isSafetoFillWater(cubes, currentX - 1, currentY)
				&& valueToBeMatch == cubes[currentX - 1][currentY]) {
			findAreaToBeFilled(cubes, valueToBeMatch, currentX - 1, currentY,
					areaTobeFilled);
		}
		// RIGHT
		if (isSafetoFillWater(cubes, currentX + 1, currentY)
				&& valueToBeMatch == cubes[currentX + 1][currentY]) {
			findAreaToBeFilled(cubes, valueToBeMatch, currentX + 1, currentY,
					areaTobeFilled);
		}

		return;
	}

	public static List<String> findMinLevelListinSurrrounding(int[][] cubes,
			int currentX, int currentY) {
		List<String> listOfMinVetrex = new ArrayList<String>();
		String vertex=null;
		int currenMin = cubes[currentX][currentY];
		// TOP
		if (isSafetoFillWater(cubes, currentX, currentY - 1)
				&& cubes[currentX][currentY - 1] < currenMin) {
			vertex=new Vertex(currentX, currentY - 1).toString();
			if(!listOfMinVetrex.contains(vertex))
			listOfMinVetrex.add(vertex);
		}
		// TOP-RIGHT
		if (isSafetoFillWater(cubes, currentX + 1, currentY - 1)
				&& cubes[currentX + 1][currentY - 1] < currenMin) {
			vertex=new Vertex(currentX+1, currentY - 1).toString();
			if(!listOfMinVetrex.contains(vertex))
			listOfMinVetrex.add(vertex);
	
		}
		// TOP-LEFT
		if (isSafetoFillWater(cubes, currentX - 1, currentY - 1)
				&& cubes[currentX - 1][currentY - 1] < currenMin) {
			vertex=new Vertex(currentX-1, currentY - 1).toString();
			if(!listOfMinVetrex.contains(vertex))
			listOfMinVetrex.add(vertex);
		}
		// BOTTOM
		if (isSafetoFillWater(cubes, currentX, currentY + 1)
				&& cubes[currentX][currentY + 1] < currenMin) {
			vertex=new Vertex(currentX, currentY + 1).toString();
			if(!listOfMinVetrex.contains(vertex))
			listOfMinVetrex.add(vertex);
		}
		// BOTTOM-LEFT
		if (isSafetoFillWater(cubes, currentX - 1, currentY + 1)
				&& cubes[currentX - 1][currentY + 1] < currenMin) {
			vertex=new Vertex(currentX-1, currentY + 1).toString();
			if(!listOfMinVetrex.contains(vertex))
			listOfMinVetrex.add(vertex);
		}
		// BOTTOM-RIGHT
		if (isSafetoFillWater(cubes, currentX + 1, currentY + 1)
				&& cubes[currentX + 1][currentY + 1] < currenMin) {
			vertex=new Vertex(currentX+1, currentY).toString();
			if(!listOfMinVetrex.contains(vertex))
			listOfMinVetrex.add(vertex);
		}
		// LEFT
		if (isSafetoFillWater(cubes, currentX - 1, currentY)
				&& cubes[currentX - 1][currentY] < currenMin) {
			vertex=new Vertex(currentX-1, currentY).toString();
			if(!listOfMinVetrex.contains(vertex))
			listOfMinVetrex.add(vertex);
		}
		// RIGHT
		if (isSafetoFillWater(cubes, currentX + 1, currentY)
				&& cubes[currentX + 1][currentY] < currenMin) {
			vertex=new Vertex(currentX+1, currentY).toString();
			if(!listOfMinVetrex.contains(vertex))
			listOfMinVetrex.add(vertex);
		}

		return listOfMinVetrex;
	}

	public static int minOfSurrounding(int[][] cubes, int i, int j) {
		//CURRENT VALUE
		List<Integer> list = new ArrayList<Integer>();
		// TOP
		if (isSafetoCompare(cubes, i, j - 1) ) {
			list.add(cubes[i][j - 1]);
		}
		// BOTTOM
		if (isSafetoCompare(cubes, i, j + 1) ) {
			list.add(cubes[i][j + 1]);
		}
		// LEFT
		if (isSafetoCompare(cubes, i - 1, j) ) {
			list.add(cubes[i - 1][j]);
		}
		// RIGHT
		if (isSafetoCompare(cubes, i + 1, j) ) {
			list.add(cubes[i + 1][j]);
		}
		int min=cubes[i][j];
		if(list.size()!=0)
		{
			min=list.get(0);
			for(int current:list)
			{
				if(current<min)
				{
					min=current;
				}
			}
		}
		return min;
	}

	public static int minOfSurroundingbyArea(int[][] cubes, int i, int j,
			List<String> area) {
		List<Integer> list = new ArrayList<Integer>();
		Integer val = 0;
		// TOP
		if (isSafetoCompare(cubes, i, j - 1)) {
			if (!area.contains(new Vertex(i, j - 1).toString())) {
				val = new Integer(cubes[i][j - 1]);
				if (!list.contains(val))
					list.add(val);
			}

		}
		// BOTTOM
		if (isSafetoCompare(cubes, i, j + 1)) {
			if (!area.contains(new Vertex(i, j + 1).toString())) {
				val = new Integer(cubes[i][j + 1]);
				if (!list.contains(val))
					list.add(val);
			}
		}
		// LEFT
		if (isSafetoCompare(cubes, i - 1, j)) {
			if (!area.contains(new Vertex(i - 1, j).toString())) {

				val = new Integer(cubes[i - 1][j]);
				if (!list.contains(val))
					list.add(val);
			}
		}
		// RIGHT
		if (isSafetoCompare(cubes, i + 1, j)) {
			if (!area.contains(new Vertex(i + 1, j).toString())) {
				val = new Integer(cubes[i + 1][j]);
				if (!list.contains(val))
					list.add(val);

			}
		}
		int min = cubes[i][j];
		if (list.size() != 0) {
			min = list.get(0);
			for (Integer temp : list) {
				if (min > temp) {
					min = temp;
				}
			}
		}

		return min;
	}

	public static boolean isSafetoCompare(int[][] orignalCube, int currentX,
			int currentY) {
		boolean isSafe = false;
		int maxX = 0;
		int maxY = 0;
		if (orignalCube != null) {
			maxX = orignalCube.length;
			maxY = orignalCube[0].length;
		}
		if ((currentX >= 0 && currentX < maxX)
				&& (currentY >= 0 && currentY < maxY)) {
			isSafe = true;
		}
		return isSafe;
	}

	public static boolean isSafetoFillWater(int[][] orignalCube, int currentX,
			int currentY) {
		boolean isSafe = false;
		int maxX = 0;
		int maxY = 0;
		if (orignalCube != null) {
			maxX = orignalCube.length;
			maxY = orignalCube[0].length;
		}
		if ((currentX >= 1 && currentX < maxX - 1)
				&& (currentY >= 1 && currentY < maxY - 1)) {
			isSafe = true;
		}
		return isSafe;
	}

	public static void main(String[] args) {

		int input1 = 3;
		int input2 = 6;
		int[] input3 = { 3, 3, 4, 4, 4, 2, 3, 1, 3, 2, 1, 4, 7, 3, 1, 6, 4, 1 };
		System.out.println(GetWaterLevel(input1, input2, input3));

	}

}
