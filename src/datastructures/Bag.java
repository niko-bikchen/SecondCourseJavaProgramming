/**
 * File name: Bag.java
 * ===================
 * This class implements bag
 * via linked list
 */
package datastructures;

import java.util.*;

public class Bag<Item> implements Iterable<Item> {

	private Node head = null; // First element in the bag
	private int count = 0; // Number of elements in the bag

	private class Node {
		Item item;
		Node next;
	}

	/**
	 * Adds element to the bag
	 * @param	item	element to add
	 */
	public void add(Item item) {
		if (item == null)
			throw new NullPointerException("You cannot put a 'null' element inside the bag");
		Node oldHead = head;
		head = new Node();
		head.item = item;
		head.next = oldHead;
		count++;
	}
	
	/**
	 * Checks whether the bag is empty
	 * 
	 * @return true - if the bag is empty
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Returns the number of elements in the bag
	 * 
	 * @return number of elements in the bag
	 */
	public int size() {
		return count;
	}
	
	/**
	 * Returns iterator for bag
	 * 
	 * @return iterator for bag
	 */
	@Override
	public Iterator<Item> iterator() {
		return new BagIterator();
	}

	/**
	 * This inner class implements iterator for bag
	 */
	private class BagIterator implements Iterator<Item> {

		private Node current = head; // First element in the bag

		/**
		 * Check whether the next element is present
		 * 
		 * @return true - if the next element is present
		 */
		@Override
		public boolean hasNext() {
			return current != null;
		}

		/**
		 * Returns current element and moves to the next one
		 * 
		 * @return current element
		 */
		@Override
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}

	}

}
