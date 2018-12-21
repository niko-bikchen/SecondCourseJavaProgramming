/**
 * File name: Test.java
 * ====================
 * This class sorts objects of the Car class using:
 * • Insertion sort
 * • Selection sort
 * • Shell sort
 * • Merge sort
 * Methods which implement these algorithms are overloaded
 * so that they could use comparators.
 */
package compcomparexamp;

import java.io.*;
import java.util.*;

public class Test {

	public static void main(String[] args) throws IOException {
		ArrayList<Car> cars = new ArrayList<>();
		parseData(cars);
		System.out.println("========= By type =========");
		//insertionSort(cars);
		//selectionSort(cars);
		//shellSort(cars);
		//mergeSort(cars);
		//print(cars);
		System.out.println("========= By color =========");
		//insertionSort(cars, Car.BY_COLOR);
		//selectionSort(cars, Car.BY_COLOR);
		//shellSort(cars, Car.BY_COLOR);
		//mergeSort(cars, Car.BY_COLOR);
		//print(cars);
		System.out.println("========= By producer =========");
		//insertionSort(cars, Car.BY_PRODUCER);
		//selectionSort(cars, Car.BY_PRODUCER);
		//shellSort(cars, Car.BY_PRODUCER);
		//mergeSort(cars, Car.BY_PRODUCER);
		//print(cars);
		System.out.println("========= By horsepowers =========");
		//insertionSort(cars, Car.BY_HORSEPOWERS);
		//selectionSort(cars, Car.BY_HORSEPOWERS);
		//shellSort(cars, Car.BY_HORSEPOWERS);
		//mergeSort(cars, Car.BY_HORSEPOWERS);
		//print(cars);
	}

	/**
	 * This method reads information from file
	 * and makes Car objects from it.
	 * @param cars	an ArrayList which needs to be filled with Car objects.
	 * @throws IOException	if an IO error occurs.
	 */
	private static void parseData(ArrayList<Car> cars) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("data.txt"));
		String line = null;
		String[] arr = null;
		while (true) {
			line = bReader.readLine();
			if (line == null)
				break;
			arr = line.split("\\+");
			cars.add(new Car(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3]));
		}
		bReader.close();
	}
	
	/**
	 * This method prints out the contents of the given ArrayList.
	 * @param cars	an ArrayList which needs to be printed out.
	 */
	private static void print(ArrayList<Car> cars) {
		for (int i = 0; i < cars.size(); i++) {
			System.out.println(cars.get(i));
		}
	}

	private static void insertionSort(ArrayList<Car> cars) {
		Car temp = null;
		int j = 0;
		for (int i = 0; i < cars.size() - 1; i++) {
			if (cars.get(i).compareTo(cars.get(i + 1)) > 0) {
				temp = cars.get(i + 1);
				cars.set(i + 1, cars.get(i));
				for (j = i; j > 0 && temp.compareTo(cars.get(j - 1)) < 0; j--) {
					cars.set(j, cars.get(j - 1));
				}
				cars.set(j, temp);
			}
		}
	}

	private static void insertionSort(ArrayList<Car> cars, Comparator<Car> comparator) {
		Car temp = null;
		int j = 0;
		for (int i = 0; i < cars.size() - 1; i++) {
			if (comparator.compare(cars.get(i), cars.get(i + 1)) > 0) {
				temp = cars.get(i + 1);
				cars.set(i + 1, cars.get(i));
				for (j = i; j > 0 && comparator.compare(temp, cars.get(j - 1)) < 0; j--) {
					cars.set(j, cars.get(j - 1));
				}
				cars.set(j, temp);
			}
		}
	}

	private static void selectionSort(ArrayList<Car> cars) {
		Car temp = null;
		int min = 0;
		for (int i = 0; i < cars.size(); i++) {
			min = i;
			for (int j = i + 1; j < cars.size(); j++) {
				if (cars.get(j).compareTo(cars.get(min)) < 0) {
					min = j;
				}
			}
			temp = cars.get(i);
			cars.set(i, cars.get(min));
			cars.set(min, temp);
		}
	}

	private static void selectionSort(ArrayList<Car> cars, Comparator<Car> comparator) {
		Car temp = null;
		int min = 0;
		for (int i = 0; i < cars.size(); i++) {
			min = i;
			for (int j = i + 1; j < cars.size(); j++) {
				if (comparator.compare(cars.get(j), cars.get(min)) < 0) {
					min = j;
				}
			}
			temp = cars.get(i);
			cars.set(i, cars.get(min));
			cars.set(min, temp);
		}
	}

	private static void shellSort(ArrayList<Car> cars) {
		int h = 1;
		int size = cars.size();
		Car temp = null;
		while (h < size / 3)
			h = 3 * h + 1;
		while (h >= 1) {
			for (int i = h; i < size; i++) {
				for (int j = i; j >= h; j -= h) {
					if (cars.get(j).compareTo(cars.get(j - h)) < 0) {
						temp = cars.get(j);
						cars.set(j, cars.get(j - h));
						cars.set(j - h, temp);
					} else
						break;

				}
			}
			h = h / 3;
		}
	}
	
	private static void shellSort(ArrayList<Car> cars, Comparator<Car> comparator) {
		int h = 1;
		int size = cars.size();
		Car temp = null;
		while (h < size / 3)
			h = 3 * h + 1;
		while (h >= 1) {
			for (int i = h; i < size; i++) {
				for (int j = i; j >= h; j -= h) {
					if (comparator.compare(cars.get(j), cars.get(j - h)) < 0) {
						temp = cars.get(j);
						cars.set(j, cars.get(j - h));
						cars.set(j - h, temp);
					} else
						break;

				}
			}
			h = h / 3;
		}
	}
	
	private static void mergeSort(ArrayList<Car> cars) {
		int N = cars.size();
		ArrayList<Car> aux = new ArrayList<Car>(N);
		for (int sz = 1; sz < N; sz = sz + sz)
			for (int lo = 0; lo < N - sz; lo += sz + sz)
				merge(cars, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
	}
	
	private static void mergeSort(ArrayList<Car> cars, Comparator<Car> comparator) {
		int N = cars.size();
		ArrayList<Car> aux = new ArrayList<Car>(N);
		for (int sz = 1; sz < N; sz = sz + sz)
			for (int lo = 0; lo < N - sz; lo += sz + sz)
				merge(cars, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1), comparator);
	}
	
	public static void merge(ArrayList<Car> a, ArrayList<Car> aux, int lo, int mid, int hi) {
		for (int k = lo; k <= hi; k++)
			aux.add(k, a.get(k));
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a.set(k, aux.get(j++));
			else if (j > hi)
				a.set(k, aux.get(i++));
			else if (aux.get(j).compareTo(aux.get(i)) < 0)
				a.set(k, aux.get(j++));
			else
				a.set(k, aux.get(i++));
		}
	}
	
	public static void merge(ArrayList<Car> a, ArrayList<Car> aux, int lo, int mid, int hi, Comparator<Car> comparator) {
		for (int k = lo; k <= hi; k++)
			aux.add(k, a.get(k));
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a.set(k, aux.get(j++));
			else if (j > hi)
				a.set(k, aux.get(i++));
			else if (comparator.compare(aux.get(j), aux.get(i)) < 0)
				a.set(k, aux.get(j++));
			else
				a.set(k, aux.get(i++));
		}
	}
}
