package eolimp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class DivideBy8 {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"));
		String line = bReader.readLine();
		if (line.length() < 3) {
			if (Integer.parseInt(line) % 8 == 0) {
				bWriter.write(line + "");
				bWriter.flush();
				bReader.close();
				bWriter.close();
				return;
			}
		}
		ArrayList<Integer> numbers = new ArrayList<>(line.length());
		for (int i = 0; i < line.length(); i++) {
			numbers.add(Character.getNumericValue(line.charAt(i)));
		}
		ArrayList<String> triples = findTriples(numbers);
		if (triples.size() == 0) {
			bWriter.write("-1");
			bWriter.flush();
			bReader.close();
			bWriter.close();
			return;
		}
		Collections.sort(numbers);
		String max = triples.get(0);
		for (int i = 0; i < triples.size(); i++) {
			if (max.compareTo(triples.get(i)) > 0)
				max = triples.get(i);
		}
		String result = "";
		boolean ok = false;
		for (int i = 0; i < numbers.size(); i++) {
			if (numbers.get(i) == 0 && !ok) {
				continue;
			} else
				ok = true;
				result += numbers.get(i);
		}
		result += max;
		bWriter.write(result);
		bWriter.flush();
		bReader.close();
		bWriter.close();
	}

	private static ArrayList<String> findTriples(ArrayList<Integer> numbers) {
		ArrayList<String> triples = new ArrayList<>(10);
		String triple = null;
		for (int i = 0; i < numbers.size() - 2; i++) {
			for (int j = i + 1; j < numbers.size() - 1; j++) {
				for (int l = j + 1; l < numbers.size(); l++) {
					triple = numbers.get(i) + "" + numbers.get(j) + "" + numbers.get(l) + "";
					triple = ohBabyItsTripple(triple);
					if (triple != null)
						triples.add(triple);

				}
			}
		}
		return triples;
	}

	private static String ohBabyItsTripple(String triple) {
		String[] array = new String[6];
		String[] divideBy8 = new String[6];
		int count = 0;
		String small = null;
		array[0] = triple.charAt(1) + "" + triple.charAt(0) + "" + triple.charAt(2) + "";
		array[1] = triple.charAt(1) + "" + triple.charAt(2) + "" + triple.charAt(0) + "";
		array[2] = triple.charAt(2) + "" + triple.charAt(0) + "" + triple.charAt(1) + "";
		array[3] = triple.charAt(2) + "" + triple.charAt(1) + "" + triple.charAt(0) + "";
		array[4] = triple.charAt(0) + "" + triple.charAt(1) + "" + triple.charAt(2) + "";
		array[5] = triple.charAt(0) + "" + triple.charAt(2) + "" + triple.charAt(1) + "";
		for (int i = 0; i < 6; i++) {
			if (Integer.parseInt(array[i]) % 8 == 0)
				divideBy8[count++] = array[i];
		}
		small = divideBy8[0];
		for (int i = 1; i < count; i++) {
			if (small.compareTo(divideBy8[i]) > 0)
				small = divideBy8[i];
		}
		return small;
	}
}
