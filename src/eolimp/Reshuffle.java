/**
 * File name: Reshuffle.java
 * ========================
 * This program checks if the 
 * given sequence is reshuffle or not
 */
package eolimp;

import java.io.*;
import java.util.Scanner;

public class Reshuffle {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		FileWriter writer = new FileWriter("output.txt");
		String line = null;
		while ((line = bReader.readLine()) != null) {
			writer.write("" + check(line) + "\n");
			writer.flush();
		}
		bReader.close();
		writer.close();
	}

	private static int check(String line) {
		Scanner scanner = new Scanner(line);
		int count = scanner.nextInt();
		boolean[] list = new boolean[count + 1];
		int n = 0;
		while (scanner.hasNextInt()) {
			n = scanner.nextInt();
			if (n <= count)
				list[n] = true;
		}
		for (n = 1; n <= count; n++) {
			if (!list[n])
				break;
		}
		scanner.close();
		if (n > count)
			return 0;
		else
			return n;
	}
}
