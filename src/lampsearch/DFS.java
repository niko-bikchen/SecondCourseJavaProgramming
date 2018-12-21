/**
 * File name: DFS.java
 * ===================
 * This class performs depth-first search for the given graph.
 */
package lampsearch;

import java.util.LinkedList;

public class DFS {
	private int[] edgeTo; // an array of edges
	private boolean[] marked; // here we mark whether we visited one or another vertex
	private int[] distTo; // an array of distances to one or another vertex
	private int s; // starting vertex
	private int f; // final vertex
	private int vertexesVisited; // number of vertexes you need to visit to get to the final one

	public DFS(Graph g, int start, int finish) {
		s = start;
		f = finish;
		edgeTo = new int[g.getVertexes()];
		marked = new boolean[g.getVertexes()];
		distTo = new int[g.getVertexes()];
		dfs(g, start);
		distTo[s] = 0;
	}

	/**
	 * Performs depth-first search for the given graph beginning from the given
	 * vertex
	 * 
	 * @param g
	 *            graph where we will use depth-first search
	 * @param s
	 *            vertex from which we begin depth-first search
	 */
	private void dfs(Graph g, int s) {
		marked[s] = true;
		for (int w : g.adj(s)) {
			if (!marked[w]) {
				edgeTo[w] = s;
				distTo[w] = distTo[s] + 1;
				dfs(g, w);
			}
		}
	}

	public int getVertexesVisited() {
		return vertexesVisited;
	}

	/**
	 * Returns distance to the final vertex
	 * 
	 * @return distance to the final vertex
	 */
	public int getDistance() {
		return distTo[f];
	}

	/**
	 * Returns path from the starting vertex to the final one
	 * 
	 * @return path from the starting vertex to the final one
	 */
	public Iterable<Integer> path() {
		LinkedList<Integer> path = new LinkedList<>();
		if (!hasPath())
			return null;
		for (int x = f; x != s; x = edgeTo[x]) {
			path.add(x);
			vertexesVisited++;
		}
		path.add(s);
		return path;
	}

	/**
	 * Checks whether there is a path to the final vertex
	 * 
	 * @return true if the path exists, otherwise, false
	 */
	public boolean hasPath() {
		return marked[f];
	}
}
