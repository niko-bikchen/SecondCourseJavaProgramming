/**
 * File name: CollisionSystem.java
 * ===============================
 * This program emulates collisions 
 * between balls and between balls and
 * walls.
 * Additionally, after balls collide, they
 * change their radius and color.
 */
package eventmodeling;

import edu.princeton.cs.introcs.StdDraw;

public class CollisionSystem {
	private MinPQ<Event> pq; // the priority queue
	private double t = 0.0; // simulation clock time
	private Particle[] particles; // the array of particles
	private final double FREQUENCY = 0.2; // frequency which affects how often canvas updates
	private int n;

	public CollisionSystem(Particle[] particles, int n) {
		this.particles = particles;
		this.n = n;
	}

	private void predict(Particle a) {
		if (a == null)
			return;
		for (int i = 0; i < n; i++) {
			double dt = a.timeToHit(particles[i]);
			pq.insert(new Event(t + dt, a, particles[i]));
		}
		pq.insert(new Event(t + a.timeToHitVerticalWall(), a, null));
		pq.insert(new Event(t + a.timeToHitHorizontalWall(), null, a));
	}

	private void redraw() {
		StdDraw.clear();
		for (int i = 0; i < particles.length; i++) {
			particles[i].draw();
		}
		StdDraw.show(30);
		pq.insert(new Event(t + 1.0 / FREQUENCY, null, null));
	}

	public void simulate() {
		pq = new MinPQ<Event>();
		for (int i = 0; i < n; i++)
			predict(particles[i]);
		pq.insert(new Event(0, null, null));
		while (!pq.isEmpty()) {
			Event event = pq.delMin();
			if (!event.isValid())
				continue;
			Particle a = event.a;
			Particle b = event.b;
			for (int i = 0; i < n; i++)
				particles[i].move(event.time - t);
			t = event.time;
			if (a != null && b != null)
				a.bounceOff(b);
			else if (a != null && b == null)
				a.bounceOffVerticalWall();
			else if (a == null && b != null)
				b.bounceOffHorizontalWall();
			else if (a == null && b == null)
				redraw();
			predict(a);
			predict(b);
		}

	}

	private class Event implements Comparable<Event> {
		private double time; // time of event
		private Particle a, b; // particles involved in event
		private int countA, countB; // collision counts for a and b

		public Event(double t, Particle a, Particle b) {
			time = t;
			this.a = a;
			this.b = b;
			if (a != null)
				countA = a.getCount();
			else
				countA = -1;
			if (b != null)
				countB = b.getCount();
			else
				countB = -1;
		}

		public int compareTo(Event that) {
			return Double.compare(this.time, that.time);
		}

		public boolean isValid() {
			if (a != null && a.getCount() != countA)
				return false;
			if (b != null && b.getCount() != countB)
				return false;
			return true;
		}

	}
}
