package ie.atu.sw;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

	/*
	 * TODO: create the options methods;
	 */

	// Creating a EmbeddingFileIO object.
	EmbeddingFileIO io = new EmbeddingFileIO();

	// Class variable to track the menu state.
	private boolean menuRunnig = true;

	// Creating a new class Scanner object to take user input
	private Scanner s;

	// Constructor initiating the Scanner s object.
	public Menu() {
		s = new Scanner(System.in);
	}

	// Method to containing a switch statement to process user input.
	// TODO; a user exception to prevent the user to input a invalid input.
	public void start() {

		while (menuRunnig) {

			options(); // Calling the private options method to print the user options.

			int choice = s.nextInt();

			switch (choice) {

			case 1 -> filePath();
			case 2 -> outputFile();
			case 3 -> word();
			case 4 -> menuRunnig = false;
			default -> System.out.println("Invalid Selction");

			}
		}
	}

	/*
	 * Method to get the user Embedding Word file path. Not sure if this method
	 * suppose to be here or in the EmbeddingFileIO class
	 */
	public String filePath() {

		System.out.println("Enter the Word Embedding file path > ");

		String fpath = s.next();
		return fpath;

	}

	// TODO I am not sure how to handle in array input. If it will be taken as an
	// argument for this method or somehow a class variable.
	public void outputFile() {

		String[] array = { "Alice", "Bob", "Charlie", "Diana", "Edward" }; // This array is here just for testing, must
																			// be removed

		s = new Scanner(System.in);

		System.out.print("[INFO] Please provide the output file name > ");
		String fileName = s.next() + ".txt"; // Add a txt extension to the file name.

		try {
			io.fileWriter(array, fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void word() {
		// TODO;
	}

	/*
	 * The method print the application header and available options to the console.
	 */
	private void options() {

		System.out.println("*************************************************************");
		System.out.println("*                                                           *");
		System.out.println("*      ATU - Dept. of Computer Science & Applied Physics    *");
		System.out.println("*         Similarity Search with Word Embeddings            *");
		System.out.println("*                                                           *");
		System.out.println("*************************************************************");
		System.out.println();
		System.out.println("(1) Specify Embedding File");
		System.out.println("(2) Specify a Output File name");
		System.out.println("(3) Enter a word");
		System.out.println("(4) Quit");
	}

}
