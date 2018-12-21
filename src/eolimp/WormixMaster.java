/**
 * File name: WormixMaster.java
 * ============================
 * This program finds minimum time to obtain
 * a certain number of points
 */
package eolimp;

import java.io.*;

public class WormixMaster {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"));
		String line = bReader.readLine();
		String[] arr = line.split(" ");
		int tasks = Integer.parseInt(arr[0]);
		int pointsNeeded = Integer.parseInt(arr[1]);
		int optiTime = 10000000;
		int pointsTemp = 0;
		int timeTemp = 0;
		Mission[] missions = new Mission[tasks];
		for (int i = 0; i < tasks; i++) {
			line = bReader.readLine();
			arr = line.split(" ");
			missions[i] = new Mission(Integer.parseInt(arr[1]), Integer.parseInt(arr[0]));
		}
		byte[] combinations = new byte[tasks + 1];
		for (int i = 0; combinations[tasks] != 1;) {
			while (true) {
				if (combinations[i] == 0) {
					combinations[i]++;
					i = 0;
					break;
				} else {
					combinations[i]--;
					i++;
				}
			}
			for (int j = 0; j < missions.length; j++) {
				if (combinations[j] != 0) {
					timeTemp += missions[j].time;
					pointsTemp += missions[j].value;
				}
			}
			if (pointsTemp >= pointsNeeded) {
				if (timeTemp < optiTime)
					optiTime = timeTemp;
			}
			pointsTemp = 0;
			timeTemp = 0;
		}
		if (optiTime == 10000000) {
			bWriter.write(-1 + "");
			bWriter.flush();
			bWriter.close();
			bReader.close();
			return;
		}
		bWriter.write(optiTime + "");
		bWriter.flush();
		bWriter.close();
		bReader.close();
	}

	private static class Mission {
		private int time;
		private int value;

		public Mission(int time, int value) {
			this.time = time;
			this.value = value;
		}

		@Override
		public String toString() {
			return value + " " + time;
		}

	}
}