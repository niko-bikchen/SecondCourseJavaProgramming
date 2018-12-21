/*
 * File name: Percolation.java
 * ===========================
 * Implements grid with cells which are later
 * opened and connected with each other 
 * in order to make grid percolate
 */
package percolation_prog;

public class Percolation {

	WeightedQuickUnionUF unionUF; 
	boolean[][] cells; // Boolean array for marking opened cells
	private int openedCells = 0; // Number of opened cells
	int bottomNode, size; // Variables to for the top "abstract" node and size of the grid 
	int topNode = 0; // Variable for the top "abstract" node 

	// Constructor which initializes the grid
	public Percolation(int n) {
		size = n;
		unionUF = new WeightedQuickUnionUF(n * n + 2);
		cells = new boolean[n][n];
		bottomNode = n * n + 1;
	}

	/*
	 * This method opens a random cell and connects it with the nearest open cells
	 * if there are any
	 */
	public void open(int row, int column) {
		if (!isOpened(row, column)) {
			cells[row - 1][column - 1] = true;
			openedCells++;

			if (row == 1)
				unionUF.union(topNode, findNumber(row, column));

			if (row == size)
				unionUF.union(bottomNode, findNumber(row, column));

			if (row > 1 && isOpened(row - 1, column))
				unionUF.union(findNumber(row, column), findNumber(row - 1, column));

			if (row < size && isOpened(row + 1, column))
				unionUF.union(findNumber(row, column), findNumber(row + 1, column));

			if (column < size && isOpened(row, column + 1))
				unionUF.union(findNumber(row, column), findNumber(row, column + 1));

			if (column > 1 && isOpened(row, column - 1))
				unionUF.union(findNumber(row, column), findNumber(row, column - 1));
		}
	}
	
	/* This method converts position of the 
	 * given cell into one number so it can
	 * be passed to the WeightedQuickUnion
	 */
	public int findNumber(int row, int column) {
		return size * (row - 1) + column;
	}

	// This method returns the number of opened cells
	public int getOpenedCells() {
		return openedCells;
	}

	// This method checks if the given cell is already open
	public boolean isOpened(int row, int column) {
		return cells[row - 1][column - 1];
	}

	/* This method checks whether the grid percolates.
	 * If top and bottom "abstract" nodes are connected
	 * via cells the grid will percolate
	 */
	public boolean percolates() {
		return unionUF.connected(topNode, bottomNode);
	}

}
