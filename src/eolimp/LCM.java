package eolimp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class LCM {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"));
		String line = bReader.readLine();
		int count = Integer.parseInt(line);
		line = bReader.readLine();
		String[] arr = line.split(" ");
		int[] numbers = new int[count];
		for (int i = 0; i < count; i++) {
			numbers[i] = Integer.parseInt(arr[i]);
		}
		BigInteger result = new BigInteger("0");
		BigInteger a = null;
		BigInteger b = null;
		if (numbers.length > 2) {
			a = new BigInteger(Integer.toString(numbers[0]));
			b = new BigInteger(Integer.toString(numbers[1]));
			result = a.compareTo(b) > 0 ? findLCM(a, b) : findLCM(b, a);
			for (int i = 2; i < numbers.length; i++) {
				b = new BigInteger(Integer.toString(numbers[i]));
				result = result.compareTo(b) > 0 ? findLCM(result, b) : findLCM(b, result);
			}
			bWriter.write(result + "");
			bReader.close();
			bWriter.close();
		} else if (numbers.length == 2) {
			a = new BigInteger(Long.toString(numbers[0]));
			b = new BigInteger(Long.toString(numbers[1]));
			result = a.compareTo(b) > 0 ? findLCM(a, b) : findLCM(b, a);
			bWriter.write(result + "");
			bReader.close();
			bWriter.close();
		} else {
			bWriter.write(result + "");
			bReader.close();
			bWriter.close();
		}
	}

	private static BigInteger findGCD(BigInteger a, BigInteger b) {
		if (b.compareTo(new BigInteger("0")) == 0)
			return a;
		return findGCD(b, a.mod(b));
	}

	private static BigInteger findLCM(BigInteger a, BigInteger b) {
		return (a.multiply(b)).divide(findGCD(a, b));
	}
}
