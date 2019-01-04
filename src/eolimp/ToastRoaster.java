package eolimp;

import java.io.*;

public class ToastRoaster {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"));
		String[] data = bReader.readLine().split(" ");
		int n = Integer.parseInt(data[0]);
		int k = Integer.parseInt(data[1]);
		int time = 0;
		if (n <= k && k > 0 && n > 0) {
			bWriter.write("4");
			bWriter.flush();
			bReader.close();
			bWriter.close();
			return;
		} else if ((k == 0 && n == 0) || (n == 0 && k > 0)) {
			bWriter.write("0");
			bWriter.flush();
			bReader.close();
			bWriter.close();
			return;
		}
		time = 2 * n / k;
		if (2 * n % k > 0)
			time++;
		bWriter.write(time * 2 + "");
		bWriter.flush();
		bReader.close();
		bWriter.close();
	}

}
