/**
 * File name: MinInStack.java
 * ==========================
 * This program carries out "n" operations with a stack
 * by using pop() and push() methods depending on the
 * value of the "x" received from the formula:
 * xi = (a*x_i-1^2 + b*x_i-1 + c) / 100 mod 10^6.
 * After each operation we take the lowest value from the
 * stack of x'es and sum it with the other ones from
 * previous operations 
 */
package eolimp;

import java.util.Scanner;
import java.util.Stack;

public class MinInStack {
	public static void main(String[] args) {
		String[] stringData = null;
		String string = null;
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			string = in.nextLine();
			stringData = string.split(" ");
			System.out.println(makeOperations(Long.parseLong(stringData[0]), 
					Long.parseLong(stringData[1]),
					Long.parseLong(stringData[2]), 
					Long.parseLong(stringData[3]), 
					Long.parseLong(stringData[4])));
		}
		in.close();
	}

	private static long makeOperations(long n, long a, long b, long c, long x) {
		Stack<Number> stack = new Stack<>();
		long bigTen = 1000000;
		long sumOfMin = 0;
		long min = 0;
		for (int i = 0; i < n; i++) {
			x = (a * x * x + b * x + c) / 100 % bigTen;
			if (x % 5 < 2) {
				if (!stack.isEmpty())
					stack.pop();
			} else {
				if(stack.isEmpty())
					min = x;
				else
					min = Math.min(x, stack.peek().curMin);
				MinInStack.Number number = new MinInStack().new Number(x, min);
				stack.push(number);
			}
			if (!stack.isEmpty())
				sumOfMin += stack.peek().curMin;
		}
		return sumOfMin;
	}

	private class Number {
		long value = 0;
		long curMin = 0;

		public Number(long value, long curMin) {
			this.value = value;
			this.curMin = curMin;
		}
	}
}
