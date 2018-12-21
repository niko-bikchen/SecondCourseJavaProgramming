/**
 * File name: Test.java
 * ====================
 * This class reads words from the file
 * and add adds them to the SearchDictionary class.
 * Then it creates a window where the user can input 
 * the word he looks for, with or without joker, and receive
 * the words from file which correspond to the given input. 
 */
package dictionary;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Test {

	public static void main(String[] args) {
		SearchDictionary sDictionary = new SearchDictionary(26);
		initDictionary(sDictionary, new File("input.txt"));
		DictWindow dictWindow = new DictWindow();
		dictWindow.initialize();
		dictWindow.getWindow().setTitle("Dictionary search. Words in the dictionary: " + sDictionary.countWords());
		initButtons(dictWindow, sDictionary);
		//sDictionary.print();
		Iterator<String> iterator = sDictionary.query("a");
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	private static void initButtons(DictWindow dictWindow, SearchDictionary sDictionary) {
		JTextField searchField = dictWindow.getSearchField();
		JTextArea resultField = dictWindow.getResultField();

		dictWindow.getAddWordBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String toAdd = JOptionPane.showInputDialog("Enter word to add: ");
				toAdd = toAdd.replaceAll(" ", "");
				if (!toAdd.equals("")) {
					if (!sDictionary.hasWord(toAdd)) {
						sDictionary.addWord(toAdd);
						JOptionPane.showMessageDialog(null,
								"The word " + "\"" + toAdd + "\"" + " was added to the dictionary");
						dictWindow.getWindow()
								.setTitle("Dictionary search. Words in the dictionary: " + sDictionary.countWords());
					} else {
						JOptionPane.showMessageDialog(null,
								"The word " + "\"" + toAdd + "\"" + " already exists in the " + "dictionary");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Cannot add " + "\"" + toAdd + "\"" + " to the dictionary");
				}
			}
		});

		dictWindow.getDeleteBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String toDel = JOptionPane.showInputDialog(null, "Enter the word to delete: ");
				toDel = toDel.replaceAll(" ", "");
				if (!toDel.equals("")) {
					if (sDictionary.delWord(toDel) != null) {
						JOptionPane.showMessageDialog(null,
								"The word " + "\"" + toDel + "\"" + " was deleted from the dictionary");
						dictWindow.getWindow()
								.setTitle("Dictionary search. Words in the dictionary: " + sDictionary.countWords());
					} else {
						JOptionPane.showMessageDialog(null,
								"The word " + "\"" + toDel + "\"" + " was not found in the dictionary");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"The word " + "\"" + toDel + "\"" + " was not found in the dictionary");
				}
			}
		});

		dictWindow.getSearchBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String word = searchField.getText();
				word = word.replaceAll(" ", "");
				if (!word.equals("")) {
					String query = searchField.getText();
					ArrayList<String> resultSet = sDictionary.search(query);
					if (resultSet.size() != 0) {
						resultField.setText("");
						for (int i = 0; i < resultSet.size(); i++) {
							resultField.append(resultSet.get(i) + "\n");
						}
					} else {
						resultField.setText("Cannot find the word you've entered");
					}
				} else {
					resultField.setText("Cannot find the word you've entered");
				}
			}
		});

		searchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String word = searchField.getText();
				word = word.replaceAll(" ", "");
				if (!word.equals("")) {
					String query = searchField.getText();
					ArrayList<String> resultSet = sDictionary.search(query);
					if (resultSet.size() != 0) {
						resultField.setText("");
						for (int i = 0; i < resultSet.size(); i++) {
							resultField.append(resultSet.get(i) + "\n");
						}
					} else {
						resultField.setText("Cannot find the word you've entered");
					}
				} else {
					resultField.setText("Cannot find the word you've entered");
				}
			}
		});
	}

	private static void initDictionary(SearchDictionary sDictionary, File file) {
		try (BufferedReader bReader = new BufferedReader(new FileReader(file))) {
			String line = null;
			while (true) {
				line = bReader.readLine();
				if (line == null)
					break;
				if (line.equals(""))
					continue;
				String[] words = line.split(" ");
				for (int i = 0; i < words.length; i++) {
					if (!sDictionary.hasWord(words[i]))
						sDictionary.addWord(words[i]);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't find the specified file");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("An error occured while reading from file");
			e.printStackTrace();
		}
	}
}
