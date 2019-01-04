package eolimp;

import java.io.*;

public class EdgeCounter {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"));
		int n = Integer.parseInt(bReader.readLine());
		int counter = 0;
		String line = "";
		for (int i = 0; i < n; i++) {
			line = bReader.readLine();
			for (int j = 0; j < line.length(); j++) {
				if (line.charAt(j) == '1')
					counter++;
			}
		}
		bWriter.write(counter + "");
		bReader.close();
		bWriter.close();
	}

}
