/**
 * File name: Kosaraju.java
 * ========================
 * This file implements kosaraju algorithm for
 * searching strongly connected components
 */
package eulerhamilton;

public class Kosaraju {
	private boolean marked[];
	private int[] id;
	private int count;

	public Kosaraju(Digraph digraph){
		marked = new boolean[digraph.getVertexes()];
		id = new int[digraph.getVertexes()];
		DFSReverse dfs = new DFSReverse(digraph.reverse());
		for (int v : dfs.reversePost()){
			if (!marked[v]){
				dfs(digraph, v);
				count++;
			}
		}
	}

	// DFS search used in kosoraju algorithm
	private void dfs(Digraph digraph, int v) {
		marked[v] = true;
		id[v] = count;
		for (int w : digraph.adj(v))
			if (!marked[w]) {
				dfs(digraph, w);
			}
	}

	/**
	 * Checks whether there any cycles in the graph
	 * 
	 * @return true if the cycle exists, otherwise, false
	 */
	public boolean hasCycle() {
		int i = 0;
		int j = 0;
		int counter = 0;
		while (j != count) {
			if (i == id.length) {
				i = 0;
				j++;
				counter = 0;
			}
			if (id[i++] == j)
				counter++;
			if (counter >= 2)
				return true;
		}
		return false;
	}

	/**
	 * Checks whether the two given vertexes are strongly connected
	 * 
	 * @param v
	 *            first vertex
	 * @param w
	 *            second vertex
	 * @return true if the vertexes are strongly connected, otherwise, false
	 */
	public boolean stronglyConnected(int v, int w) {
		return id[v] == id[w];
	}
}
