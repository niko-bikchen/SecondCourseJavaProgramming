/**
 * File name: DFSReverse.java
 * ==========================
 * This class implements topological sorting
 */
package wordnet;

import java.util.Stack;

public class DFSReverse {

	private boolean[] marked;
	private Stack<Integer> reversePost;

	public DFSReverse(Digraph digraph) {
		reversePost = new Stack<Integer>();
		marked = new boolean[digraph.getVertexes()];
		for (int v = 0; v < digraph.getVertexes(); v++)
			if (!marked[v])
				dfs(digraph, v);
	}

	// DFS algorithm used for topological sorting
	private void dfs(Digraph digraph, int v) {
		marked[v] = true;
		for (int w : digraph.adj(v))
			if (!marked[w])
				dfs(digraph, w);
		reversePost.push(v);
	}

	/**
	 * Returns vertexes of the graph after topological sorting
	 * 
	 * @return vertexes of the graph after topological sorting
	 */
	public Iterable<Integer> reversePost() {
		return reversePost;
	}
}
