package mystructures;

import java.util.Iterator;

public class RandomizedQueueTest {

	public static void main(String[] args) {
		RandomizedQueue<Integer> queue = new RandomizedQueue<>();
		Iterator<Integer> iterator = queue.iterator();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);
		queue.enqueue(6);
		queue.enqueue(7);
		queue.enqueue(8);
		queue.enqueue(9);
		queue.dequeue();
		iterator = queue.iterator();
		System.out.println();
		System.out.print("[ ");
		while (iterator.hasNext())
			System.out.print(iterator.next() + " ");
		System.out.print("]");
		queue.dequeue();
		iterator = queue.iterator();
		System.out.println();
		System.out.print("[ ");
		while (iterator.hasNext())
			System.out.print(iterator.next() + " ");
		System.out.print("]");
		queue.dequeue();
		iterator = queue.iterator();
		System.out.println();
		System.out.print("[ ");
		while (iterator.hasNext())
			System.out.print(iterator.next() + " ");
		System.out.print("]");
		queue.dequeue();
		iterator = queue.iterator();
		System.out.println();
		System.out.print("[ ");
		while (iterator.hasNext())
			System.out.print(iterator.next() + " ");
		System.out.print("]");
		queue.dequeue();
		iterator = queue.iterator();
		System.out.println();
		System.out.print("[ ");
		while (iterator.hasNext())
			System.out.print(iterator.next() + " ");
		System.out.print("]");
		System.out.println(queue.sample());
		System.out.println(queue.sample());
		iterator = queue.iterator();
		System.out.println();
		System.out.print("[ ");
		while (iterator.hasNext())
			System.out.print(iterator.next() + " ");
		System.out.print("]");
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.enqueue(8);
		System.out.println(queue.sample());
		iterator = queue.iterator();
		System.out.println();
		System.out.print("[ ");
		while (iterator.hasNext())
			System.out.print(iterator.next() + " ");
		System.out.print("]");
	}
}
