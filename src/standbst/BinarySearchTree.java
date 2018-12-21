/**
 * File name: BinarySearchTree.java
 * ===========================
 * This java class implements binary search tree 
 * and methods for working with it.
 */
package standbst;

import datastructures.*;

class BinarySearchTree<Key extends Comparable<Key>, Value> {
	private Node root;

	private class Node {
		int count = 0;
		Key key;
		Value value;
		Node left;
		Node right;

		public Node(Key key, Value value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString() {
			return key + "-" + value;
		}
	}

	/**
	 * Adds the given key and its value into the binary search tree. If the given
	 * key already exists in the tree, its value will be replaced by the given one.
	 * 
	 * @param key
	 *            key to add
	 * @param value
	 *            value for the key
	 */
	public void put(Key key, Value value) {
		root = put(root, key, value);
	}

	private Node put(Node temp, Key key, Value value) {
		if (temp == null) {
			Node node = new Node(key, value);
			node.count++;
			return node;
		}
		int cmp = key.compareTo(temp.key);
		if (cmp > 0)
			temp.right = put(temp.right, key, value);
		else if (cmp < 0)
			temp.left = put(temp.left, key, value);
		else if (cmp == 0)
			temp.value = value;
		temp.count = 1 + size(temp.left) + size(temp.right);
		return temp;
	}

	/**
	 * Returns the value which corresponds to the given key
	 * 
	 * @param key
	 *            the key value of which we look for
	 * @return value of the given key
	 */
	public Value get(Key key) {
		Node temp = root;
		while (temp != null) {
			int cmp = key.compareTo(temp.key);
			if (cmp > 0)
				temp = temp.right;
			if (cmp < 0)
				temp = temp.left;
			if (cmp == 0)
				return temp.value;
		}
		return null;
	}

	/**
	 * Finds the largest key which is smaller or equal to the given
	 * 
	 * @param key
	 *            key for which we find the largest key which is smaller or equal to
	 *            this key
	 * @return the largest key which is smaller or equal to the given
	 */
	public Key floor(Key key) {
		Node x = floor(root, key);
		if (x == null)
			return null;
		return x.key;
	}

	private Node floor(Node temp, Key key) {
		if (temp == null)
			return null;
		int cmp = key.compareTo(temp.key);
		if (cmp < 0)
			return floor(temp.left, key);
		if (cmp == 0)
			return temp;
		Node help = floor(temp.right, key);
		if (help != null)
			return help;
		else
			return temp;
	}

	/**
	 * Finds the smallest key which is greater or equal to the given
	 * 
	 * @param key
	 *            key for which we find the smallest key which is greater or equal
	 *            to this key
	 * @return the smallest key which is greater or equal to the given
	 */
	public Key ceiling(Key key) {
		Node x = ceiling(root, key);
		if (x == null)
			return null;
		return x.key;
	}

	private Node ceiling(Node temp, Key key) {
		if (temp == null)
			return null;
		int cmp = key.compareTo(temp.key);
		if (cmp > 0)
			return ceiling(temp.right, key);
		if (cmp == 0)
			return temp;
		Node help = ceiling(temp.left, key);
		if (help != null)
			return help;
		else
			return temp;
	}

	/**
	 * Finds the position of the key according to its value
	 * 
	 * @param key
	 *            the key which position needs to be found
	 * @return position of the given key
	 */
	public int rank(Key key) {
		return rank(root, key);
	}

	private int rank(Node temp, Key key) {
		if (temp == null)
			return 0;
		int cmp = key.compareTo(temp.key);
		if (cmp < 0)
			return rank(temp.left, key);
		else if (cmp > 0)
			return 1 + size(temp.left) + rank(temp.right, key);
		else if (cmp == 0)
			return size(temp.left);
		return 0;
	}

	/**
	 * Returns number of the elements in the array (symbol table)
	 * 
	 * @return number of elements in the array (symbol table)
	 */
	public int size() {
		return size(root);
	}

	private int size(Node temp) {
		if (temp == null)
			return 0;
		return temp.count;
	}

	/**
	 * Fins the smallest key
	 * 
	 * @return the smallest key
	 */
	public Key min() {
		Node min = root;
		while (true) {
			if (min.left == null)
				break;
			min = min.left;
		}
		return min.key;
	}

	/**
	 * Fins the smallest key in the subtree where the given key is its root
	 * 
	 * @param key
	 *            the root of the subtree
	 * 
	 * @return the smallest key in the subtree where the given key is its root
	 */
	public Node getMin(Key key) {
		Node min = root;
		while (min != null) {
			int cmp = key.compareTo(min.key);
			if (cmp > 0)
				min = min.right;
			if (cmp < 0)
				min = min.left;
			if (cmp == 0)
				break;
		}
		while (true) {
			if (min.left == null)
				break;
			min = min.left;
		}
		return min;
	}

	/**
	 * Fins the largest key
	 * 
	 * @return the largest key
	 */
	public Key max() {
		Node max = root;
		while (true) {
			if (max.right == null)
				break;
			max = max.right;
		}
		return max.key;
	}

	/**
	 * Fins the largest key in the subtree where the given key is its root
	 * 
	 * @param key
	 *            the root of the subtree
	 * 
	 * @return the largest key in the subtree where the given key is its root
	 */
	public Node getMax(Key key) {
		Node max = root;
		while (max != null) {
			int cmp = key.compareTo(max.key);
			if (cmp > 0)
				max = max.right;
			if (cmp < 0)
				max = max.left;
			if (cmp == 0)
				break;
		}
		while (true) {
			if (max.right == null)
				break;
			max = max.right;
		}
		return max;
	}

	/**
	 * Returns the set of the keys of the tree
	 * 
	 * @return set of the keys of the tree
	 */
	public Iterable<Key> keys() {
		LinkedQueue<Key> queue = new LinkedQueue<>();
		inorder(root, queue);
		return queue;
	}

	private void inorder(Node temp, LinkedQueue<Key> queue) {
		if (temp == null)
			return;
		inorder(temp.left, queue);
		queue.enqueue(temp.key);
		inorder(temp.right, queue);
	}

	/**
	 * Deletes the smallest element in the tree
	 * 
	 * @throws NullPointerException
	 *             if the tree is empty
	 */
	public void deleteMin() {
		if (root == null)
			throw new NullPointerException();
		root = deleteMin(root);
	}

	private Node deleteMin(Node temp) {
		if (temp.left == null)
			return temp.right;
		temp.left = deleteMin(temp.left);
		temp.count = 1 + size(temp.left) + size(temp.right);
		return temp;
	}

	/**
	 * Deletes the largest element in the tree
	 * 
	 * @throws NullPointerException
	 *             if the tree is empty
	 */
	public void deleteMax() {
		if (root == null)
			throw new NullPointerException();
		root = deleteMax(root);
	}

	private Node deleteMax(Node temp) {
		if (temp.right == null)
			return temp.left;
		temp.right = deleteMax(temp.right);
		temp.count = 1 + size(temp.left) + size(temp.right);
		return temp;
	}

	/**
	 * Deletes the given key and its value if it is present in the table
	 * 
	 * @param key
	 *            key to be deleted
	 * @throws NullPointerException
	 *             if the tree is empty
	 */
	public void delete(Key key) {
		if (root == null)
			throw new NullPointerException();
		root = delete(root, key);
	}

	private Node delete(Node temp, Key key) {
		if (temp == null)
			return null;
		int cmp = key.compareTo(temp.key);
		if (cmp < 0)
			temp.left = delete(temp.left, key);
		else if (cmp > 0)
			temp.right = delete(temp.right, key);
		else {
			if (temp.right == null)
				return temp.left;
			if (temp.left == null)
				return temp.right;
			Node help = temp;
			temp = getMin(help.right.key);
			temp.right = deleteMin(help.right);
			temp.left = help.left;
		}
		temp.count = size(temp.left) + size(temp.right) + 1;
		return temp;
	}

	/**
	 * Prints the contents of the tree
	 */
	public void print() {
		print(root);
		System.out.println();
	}

	private void print(Node root) {
		if (root != null) {
			print(root.left);
			System.out.print(root + " ");
			print(root.right);
		}
	}
}