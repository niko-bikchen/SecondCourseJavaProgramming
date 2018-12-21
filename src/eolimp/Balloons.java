package eolimp;

import java.io.IOException;
import java.util.Scanner;

public class Balloons {

	public static void main(String[] args) throws IOException {
		int number = 0;
		int[] colors;
		String string;
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			number = Integer.parseInt(in.nextLine());
			string = in.nextLine();
			String[] strArray = string.split(" ");
			colors = new int[strArray.length];
			for(int i = 0; i < colors.length; i++) {
				colors[i] = Integer.parseInt(strArray[i]);
			}
			System.out.println(paintTheBalloons(number, colors));
		}
		in.close();
	}

	private static int paintTheBalloons(int number, int[] colors) {
		int count = 0, countMax = 0;
		boolean present = false;
		for (int i = 1, j = 0, k = 9; i <= k; i++) {
			while (j < colors.length) {
				if (colors[j] == i)
					count++;
				if (colors[j] == k)
					present = true;
				j++;
			}
			if (count > countMax)
				countMax = count;
			if (!present)
				k--;
			j = count = 0;
		}
		return colors.length - countMax;
	}

}
