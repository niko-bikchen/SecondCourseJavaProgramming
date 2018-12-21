/**
 * File name: Point.java
 * =====================
 * This file implements point
 * and some methods for working with points
 */
package pointconnector;

import java.util.*;

import edu.princeton.cs.introcs.StdDraw;

public class Point implements Comparable<Point> {

	public final Comparator<Point> SLOPE_ORDER = new SOrder();

	private final double x;
	private final double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void draw() {
		StdDraw.point(x, y);
	}

	public void drawTo(Point that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	public double slopeTo(Point that) {
		if (this.x == that.x && this.y == that.y)
			return Double.NEGATIVE_INFINITY;
		if (this.y == that.y)
			return 0.0;
		if (this.x == that.x)
			return Double.POSITIVE_INFINITY;
		return ((that.y - this.y) / (that.x - this.x));
	}

	public int compareTo(Point that) {
		if (this.y < that.y || (this.y == that.y && this.x < that.y))
			return -1;
		if (this.y == that.y && this.x == that.x)
			return 0;
		return 1;
	}

	private class SOrder implements Comparator<Point> {
		public int compare(Point p, Point q) {
			if (slopeTo(p) < slopeTo(q))
				return 1;
			if (slopeTo(p) > slopeTo(q))
				return -1;
			return 0;
		}
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
