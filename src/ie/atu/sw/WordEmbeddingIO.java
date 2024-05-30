package ie.atu.sw;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WordEmbeddingIO {
	private Scanner s;
	
	//Constructor initiating the class variables.
	public WordEmbeddingIO() {
		this.s = new Scanner(System.in);
	}
	

	/*
	 * Writes the contents of a specified array to a file. Each element of the array is written in a text file adding ", " to improve readability. 
	 * 
	 * @param words array of String
	 * @param fileName file name provided by user
	 * @throws IOException if an I/O error occurs while writing to the file
	 */

	public void fileWriter(String[] words, String fileName) throws IOException {

		BufferedWriter in = new BufferedWriter(new FileWriter(fileName));

		for (int i = 0; i < words.length; i++) {
			in.write(words[i]);
			
			if (i < words.length - 1) {
				in.write(", ");
				
			}
		}
		
		in.flush();
		in.close();

	}

	
	/*
	 * Method takes a the output file name from user and use as argument to fileWritter method.
	 * 
	 * @param array array with the processed words
	 * @param fileName name of the output file
	 */

	public void outputFile(String[] array) {

		// This array is here just for testing, must be removed
		//String[] array = { "Alice", "Bob", "Charlie", "Diana", "Edward" }; 

		System.out.print("[INFO] Please provide the output file name > ");
		String fileName = s.next() + ".txt"; // Add a txt extension to the file name.

		try {
			fileWriter(array, fileName);
		} catch (IOException e) {
			System.err.println("Error file not created");
			e.printStackTrace();
		}

	}
	
	
	/*
	 * Method to get the user EmbeddingWord file path.
	 */
	public String filePath() {

		System.out.println("Enter the Word Embedding file path > ");

		String fpath = s.next();
		return fpath;

	}

	public void word() {
		// Create a method that take a String from user
	}

}
