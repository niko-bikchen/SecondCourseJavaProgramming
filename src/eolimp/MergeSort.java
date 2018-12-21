/**
 * File name: SortByChoise.java
 * ============================
 * This class sorts an array of Robot objects
 */
package eolimp;

import java.io.*;

public class MergeSort {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"));
		Robot[] robots = null;
		robots = new Robot[Integer.parseInt(bReader.readLine())];
		for (int i = 0; i < robots.length; i++) {
			String[] strArr = bReader.readLine().split(" ");
			robots[i] = new Robot(Integer.parseInt(strArr[0]), Integer.parseInt(strArr[1]));
		}
		//MergeBU.sort(robots);
		for (int i = 0; i < robots.length; i++) {
			bWriter.write(robots[i].toString());
			bWriter.newLine();
			bWriter.flush();
		}
		bReader.close();
		bWriter.close();
	}
}

class Robot implements Comparable<Robot> {

	int primary_number = 0;
	int secondary_number = 0;

	public Robot(int p, int s) {
		primary_number = p;
		secondary_number = s;
	}

	@Override
	public int compareTo(Robot o) {
		if (this.primary_number < o.primary_number)
			return -1;
		if (this.primary_number > o.primary_number)
			return 1;
		return 0;
	}

	@Override
	public String toString() {
		return primary_number + " " + secondary_number;
	}
}
/*
class MergeBU {

	public static void merge(Robot[] a, Robot[] aux, int lo, int mid, int hi) {
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}
	}

	public static void sort(Robot[] a) {
		int N = a.length;
		Robot[] aux = new Robot[N];
		for (int sz = 1; sz < N; sz = sz + sz)
			for (int lo = 0; lo < N - sz; lo += sz + sz)
				merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
	}

	private static boolean less(Robot v, Robot w) {
		return v.compareTo(w) < 0;
	}
}

class InsertionSort {
	private static boolean less(Robot v, Robot w) {
		return v.compareTo(w) < 0;
	}

	private static void exch(Robot[] a, int i, int j) {
		Robot swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	public static void sort(Robot[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			for (int j = i; j > 0; j--)
				if (less(a[j], a[j - 1]))
					exch(a, j, j - 1);
				else
					break;
		}
	}

	public static void sort(Robot[] a, int l, int h) {
		for (int i = l; i <= h; i++) {
			for (int j = i; j > 0; j--)
				if (less(a[j], a[j - 1]))
					exch(a, j, j - 1);
				else
					break;
		}
	}
}
*/