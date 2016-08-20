package com.techgig.passcount;

import java.io.*;

public class CandidateCode {
	/*
	 * INVALID_INPUT
	 */
	private static final int INVALID_INPUT = -1;

	private static int count = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int noOfPlayers = 5;
		int shiftLength = 2;
		int maxNoOfPass = 3;

		// System.out.println(getNextPosition(passTracker,
		// noOfPlayers,currentPosition, shiftLength));
		System.out.println("PassCount:"
				+ passCount(noOfPlayers, maxNoOfPass, shiftLength));

	}

	public static int passCount(int input1, int input2, int input3) {
		int passCount = INVALID_INPUT;
		int START = 0;
		if (!isValidInput(input1, input2, input3)) {
			return passCount;
		}
		int[] passTracker = new int[input1];
		passTracker[0] = 1;

		passCountUtil(passTracker, input1, input2, input3, START);
		return CandidateCode.count;

	}

	private static void passCountUtil(int[] passTracker, int noOfPlayers,
			int maxNoOfPass, int shiftLength, int currentPosition) {
		if (passTracker[currentPosition] == maxNoOfPass) {
			return;
		}
		CandidateCode.count++;
		int nextPosition = getNextPosition(passTracker, noOfPlayers,
				currentPosition, shiftLength);
		passTracker[nextPosition] = passTracker[nextPosition] + 1;
		passCountUtil(passTracker, noOfPlayers, maxNoOfPass, shiftLength,
				nextPosition);

	}

	private static int getNextPosition(int[] passTracker, int noOfPlayers,
			int currentPosition, int shiftLength) {
		int currentNoOfPass = passTracker[currentPosition];
		int nextPosition = currentPosition;
		if (currentNoOfPass % 2 == 0) {
			// Left-ClockWise
			nextPosition = rotateClockWise(noOfPlayers, shiftLength,
					currentPosition);
		} else {
			// RIght- AntiClockWise
			nextPosition = rotateAntiClockWise(noOfPlayers, shiftLength,
					currentPosition);
		}
		return nextPosition;
	}

	// Left
	private static int rotateClockWise(int noOfPlayers, int shiftLength,
			int currentPosition) {
		int nextPosition = currentPosition + shiftLength;

		nextPosition = nextPosition % noOfPlayers;
		return nextPosition;
	}

	// Right
	private static int rotateAntiClockWise(int noOfPlayers, int shiftLength,
			int currentPosition) {
		int nextPosition = currentPosition - shiftLength;
		nextPosition=nextPosition < 0 ? noOfPlayers + nextPosition : nextPosition;

		return nextPosition;
	}

	private static boolean isValidInput(int noOfPlayers, int maxNoOfPass,
			int shiftLength) {
		boolean isvalid = Boolean.FALSE;
		if ((noOfPlayers >= 3 && noOfPlayers <= 1000)
				&& (maxNoOfPass >= 3 && maxNoOfPass <= 1000)
				&& (shiftLength > 0 && shiftLength<noOfPlayers )) {
			isvalid = Boolean.TRUE;
		}
		return isvalid;
	}

}
