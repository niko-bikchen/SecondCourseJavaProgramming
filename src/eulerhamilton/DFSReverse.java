/**
 * File name: DFSReverse.java
 * ==========================
 * This class implements topological sorting
 */
package eulerhamilton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class DFSReverse {

	private boolean[] marked;
	private ArrayList<Integer> reversePost;

	public DFSReverse(LinkedList<Integer>[] adj) {
		reversePost = new ArrayList<>();
		marked = new boolean[adj.length];
		for (int v = 0; v < adj.length; v++)
			if (!marked[v])
				dfs(adj, v);
	}

	// DFS algorithm used for topological sorting
	private void dfs(LinkedList<Integer>[] adj, int v) {
		marked[v] = true;
		for (int w : adj[v])
			if (!marked[w])
				dfs(adj, w);
		reversePost.add(v);
	}

	public DFSReverse(Digraph digraph) {
		reversePost = new ArrayList<>();
		marked = new boolean[digraph.getVertexes()];
		for (int v = 0; v < digraph.getVertexes(); v++)
			if (!marked[v])
				dfs(digraph, v);
	}

	private void dfs(Digraph digraph, int v) {
		marked[v] = true;
		for (int w : digraph.adj(v))
			if (!marked[w])
				dfs(digraph, w);
		reversePost.add(v);
	}

	/**
	 * Returns vertexes of the graph after topological sorting
	 * 
	 * @return vertexes of the graph after topological sorting
	 */
	public ArrayList<Integer> reversePost() {
		return reversePost;
	}
}
