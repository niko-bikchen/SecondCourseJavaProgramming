/**
 * File name: BruteForce.java
 * ==========================
 * This file implements pattern recognition
 * program. This one uses brute force.
 */
package pointconnector;

import java.io.*;
import java.util.*;
import edu.princeton.cs.introcs.*;

public class BruteForce {
	public static void main(String[] args) throws IOException {
		Point[] points = null;
		BufferedReader bReader = new BufferedReader(new FileReader("rs1423.txt"));
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("bruteOutput.txt"));
		points = makePoints(bReader.readLine(), bReader);
		drawPoints(points);
		drawLines(points, bWriter);
		bReader.close();
		bWriter.close();
	}

	/**
	 * Draws points
	 * @param points	an array of points
	 */
	private static void drawPoints(Point[] points) {
		StdDraw.setPenRadius(0.0020);
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (Point p : points) {
			p.draw();
		}
		StdDraw.show();
	}

	/**
	 * Draws lines from 4 collinear points
	 * @param points	an array of points
	 * @param bWriter	writer to write in file the order of point's connection
	 * @throws IOException	if IO problem occurs
	 */
	private static void drawLines(Point[] points, BufferedWriter bWriter) throws IOException {
		for (int i = 0; i < points.length - 3; i++) {
			for (int j = i + 1; j < points.length - 2; j++) {
				for (int k = j + 1; k < points.length - 1; k++) {
					/*
					if (points[i].slopeTo(points[j]) != points[j].slopeTo(points[k]))
						continue;
						*/
					for (int l = k + 1; l < points.length; l++) {
						if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k])
								&& points[i].slopeTo(points[j]) == points[i].slopeTo(points[l])) {
							points[i].drawTo(points[j]);
							points[j].drawTo(points[k]);
							points[k].drawTo(points[l]);
							bWriter.write(
									points[i] + " ==> " + points[j] + " ==> " + points[k] + " ==> " + points[l]);
							bWriter.newLine();
							bWriter.flush();
						}
					}
				}
			}
		}
	}

	/**
	 * Makes an array of points from the data from file
	 * @param line	line to save lines from the given file
	 * @param bReader	reader to read the given file
	 * @return	an array of points
	 * @throws IOException	if IO problem occurs
	 */
	private static Point[] makePoints(String line, BufferedReader bReader) throws IOException {
		int number = Integer.parseInt(line);
		int count = 0;
		Point[] points = new Point[number];
		Scanner scanner = null;
		while (true) {
			line = bReader.readLine();
			if (line == null)
				break;
			scanner = new Scanner(line);
			points[count++] = (new Point(scanner.nextInt(), scanner.nextInt()));
		}
		return points;
	}
}
