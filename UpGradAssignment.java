//Please Create a Package with same name.
package com.UpGrad.Assignment;

import java.util.Scanner;

class UpGradAssignment {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// Taking the input of number of events.
		int numOfEvents = scan.nextInt();

		// Creating a two dimensional array to store strings "ENTER" and "name"
		String[][] stringSequence = new String[numOfEvents][2];

		// Creating an array to store "CGPA".
		float[] cgpa = new float[numOfEvents];

		// Creating an array to store "tokens".
		int[] tokens = new int[numOfEvents];

		int count = 0;
		for (int i = 0; i < numOfEvents; i++) {
			stringSequence[i][0] = scan.next();

			// If a Student is served
			if (stringSequence[i][0].equals("SERVED")) {
				int position = maxElement(cgpa, stringSequence, tokens);
				cgpa[position] = 0;
				count++;
				continue;
			}
			stringSequence[i][1] = scan.next();
			cgpa[i] = scan.nextFloat();
			tokens[i] = scan.nextInt();
		}

		// If every Student is served then print empty and halt.
		if (count == cgpa.length / 2 && count != 1) {
			System.out.println("EMPTY");
			return;
		}

		// Printing the names of students who are yet to be served.
		for (int i = 0; i < cgpa.length; i++) {
			if (cgpa[i] != 0f) {
				System.out.println(stringSequence[i][1]);
				System.out.println();
			}
		}

	}

	public static int maxElement(float[] cgpa, String[][] stringSequence, int[] tokens) {
		float max = cgpa[0];
		int index = 0;

		for (int i = 0; i < cgpa.length; i++) {
			if (max < cgpa[i]) {
				max = cgpa[i];
				index = i;
			} else if (max == cgpa[i] && index != i && max != 0f) {

				// Any students having the same CGPA will be served by name in ascending
				// case-sensitive alphabetical order.
				if ((stringSequence[index][1].compareTo(stringSequence[i][1]) > 0)) {
					index = i;
				}

				// Any students having the same CGPA and name will be served in ascending token
				// order.
				else if (stringSequence[index][1].compareTo(stringSequence[i][1]) == 0) {
					if (tokens[i] < tokens[index]) {
						index = i;
					}
				}
			}
		}
		return index;
	}
}
