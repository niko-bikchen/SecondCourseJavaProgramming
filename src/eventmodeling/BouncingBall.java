/**
 * File name: BouncingBall.java
 * ============================
 * This program emulates collisions 
 * between balls and walls.
 */
package eventmodeling;

import edu.princeton.cs.introcs.StdDraw;
import java.io.*;

public class BouncingBall {
	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(args[0]);
		Ball[] balls = new Ball[n];
		for (int i = 0; i < n; i++)
			balls[i] = new Ball();
		while (true) {
			StdDraw.clear();
			for (int i = 0; i < n; i++) {
				balls[i].move(0.5);
				balls[i].draw();
			}
			StdDraw.show(50);
			StdDraw.clear();
		}

	}

}
