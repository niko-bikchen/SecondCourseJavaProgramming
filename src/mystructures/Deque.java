/** 
 * File name: Deque.java
 * ====================
 * This class implements dequeue data structure
 * using the doubly linked list as the base
 */
package mystructures;

import java.util.*;

public class Deque<Item> implements IDeque<Item>, Iterable<Item> {

	private Node head = null; // First element
	private Node tail = null; // Last element
	private int count = 0; // Number of elements

	private class Node {
		Item item;
		Node next;
		Node prev;
	}

	/**
	 * Checks whether the dequeue is empty
	 * 
	 * @return true - if it is empty
	 */
	@Override
	public boolean isEmpty() {
		return (head == null && tail == null);
	}

	/**
	 * Returns number of elements in the dequeue
	 * 
	 * @return number of elements in the dequeue
	 */
	@Override
	public int size() {
		return count;
	}

	/**
	 * Adds element to the beginning of the dequeue
	 * 
	 * @param	item	element to add
	 */
	@Override
	public void addFirst(Item item) {
		if (item == null)
			throw new NullPointerException("You cannot put a 'null' element inside the deque");
		Node oldHead = head;
		head = new Node();
		head.item = item;
		head.next = null;
		head.prev = oldHead;
		if (oldHead != null) {
			oldHead.next = head;
		}
		if (tail == null)
			tail = head;
		count++;
	}

	/**
	 * Adds element to the end of the dequeue
	 * 
	 * @param	item	element to add
	 */
	@Override
	public void addLast(Item item) {
		if (item == null)
			throw new NullPointerException("You cannot put a 'null' element inside the deque");
		Node oldTail = tail;
		tail = new Node();
		tail.item = item;
		tail.prev = null;
		tail.next = oldTail;
		if (oldTail != null)
			oldTail.prev = tail;
		if (head == null)
			head = tail;
		count++;
	}

	/**
	 * Removes and returns the first element in the dequeue
	 * 
	 * @return removed first element
	 */
	@Override
	public Item removeFirst() {
		if (isEmpty())
			throw new NoSuchElementException("You cannot delete anything from the empty deque");
		Node newHead = head.prev;
		Node oldHead = head;
		head.prev = null;
		if (newHead != null)
			newHead.next = null;
		head = newHead;
		if (count == 1)
			tail = null;
		count--;
		return oldHead.item;
	}

	/**
	 * Removes and returns the last element in the dequeue
	 * 
	 * @return removed last element
	 */
	@Override
	public Item removeLast() {
		if (isEmpty())
			throw new NoSuchElementException("You cannot delete anything from the empty deque");
		Node newTail = tail.next;
		Node oldTail = tail;
		tail.next = null;
		if (newTail != null)
			newTail.prev = null;
		tail = newTail;
		if (count == 1)
			head = null;
		count--;
		return oldTail.item;
	}

	/**
	 * Returns iterator for dequeue
	 * 
	 * @return iterator for dequeue
	 */
	@Override
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	/**
	 * This inner class implements iterator for dequeue
	 */
	private class DequeIterator implements Iterator<Item> {
		
		private Node currentH = head;

		/**
		 * Check whether the next element is present 
		 * 
		 * @return true - if the next element is present
		 */
		@Override
		public boolean hasNext() {
			return currentH != null;
		}

		/**
		 * Returns current element and moves to the next one
		 * 
		 * @return current element
		 */
		@Override
		public Item next() {
			Item item = currentH.item;
			currentH = currentH.prev;
			return item;
		}
	}

}
