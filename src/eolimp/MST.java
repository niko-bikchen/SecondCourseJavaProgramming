package eolimp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class MST {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"));
		EdgeWeightedGraph eGraph = new EdgeWeightedGraph(bReader);
		KruskalMST kMst = new KruskalMST(eGraph);
		bWriter.write((int)kMst.weight() + "");
		bWriter.flush();
	}
}

class UF {
    private int[] parent;
    private byte[] rank;
    private int count;

    public UF(int n) {
        count = n;
        parent = new int[n];
        rank = new byte[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }
    
    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];    // path compression by halving
            p = parent[p];
        }
        return p;
    }
    
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make root of smaller rank point to root of larger rank
        if      (rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
        else if (rank[rootP] > rank[rootQ]) parent[rootQ] = rootP;
        else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
        count--;
    }
}

class KruskalMST {
	private LinkedList<Edge> mst = new LinkedList<>(); // MST edges
	private int weight;

	public KruskalMST(EdgeWeightedGraph G) {
		// more efficient to build heap by passing array of edges
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        for (Edge e : G.edges()) {
            pq.add(e);
        }

        UF uf = new UF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.remove();
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) {
                uf.union(v, w);  
                mst.add(e); 
                weight += e.weight();
            }
        }
	}
	
	public double weight() {
        return weight;
    }
}

class Edge implements Comparable<Edge> {

	private final int v;
	private final int w;
	private final double weight;

	public Edge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	public double weight() {
		return weight;
	}

	public int either() {
		return v;
	}

	public int other(int vertex) {
		if (vertex == v)
			return w;
		else if (vertex == w)
			return v;
		return -1;
	}

	@Override
	public int compareTo(Edge that) {
		return Double.compare(this.weight, that.weight);
	}
}


class EdgeWeightedGraph {
	private final int V;
	private int E;
	private LinkedList<Edge>[] adj;
	
	public EdgeWeightedGraph(BufferedReader bReader) throws IOException {
		String[] data = bReader.readLine().split(" ");
		V = Integer.parseInt(data[0]);
		E = Integer.parseInt(data[1]);
		adj = (LinkedList<Edge>[]) new LinkedList[E];
		String line = null;
		while(true) {
			line = bReader.readLine();
			if(line == null)
				break;
			data = line.split(" ");
			int v = Integer.parseInt(data[0]) - 1;
			int w = Integer.parseInt(data[1]) - 1;
			double weight = Integer.parseInt(data[2]);
			Edge e = new Edge(v, w, weight);
			addEdge(e);
		}
	}
	
	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	public void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);
		if(adj[v] == null)
			adj[v] = new LinkedList<>();
		adj[v].add(e);
		if(adj[w] == null)
			adj[w] = new LinkedList<>();
		adj[w].add(e);
		E++;
	}
	
	public Iterable<Edge> adj(int v) {
		return adj[v];
	}

	public int degree(int v) {
		return adj[v].size();
	}

	public Iterable<Edge> edges() {
		LinkedList<Edge> list = new LinkedList<Edge>();
		for (int v = 0; v < V; v++) {
			int selfLoops = 0;
			for (Edge e : adj(v)) {
				if (e.other(v) > v) {
					list.add(e);
				}
				else if (e.other(v) == v) {
					if (selfLoops % 2 == 0)
						list.add(e);
					selfLoops++;
				}
			}
		}
		return list;
	}

}