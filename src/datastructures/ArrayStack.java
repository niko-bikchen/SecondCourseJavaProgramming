/**
 * File name: ArrayStack.java
 * ===========================
 * This class implements stack via
 * array
 */
package datastructures;

import java.util.*;

public class ArrayStack<Item> implements IStack<Item>, Iterable<Item> {

	private Item[] arr; // Array of elements
	private int count = 0; // Number of elements

	/**
	 * Constructor initializes an array with one element
	 */
	@SuppressWarnings("unchecked")
	public ArrayStack() {
		arr = (Item[]) new Object[1];
	}

	/**
	 * Adds element on the top of the stack.
	 * We also can resize the array if its number of elements 
	 * corresponds the clause
	 * 
	 * @param	item	element to add
	 */
	@Override
	public void push(Item item) {
		if (item == null)
			throw new NullPointerException("You cannot put a 'null' element inside the stack");
		if (count == arr.length)
			resize(arr.length * 2);
		arr[count++] = item;
	}

	/**
	 * Removes and returns element from the top of the stack.
	 * We also can resize the array if its number of elements 
	 * corresponds the clause
	 * 
	 * @param	item	element to add
	 */
	@Override
	public Item pop() {
		if (isEmpty())
			throw new NoSuchElementException("You cannot delete anything from the empty stack");
		Item item = arr[--count];
		arr[count] = null;
		if (count > 0 && count == arr.length / 4)
			resize(arr.length / 2);
		return item;
	}

	/**
	 * Checks whether the stack is empty
	 * 
	 * @return true - if it is empty
	 */
	@Override
	public boolean isEmpty() {
		return count == 0;
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
	 * Resizes stack to the given capacity
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

	/**
	 * Returns iterator for stack
	 * 
	 * @return iterator for stack
	 */
	@Override
	public Iterator<Item> iterator() {
		return new ArrayStackIterator();
	}

	/**
	 * This inner class implements iterator for stack
	 */
	private class ArrayStackIterator implements Iterator<Item> {

		private int index = count; // Index of the top element

		/**
		 * Check whether the next element is present
		 * 
		 * @return true - if the next element is present
		 */
		@Override
		public boolean hasNext() {
			return index > 0;
		}

		/**
		 * Returns current element and moves to the next one
		 * 
		 * @return current element
		 */
		@Override
		public Item next() {
			return arr[--index];
		}

	}

}
