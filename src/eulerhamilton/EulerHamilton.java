/**
 * File name: EulerHamilton.java
 * =====================================
 * This class find Euler and Hamilton cycles
 * in the given graph.
 */
package eulerhamilton;

import java.util.LinkedList;

public class EulerHamilton {

	private Graph graph;
	private boolean[] marked;
	private int[] edgeTo;
	private int[] hamPath;
	private int begin_vertex;
	private LinkedList<Integer>[] adj;
	private LinkedList<String> path;

	public EulerHamilton(Graph graph) {
		this.graph = graph;
		marked = new boolean[graph.getVertexes()];
		edgeTo = new int[graph.getVertexes()];
		adj = graph.copyVertexes();
		path = new LinkedList<>();
	}

	private void dfs(Graph g, int s) {
		marked[s] = true;
		for (int w : g.adj(s)) {
			if (!marked[w]) {
				edgeTo[w] = s;
				dfs(g, w);
			}
		}
	}
	
	private int dfs(Graph g, int s, boolean[] visited) {
		visited[s] = true;
		int count = 1;
		for (int w : adj[s]) {
			if (!visited[w]) {
				count += dfs(g, w, visited);
			}
		}
		return count;
	}

	/**
	 * Checks whether the graph is connected
	 * 
	 * @return true if it is connected, otherwise, false
	 */
	private boolean isConnected() {
		for (begin_vertex = 0; begin_vertex < graph.getVertexes(); begin_vertex++)
			if (graph.degree(begin_vertex) > 0)
				break;
		dfs(graph, begin_vertex);
		for (int i = 0; i < graph.getVertexes(); i++) {
			if (marked[i] == false && graph.degree(i) > 0)
				return false;
		}
		return true;
	}

	/**
	 * Checks whether the graph has Euler cycle
	 * 
	 * @return true if the Euler cycle exists, otherwise, false
	 */
	private boolean isEulerCycle() {
		if (!isConnected())
			return false;
		int odd_number = 0;
		for (int i = 0; i < graph.getVertexes(); i++) {
			if (graph.degree(i) % 2 != 0)
				odd_number++;
		}
		if (odd_number == 0)
			return true;
		return false;
	}
	
	private void getPath(int v) {
		for (int i = 0; i < adj[v].size(); i++) {
			int w = adj[v].get(i);
			if(isSuitable(v, w)) {
				path.add(v + " - "  + w);
				removeEdge(v, w);
				getPath(w);
			}
		}
	}

	private boolean isSuitable(int v, int w) {
		if(adj[v].size() == 1)
			return true;
		int before_remove = dfs(graph, v, new boolean[graph.getVertexes()]);
		removeEdge(v, w);
		int after_remove = dfs(graph, v, new boolean[graph.getVertexes()]);
		addEdge(v, w);
		if(before_remove > after_remove)
			return false;
		return true;
	}
	
	public void removeEdge(Integer v1, Integer v2) {
		adj[v1].remove(v2);
		adj[v2].remove(v1);
	}
	
	public void addEdge(int v1, int v2) {
		adj[v1].add(v2);
		adj[v2].add(v1);
	}

	/**
	 * Returns Euler cycle if it exists
	 * 
	 * @return Euler cycle if it exists
	 */
	public Iterable<String> eulerCycle() {
		if (isEulerCycle()) {
			getPath(0);
			return path;
		} else {
			System.out.println("Cycle doesn't exist");
			return null;
		}
	}

	/**
	 * Checks whether we can put the vertex v in our Hamilton cycle
	 * 
	 * @return true if we can put v in the Hamilton cycle, otherwise, false
	 */
	private boolean isValid(int v, int pos) {
		if (!graph.hasEdge(hamPath[pos - 1], v))
			return false;
		for (int i = 0; i < pos; i++) {
			if (hamPath[i] == v)
				return false;
		}
		return true;
	}

	/**
	 * Finds Hamilton cycle
	 * 
	 * @param pos
	 *            vertex from which we start looking for Hamilton cycle
	 * @return true if the Hamilton cycle exists, otherwise, false
	 */
	private boolean hamCycleFinder(int pos) {
		if (pos == graph.getVertexes()) {
			if (graph.hasEdge(hamPath[pos - 1], hamPath[0]))
				return true;
			else
				return false;
		}
		for (int v = 1; v < graph.getVertexes(); v++) {
			if (isValid(v, pos)) {
				hamPath[pos] = v;
				if (hamCycleFinder(pos + 1) == true)
					return true;
				hamPath[pos] = -1;
			}
		}
		return false;
	}

	/**
	 * Returns Hamilton cycle if it exists
	 * 
	 * @return Hamilton cycle if it exists
	 */
	public Iterable<Integer> hamCycle() {
		hamPath = new int[graph.getVertexes()];
		for (int i = 0; i < hamPath.length; i++) {
			hamPath[i] = -1;
		}
		hamPath[0] = 0;
		if (hamCycleFinder(1) == false) {
			System.out.println("Cycle doesn't exist");
			return null;
		}
		LinkedList<Integer> path = new LinkedList<>();
		for (int i = 0; i < hamPath.length; i++) {
			if (hamPath[i] != -1)
				path.add(hamPath[i]);
		}
		path.add(hamPath[0]);
		return path;
	}
}
