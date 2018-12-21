/**
 * File name: TestDrive.java
 * =========================
 * This file tests board solver.
 */
package puzzle8;

import java.io.*;

public class TestDrive {
	public static void main(String[] args) {
		int[][] blocks = null;
		int dimensions = 0;
		try (BufferedReader bReader = new BufferedReader(new FileReader(args[0]))) {
			dimensions = Integer.parseInt(bReader.readLine());
			blocks = new int[dimensions][dimensions];
			for(int i = 0; i < dimensions; i++) {
				String[] arr = bReader.readLine().split(" ");
				for (int j = 0; j < dimensions; j++) {
					blocks[i][j] = Integer.parseInt(arr[j]);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't find the given file");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("An IO error occured");
			e.printStackTrace();
		}
		Board initial = new Board(blocks);
		Solver solver = new Solver(initial);
		if (!solver.isSolvable()) {
			System.out.println("This board cannot be solved");
		} else {
			solver.solve();
			System.out.println("Minimum moves: " + solver.moves());
			System.out.println("--------");
			System.out.println("END");
			for (Board board : solver.solution()) {
				System.out.println("/" + "\\" + "\n" + "||" + "\n" + "||");
				System.out.println(board);
			}
		}
	}
}
