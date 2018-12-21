package mystructures;

import java.util.Iterator;

import datastructures.ArrayQueue;

public class Test {

	public static void main(String[] args) {
		ArrayQueue<Integer> aQueue = new ArrayQueue<>();
		Iterator<Integer> iterator;
		aQueue.enqueue(1);
		aQueue.enqueue(2);
		System.out.println(aQueue.dequeue());
		aQueue.enqueue(3);
		aQueue.enqueue(4);
		aQueue.enqueue(5);
		aQueue.enqueue(6);
		aQueue.enqueue(7);
		aQueue.enqueue(8);
		aQueue.enqueue(9);
		System.out.println(aQueue.dequeue());
		aQueue.enqueue(10);
		aQueue.enqueue(11);
		System.out.println(aQueue.dequeue());
		System.out.println(aQueue.dequeue());
		System.out.println(aQueue.dequeue());
		aQueue.enqueue(12);
		System.out.println(aQueue.dequeue());
		aQueue.enqueue(13);
		System.out.println(aQueue.dequeue());
		System.out.println(aQueue.dequeue());
		System.out.println(aQueue.dequeue());
		System.out.println(aQueue.dequeue());
		System.out.println(aQueue.dequeue());
		System.out.println(aQueue.dequeue());
		aQueue.enqueue(14);
		aQueue.enqueue(15);
		aQueue.enqueue(16);
		iterator = aQueue.iterator();
		System.out.print("[ ");
		while (iterator.hasNext())
			System.out.print(iterator.next() + " ");
		System.out.print("]");
	}
}
