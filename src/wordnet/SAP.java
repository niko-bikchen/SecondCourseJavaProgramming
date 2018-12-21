/**
 * File name: SAP.java
 * ===================
 * This class is used for finding the shortest path and the closest common 
 * ancestor from this path for the two vertexes
 */
package wordnet;

import java.io.*;

public class SAP {

	private Digraph digraph;
	private int length;
	private int ancestor;

	public SAP(Digraph digraph) {
		this.digraph = digraph;
		length = -1;
		ancestor = -1;
	}

	/**
	 * Returns the shortest path between v and w
	 * 
	 * @return the shortest path between v and w
	 */
	public int length(int v, int w) {
		calculate(v, w);
		return length;
	}

	/**
	 * Returns the shortest path between each vertex from v and w
	 * 
	 * @return the shortest path between each vertex from v and w
	 */
	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		calculate(v, w);
		return length;
	}

	/**
	 * Returns the closest from the shortest path between v and w common ancestor of v and w
	 * 
	 * @return the closest from the shortest path between v and w common ancestor of v and w
	 */
	public int ancestor(int v, int w) {
		calculate(v, w);
		return ancestor;
	}

	/**
	 * Returns the closest common ancestor for each vertex from v and w
	 * from the shortest path between each vertex from v and w
	 * 
	 * @return the closest common ancestor for each vertex from v and w
	 */
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		calculate(v, w);
		return ancestor;
	}

	/**
	 * Calculates the shortest path between v and w and finds the closest ancestor
	 * of v and w
	 */
	private void calculate(int v, int w) {
		length = -1;
		ancestor = -1;
		if (v == w) {
			length = 0;
			ancestor = v;
			return;
		}
		BFSModified v_bfs = new BFSModified(digraph, v);
		BFSModified w_bfs = new BFSModified(digraph, w);
		for (int i = 0; i < digraph.getVertexes(); i++) {
			if (v_bfs.hasPath(i) && w_bfs.hasPath(i)) {
				if (length == -1 && ancestor == -1) {
					length = v_bfs.getDistance(i) + w_bfs.getDistance(i);
					ancestor = i;
				} else if (v_bfs.getDistance(i) + w_bfs.getDistance(i) < length) {
					length = v_bfs.getDistance(i) + w_bfs.getDistance(i);
					ancestor = i;
				}
			}
		}
	}

	/**
	 * Calculates the shortest path for each element from v and w and finds the
	 * closest ancestor from the shortest path for each element from v and w
	 */
	private void calculate(Iterable<Integer> v, Iterable<Integer> w) {
		length = -1;
		ancestor = -1;
		BFSModified v_bfs = new BFSModified(digraph, v);
		BFSModified w_bfs = new BFSModified(digraph, w);
		for (int i = 0; i < digraph.getVertexes(); i++) {
			if (v_bfs.hasPath(i) && w_bfs.hasPath(i)) {
				if (length == -1 && ancestor == -1) {
					length = v_bfs.getDistance(i) + w_bfs.getDistance(i);
					ancestor = i;
				} else if (v_bfs.getDistance(i) + w_bfs.getDistance(i) < length) {
					length = v_bfs.getDistance(i) + w_bfs.getDistance(i);
					ancestor = i;
				}
			}
		}
	}

	// Testing
	public static void main(String[] args) throws FileNotFoundException {
		Digraph digraph = new Digraph(new BufferedReader(new FileReader("digraph1.txt")));
		SAP sap = new SAP(digraph);

		int length1 = sap.length(3, 1);
		int ancestor1 = sap.ancestor(3, 1);

		int length2 = sap.length(9, 12);
		int ancestor2 = sap.ancestor(9, 12);

		int length3 = sap.length(7, 2);
		int ancestor3 = sap.ancestor(7, 2);

		int length4 = sap.length(1, 6);
		int ancestor4 = sap.ancestor(1, 6);

		System.out.println("length = " + length1 + "; ancestor = " + ancestor1);
		System.out.println("length = " + length2 + "; ancestor = " + ancestor2);
		System.out.println("length = " + length3 + "; ancestor = " + ancestor3);
		System.out.println("length = " + length4 + "; ancestor = " + ancestor4);
	}
}
