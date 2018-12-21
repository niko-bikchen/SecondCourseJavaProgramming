/**
 * File name: ConvexHull.java
 * =========================
 * This class builds a convex hull which
 * will contain a given set of points
 */
package convexshell;

import java.io.*;
import java.util.*;
import edu.princeton.cs.introcs.StdDraw;

public class ConvexHull {

	public static void main(String[] args) throws IOException {
		Point2D[] points = null;
		BufferedReader bReader = new BufferedReader(new FileReader("input100.txt"));
		points = makePoints(bReader.readLine(), bReader);
		drawPoints(points);
		makeHull(points);
	}

	/**
	 * Finds and draws a convex hull which will contain a given set of points
	 * @param points an array (set) of points
	 */
	private static void makeHull(Point2D[] points) {
		Point2D lowest = findLowest(points);
		Arrays.sort(points, lowest.POLAR_ORDER);
		Point2D[] hull = new Point2D[points.length];
		int count = 0;
		for (int i = 0; i < points.length; i++) {
			while (count >= 2 && Point2D.ccw(hull[count - 2], hull[count - 1], points[i]) <= 0) {
				hull[count - 1] = null;
				count--;
			}
			hull[count++] = points[i];
		}
		for (int i = 0; i < count - 1; i++) {
			hull[i].drawTo(hull[i + 1]);
		}
		hull[0].drawTo(hull[count - 1]);
	}

	/**
	 * Finds lowest point in the given array of points
	 * @param points an array of points
	 * @return the lowest point in the array
	 */
	private static Point2D findLowest(Point2D[] points) {
		Point2D low = points[0];
		for (int i = 1; i < points.length; i++) {
			if (points[i].getY() < low.getY())
				low = points[i];
		}
		return low;
	}

	/**
	 * Reads data from file and makes points from it
	 * @param line first line from the file which contains total number of points
	 * @param bReader buffered reader to read from file
	 * @return an array of points
	 * @throws IOException if an IO exception occurs
	 */
	private static Point2D[] makePoints(String line, BufferedReader bReader) throws IOException {
		int number = Integer.parseInt(line);
		int count = 0;
		Point2D[] points = new Point2D[number];
		Scanner scanner = null;
		while (true) {
			line = bReader.readLine();
			if (line == null)
				break;
			scanner = new Scanner(line);
			points[count++] = (new Point2D(scanner.nextInt(), scanner.nextInt()));
		}
		return points;
	}

	/**
	 * Draws points
	 * @param points an array of points to be drawn
	 */
	private static void drawPoints(Point2D[] points) {
		StdDraw.setPenRadius(0.0040);
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (Point2D p : points) {
			p.draw();
		}
		StdDraw.show();
	}
}
