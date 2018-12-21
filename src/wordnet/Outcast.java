/**
 * File name: Outcast.java
 * =======================
 * This class finds an outcast among the specified nouns
 */
package wordnet;

public class Outcast {

	private WordNet wordNet;

	public Outcast(WordNet wordNet) {
		this.wordNet = wordNet;
	}

	/**
	 * Finds an outcast among the specified nouns
	 * 
	 * @param nouns
	 *            nouns where we look for an outcast
	 * @return an outcast among the specified nouns
	 */
	public String outcast(String[] nouns) {
		int max = 0;
		int current_max = 0;
		String outcast = "";
		for (int i = 0; i < nouns.length; i++) {
			current_max = 0;
			for (int j = 0; j < nouns.length; j++) {
				if (i != j) {
					current_max += wordNet.distance(nouns[i], nouns[j]);
				}
			}
			if (current_max > max) {
				max = current_max;
				outcast = nouns[i];
			}
		}
		return outcast;
	}

	// Testing
	public static void main(String[] args) {
		WordNet wordNet = new WordNet("synsets.txt", "hypernyms.txt");
		String[] words = { "apple", "orange", "cat", "banana" };
		String[] words1 = { "car", "train", "plane", "fish" };
		Outcast outcast = new Outcast(wordNet);
		System.out.println(outcast.outcast(words));
		System.out.println(outcast.outcast(words1));
	}
}
