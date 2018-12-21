/**
 * File name: LinkedStack.java
 * ===========================
 * This class implements stack via
 * linked list
 */
package datastructures;

import java.util.*;

public class LinkedStack<Item> implements IStack<Item>, Iterable<Item> {

	private Node head = null; // First element
	private int count = 0; // Number of elements

	private class Node {
		Item item;
		Node next;
	}

	/**
	 * Adds element on the top of the stack
	 * 
	 * @param	item	element to add
	 */
	@Override
	public void push(Item item) {
		if (item == null)
			throw new NullPointerException("You cannot put a 'null' element inside the stack");
		Node oldHead = head;
		head = new Node();
		head.item = item;
		head.next = oldHead;
		count++;
	}

	/**
	 * Removes and returns element from the top of the stack
	 * 
	 * @param	item	element to add
	 */
	@Override
	public Item pop() {
		if (isEmpty())
			throw new NoSuchElementException("You cannot delete anything from the empty stack");
		Item item = head.item;
		head = head.next;
		count--;
		return item;
	}

	/**
	 * Checks whether the stack is empty
	 * 
	 * @return true - if it is empty
	 */
	@Override
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Returns number of elements in the stack
	 * 
	 * @return number of elements in the stack
	 */
	@Override
	public int size() {
		return count;
	}

	/**
	 * Returns iterator for stack
	 * 
	 * @return iterator for stack
	 */
	@Override
	public Iterator<Item> iterator() {
		return new LinkedStackIterator();
	}

	/**
	 * This inner class implements iterator for stack
	 */
	private class LinkedStackIterator implements Iterator<Item> {

		private Node current = head; // Element on the top of the stack

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
