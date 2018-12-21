/**
 * @author Mykola Bikchentaev
 * This program encrypts text from the given file and saves the encrypted text into the encrypted.txt,
 * and also can decrypt it and save the decrypted text into the decrypted.txt.
 */
package cypherthestring;

import java.io.*;
import java.util.*;

public class CesarCypher {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("What do you want to do?:" + "\n1 - Encrypt text" + "\n2 - Decrypt text" + "\n0 - Exit");
		int action = input.nextInt();
		boolean active = true;
		while (active) {
			switch (action) {
			case 1:
				try {
					encrypt(input);
				} catch (IOException e) {
					System.err.println("An error occured while encrypting the text");
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					decrypt(input);
				} catch (IOException e) {
					System.err.println("An error occured while decrypting the text");
					e.printStackTrace();
				}
				break;
			case 0:
				active = false;
				break;
			default:
				System.out.println("Unknown command");
				break;
			}
			if (active) {
				System.out.println(
						"What do you want to do?:" + "\n1 - Encrypt text" + "\n2 - Decrypt text" + "\n0 - Exit");
				action = input.nextInt();
			}
		}
		System.out.println("Bye");
		input.close();
	}

	/**
	 * This method receives letter shift from the input and decrypts 
	 * encrypted text from the encrypted.txt and saves the decrypted 
	 * text to the decrypted.txt.
	 */
	private static void decrypt(Scanner input) throws IOException {
		BufferedReader bReader = getBReader(input);
		BufferedWriter bWriter = getBWriter("decrypted.txt");
		if(bReader == null || bWriter == null) {
			System.err.println("Failed to perform an operation you requested");
			return;
		}
		String result = "";
		System.out.print("Enter letter shift: ");
		int shift = input.nextInt();
		char c;
		int a1;
		String concat = null;
		String line = null;
		while (true) {
			line = bReader.readLine();
			if (line == null)
				break;
			String[] words = line.split(" ");
			for (int i = 0; i < words.length; i++) {
				words[i] = words[i].replaceAll("[^\\p{L}\\p{Nd}]+", "");
				for (int j = 0; j < words[i].length(); j++) {
					c = words[i].charAt(j);
					if (c >= 'A' && c <= 'Z') {
						a1 = c - 'A' - shift;
						if (a1 < 0)
							a1 = a1 + 26;
						char s = (char) a1;
						s = (char) (s + 'A');
						concat = "";
						concat = concat + s;
						result = result + concat;
					} else if (c >= 'a' && c <= 'z') {
						a1 = c - 'a' - shift;
						if (a1 < 0)
							a1 = a1 + 26;
						char s = (char) a1;
						s = (char) (s + 'a');
						concat = "";
						concat = concat + s;
						result = result + concat;
					}
				}
				bWriter.write(result + " ");
				bWriter.flush();
				result = "";
			}
			bWriter.newLine();
			bWriter.flush();
		}
		System.out.println("Complete. Decrypted text is stored in the \"decrypted.txt\"");
		bReader.close();
		bWriter.close();
	}

	/**
	 * This method receives letter shift from the input and encrypts 
	 * text from the given file and saves the encrypted text to the
	 * encrypted.txt.
	 */
	private static void encrypt(Scanner input) throws IOException {
		BufferedReader bReader = getBReader(input);
		BufferedWriter bWriter = getBWriter("encrypted.txt");
		if(bReader == null || bWriter == null) {
			System.err.println("Failed to perform an operation you requested");
			return;
		}
		String result = "";
		System.out.print("Enter letter shift: ");
		int shift = input.nextInt();
		char c;
		int a1;
		String concat = null;
		String line = null;
		while (true) {
			line = bReader.readLine();
			if (line == null)
				break;
			String[] words = line.split(" ");
			for (int i = 0; i < words.length; i++) {
				words[i] = words[i].replaceAll("[^\\p{L}\\p{Nd}]+", "");
				for (int j = 0; j < words[i].length(); j++) {
					c = words[i].charAt(j);
					if (c >= 'A' && c <= 'Z') {
						a1 = c - 'A' + shift;
						a1 = a1 % 26;
						char s = (char) a1;
						s = (char) (s + 'A');
						concat = "";
						concat = concat + s;
						result = result + concat;
					} else if (c >= 'a' && c <= 'z') {
						a1 = c - 'a' + shift;
						a1 = a1 % 26;
						char s = (char) a1;
						s = (char) (s + 'a');
						concat = "";
						concat = concat + s;
						result = result + concat;
					}
				}
				bWriter.write(result + " ");
				bWriter.flush();
				result = "";
			}
			bWriter.newLine();
			bWriter.flush();
		}
		System.out.println("Complete. Encrypted text is stored in the \"encrypted.txt\"");
		bReader.close();
		bWriter.close();
	}

	/**
	 * Creates BufferedReader object
	 * @return BufferedReader object
	 */
	private static BufferedReader getBReader(Scanner input) {
		BufferedReader bReader = null;
		String fileName = null;
		int tries = 4;
		while (tries > 0 && bReader == null) {
			try {
				System.out.println("Enter name of the file: ");
				fileName = input.next();
				bReader = new BufferedReader(new FileReader(fileName));
			} catch (FileNotFoundException e) {
				tries--;
				if(tries > 0)
					System.err.println("File not found. Try again. Tries left: " + tries);
			}
		}
		return bReader;
	}

	/**
	 * Creates BufferedWriter object
	 * @return BufferedWriter object
	 */
	private static BufferedWriter getBWriter(String fileName) {
		BufferedWriter bWriter = null;
		try {
			bWriter = new BufferedWriter(new FileWriter(fileName));
		} catch (IOException e) {
			System.out.println("En error occured while creating BufferedWriter");
			e.printStackTrace();
		}
		return bWriter;
	}

}
