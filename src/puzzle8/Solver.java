/**
 * File name: Solver.java
 * ======================
 * This file implements the board solver.
 */
package puzzle8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solver {

	private Board initial; // initial board
	private PriorityQueue<Board> boards = new PriorityQueue<>(new boardComparator());
	private LinkedList<Board> history = new LinkedList<>(); // a sequence of boards which leads to the goal one
	private int movesMade = 0; // minimum number of moves you need to solve the initial board

	public Solver(Board initial) {
		this.initial = initial;
	}

	/**
	 * Checks whether the initial board is solvable
	 * 
	 * @return true - if it is solvable, otherwise, false
	 */
	public boolean isSolvable() {
		int count = 0;
		ArrayList<Integer> arrayList = new ArrayList<>();
		int[][] help = initial.getBoard();
		for (int i = 0; i < help.length; i++) {
			for (int j = 0; j < help[i].length; j++) {
				arrayList.add(help[i][j]);
			}
		}
		for (int i = 0; i < arrayList.size() - 1; i++) {
			for (int j = i + 1; j < arrayList.size(); j++) {
				if (arrayList.get(i) != 0 && arrayList.get(j) != 0 && arrayList.get(i) > arrayList.get(j))
					count++;
			}
		}
		return count % 2 == 0;
	}

	/**
	 * Returns a minimum number of moves that is needed to solve this board
	 * 
	 * @return a minimum number of moves that is needed to solve this board or "-1"
	 *         if the initial board could not be solved
	 */
	public int moves() {
		if (movesMade == 0)
			return -1;
		return movesMade;
	}

	/**
	 * Returns a sequence of boards which leads to the goal one or "null" if the
	 * initial board cannot be solved
	 * 
	 * @return a sequence of boards which leads to the goal one or "null" if the
	 *         initial board cannot be solved
	 */
	public Iterable<Board> solution() {
		if (history.size() == 0)
			return null;
		return history;
	}

	/**
	 * This comparator compares the boards using the Hamming distance function
	 */
	private class boardComparator implements Comparator<Board> {
		@Override
		public int compare(Board o1, Board o2) {
			if (o1.hamming() > o2.hamming())
				return 1;
			if (o1.hamming() < o2.hamming())
				return -1;
			return 0;
		}
	}

	/**
	 * Solves the initial board
	 */
	public void solve() {
		if (isSolvable()) {
			while (!initial.isGoal()) {
				for (Board board : initial.neighbours()) {
					boards.add(board);
				}
				initial = boards.remove();
			}
			history.add(initial);
			Board help = initial.getParent();
			while (help != null) {
				history.add(help);
				help = help.getParent();
				movesMade++;
			}
		} else {
			System.out.println("This board cannot be solved");
		}
	}
	
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
