/**
 * File name: SearchDictionary.java
 * ================================
 * This class uses array of linked lists to organize
 * the words from the input file. Array of LinkedLists is used because via 
 * the hash() method realization we can make so each letter of the alphabet 
 * had its own linked list of the words which begin on this letter in the array
 * of linked lists (which is called "hub" here). If we have used only an array 
 * combined with hash function we would need to look through a great part of the 
 * array of words since there are no guarantee that words for example which begin 
 * with the "a" letter are situated not far from one another. In this realization 
 * you need just to look through a linked list which corresponds to the letter
 * the searched word begins with. The insertion and deletion in the LinkedList works 
 * in constant complexity and we also don't have to resize the array of LinkedLists 
 * since there are a fixated number of letters in the alphabet and LinkedLists don't need resizing.
 */
package dictionary;

import java.util.*;

public class SearchDictionary {

	private int m; // number of letters of the alphabet 
	private int wordsNumber = 0; // number of words in the dictionary
	private Word[] hub; // an array of linked lists

	private class Word {
		Word next;
		String value;

		private Word(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	}

	/**
	 * Constructor. Receives the number of letters in the alphabet which is needed
	 * to organize separate chaining.
	 * 
	 * @param lettersInAlphabet
	 *            number of letters in the alphabet
	 */
	public SearchDictionary(int lettersInAlphabet) {
		m = lettersInAlphabet;
		hub = new Word[m];
	}

	/**
	 * Adds the given word to the dictionary. Doesn't add an empty word ("")
	 * 
	 * @param word
	 *            a word to add
	 */
	public void addWord(String word) {
		word = word.toLowerCase();
		word = word.replaceAll("[^\\p{L}\\p{Nd}]+", "");
		word = word.replaceAll("\\d", "");
		if (word.equals(""))
			return;
		Word x = hub[hash(word)];
		while (true) {
			if (x == null) {
				hub[hash(word)] = new Word(word);
				break;
			}
			if (x.next == null) {
				x.next = new Word(word);
				break;
			}
			x = x.next;
		}
		wordsNumber++;
	}

	/**
	 * Checks whether the given word is already in the dictionary
	 * 
	 * @param word
	 *            a word to check
	 * @return true if the word is present, otherwise false
	 */
	public boolean hasWord(String word) {
		word = word.toLowerCase();
		word = word.replaceAll("[^\\p{L}\\p{Nd}]+", "");
		word = word.replaceAll("\\d", "");
		if (word.equals(""))
			return false;
		boolean present = false;
		Word x = hub[hash(word)];
		while (true) {
			if (x == null)
				break;
			if (x.value.equals(word)) {
				present = true;
				break;
			}
			if (x.next == null)
				break;
			x = x.next;
		}
		return present;
	}

	/**
	 * Deletes the given word if it is present in the dictionary
	 * 
	 * @param word
	 *            a word to delete
	 * @return a deleted word or null if the given word was not in the dictionary of
	 *         if it was an empty word ("")
	 */
	public String delWord(String word) {
		word = word.toLowerCase();
		word = word.replaceAll("[^\\p{L}\\p{Nd}]+", "");
		word = word.replaceAll("\\d", "");
		if (word.equals(""))
			return null;
		if (hasWord(word)) {
			Word x = hub[hash(word)];
			while (true) {
				if (x == null)
					break;
				if (x.next != null && x.next.value.equals(word) && x.next.next != null) {
					Word help = x.next.next;
					x.next.next = null;
					x.next = null;
					x.next = help;
					wordsNumber--;
					return word;
				} else if (x.next != null && x.next.value.equals(word) && x.next.next == null) {
					x.next = null;
					wordsNumber--;
					return word;
				} else if (x.value.equals(word)) {
					Word help = x.next;
					x.next = null;
					hub[hash(word)] = help;
					wordsNumber--;
					return word;
				}
				x = x.next;
			}
		}
		return null;
	}

	/**
	 * This method receives a word to look for (it may contain joker) and returns
	 * the set of the ArrayList of the words which correspond to that word
	 * 
	 * @param query
	 *            the word to look for (it may contain joker)
	 * @return the ArrayList of the words which correspond to that word
	 */
	public ArrayList<String> search(String query) {
		ArrayList<String> resultSet = new ArrayList<>();
		if (query.endsWith("*")) {
			query = query.substring(0, query.length() - 1);
			if (!query.equals("")) {
				Word x = hub[hash(query)];
				while (true) {
					if (x != null) {
						String check = x.value.length() < query.length() ? "" : x.value.substring(0, query.length());
						if (check.equals(query))
							resultSet.add(x.value);
					} else {
						break;
					}
					x = x.next;
				}
			}
		} else {
			Word x = hub[hash(query)];
			while (true) {
				if (x != null) {
					if (x.value.equals(query))
						resultSet.add(x.value);
				} else {
					break;
				}
				x = x.next;
			}
		}
		return resultSet;
	}

	/**
	 * This method returns iterator. It iterates through the LinkedList of the words
	 * which begin on the same letter as the query.
	 * 
	 * @param query
	 *            word or letter needed to place the iterator on the LinkedList of
	 *            the words which begin on the same letter as the query
	 * @return iterator which can iterate through the LinkedList of the words which
	 *         begin on the same letter as the query
	 */
	public Iterator<String> query(String query) {
		query = query.replaceAll("[^\\p{L}\\p{Nd}]+", "");
		query = query.replaceAll("\\d", "");
		if (query.equals(""))
			return null;
		return new dictIterator(query);
	}

	public int countWords() {
		return wordsNumber;
	}

	private int hash(String key) {
		return key.charAt(0) % m;
	}

	private class dictIterator implements Iterator<String> {

		private Word current;

		private dictIterator(String query) {
			current = hub[hash(query)];
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public String next() {
			String word = current.value;
			current = current.next;
			return word;
		}
	}

	/**
	 * This method prints the contents of the dictionary
	 */
	public void print() {
		String line = "";
		for (int i = 0; i < hub.length; i++) {
			line += i + ": ";
			for (Word x = hub[i]; x != null; x = x.next) {
				line += x + ", ";
			}
			System.out.println(line);
			line = "";
		}
		System.out.println(wordsNumber);
	}
}