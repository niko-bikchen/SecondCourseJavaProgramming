/**
 * File name: Point2D.java
 * =====================
 * This file implements point
 * and some methods for working with points
 */
package convexshell;

import java.util.*;
import edu.princeton.cs.introcs.StdDraw;

public class Point2D implements Comparable<Point2D>{

	public final Comparator<Point2D> POLAR_ORDER = new POrder();

	private final double x;
	private final double y;

	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void draw() {
		StdDraw.point(x, y);
	}

	public void drawTo(Point2D that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	public static double ccw(Point2D a, Point2D b, Point2D c) {
		return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
	}

	private class POrder implements Comparator<Point2D> {
		public int compare(Point2D q1, Point2D q2) {
			if (q1.y > y && q2.y < y)
				return -1;
			if (q1.y < y && q2.y > y)
				return 1;
			double ccw = ccw(Point2D.this, q1, q2);
			if (ccw > 0)
				return -1;
			if (ccw < 0)
				return 1;
			return q1.compareTo(q2);
		}
	}

	@Override
	public int compareTo(Point2D o) {
		if(this.x == o.x)
			return (int)(this.y - o.y);
		else
			return (int)(this.x - o.x);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Point2D))
			return false;
		if(this.y == ((Point2D) obj).getY() && this.x == ((Point2D) obj).getX())
			return true;
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		int result = Double.hashCode(x);
		result = 31 * result + Double.hashCode(y);
		return result;
	}
	
	public double getY() {
		return y;
	}
	
	public double getX() {
		return x;
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
