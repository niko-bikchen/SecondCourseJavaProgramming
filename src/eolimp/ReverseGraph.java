package eolimp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class ReverseGraph {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"));
		String line = null;
		String data[] = null;

		int size = Integer.parseInt(bReader.readLine());
		ArrayList<Integer>[] adj = new ArrayList[size];

		for (int i = 0; i < size; i++) {
			line = bReader.readLine();
			if (!line.replaceAll(" ", "").equals("")) {
				data = line.split(" ");
				for (int j = 0; j < data.length; j++) {
					int n = Integer.parseInt(data[j]);
					if (adj[n - 1] == null) {
						adj[n - 1] = new ArrayList();
						adj[n - 1].add(i + 1);
					} else {
						adj[n - 1].add(i + 1);
					}

				}

			}
		}

		bWriter.write("" + size);
		bWriter.newLine();
		for (int i = 0; i < size; i++) {
			if (adj[i] != null) {
				for (int j = 0; j < adj[i].size(); j++) {
					if (j == adj[i].size() - 1)
						bWriter.write(adj[i].get(j) + "");
					else
						bWriter.write(adj[i].get(j) + " ");
				}
			}
			if (i != size - 1)
				bWriter.newLine();

		}
		bWriter.flush();
	}
}