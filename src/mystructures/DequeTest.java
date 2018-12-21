package mystructures;

import java.util.Iterator;

public class DequeTest {
	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<>();
		Iterator<Integer> iterator;
		deque.addLast(5);
		deque.addFirst(2);
		deque.addFirst(7);
		deque.addLast(1);
		deque.addLast(11);
		deque.addLast(65);
		deque.addFirst(6);
		deque.addFirst(21);
		deque.removeLast();
		deque.removeLast();
		deque.removeLast();
		deque.removeFirst();
		deque.removeFirst();
		deque.removeFirst();
		deque.removeFirst();
		deque.removeFirst();
		deque.addFirst(12);
		deque.addFirst(14);
		deque.addLast(13);
		deque.removeLast();
		deque.removeLast();
		deque.removeLast();
		iterator = deque.iterator();
		System.out.print("[ ");
		while (iterator.hasNext())
			System.out.print(iterator.next() + " ");
		System.out.print("]");
	}
}
