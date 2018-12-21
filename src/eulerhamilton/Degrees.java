/**
 * File name: Degrees.java
 * =======================
 * This class counts indegrees and outdegrees for the given vertex from the given graph.
 * Also returns lists of sinks and sources
 */
package eulerhamilton;

import java.util.LinkedList;

public class Degrees {

	private Digraph digraph;

	public Degrees(Digraph digraph) {
		this.digraph = digraph;
	}

	public int indegree(int v) {
		int count = 0;
		for (int i = 0; i < digraph.getVertexes(); i++) {
			if (i != v && digraph.hasEdge(i, v)) {
				count++;
			}
		}
		return count;
	}

	public int outdegree(int v) {
		return digraph.degree(v);
	}

	public Iterable<Integer> sources() {
		LinkedList<Integer> sources = new LinkedList<>();
		for (int i = 0; i < digraph.getVertexes(); i++) {
			if (indegree(i) == 0)
				sources.add(i);
		}
		return sources;
	}

	public Iterable<Integer> sinks() {
		LinkedList<Integer> sinks = new LinkedList<>();
		for (int i = 0; i < digraph.getVertexes(); i++) {
			if (outdegree(i) == 0)
				sinks.add(i);
		}
		return sinks;
	}

	boolean isMap() {
		for (int i = 0; i < digraph.getVertexes(); i++) {
			if (outdegree(i) != 1)
				return false;
		}
		return true;
	}
}
