/**
 * File name: BFS.java
 * ===================
 * This class performs breadth-first search for the given graph.
 */
package wordnet;

import java.util.LinkedList;

public class BFSModified {
	private int[] edgeTo; // an array of edges
	private boolean[] marked; // here we mark whether we visited one or another vertex
	private int[] distTo; // an array of distances to one or another vertex

	public BFSModified(Digraph g, int start) {
		edgeTo = new int[g.getVertexes()];
		marked = new boolean[g.getVertexes()];
		distTo = new int[g.getVertexes()];
		bfs(g, start);
	}

	public BFSModified(Digraph g, Iterable<Integer> vertexes) {
		edgeTo = new int[g.getVertexes()];
		marked = new boolean[g.getVertexes()];
		distTo = new int[g.getVertexes()];
		bfs(g, vertexes);
	}

	// Performs bfs regarding to the set of the vertexes
	private void bfs(Digraph g, Iterable<Integer> vertexes) {
		LinkedList<Integer> queue = new LinkedList<>();
		for (int s : vertexes) {
			marked[s] = true;
			distTo[s] = 0;
			queue.add(s);
		}
		while (!queue.isEmpty()) {
			int v = queue.poll();
			for (int w : g.adj(v)) {
				if (!marked[w]) {
					queue.add(w);
					marked[w] = true;
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
				}
			}
		}
	}

	// Performs bfs regarding to the specified vertex
	private void bfs(Digraph g, int s) {
		LinkedList<Integer> queue = new LinkedList<>();
		marked[s] = true;
		distTo[s] = 0;
		queue.add(s);
		while (!queue.isEmpty()) {
			int v = queue.poll();
			for (int w : g.adj(v)) {
				if (!marked[w]) {
					queue.add(w);
					marked[w] = true;
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
				}
			}
		}
	}

	/**
	 * Returns distance to the given vertex
	 * 
	 * @param i
	 *            the vertex distance to which we want to know
	 * @return distance to the given vertex
	 */
	public int getDistance(int i) {
		return distTo[i];
	}

	/**
	 * Checks whether the path to the given vertex exists
	 * 
	 * @param i
	 *            the vertex path to which we want to check
	 * @return true if the path exist, otherwise, false
	 */
	public boolean hasPath(int i) {
		return marked[i];
	}
}
