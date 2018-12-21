package eolimp;

import java.io.*;

public class Line {
	
	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"));
		String string = bReader.readLine();
		String[] arr = string.split(" ");
		long x1 = Integer.parseInt(arr[0]);
		long y1 = Integer.parseInt(arr[1]);
		long x2 = Integer.parseInt(arr[2]);
		long y2 = Integer.parseInt(arr[3]);
		long dx = Math.abs(x2 - x1);
		long dy = Math.abs(y2 - y1);
		bWriter.write((findGCD(Math.max(dx, dy), Math.min(dx, dy)) + 1) + "");
		bWriter.flush();
		bReader.close();
		bWriter.close();
	}

	private static long findGCD(long a, long b) {
		if (b == 0)
			return a;
		return findGCD(b, a % b);
	}
}
