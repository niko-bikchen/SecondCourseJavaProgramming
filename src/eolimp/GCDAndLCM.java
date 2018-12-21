package eolimp;

import java.io.*;
import java.util.HashMap;

public class GCDAndLCM {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"));
		String line = bReader.readLine();
		String[] arr = line.split(" ");
		int gcd = Integer.parseInt(arr[0]);
		int lcm = Integer.parseInt(arr[1]);

		if(Math.max(gcd, lcm) % Math.min(gcd, lcm) != 0) {
			bWriter.write("0");
			bWriter.flush();
			bReader.close();
			bWriter.close();
			return;
		}
		
		HashMap<Integer, Integer> a = getPrimeRow(gcd);
		HashMap<Integer, Integer> b = getPrimeRow(lcm);

		for (int key : a.keySet()) {
			if (b.get(key) - a.get(key) == 0)
				b.remove(key);
		}

		bWriter.write((int) Math.pow(2, b.size()) + "");
		bWriter.flush();
		bReader.close();
		bWriter.close();
	}

	private static HashMap<Integer, Integer> getPrimeRow(int val) {
		HashMap<Integer, Integer> row = new HashMap<>();
		int count = 0;
		for (int i = 2; i <= val; i++) {
			count = 0;
			while (val % i == 0) {
				count++;
				val /= i;
			}
			if (count > 0)
				row.put(i, count);
		}
		return row;
	}
}