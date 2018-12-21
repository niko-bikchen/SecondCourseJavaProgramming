/**
 * File name: RandomizedQueue.java
 * ==============================
 * This class implements queue which
 * deletes and returns elements in random order
 * via dequeue() or returns them without 
 * removing in random order via sample().
 * In the base of this structure lies an array.
 */
package mystructures;

import java.util.*;
import edu.princeton.cs.introcs.StdRandom;

public class RandomizedQueue<Item> implements IRandomizedQueue<Item>, Iterable<Item>{

	private int count = 0; // Number of elements
	private Item[] arr; // Array of elements

	/**
	 * Constructor initializes an array with one element
	 */
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
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
		arr[count++] = item;
	}

	/**
	 * Removes and returns random element from the queue.
	 * After we have deleted an element we put on its place 
	 * an element from the end.
	 * We also can resize the array if its number of elements 
	 * corresponds the clause
	 * 
	 * @return deleted element
	 */
	@Override
	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException("You cannot delete anything from the empty queue");
		int index = StdRandom.uniform(count);
		Item item = arr[index];
		arr[index] = null;
		arr[index] = arr[--count];
		arr[count] = null;
		if (count > 0 && count == arr.length / 4)
			resize(arr.length / 2);
		return item;
	}

	/**
	 * Returns random element from the queue
	 * 
	 * @return random element from the queue
	 */
	@Override
	public Item sample() {
		int index = StdRandom.uniform(count);
		Item item = arr[index];
		return item;
	}

	/**
	 * Returns iterator for queue
	 * 
	 * @return iterator for queue
	 */
	@Override
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	/**
	 * This inner class implements iterator for queue
	 */
	private class RandomizedQueueIterator implements Iterator<Item> {

		private int index = 0; // Variable to iterate through the elements of the queue 

		/**
		 * Check whether the next element is present 
		 * 
		 * @return true - if the next element is present
		 */
		@Override
		public boolean hasNext() {
			return arr[index] != null && index < arr.length;
		}

		/**
		 * Returns current element and moves to the next one
		 * 
		 * @return current element
		 */
		@Override
		public Item next() {
			return arr[index++];
		}
	}

	/**
	 * Resizes queue to the given capacity
	 * 
	 * @param	capacity	new capacity of the queue
	 */
	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < count; i++)
			copy[i] = arr[i];
		arr = copy;
	}

}
