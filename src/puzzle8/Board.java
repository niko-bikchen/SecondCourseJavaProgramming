/**
 * File name: Board.java
 * =====================
 * This file implements the 8 puzzle board.
 */
package puzzle8;

import java.util.ArrayList;

public class Board {
	private Board parent; // Parent of this board
	private int dimension;
	private int[][] blocks; // Tiles of the board
	private int parents_number; // Number of board's parents

	/**
	 * Returns number of board's parents
	 * 
	 * @return number of board's parents
	 */
	public int getNumberOfParents() {
		return parents_number;
	}

	public Board(int[][] blocks) {
		int length = blocks.length;
		dimension = length;
		this.blocks = new int[length][length];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++)
				this.blocks[i][j] = blocks[i][j];
		}
	}

	/**
	 * Size of the board
	 * 
	 * @return size of the board
	 */
	public int dimenshion() {
		return dimension;
	}

	/**
	 * A "distance" between this board and the goal board calculated via Manhattan
	 * distance function
	 * 
	 * @return a "distance" between this board and the goal board
	 */
	public int manhattan() {
		int result = parents_number;
		int count = 1;
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[i].length; j++) {
				if (blocks[i][j] != count && blocks[i][j] != 0) {
					int true_i = (blocks[i][j] - 1) / 3;
					int true_j = (blocks[i][j] - 1) % 3;
					result += Math.abs(i - true_i) + Math.abs(j - true_j);
				}
				count++;
			}
		}
		return result;
	}

	/**
	 * A "distance" between this board and the goal board calculated via Hamming
	 * distance function
	 * 
	 * @return a "distance" between this board and the goal board
	 */
	public int hamming() {
		int result = parents_number;
		int count = 1;
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[i].length; j++) {
				if (blocks[i][j] != count && blocks[i][j] != 0)
					result++;
				count++;
			}
		}
		return result;

	}

	/**
	 * Checks whether the board is solved
	 * 
	 * @return true - if solved, otherwise, false
	 */
	public boolean isGoal() {
		return hamming() - parents_number == 0;

	}

	/**
	 * Compares two boards
	 * 
	 * @return true - if they are equal, otherwise, false
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != this.getClass())
			return false;
		Board y = (Board) obj;
		int[][] y_board = y.getBoard();
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[i].length; j++) {
				if (blocks[i][j] != y_board[i][j])
					return false;
			}
		}
		return true;

	}

	/**
	 * Returns neighbors (or the boards which you can derive from this one by
	 * rearranging the elements) of this board.
	 * 
	 * @return neighbors of this board
	 */
	public Iterable<Board> neighbours() {
		int i = 0;
		int j = 0;
		ArrayList<Board> array = new ArrayList<Board>();
		Board neighbour = null;
		int[][] table = null;
		for (i = 0; i < dimension; i++) {
			for (j = 0; j < dimension; j++)
				if (blocks[i][j] == 0)
					break;
			if (j != dimension)
				break;
		}
		if (i - 1 >= 0) {
			table = swap(i, j, i - 1, j);
			neighbour = createNeighbour(table);
			if (neighbour != null)
				array.add(neighbour);
		}
		if (j - 1 >= 0) {
			table = swap(i, j, i, j - 1);
			neighbour = createNeighbour(table);
			if (neighbour != null)
				array.add(neighbour);
		}
		if (j + 1 < dimension) {
			table = swap(i, j, i, j + 1);
			neighbour = createNeighbour(table);
			if (neighbour != null)
				array.add(neighbour);
		}
		if (i + 1 < dimension) {
			table = swap(i, j, i + 1, j);
			neighbour = createNeighbour(table);
			if (neighbour != null)
				array.add(neighbour);
		}
		return array;
	}

	/**
	 * Creates a single neighbor of this board
	 * 
	 * @param table
	 *            tiles of the board the neighbor of which you are creating
	 * @return a single neighbor of this board
	 */
	private Board createNeighbour(int[][] table) {
		Board neigbour = new Board(table);
		Board prev = parent;
		if (prev != null) {
			Board help = prev;
			if (neigbour.equals(help))
				return null;
			neigbour.setParent(this);
			return neigbour;
		}
		neigbour.setParent(this);
		return neigbour;
	}

	/**
	 * Swaps the element in a1 row b1 column with the element in a2 row b2 column
	 * 
	 * @param r1
	 *            row of the first elements
	 * @param c1
	 *            column of the first element
	 * @param r2
	 *            row of the second element
	 * @param c2
	 *            column of the second element
	 * @return a new matrix where the given elements were swapped
	 */
	private int[][] swap(int r1, int c1, int r2, int c2) {
		int[][] answer = this.getBoard();
		int oldValue = answer[r1][c1];
		answer[r1][c1] = answer[r2][c2];
		answer[r2][c2] = oldValue;
		return answer;
	}

	/**
	 * Sets the parent for the given board
	 * 
	 * @param board
	 *            the board for which we set the parent
	 */
	private void setParent(Board board) {
		parents_number = board.parents_number + 1;
		parent = board;
	}

	/**
	 * Returns the parent board of this board
	 * 
	 * @return the parent board of this board
	 */
	public Board getParent() {
		return parent;
	}

	/**
	 * Prints the board
	 */
	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < blocks.length; i++) {
			result += "------" + "\n";
			for (int j = 0; j < blocks[i].length; j++) {
				result += +blocks[i][j] + "|";
			}
			result += "\n";
		}
		result += "------";
		return result;
	}

	/**
	 * Returns the matrix which represents tiles of this board
	 * 
	 * @return the matrix which represents tiles of this board
	 */
	public int[][] getBoard() {
		int[][] board = new int[dimension][dimension];
		copy(board);
		return board;

	}

	/**
	 * Makes a copy of the given matrix
	 * 
	 * @param board
	 *            a copy of the given matrix
	 */
	public void copy(int[][] board) {
		if (board == null)
			board = new int[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++)
				board[i][j] = blocks[i][j];
		}
	}

}
