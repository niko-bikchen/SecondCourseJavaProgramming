package eolimp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Queue {
	static long[] heap = null;
	static long size;

	public static void main(String[] args){
		try (BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
				FileWriter fWriter = new FileWriter("output.txt")) {
			String[] array_1 = bReader.readLine().split(" ");
			int q_size = Integer.parseInt(array_1[0]);
			int casboxes = Integer.parseInt(array_1[1]);
			String line = bReader.readLine();
			String[] array_2 = line.split(" ");
			long[] queue = new long[q_size];
			for (int i = 0; i < q_size; i++)
				queue[i] = Integer.parseInt(array_2[i]);
			fWriter.write(serveTheQueue(queue, q_size, casboxes) + "");
			fWriter.flush();
		} catch (NumberFormatException e) {
			System.err.println("Invalid input");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static long serveTheQueue(long[] queue, int q_size, int cashboxes) {
		long time = 0;
		heap = new long[cashboxes + 1];
		if (cashboxes <= q_size) {
			for (int i = 0; i < cashboxes; i++)
				push(queue[i]);
			for (int i = cashboxes; i < q_size; i++) {
				heap[1] += queue[i];
				toCorrectPosition(1);
			}
			for (int i = 1; i < heap.length; i++) {
				time = Math.max(time, heap[i]);
			}
		} else
			for (int i = 0; i < q_size; i++)
				time = Math.max(time, queue[i]);
		return time;
	}

	private static void push(long element) {
		heap[(int) ++size] = element;
		for (int help = (int) size; help != 1 && heap[help] < heap[help / 2]; help /= 2)
			exch(heap, help, help / 2);
	}

	private static void toCorrectPosition(int index) {
		int help = 0;
		if (index * 2 + 1 <= size) {
			help = index * 2 + (heap[index * 2] > heap[index * 2 + 1] ? 1 : 0);
			if (heap[index] > heap[help]) {
				exch(heap, index, help);
				toCorrectPosition(help);
			}
		} else if (index * 2 <= size && heap[index] > heap[index * 2]) {
			exch(heap, index, index * 2);
			toCorrectPosition(index * 2);
		}
	}

	private static void exch(long[] a, long i, long j) {
		long t = a[(int) i];
		a[(int) i] = a[(int) j];
		a[(int) j] = t;
	}
}