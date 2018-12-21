/*
 * File name: ThreeN.java
 * ======================
 * Goal: To implement the following algorithm:
 * 1.input n
 * 2.print n
 * 3.if n = 1 then STOP
 * 4.if n is odd then n = 3 * n + 1
 * 5.else n = n / 2
 * 6.GOTO 2
 * 
 * 0 < n < 1,000,000
 * 
 * For each pair of numbers i and j output the numbers i and j 
 * in the same order in which they arrived at the input. 
 * After that, output the maximum length of the cycle among all integers 
 * between i and j inclusive. 
 * For each test, three numbers should be displayed in a separate line,
 * separated by one space.
 */
package eolimp;

import java.io.*;
import java.util.*;

public class ThreeN {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner in = new Scanner(System.in);
		int i = 0, j = 0;
		while (in.hasNextInt()) {
			i = in.nextInt();
			j = in.nextInt();
			System.out.println(i + " " + j + " " + doTheAlgorithm(i, j));
		}
	}

	// This method implements the given algorithm
	private static int doTheAlgorithm(int i, int j) {
		int counter = 1;
		int max = 0;
		if (i == j) {
			int c = 0;
			c = i;
			while (c != 1) {
				if (c % 2 != 0) {
					c = 3 * c + 1;
					counter++;
				} else {
					c = c / 2;
					counter++;
				}
			}
			if (max < counter)
				max = counter;
			counter = 1;
		} else if (i > j) {
			for (int c = 0; i != j; i--) {
				c = i;
				while (c != 1) {
					if (c % 2 != 0) {
						c = 3 * c + 1;
						counter++;
					} else {
						c = c / 2;
						counter++;
					}
				}
				if (max < counter)
					max = counter;
				counter = 1;
			}
		} else {
			for (int c = 0; j != i; j--) {
				c = j;
				while (c != 1) {
					if (c % 2 != 0) {
						c = 3 * c + 1;
						counter++;
					} else {
						c = c / 2;
						counter++;
					}
				}
				if (max < counter)
					max = counter;
				counter = 1;
			}
		}
		return max;
	}
}