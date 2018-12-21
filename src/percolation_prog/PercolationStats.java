/*
 * File name: PercolationStats.java
 * ================================
 * This class carries "n" tests on 
 * the "size X size" grid to calculate
 * • Deviation of percolation bound
 * • Mean value of percolation bound
 * • 95% confidence interval
 */
package percolation_prog;

import java.util.Scanner;

import edu.princeton.cs.introcs.*;

public class PercolationStats {

	private Percolation grid;
	private double[] results; // Array for the results of tests
	private int numberOfTests; // Number of tests

	/*
	 * Here we carry "times" experiments on the given "gridSize X gridSize" grid
	 */
	public PercolationStats(int gridSize, int times) {
		int row, column;
		numberOfTests = times;
		results = new double[times];
		for (int i = 0; i < times; i++) {
			grid = new Percolation(gridSize);
			while (!grid.percolates()) {
				row = StdRandom.uniform(1, gridSize + 1);
				column = StdRandom.uniform(1, gridSize + 1);
				grid.open(row, column);
			}
			double result = (double) grid.getOpenedCells() / (gridSize * gridSize);
			results[i] = result; // Here we save the result of "i" experiment
		}

	}

	// Deviation of percolation bound
	public double stddev() {
		return StdStats.stddev(results);
	}

	// Mean value of percolation bound
	public double mean() {
		return StdStats.mean(results);
	}

	// Here we calculate upper bound of 95% confidence interval
	public double confidenceUpper() {
		return mean() + ((1.96 * stddev()) / Math.sqrt(numberOfTests));
	}

	// Here we calculate lower bound of 95% confidence interval
	public double confidenceLower() {
		return mean() - ((1.96 * stddev()) / Math.sqrt(numberOfTests));
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter grid size: ");
		int gridSize = in.nextInt();
		System.out.print("Enter number of tests: ");
		int times = in.nextInt();
		PercolationStats tests = new PercolationStats(gridSize, times);
		StdOut.println("Mean : " + tests.mean() + "\n" + "Stddev : " + tests.stddev() + "\n"
				+ "95% confidence interval: " + tests.confidenceLower() + ", " + tests.confidenceUpper());
	}
}
