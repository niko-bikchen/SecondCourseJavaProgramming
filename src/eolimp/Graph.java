/**
 * File name: Graph.java
 * =====================
 * Determines whether this matrix is a matrix of adjacency of a simple non-oriented graph
 */
package eolimp;

import java.io.*;

public class Graph {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"));
		int n = Integer.parseInt(bReader.readLine());
		int[][] matrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			String[] arr = bReader.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				matrix[i][j] = Integer.parseInt(arr[j]);
			}
		}
		if (isOk(matrix, n)) {
			bWriter.write("YES");
			bWriter.newLine();
			bWriter.flush();
			bWriter.close();
			bReader.close();
		} else {
			bWriter.write("NO");
			bWriter.newLine();
			bWriter.flush();
			bWriter.close();
			bReader.close();
		}
	}

	private static boolean isOk(int[][] matrix, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] != matrix[j][i] || matrix[i][i] != 0)
					return false;
			}
		}
		return true;
	}

}
