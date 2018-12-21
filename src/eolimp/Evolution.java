/**
 * File name: Evolution.java
 * =========================
 * Finds the lowest ancestor of the two species in the binary tree.
 */
package eolimp;

import java.io.*;
import java.math.*;
import java.util.ArrayList;

public class Evolution {

	public static void main(String[] args){

		try (BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
				BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"))) {
			;
			int n = Integer.parseInt(bReader.readLine());
			BigInteger sp1 = new BigInteger(bReader.readLine());
			BigInteger sp2 = new BigInteger(bReader.readLine());
			bWriter.write(findAncestor(sp1, sp2, n) + "");
			bWriter.flush();
		} catch (NumberFormatException e) {
			System.err.println("Invalid input");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static BigInteger findAncestor(BigInteger sp1, BigInteger sp2, int n) {
		ArrayList<BigInteger> sp1_parent = new ArrayList<>();
		ArrayList<BigInteger> sp2_parent = new ArrayList<>();
		if (sp1.compareTo(sp2) == 0)
			return sp1;
		sp1_parent.add(sp1);
		for (int i = n; i >= 0; i--) {
			sp1 = sp1.divide(new BigInteger("2"));
			sp1_parent.add(sp1);
		}
		sp2_parent.add(sp2);
		for (int i = n; i >= 0; i--) {
			sp2 = sp2.divide(new BigInteger("2"));
			sp2_parent.add(sp2);
		}
		sp1_parent.retainAll(sp2_parent);
		BigInteger max = sp1_parent.get(0);
		for (int i = 1; i < sp1_parent.size(); i++) {
			if (max.compareTo(sp1_parent.get(i)) < 0) {
				max = sp1_parent.get(i);
			}
		}
		return max;
	}
}
