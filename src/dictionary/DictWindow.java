/**
 * File name: DictWindow.java
 * ==========================
 * This class creates window with the text field
 * for entering the word to look for and text area
 * for the results of the search.
 */
package dictionary;

import java.awt.*;
import javax.swing.*;

public class DictWindow {

	private JFrame window;
	private JTextField searchField;
	private JTextArea resultField;
	private JButton addWordBtn;
	private JButton searchBtn;
	private JButton deleteBtn;

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public void initialize() {
		window = new JFrame();
		window.setTitle("Dictionary Search");
		window.setBounds(100, 100, 650, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel input_output = new JPanel();
		window.getContentPane().add(input_output);
		input_output.setLayout(new BoxLayout(input_output, BoxLayout.Y_AXIS));

		Component verticalGlue = Box.createVerticalGlue();
		input_output.add(verticalGlue);

		JPanel searchPnl = new JPanel();
		input_output.add(searchPnl);

		JLabel lblSearch = new JLabel("Search:");
		searchPnl.add(lblSearch);

		searchField = new JTextField();

		searchField.setToolTipText("Enter the word you look for");
		searchPnl.add(searchField);
		searchField.setColumns(40);

		searchBtn = new JButton("Go");
		searchPnl.add(searchBtn);

		Component verticalGlue_2 = Box.createVerticalGlue();
		input_output.add(verticalGlue_2);

		JPanel searchResultPnl = new JPanel();
		input_output.add(searchResultPnl);

		resultField = new JTextArea();
		resultField.setEditable(false);
		resultField.setRows(10);
		resultField.setColumns(50);
		JScrollPane scrollPane = new JScrollPane(resultField);
		searchResultPnl.add(scrollPane);

		Component verticalGlue_1 = Box.createVerticalGlue();
		input_output.add(verticalGlue_1);

		JPanel buttons = new JPanel();
		window.getContentPane().add(buttons, BorderLayout.SOUTH);
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));

		Component horizontalGlue_2 = Box.createHorizontalGlue();
		buttons.add(horizontalGlue_2);

		addWordBtn = new JButton("Add word");
		buttons.add(addWordBtn);

		Component horizontalGlue = Box.createHorizontalGlue();
		buttons.add(horizontalGlue);

		deleteBtn = new JButton("Delete word");
		buttons.add(deleteBtn);

		Component horizontalGlue_3 = Box.createHorizontalGlue();
		buttons.add(horizontalGlue_3);

		window.setVisible(true);
	}

	public JTextField getSearchField() {
		return searchField;
	}

	public JTextArea getResultField() {
		return resultField;
	}

	public JButton getAddWordBtn() {
		return addWordBtn;
	}

	public JButton getSearchBtn() {
		return searchBtn;
	}

	public JButton getDeleteBtn() {
		return deleteBtn;
	}

	public JFrame getWindow() {
		return window;
	}

}
