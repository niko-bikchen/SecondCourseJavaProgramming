package eolimp;

import java.util.Scanner;

public class YoungGardener {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			System.out.println(waterTheTree(in.nextInt()));
		}
	}

	private static int waterTheTree(int layers) {
		int liters = 1;
		for(int i = 1; i <= layers; i++) {
			liters+=2*i;
		}
		return liters;
	}
}
