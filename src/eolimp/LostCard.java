/**
 * File name: LostCard.java
 * ========================
 * This program find the number of the lost card
 */
package eolimp;

import java.io.*;
import java.util.*;

public class LostCard {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		FileWriter fWriter = new FileWriter("output.txt");
		String string = null;
		while (true) {
			string = bReader.readLine();
			if (string == null)
				break;
			fWriter.write("" + findCard(string) + "\n");
			fWriter.flush();
		}
		bReader.close();
		fWriter.close();
	}

	private static int findCard(String string) {
		Scanner scanner = new Scanner(string);
		int count = 0;
		int sum_this = 1, sum_that = 0;
		count = scanner.nextInt();
		for (int i = 2; scanner.hasNextInt(); i++) {
			if (i <= count)
				sum_this += i;
			sum_that += scanner.nextInt();
		}
		scanner.close();
		return sum_this - sum_that;
	}
}
