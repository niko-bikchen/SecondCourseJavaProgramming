package standbst;

public class Test {

	public static void main(String[] args) {
		SymbolTable<Integer, Integer> sTable = new SymbolTable<>();
		sTable.put(1, 1);
		sTable.put(2, 2);
		sTable.put(4, 4);
		sTable.put(5, 5);
		sTable.put(6, 6);
		System.out.println(sTable);
		sTable.put(7, 3);
		//System.out.println(sTable);
		System.out.println(sTable.floor(4));
		System.out.println(sTable.ceiling(4));
		//sTable.delete("d");
		//System.out.println(sTable);
		//System.out.println(sTable.min());
		//System.out.println(sTable.max());
		//Iterator<Integer> iterable = (Iterator<Integer>) sTable.keys();
		//System.out.println(iterable.next());
		//System.out.println(iterable.next());
		//System.out.println(iterable.next());
		//sTable.deleteMax();
		//sTable.deleteMin();
		//System.out.println(sTable.ceiling("b"));
		//System.out.println(sTable);
		//System.out.println(sTable.select(0));
		//System.out.println(sTable.size(0, sTable.size()));
		//Iterable<String> iterator = sTable.keys();
		BinarySearchTree<String, Integer> bTree = new BinarySearchTree<>();
		bTree.put("s", 1);
		bTree.put("e", 2);
		bTree.put("a", 3);
		bTree.put("c", 4);
		bTree.put("x", 5);
		bTree.put("r", 6);
		bTree.put("h", 7);
		bTree.put("m", 8);
		bTree.print();
		bTree.delete("c");
		bTree.print();
		System.out.println(bTree.max());
		System.out.println(bTree.min());
		System.out.println(bTree.ceiling("c"));
		System.out.println(bTree.floor("c"));
		System.out.println(bTree.size());
	}
}