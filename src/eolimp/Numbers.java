package eolimp;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;

class Number {
	Number ancestor;
	int number;

	public Number(int number, Number ancestor) {
		this.number = number;
		this.ancestor = ancestor;
	}

	@Override
	public String toString() {
		return number + "";
	}

	@Override
	public boolean equals(Object obj) {
		Number n = (Number) obj;
		return this.number == n.number;
	}
}

public class Numbers {

	private static HashMap<Integer, Boolean> marked = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"));
		String[] data = bReader.readLine().split(" ");
		Number a = new Number(Integer.parseInt(data[0]), null);
		Number b = new Number(Integer.parseInt(data[1]), null);
		LinkedList<Number> divisors = new LinkedList<>();
		if (isNatural(a.number) || a.number > b.number) {
			bWriter.write("Impossible");
			bWriter.flush();
			bWriter.close();
			bReader.close();
			return;
		}
		Number result = findNumber(a, b, divisors);
		String path = "";
		if (result != null) {
			while (result.ancestor != null) {
				path += result.number + " ";
				result = result.ancestor;
			}
			path += a;
			String[] arr = path.split(" ");
			for(int i = arr.length - 1; i >= 0; i--) {
				bWriter.write(arr[i]);
				if(i > 0)
					bWriter.newLine();
			}
		} else {
			bWriter.write("Impossible");
		}
		bWriter.flush();
		bReader.close();
		bWriter.close();
	}

	private static Number findNumber(Number a, Number b, LinkedList<Number> divisors) {
		Number temp = null;
		makeDivisors(a, divisors);
		while (!divisors.isEmpty()) {
			temp = divisors.pop();
			if (!marked.containsKey(temp.number)) {
				if (temp.number > b.number)
					continue;
				if (temp.number == b.number)
					return temp;
				if (temp.number != b.number) {
					marked.put(temp.number, true);
					a = temp;
					makeDivisors(a, divisors);
				}
			}

		}
		return null;
	}

	private static void makeDivisors(Number a, LinkedList<Number> divisors) {
		int divisor = 2;
		while (divisor <= a.number / 2) {
			if (a.number % divisor == 0) {
				Number number = new Number(divisor + a.number, a);
				if (!divisors.contains(number))
					divisors.push(number);
			}
			divisor++;
		}
	}

	private static boolean isNatural(int a) {
		if (a > 1) {
			int divisor = 2;
			while (divisor <= a / 2) {
				if (a % divisor == 0)
					return false;
				divisor++;
			}
		}
		return true;
	}
}
