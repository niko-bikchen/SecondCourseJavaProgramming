package eolimp;

import java.io.*;
import java.util.TreeSet;

public class TelephoneNumbers {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"));
		int n_tests = Integer.parseInt(bReader.readLine());
		int n_phones = 0;
		String number = null;
		boolean valid = true;
		TreeSet<String>[] trees = null;
		for(int i = 0; i < n_tests; i++) {
			
			n_phones = Integer.parseInt(bReader.readLine());
			valid = true;
			trees = new TreeSet[11];
			for(int k = 1; k < 11; k++)
				trees[k] = new TreeSet<>();
			
			for(int j = 0; j < n_phones; j++) {
				number = bReader.readLine();
				if(!isPrefix(number, trees)) {
					trees[number.length()].add(number);
				} else {
					valid = false;
					break;
				}
			}
			
			if(valid) {
				bWriter.write("YES");
				bWriter.newLine();
				bWriter.flush();
			} else {
				bWriter.write("NO");
				bWriter.newLine();
				bWriter.flush();
			}
		}
	}

	private static boolean isPrefix(String number, TreeSet<String>[] trees) {
		for(int i = number.length(); i > 0; i--) {
			if(trees[i].contains(number.substring(0, i)))
				return true;
		}
		return false;
	}
}