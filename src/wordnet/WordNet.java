/**
 * File name: WordNet.java
 * =======================
 * This class implements the wordnet
 */
package wordnet;

import java.io.*;
import java.util.*;

public class WordNet {

	private int size; // size of the wordnet
	private Map<Integer, Synset> synsets; // here we keep synsets
	private Map<String, List<Integer>> nouns_list; // here we keep nouns
	private Digraph wordn_graph; // the representation of the wordnet as a directed graph

	/**
	 * This class represents a synset together with its meaning
	 */
	private class Synset {
		String nouns;
		String meaning;

		Synset(String nouns, String meaning) {
			this.nouns = nouns;
			this.meaning = meaning;
		}
	}

	/**
	 * WordNet constructor. Receives two files: one constains synsets, the other
	 * describes connections between synsets.
	 * 
	 * @param synsets
	 *            a file with synsets
	 * @param hypernyms
	 *            a file which describes the connection between synsets
	 */
	public WordNet(String synsets, String hypernyms) {
		try (BufferedReader syn_reader = new BufferedReader(new FileReader(synsets));
				BufferedReader hyper_reader = new BufferedReader(new FileReader(hypernyms))) {

			// Here we initialize maps of synsets and nouns
			this.synsets = new HashMap<>();
			nouns_list = new HashMap<>();
			size = 0;
			while (true) {
				String line = syn_reader.readLine();
				if (line == null)
					break;
				size++;
				String[] data = line.split("\\,");
				int id = Integer.parseInt(data[0]);
				this.synsets.put(id, new Synset(data[1], data[2]));
				String[] nouns = data[1].split(" ");
				for (int i = 0; i < nouns.length; i++) {
					if (nouns_list.get(nouns[i]) == null) {
						List<Integer> ids = new LinkedList<>();
						nouns_list.put(nouns[i], ids);
						nouns_list.get(nouns[i]).add(id);
					} else {
						nouns_list.get(nouns[i]).add(id);
					}
				}
			}

			// Here we initialize synset graph
			wordn_graph = new Digraph(size);
			while (true) {
				String line = hyper_reader.readLine();
				if (line == null)
					break;
				String[] vertexes = line.split("\\,");
				int base = Integer.parseInt(vertexes[0]);
				for (int i = 1; i < vertexes.length; i++) {
					int v = Integer.parseInt(vertexes[i]);
					wordn_graph.addEdge(base, v);
				}
			}

			// Here we check whether our synset graph is DAG with a root
			if (!isRootedDag(wordn_graph))
				throw new IllegalArgumentException();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks whether a synset graph is DAG with a root
	 * 
	 * @param graph
	 *            a synset graph
	 * @return true if it is rooted DAG, otherwise, false
	 */
	private boolean isRootedDag(Digraph graph) {
		Kosaraju kosaraju = new Kosaraju(graph);
		if (kosaraju.hasCycle())
			return false;
		int check = 0;
		int count = 0;
		for (int i = 0; i < graph.getVertexes(); i++) {
			count = 0;
			for (int v : graph.adj(i))
				count++;
			if (count == 0)
				check++;
		}
		if (check > 1)
			return false;
		return true;
	}

	/*
	 * Returns list of all nouns
	 */
	public Iterable<String> nouns() {
		LinkedList<String> nouns = new LinkedList<>();
		for (int n : synsets.keySet())
			nouns.add(synsets.get(n).nouns);
		return nouns;
	}

	/**
	 * Checks whether our WordNet contains a given noun
	 * 
	 * @param word
	 *            a noun to look for
	 * @return true if our WordNet contains a given noun, otherwise, false
	 */
	public boolean isNoun(String word) {
		return nouns_list.containsKey(word);
	}

	/**
	 * Returns distance between the two given nouns
	 * 
	 * @param noun_a
	 *            first noun
	 * @param noun_b
	 *            second noun
	 * @return distance between given nouns
	 */
	public int distance(String noun_a, String noun_b) {
		if (!nouns_list.containsKey(noun_a) || !nouns_list.containsKey(noun_b))
			throw new IllegalArgumentException();
		SAP sap = new SAP(wordn_graph);
		return sap.length(nouns_list.get(noun_a), nouns_list.get(noun_b));
	}

	/**
	 * Returns the closest common ancestor of the two given nouns
	 * 
	 * @param noun_a
	 *            first noun
	 * @param noun_b
	 *            second noun
	 * @return the closest common ancestor of the two given nouns
	 */
	public String sap(String noun_a, String noun_b) {
		if (!nouns_list.containsKey(noun_a) || !nouns_list.containsKey(noun_b))
			throw new IllegalArgumentException();
		SAP sap = new SAP(wordn_graph);
		int ancestor_id = sap.ancestor(nouns_list.get(noun_a), nouns_list.get(noun_b));
		return synsets.get(ancestor_id).nouns;
	}

	/**
	 * Returns the number of synsets
	 * 
	 * @return the number of synsets
	 */
	public int synsetNumber() {
		return synsets.size();
	}

	// Testing
	public static void main(String[] args) throws IOException {
		WordNet wordNet = new WordNet("synsets.txt", "hypernyms.txt");

		System.out.println(wordNet.synsetNumber());

		System.out.println(wordNet.isNoun("AIDS"));

		System.out.println(wordNet.isNoun("lobster"));

		System.out.println(wordNet.isNoun("helmsman"));

		System.out.println(wordNet.isNoun("fruit"));

		System.out.println(wordNet.isNoun("banana"));

		System.out.println(wordNet.isNoun("hood"));

		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		String string = "";
		while (!string.equals("0")) {
			System.out.print("Word 1: ");
			String word1 = bReader.readLine();
			System.out.print("Word 2: ");
			String word2 = bReader.readLine();
			System.out.println(wordNet.sap(word1, word2));
			System.out.println(wordNet.distance(word1, word2));
			System.out.print("To exit enter 0. To continue enter 1: ");
			string = bReader.readLine();
			while (!string.equals("0") && !string.equals("1")) {
				System.out.print("Incorrect input. Try again: ");
				string = bReader.readLine();
			}
		}
	}
}
