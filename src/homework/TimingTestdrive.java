package homework;

import edu.princeton.cs.introcs.Stopwatch;

public class TimingTestdrive {
	public static void main(String[] args) {
		for(int n = 1; n <= 22000; n+= 1000) {
			Stopwatch stopwatch = new Stopwatch();
			Timing.trial(n, 777280);
			System.out.println(n + " , " + stopwatch.elapsedTime());
		}
	}
}
