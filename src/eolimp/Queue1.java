package eolimp;

import java.io.*;

public class Queue1 {
	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		FileWriter fWriter = new FileWriter("output.txt");
		String[] array_1 = bReader.readLine().split(" ");
		int q_size = Integer.parseInt(array_1[0]);
		int casboxes = Integer.parseInt(array_1[1]);
		String line = bReader.readLine();
		String[] array_2 = line.split(" ");
		int[] queue = new int[q_size];
		for (int i = 0; i < q_size; i++)
			queue[i] = Integer.parseInt(array_2[i]);
		fWriter.write(serveTheQueue(queue, q_size, casboxes) + "");
		fWriter.flush();
		bReader.close();
		fWriter.close();
	}

	private static int serveTheQueue(int[] queue, int q_size, int casboxes) {
		int time = 0;
		int i = 0;
		int k = 0;
		int n = 0;
		int[] list = new int[casboxes];

		for (int j = k; j < casboxes && i < q_size; j++)
			list[j] = queue[i++];

		k = 0;

		while (n < q_size) {
			if (k >= casboxes) {
				k--;
				if (i < q_size) {
					if (list[k] == 0) {
						for (int j = k; j < casboxes && i < q_size; j++)
							list[j] = queue[i++];
						k = 0;
						time++;
						n++;
					} else {
						k = 0;
						time++;
					}
				} else {
					if (list[k] == 0) {
						list[k] = -1;
						k = 0;
						time++;
						n++;
					} else if (list[k] < 0) {
						k = 0;
						time++;
					}
				}
			}
			if (i < q_size) {
				if (list[k] == 0) {
					for (int j = k; j < casboxes && i < q_size; j++)
						list[j] = queue[i++];
					n++;
				}
			} else {
				if (list[k] == 0) {
					list[k] = -1;
					n++;
				}
			}
			list[k] = list[k] - 1;
			k++;
		}
		/*
		 * if (k >= casboxes || k + 1 >= casboxes) { k = 0; time++; } while (n !=
		 * casboxes) { if (list[k] != 0) list[k] = list[k] - 1; if (list[k] == 0) n++;
		 * if (k == casboxes) { k = 0; time++; } k++; }
		 */
		/*
		 * while(i < q_size) { for(int j = 0; j < casboxes && i < q_size && k <
		 * casboxes; j++) list[k++] = queue[i++]; for(; k <= casboxes; k++) { if(k ==
		 * casboxes) { k = 0; time++; } list[k] = list[k] - 1; if(list[k] == 0) break; }
		 * }
		 */
		return time;
	}
}
