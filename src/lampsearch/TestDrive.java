/**
 * File name: TestDrive.java
 * =========================
 * This class tests breadth-first search and
 * depth-first search on the given graph.
 */
package lampsearch;

import java.io.*;

public class TestDrive {

	public static void main(String[] args) throws FileNotFoundException {
		BufferedReader bReader = new BufferedReader(new FileReader("test.txt"));
		Graph graph = new Graph(bReader);
		int lampPosition = graph.placeTheLamp();
		int startPosition = 0;
		System.out.println("Looking for the lamp placed at " + lampPosition + ", beginning from " + startPosition);
		BFS bfs = new BFS(graph, startPosition, lampPosition);
		DFS dfs = new DFS(graph, startPosition, lampPosition);
		
		System.out.println("=== BFS ===");
		
		if (bfs.hasPath()) {
			for (int i : bfs.path()) {
				System.out.print(i + " <- ");
			}
			System.out.print("START");
			System.out.println("\nVertexes visited: " + bfs.getVertexesVisited());
			System.out.println("Distance covered: " + bfs.getDistance());
		} else {
			System.out.println("Path to the lamp does not exist");
		}
		
		System.out.println("=== DFS ===");
		
		if (dfs.hasPath()) {
			for (int i : dfs.path()) {
				System.out.print(i + " <- ");
			}
			System.out.print("START");
			System.out.println("\nVertexes visited: " + dfs.getVertexesVisited());
			System.out.println("Distance covered: " + dfs.getDistance());
		} else {
			System.out.println("Path to the lamp does not exist");
		}
	}
}
