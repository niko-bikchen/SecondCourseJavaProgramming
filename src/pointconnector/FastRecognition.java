/**
 * File name: FastRecognition.java
 * ===============================
 * This file implements pattern recognition 
 * program. This one uses element sorting
 * to speed up performance.
 */
package pointconnector;

import java.io.*;
import java.util.*;
import edu.princeton.cs.introcs.StdDraw;

public class FastRecognition {
	public static void main(String[] args) throws IOException {
		Point[] points = null;
		BufferedReader bReader = new BufferedReader(new FileReader("rs1423.txt"));
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("fastOutput.txt"));
		points = makePoints(bReader.readLine(), bReader);
		drawPoints(points);
		drawLines(points, bWriter);
		bReader.close();
		bWriter.close();
	}

	/**
	 * Draws lines from 4 collinear points
	 * @param points	an array of points
	 * @param bWriter	writer to write in file the order of point's connection
	 * @throws IOException	if IO problem occurs
	 */
	private static void drawLines(Point[] points, BufferedWriter bWriter) throws IOException {
		double previous = 0.0;
		Stack<Point> collinear = null;
		for (Point helper : points) {
			Point[] copy = Arrays.copyOf(points, points.length);
			Arrays.sort(copy, helper.SLOPE_ORDER);
			previous = 0.0;
			collinear = new Stack<Point>();
			for (Point helper2 : copy) {
				if (helper2.slopeTo(helper) != previous) {
					if (collinear.size() >= 3) {
						collinear.add(helper);
						Collections.sort(collinear);
						StringBuffer buffer = new StringBuffer();
						for (int i = 0; i < collinear.size(); i++) {
							buffer.append(collinear.get(i));
							if (i < collinear.size() - 1)
								buffer.append(" ==> ");
						}
						bWriter.write(buffer.toString());
						bWriter.newLine();
						bWriter.flush();
						for (int i = 0; i < collinear.size() - 1; i++) {
							collinear.get(i).drawTo(collinear.get(i + 1));
						}
					}
					collinear.clear();
				}
				collinear.add(helper2);
				previous = helper2.slopeTo(helper);
			}
		}
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
