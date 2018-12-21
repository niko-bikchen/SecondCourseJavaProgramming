package eolimp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GCD {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"));
		String line = bReader.readLine();
		int count = Integer.parseInt(line);
		line = bReader.readLine();
		String[] arr = line.split(" ");
		int[] numbers = new int[count];
		for (int i = 0; i < count; i++) {
			numbers[i] = Integer.parseInt(arr[i]);
		}
		int result = 1;
		if (numbers.length > 2) {
			result = findGCD(Math.max(numbers[0], numbers[1]), Math.min(numbers[0], numbers[1]));
			for (int i = 2; i < numbers.length; i++) {
				result = findGCD(Math.max(result, numbers[i]), Math.min(result, numbers[i]));
			}
			bWriter.write(result + "");
			bReader.close();
			bWriter.close();
		} else if (numbers.length == 2) {
			result = findGCD(Math.max(numbers[0], numbers[1]), Math.min(numbers[0], numbers[1]));
			bWriter.write(result + "");
			bReader.close();
			bWriter.close();
		} else {
			bWriter.write(result + "");
			bReader.close();
			bWriter.close();
		}
	}

	private static int findGCD(int a, int b) {
		if (b == 0)
			return a;
		return findGCD(b, a % b);
	}
}
