/**
 * File name: EulirianGraph.java
 * =============================
 * This file implements eulirian graph each vertex
 * of which is presented with its x and y coordinates
 */
package eulerhamilton;

import java.io.*;
import java.util.*;
import edu.princeton.cs.introcs.StdDraw;

public class EulirianGraph {
	private int vertexes; // number of vertexes
	private int edges; // number of edges
	private LinkedList<Vertex>[] adj; // an array of vertexes
	private Vertex[] vertexs_list;

	private class Vertex {
		int id;
		int x;
		int y;

		public Vertex(int id, int x, int y) {
			this.x = x;
			this.y = y;
			this.id = id;
		}
	}

	public EulirianGraph(int vertexes) {
		this.vertexes = vertexes;
		vertexs_list = new Vertex[vertexes];
		adj = (LinkedList<Vertex>[]) new LinkedList[vertexes];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new LinkedList<Vertex>();
		}
	}

	public EulirianGraph(BufferedReader bReader) {
		String line = null;
		String[] arr = null;
		int count = 0;
		try {
			vertexes = Integer.parseInt(bReader.readLine());
			edges = Integer.parseInt(bReader.readLine());
			adj = (LinkedList<Vertex>[]) new LinkedList[vertexes];
			vertexs_list = new Vertex[vertexes];
			for (int i = 0; i < adj.length; i++) {
				adj[i] = new LinkedList<Vertex>();
			}
			while (true) {
				line = bReader.readLine();
				if (line == null)
					break;
				arr = line.split(" ");
				Vertex v1 = new Vertex(count, Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
				vertexs_list[count++] = v1;
				Vertex v2 = new Vertex(count, Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
				vertexs_list[count++] = v2;
				addEdge(v1, v2);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds edge between vertex 1 and vertex 2
	 * 
	 * @param v1
	 *            first vertex
	 * @param v2
	 *            second vertex
	 */
	public void addEdge(Vertex v1, Vertex v2) {
		adj[v1.id].add(v2);
		adj[v2.id].add(v1);
	}

	/**
	 * Degree of the given vertex
	 * 
	 * @param v
	 *            vertex which degree you want to know
	 * @return a degree of the given vertex
	 */
	public int degree(int v) {
		return adj[v].size();
	}

	/**
	 * Returns number of vertexes
	 * 
	 * @return number of vertexes
	 */
	public int getVertexes() {
		return vertexes;
	}

	/**
	 * Returns number of edges
	 * 
	 * @return number of edges
	 */
	public int getEdges() {
		return edges;
	}

	/**
	 * Returns adjacent vertexes regarding to the given one
	 * 
	 * @return adjacent vertexes regarding to the given one
	 */
	public Iterable<Integer> adj(int v) {
		LinkedList<Integer> clone = new LinkedList<>();
		for (Vertex n : adj[v]) {
			clone.add(n.id);
		}
		Collections.sort(clone);
		return clone;
	}

	/**
	 * Prints out (twice) edges of the graph
	 */
	public void print() {
		for (int v = 0; v < vertexes; v++) {
			for (Vertex w : adj[v]) {
				System.out.println(vertexs_list[v].id + "-" + w.id);
			}
		}
	}

	/**
	 * Draws eulirian graph
	 */
	public void show() {
		boolean[][] drawn = new boolean[vertexes][vertexes];
		StdDraw.setPenRadius(0.0040);
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for(Vertex v : vertexs_list) {
			StdDraw.point(v.x, v.y);
		}
		for(int i = 0; i < vertexes; i++) {
			for(Vertex v : adj[i]) {
				if(!drawn[i][v.id]) {
					StdDraw.line(vertexs_list[i].x, vertexs_list[i].y, v.x, v.y);
					drawn[i][v.id] = true;
				}
			}
		}
		StdDraw.show();
	}
}
