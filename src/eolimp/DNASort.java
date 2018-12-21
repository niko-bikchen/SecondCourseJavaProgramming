package eolimp;

import java.io.*;
import java.util.Arrays;

public class DNASort {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"));
		DNA[] list = null;
		String line;
		String[] arr = null;
		int tests = 0;
		int length = 0;
		int lines = 0;
		int swaps = 0;
		tests = Integer.parseInt(bReader.readLine());
		bReader.readLine();
		for (int i = 0; i < tests; i++) {
			line = bReader.readLine();
			if (line == null)
				break;
			arr = line.split(" ");
			length = Integer.parseInt(arr[0]);
			lines = Integer.parseInt(arr[1]);
			list = new DNA[lines];
			for (int j = 0; j < lines; j++) {
				line = bReader.readLine();
				for (int k = 0; k < length - 1; k++) {
					for (int l = k + 1; l < length; l++) {
						if (Character.toString(line.charAt(k)).compareTo(Character.toString(line.charAt(l))) > 0)
							swaps++;
					}
				}
				list[j] = new DNA(line, swaps);
				swaps = 0;
			}
			Arrays.sort(list);
			for (int j = 0; j < list.length; j++) {
				bWriter.write(list[j].toString());
				bWriter.newLine();
			}
			bWriter.flush();
			bWriter.newLine();
			bReader.readLine();
		}
		bReader.close();
		bWriter.close();
	}
}

class DNA implements Comparable<DNA> {
	private String value;
	private int swaps;

	public DNA(String v, int s) {
		value = v;
		swaps = s;
	}

	@Override
	public int compareTo(DNA o) {
		if (this.swaps < o.swaps)
			return -1;
		if (this.swaps > o.swaps)
			return 1;
		return 0;
	}

	@Override
	public String toString() {
		return value;
	}
}