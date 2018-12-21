/**
 * File name: ArrayQueue.java
 * ==========================
 * This class implements queue via
 * cyclic array
 */
package datastructures;

import java.util.*;

public class ArrayQueue<Item> implements IQueue<Item>, Iterable<Item> {

	private Item[] arr; // Array of elements
	private int count = 0; // Number of elements
	private int index = 0; // Variable which we use for adding and removing elements
	private int begin = 0; // Index of the first element

	/**
	 * Constructor initializes an array with one element
	 */
	@SuppressWarnings("unchecked")
	public ArrayQueue() {
		arr = (Item[]) new Object[1];
	}

	/**
	 * Checks whether the queue is empty
	 * 
	 * @return true - if it is empty
	 */
	@Override
	public boolean isEmpty() {
		return count == 0;
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
	 * Adds element to the queue.
	 * We also can resize the array if its number of elements 
	 * corresponds the clause
	 * 
	 * @param	item	element to add
	 */
	@Override
	public void enqueue(Item item) {
		if (item == null)
			throw new NullPointerException("You cannot put a 'null' element inside the queue");
		if (count == arr.length)
			resize(arr.length * 2);
		if (index >= arr.length)
			index = 0;
		arr[index++] = item;
		count++;
	}

	/**
	 * Removes and returns element from the queue.
	 * We also can resize the array if its number of elements 
	 * corresponds the clause
	 * 
	 * @return deleted element
	 */
	@Override
	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException("You cannot delete anything from the empty queue");
		if (begin == arr.length)
			begin = 0;
		Item item = arr[begin];
		arr[begin++] = null;
		count--;
		if (count > 0 && count == arr.length / 4)
			resize(arr.length / 2);
		return item;
	}

	/**
	 * Resizes queue to the given capacity.
	 * While resizing we also rearrange elements in the array:
	 * [ 3 1 2 ] ==> [1 2 3 ... ...]
	 * 
	 * @param	capacity	new capacity of the queue
	 */
	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		int b = begin;
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < count; i++) {
			copy[i] = arr[b++];
			if (b == arr.length)
				b = 0;
		}
		arr = copy;
		index = count;
		begin = 0;
	}

	/**
	 * Returns iterator for queue
	 * 
	 * @return iterator for queue
	 */
	@Override
	public Iterator<Item> iterator() {
		return new ArrayQueueIterator();
	}

	/**
	 * This inner class implements iterator for queue
	 */
	private class ArrayQueueIterator implements Iterator<Item> {

		private int index = begin; // Index of the first element
		private int n = 0; 

		/**
		 * Check whether the next element is present
		 * 
		 * @return true - if the next element is present
		 */
		@Override
		public boolean hasNext() {
			return arr[index] != null && n < count;
		}

		/**
		 * Returns current element and moves to the next one
		 * 
		 * @return current element
		 */
		@Override
		public Item next() {
			Item item = arr[index++];
			if (index == arr.length)
				index = 0;
			n++;
			return item;
		}

	}

}
