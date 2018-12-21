/**
 * File name: DirectedEulerHamilton.java
 * =====================================
 * This class find Euler and Hamilton cycles
 * in the given directed graph.
 */
package eulerhamilton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class DirectedEulerHamilton {

	private Digraph digraph;
	private Kosaraju kosaraju;
	private Degrees degrees;
	private LinkedList<Integer> hamPath;
	private LinkedList<Integer>[] adj;
	private LinkedList<Integer> eulerPath;

	public DirectedEulerHamilton(Digraph digraph) {
		this.digraph = digraph;
		kosaraju = new Kosaraju(digraph);
		degrees = new Degrees(digraph);
		hamPath = new LinkedList<>();
		eulerPath = new LinkedList<>();
		adj = digraph.copyVertexes();
	}

	private boolean isSC() {
		for (int i = 0; i < digraph.getVertexes(); i++) {
			for (int j = 0; j < digraph.getVertexes(); j++) {
				if (i != j && !kosaraju.stronglyConnected(i, j))
					return false;
			}
		}
		return true;
	}

	/**
	 * Checks whether the graph has Euler cycle
	 * 
	 * @return true if the Euler cycle exists, otherwise, false
	 */
	private boolean isEulerCycle() {
		if (!isSC())
			return false;
		for (int i = 0; i < digraph.getVertexes(); i++) {
			if (degrees.indegree(i) != degrees.outdegree(i))
				return false;
		}
		return true;
	}

	public void removeEdge(Integer v1, Integer v2) {
		adj[v1].remove(v2);
	}

	private void getPath(int v) {
		Stack<Integer> path = new Stack<>();
		int current = v;
		path.push(current);
		int next = 0;
		while (!path.isEmpty()) {
			if (adj[current].size() > 0) {
				path.push(current);
				next = adj[current].remove();
				current = next;
			} else {
				eulerPath.add(current);
				current = path.pop();
			}
		}
	}

	/**
	 * Returns Euler cycle
	 * 
	 * @return Euler cycle
	 */
	public Iterable<Integer> eulerCycle() {
		if (isEulerCycle()) {
			getPath(0);
			Collections.reverse(eulerPath);
			return eulerPath;
		} else {
			System.out.println("Path doesn't exist");
			return null;
		}
	}

	/**
	 * Checks whether the graph has Hamilton cycle
	 * 
	 * @return true if the Hamilton cycle exists, otherwise, false
	 */
	private boolean isHamCycle() {
		DFSReverse dfsReverse = new DFSReverse(digraph);
		ArrayList<Integer> reverse = dfsReverse.reversePost();
		Collections.reverse(reverse);
		hamPath.add(reverse.get(0));
		for (int i = 0, j = i + 1; i < reverse.size() - 1; i++, j++) {
			if (!digraph.hasEdge(reverse.get(i), reverse.get(j)))
				return false;
			hamPath.add(reverse.get(j));
		}
		return true;
	}

	/**
	 * Returns Hamilton cycle
	 * 
	 * @return Hamilton cycle
	 */
	public Iterable<Integer> hamCycle() {
		if (isHamCycle()) {
			return hamPath;
		} else {
			System.out.println("Path doesn't exist");
			return null;
		}
	}
}