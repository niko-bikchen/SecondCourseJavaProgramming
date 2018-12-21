/**
 * File name: Tailoring.java
 * =========================
 * This program calculates the number of
 * suits for the given number of sportsman  
 */
package eolimp;

import java.io.*;

public class Tailoring {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		FileWriter fWriter = new FileWriter("output.txt");
		int sportsman_number = 0;
		int span_max = 0, max = 0;
		int span_min = 0, min = 0;
		String sizes_span = null;
		String string = null;
		while (true) {
			string = bReader.readLine();
			if (string == null)
				break;
			sportsman_number = Integer.parseInt(string);
			sizes_span = bReader.readLine();
			string = bReader.readLine();
			String[] strArr = string.split(" ");
			max = Integer.parseInt(strArr[0]);
			min = Integer.parseInt(strArr[1]);
			span_max = Math.max(max, min);
			span_min = Math.min(max, min);
			fWriter.write("" + sewClothes(sportsman_number, sizes_span, span_max, span_min) + "\n");
			fWriter.flush();
		}
		bReader.close();
		fWriter.close();
	}

	private static int sewClothes(int sportsman_number, String sizes_span, int span_max, int span_min) {
		int count = 0;
		int number = 0;
		String[] strArr = sizes_span.split(" ");
		for (int i = 0; i < sportsman_number; i++) {
			number = Integer.parseInt(strArr[i]);
			if (number <= span_max && number >= span_min)
				count++;
		}
		return count;
	}
}
