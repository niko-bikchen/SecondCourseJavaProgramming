package eolimp;

import java.io.*;

public class GoodNumber {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"));
		bWriter.write((long) Math.pow(5, Integer.parseInt(bReader.readLine())) + "");
		bReader.close();
		bWriter.close();
	}

}
