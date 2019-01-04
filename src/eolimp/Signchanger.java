package eolimp;

import java.io.*;

public class Signchanger {
	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"));

		int n = Integer.parseInt(bReader.readLine());
		int[] numbers = new int[n];

		String[] data = bReader.readLine().split(" ");
		for (int i = 0; i < n; i++)
			numbers[i] = Integer.parseInt(data[i]);

		int m = Integer.parseInt(bReader.readLine());
		String[] operation = null;

		for (int i = 0; i < m; i++) {

			operation = bReader.readLine().split(" ");
			int a = Integer.parseInt(operation[1]) - 1;
			int b = Integer.parseInt(operation[2]);

			switch (operation[0]) {
			case "0":
				numbers[a] = b;
				break;
			case "1":
				bWriter.write(sum(a, b, numbers) + "");
				bWriter.newLine();
				break;
			}
		}

		bWriter.flush();
		bReader.close();
		bWriter.close();
	}

	private static int sum(int a, int b, int[] numbers) throws IOException {
		int l = a;
		int r = b - 1;
		int sum = 0;
		for (int i = 2; l <= r; l++, i++)
			sum += numbers[l] * (i % 2 == 0 ? 1 : -1);
		return sum;
	}
}
