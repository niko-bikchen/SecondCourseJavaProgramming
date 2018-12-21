/**
 * File name: SymbolTable.java
 * ===========================
 * This java class implements symbol table
 * and methods for working with it.
 */
package standbst;

import java.util.Iterator;

public class SymbolTable<Key extends Comparable<Key>, Value> {

	private class Node<Key, Value> {
		Key key;
		Value val;

		@Override
		public String toString() {
			return key + "-" + val;
		}
	}

	private Node<Key, Value>[] map; // array (symbol table) where keys and their values will be stored
	private int n; // number of the elements in the array (symbol table)

	public SymbolTable() {
		map = new Node[10];
	}

	public SymbolTable(int size) {
		map = new Node[size];
	}

	/**
	 * Adds key and value to the symbol table. If the given key was already present
	 * in the table, its value will be changed to the given one.
	 * 
	 * @param key
	 *            key to insert
	 * @param val
	 *            value to be inserted with the key
	 */
	public void put(Key key, Value val) {
		if (key == null)
			return;
		int i = rank(key);
		if (isEmpty()) {
			Node t = new Node();
			t.key = key;
			t.val = val;
			map[n++] = t;
			return;
		}
		if (i < n && map[i].key.compareTo(key) == 0)
			map[i].val = val;
		else {
			if (n == map.length)
				resize(2 * map.length);
			for (int j = n; j > i; j--) {
				map[j] = map[j - 1];
			}
			map[i] = new Node();
			map[i].key = key;
			map[i].val = val;
			n++;
		}
	}

	/**
	 * Deletes the given key and its value if it is present in the table
	 * 
	 * @param key
	 *            key to be deleted
	 * 
	 * @throws NullPointerException
	 *             if the given key is not present in the table
	 */
	public void delete(Key key) {
		if (get(key) == null)
			throw new NullPointerException();
		else {
			int i = rank(key);
			if (i == n - 1) {
				map[i].key = null;
				map[i].val = null;
				n--;
				return;
			}
			if (i < n && map[i].key.compareTo(key) == 0) {
				map[i].key = null;
				map[i].val = null;
				for (int j = i; j < n - 1; j++) {
					map[j] = map[j + 1];
				}
				n--;
			}
		}
	}

	/**
	 * Fins the smallest key
	 * 
	 * @return the smallest key
	 */
	public Key min() {
		return map[0].key;
	}

	/**
	 * Fins the largest key
	 * 
	 * @return the largest key
	 */
	public Key max() {
		return map[n - 1].key;
	}

	/**
	 * Deletes the smallest key
	 */
	public void deleteMin() {
		delete(map[0].key);
	}

	/**
	 * Deletes the largest key
	 */
	public void deleteMax() {
		delete(map[n - 1].key);
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
		int i = rank(key);
		if (get(key) != null)
			return map[i].key;
		if (i == 0)
			return null;
		return map[i - 1].key;

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
		int i = rank(key);
		if (get(key) != null)
			return map[i].key;
		if (i == n - 1)
			return null;
		return map[i].key;
	}

	/**
	 * Returns the value of the given key
	 * 
	 * @param key
	 *            the key value of which we look for
	 * @return value of the given key
	 */
	public Value get(Key key) {
		if (isEmpty())
			return null;
		int i = rank(key);
		if (i < n && map[i].key.compareTo(key) == 0)
			return map[i].val;
		else
			return null;
	}

	/**
	 * Returns the key which is on the "k" position in the array (symbol table)
	 * 
	 * @param k
	 *            position of the key
	 * @return the key which is on the "k" position in the array (symbol table)
	 */
	public Key select(int k) {
		if (k < n && k >= 0)
			return map[k].key;
		else
			return null;
	}

	/**
	 * Checks whether the table is empty
	 * 
	 * @return true if it is empty, otherwise, false
	 */
	public boolean isEmpty() {
		return n == 0;
	}

	/**
	 * Returns number of the elements in the array (symbol table)
	 * 
	 * @return number of elements in the array (symbol table)
	 */
	public int size() {
		return n;
	}

	/**
	 * Returns number of elements in the given range
	 * 
	 * @return number of elements in the given range or "0" if one the borders of
	 *         the range are less than 0 or more than array (symbol table) size
	 */
	public int size(int lo, int hi) {
		int count = 0;
		if ((lo < 0 || lo > map.length) || (hi < 0 || hi > map.length))
			return 0;
		while (lo != hi) {
			if (map[lo] != null)
				count++;
			lo++;
		}
		return count;
	}

	/**
	 * Checks whether the table contains a specific key
	 * 
	 * @param key
	 *            key to check
	 * @return true if such key exists in the table, otherwise, false
	 */
	public boolean contains(Key key) {
		return get(key) != null;
	}

	/**
	 * Returns iterator
	 * 
	 * @return iterator
	 */
	public Iterable<Key> keys() {
		return new KeyIterator();
	}

	/**
	 * Returns iterator for the given range of keys
	 * 
	 * @param lo
	 *            left border of the range
	 * @param hi
	 *            right border of the range
	 * @return iterator for the given range of keys
	 */
	public Iterable<Key> keys(int lo, int hi) {
		if ((lo < 0 || lo > map.length) || (hi < 0 || hi > map.length))
			return null;
		return new KeyIterator(lo, hi);
	}

	/**
	 * Finds the position of the key according to its value
	 * 
	 * @param key
	 *            the key which position needs to be found
	 * @return position of the given key
	 */
	private int rank(Key key) {
		int lo = 0, hi = n - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(map[mid].key);
			if (cmp < 0)
				hi = mid - 1;
			else if (cmp > 0)
				lo = mid + 1;
			else if (cmp == 0)
				return mid;
		}
		return lo;
	}

	@Override
	public String toString() {
		String out = "";
		out += "[ ";
		for (int i = 0; i < n; i++) {
			if (i == n - 1) {
				out += map[i];
			} else
				out += map[i] + ", ";
		}
		out += " ]";
		return out;
	}

	/**
	 * Resizes the array (symbol table) of keys
	 * 
	 * @param capacity
	 *            new size of the array (symbol table)
	 */
	private void resize(int capacity) {
		Node<Key, Value>[] copy = new Node[capacity];
		for (int i = 0, j = 0; i < n; i++) {
			if (copy[i] != null) {
				copy[j] = map[i];
				j++;
			}
		}
		map = copy;
	}

	/**
	 * Iterator implementation
	 */
	private class KeyIterator implements Iterator<Key>, Iterable<Key> {
		private int lo;
		private int hi;

		public KeyIterator() {
			lo = 0;
			hi = n;
		}

		public KeyIterator(int lo, int hi) {
			this.lo = lo;
			this.hi = hi;
		}

		@Override
		public boolean hasNext() {
			return lo < hi;
		}

		@Override
		public Key next() {
			return map[lo++].key;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Iterator<Key> iterator() {
			return this;
		}
	}
}
