package eolimp;

import java.io.*;

public class UnionDay {

	private class City {
		int id;
		private double x;
		private double y;

		public City(int x, int y, int id) {
			this.x = x;
			this.y = y;
			this.id = id;
		}
	}

	public static void main(String[] args) throws IOException {
		UnionDay iDay = new UnionDay();
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"));
		int number = Integer.parseInt(bReader.readLine());
		City[] cities = new City[number];
		String[] data = null;
		UnionDay.City city = null;
		for (int i = 0; i < number; i++) {
			data = bReader.readLine().split(" ");
			city = iDay.new City(Integer.parseInt(data[0]), Integer.parseInt(data[1]), i);
			cities[i] = city;
		}
		String string = String.format("%.10f%n", findShortestPath(cities));
		bWriter.write(string + "");
		bWriter.flush();
		bReader.close();
		bWriter.close();
	}

	private static double countDistance(City a, City b) {
		return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
	}

	private static double findShortestPath(City[] cities) {
		City closest = cities[0];
		City current = cities[0];
		City prev = cities[0];
		double distance = 0;
		double shortes_distance = 1000000000;
		double shortest_path = 0;
		int count = 0;
		while (count < cities.length) {
			for (int j = 0; j < cities.length; j++) {
				if (j != closest.id && j != prev.id) {
					distance = countDistance(closest, cities[j]);
					if (Double.compare(distance, shortes_distance) < 0) {
						shortes_distance = distance;
						current = cities[j];
					}
				}
			}
			count++;
			shortest_path += shortes_distance;
			prev = closest;
			closest = current;
			shortes_distance = 1000000000;
			distance = 0;
		}
		return shortest_path;
	}
}
