package eolimp;

import java.io.*;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class YEquation {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"));
		int x = Integer.parseInt(bReader.readLine());
		bWriter.write(calculate(x)+"");
		bWriter.flush();
		bReader.close();
		bWriter.close();
	}

	private static long calculate(long x) {
		long y = 0;
		if (x >= 10)
			y = (x * x * x) + 5 * x;
		if (x < 10)
			y = (x * x) - 2 * x + 4;
		return y;
	}
}
