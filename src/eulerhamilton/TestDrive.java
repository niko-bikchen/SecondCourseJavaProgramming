package eulerhamilton;

public class TestDrive {

	public static void main(String[] args) {
		/*
		Graph graph = new Graph(6);
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		graph.addEdge(4, 0);
		
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(2, 1);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		graph.addEdge(3, 1);
		graph.addEdge(4, 2);
		graph.addEdge(5, 1);
		graph.addEdge(5, 3);
		
		EulerHamilton eh = new EulerHamilton(graph);
		for(String n : eh.eulerCycle()) {
			System.out.println(n);
		}
		
		for(int n : eh.hamCycle()) {
			System.out.println(n);
		}
		*/
		Digraph digraph = new Digraph(7);
		
		digraph.addEdge(3, 6);
	    digraph.addEdge(3, 0);
	    digraph.addEdge(6, 3);
	    digraph.addEdge(6, 1);
	    digraph.addEdge(4, 2);
	    digraph.addEdge(4, 5);
	    digraph.addEdge(5, 0);
	    digraph.addEdge(5, 4); 
	    digraph.addEdge(0, 1);
	    digraph.addEdge(0, 2);
	    digraph.addEdge(1, 4);
	    digraph.addEdge(2, 6);
	    digraph.addEdge(1, 5);
	    digraph.addEdge(2, 3);
        
        DirectedEulerHamilton deh = new DirectedEulerHamilton(digraph);
        
        for(int n : deh.eulerCycle()) {
			System.out.println(n);
		}
        
        System.out.println();
        
        for(int n : deh.hamCycle()) {
			System.out.println(n);
		}
	}

}
