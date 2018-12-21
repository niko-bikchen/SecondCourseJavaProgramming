package eolimp;

import java.io.*;

public class Test {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"));
		String line = bReader.readLine();
		String[] arr = line.split(" ");
		int a = Integer.parseInt(arr[0]);
		int b = Integer.parseInt(arr[1]);
		int c = Integer.parseInt(arr[2]);
		int d = Integer.parseInt(arr[3]);
		int gcd = findGCD(findGCD(Math.max(a, b), Math.min(a, b)), findGCD(Math.max(c, d), Math.min(c, d)));
		a /= gcd;
		b /= gcd;
		c /= gcd;
		d /= gcd;
		bWriter.write((a + b + c + d) + "");
		bReader.close();
		bWriter.close();
	}

	private static int findGCD(int a, int b) {
		if (b == 0)
			return a;
		return findGCD(b, a % b);
	}
}
