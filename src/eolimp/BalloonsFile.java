/**
 * File name: BalloonsFile.java
 * ==========================
 * This program receives number of balloons
 * and their colors and returns the lowest
 * number of actions needed to paint all
 * balloons in the same color.
 */
package eolimp;

import java.io.*;

public class BalloonsFile {

	public static void main(String[] args) throws IOException {
		int number = 0;
		int[] colors;
		String string;
		BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
		FileWriter fileWriter = new FileWriter("output.txt");
		string = bufferedReader.readLine();
		number = Integer.parseInt(string);
		string = bufferedReader.readLine();
		String[] strArray = string.split(" ");
		colors = new int[strArray.length];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = Integer.parseInt(strArray[i]);
		}
		fileWriter.write("" + (paintTheBalloons(number, colors)));
		fileWriter.flush();
		bufferedReader.close();
		fileWriter.close();
	}

	private static int paintTheBalloons(int number, int[] colors) {
		int count = 0, countMax = 0;
		boolean present = false;
		for (int i = 1, j = 0, k = 9; i <= k; i++) {
			while (j < colors.length) {
				if (colors[j] == i)
					count++;
				if (colors[j] == k)
					present = true;
				j++;
			}
			if (count > countMax)
				countMax = count;
			if (!present)
				k--;
			j = count = 0;
		}
		return colors.length - countMax;
	}

}
