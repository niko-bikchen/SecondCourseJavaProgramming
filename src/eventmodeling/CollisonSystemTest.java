/**
 * File name: CollisionSystemTest.java
 * ==================================
 * This class tests CollisionSystem.java
 * which emulates collisions between balls
 * and between balls and walls.
 */
package eventmodeling;

import edu.princeton.cs.introcs.StdDraw;

public class CollisonSystemTest {
	public static void main(String[] args) {
		StdDraw.setCanvasSize(600, 600);
		int n = Integer.parseInt(args[0]);
		Particle[] particles = new Particle[n];
		for (int i = 0; i < n; i++)
			particles[i] = new Particle();
		CollisionSystem system = new CollisionSystem(particles, n);
		system.simulate();
	}
}
