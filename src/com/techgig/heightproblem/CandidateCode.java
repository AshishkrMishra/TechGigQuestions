package com.techgig.heightproblem;

public class CandidateCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int input1 = 9;
		int[] input2 = { 8, 4, 5, 2, 0, 2, 2, 0, 0 };
		int[] result = uniqueValue(input1, input2);
		for (int i : result) {
			System.out.print(" " + i);
		}
		System.out.println();

	}

	public static int[] uniqueValue(int input1, int[] input2) {
		int noOfEmployes = input1;
		int[] result = new int[noOfEmployes];
		for (int height = 1; height <= noOfEmployes; height++) {
			insertIntoRightPosition(result, input2, height);
		}
		return result;
	}

	public static void insertIntoRightPosition(int[] result, int[] left,
			int height) {

		int leftCount = left[height - 1];
		int count = 0, interator = 0;
		while (count <= leftCount) {
			if (result[interator] == 0) {
				count++;
			}
			interator++;
		}
		result[interator-1] = height;

	}

}
