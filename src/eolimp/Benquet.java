package eolimp;

import java.io.*;
import java.util.*;

public class Benquet {

	public static void main(String[] args){
		try (BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
				BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"))) {
			String line = null;
			while (true) {
				line = bReader.readLine();
				if (line == null)
					break;
				String[] array = line.split(" ");
				ArrayList<Long> guestList = new ArrayList<>();
				for (int i = 0; i < array.length; i++) {
					guestList.add((long) Integer.parseInt(array[i]));
				}
				Collections.sort(guestList, Collections.reverseOrder());
				bWriter.write(communicate(guestList) ? "ok" : "fail");
				bWriter.flush();
				bWriter.newLine();
				bWriter.newLine();
			}
		} catch (NumberFormatException e) {
			System.err.println("Invalid input");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean communicate(ArrayList<Long> guestList) {
		while (!guestList.isEmpty()) {
			Collections.sort(guestList, Collections.reverseOrder());
			long top = guestList.get(0);
			guestList.remove(0);
			Stack<Long> stack = new Stack<>();
			while (top > 0) {
				if (guestList.isEmpty())
					return false;
				if (guestList.get(0) != 1)
					stack.push(guestList.get(0) - 1);
				guestList.remove(0);
				top--;
			}
			while (!stack.isEmpty()) {
				guestList.add(stack.peek());
				stack.pop();
			}
		}
		return true;
	}
}
