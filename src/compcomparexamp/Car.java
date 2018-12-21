/**
 * File name: Car.java
 * ===================
 * This class describes car and contains
 * compareTo() method in order to compare
 * Car objects. This class also contains
 * several inner classes which implement Comparator
 * interface so that we could compare Car objects
 * in different ways.
 */
package compcomparexamp;

import java.util.*;

public class Car implements Comparable<Car> {
	private int horsepowers;
	private String producer;
	private String color;
	private String type;
	public static final byHorsepowers BY_HORSEPOWERS = new Car().new byHorsepowers();
	public static final byProducer BY_PRODUCER = new Car().new byProducer();
	public static final byColor BY_COLOR = new Car().new byColor();

	public Car(int h, String p, String c, String t) {
		horsepowers = h;
		producer = p;
		color = c;
		type = t;
	}

	public Car() {}

	@Override
	public String toString() {
		return "Car [horsepowers=" + horsepowers + ", producer=" + producer + ", color=" + color + ", type=" + type
				+ "]";
	}

	@Override
	public int compareTo(Car o) {
		return this.type.compareTo(o.type);
	}

	private class byHorsepowers implements Comparator<Car> {

		@Override
		public int compare(Car o1, Car o2) {
			if (o1.horsepowers > o2.horsepowers)
				return 1;
			if (o1.horsepowers < o2.horsepowers)
				return -1;
			return 0;
		}

	}
	
	private class byProducer implements Comparator<Car> {

		@Override
		public int compare(Car o1, Car o2) {
			return o1.producer.compareTo(o2.producer);
		}

	}
	
	private class byColor implements Comparator<Car> {

		@Override
		public int compare(Car o1, Car o2) {
			return o1.color.compareTo(o2.color);
		}

	}
}
