/**
 * File name: IceCream.java
 * ========================
 * This program finds maximum from the minimum distances between
 * ice cream sellers 
 */
package eolimp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IceCream {

	public static void main(String[] args){
		try (BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
				BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"))) {
			String[] arr = bReader.readLine().split(" ");
			int n = Integer.parseInt(arr[0]);
			int k = Integer.parseInt(arr[1]);
			arr = bReader.readLine().split(" ");
			int[] kiosks = new int[n];
			for (int i = 0; i < n; i++)
				kiosks[i] = Integer.parseInt(arr[i]);
			int begin = 0;
			int end = kiosks[n - 1];
			int mid = 0;
			while (begin <= end) {
				mid = (begin + end) / 2;
				if (valid(mid, n, k, kiosks))
					begin = mid + 1;
				else
					end = mid - 1;
			}
			bWriter.write(begin - 1 + "");
			bWriter.flush();
		} catch (NumberFormatException e) {
			System.err.println("Invalid input");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean valid(int mid, int n, int k, int[] kiosks) {
		int i = 0;
		int occupied = 1;
		int dist = 0;
		for (i = 1; i < n; i++) {
			dist += kiosks[i] - kiosks[i - 1];
			if (dist >= mid) {
				dist = 0;
				occupied++;
			}
		}
		return occupied >= k;
	}
}
