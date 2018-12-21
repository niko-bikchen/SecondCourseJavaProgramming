package eolimp;

import java.util.Scanner;

public class Bunnies {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			System.out.println(releaseTheRabbit(in.nextInt(), in.nextInt()));
		}
	}

	private static int releaseTheRabbit(int month, int bound) {
		int rabbits = 1;
		for(int i = 0; i < month; i++) {
			if(rabbits > bound) {
				rabbits-=bound;
			}
			rabbits+=rabbits;
		}
		return rabbits;
	}
}
