/**
 * File name: LinkedQueue.java
 * =========================== 
 * This class implements queue using
 * linked list
 */
package datastructures;

import java.util.*;

public class LinkedQueue<Item> implements IQueue<Item>, Iterable<Item> {

	private Node head = null; // First element of the queue
	private Node tail = null; // Last element of the queue
	private int count = 0; // Number of elements

	private class Node {
		Item item;
		Node next;
	}

	/**
	 * Checks whether the queue is empty
	 * 
	 * @return true - if it is empty
	 */
	@Override
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Returns number of elements in the queue
	 * 
	 * @return number of elements in the queue
	 */
	@Override
	public int size() {
		return count;
	}

	/**
	 * Adds element to the queue
	 * 
	 * @param	item	element to add
	 */
	@Override
	public void enqueue(Item item) {
		if (item == null)
			throw new NullPointerException("You cannot put a 'null' element inside the queue");
		Node oldTail = tail;
		tail = new Node();
		tail.item = item;
		tail.next = null;
		count++;
		if (isEmpty())
			head = tail;
		else
			oldTail.next = tail;
	}

	/**
	 * Removes and returns element from the queue
	 * 
	 * @return deleted element
	 */
	@Override
	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException("You cannot delete anything from the empty queue");
		Item item = head.item;
		head = head.next;
		count--;
		if (isEmpty())
			tail = null;
		return item;
	}

	/**
	 * Returns iterator for queue
	 * 
	 * @return iterator for queue
	 */
	@Override
	public Iterator<Item> iterator() {
		return new LinkedQueueIterator();
	}

	/**
	 * This inner class implements iterator for queue
	 */
	private class LinkedQueueIterator implements Iterator<Item> {

		private Node current = tail;

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
