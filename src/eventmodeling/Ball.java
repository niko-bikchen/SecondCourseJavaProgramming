/**
 * File name: Ball.java
 * ====================
 * This class describes a ball
 * whose collisions with walls
 * we emulate.
 */
package eventmodeling;

import edu.princeton.cs.introcs.*;

public class Ball {
	private double rx, ry;
	private double vx, vy;
	private final double radius = 0.01;

	public Ball() {
		rx = StdRandom.uniform(0.0 + radius, 1.0 - radius);
		ry = StdRandom.uniform(0.0 + radius, 1.0 - radius);
		vx = StdRandom.uniform(-0.05, 0.05);
		vy = StdRandom.uniform(-0.05, 0.05);
	}

	public void move(double dt) {
		if ((rx + vx * dt < radius) || (rx + vx * dt > 1.0 - radius))
			vx = -vx;
		if ((ry + vy * dt < radius) || (ry + vy * dt > 1.0 - radius))
			vy = -vy;
		rx = rx + vx * dt;
		ry = ry + vy * dt;
	}

	public void draw() {
		StdDraw.filledCircle(rx, ry, radius);
	}
}
