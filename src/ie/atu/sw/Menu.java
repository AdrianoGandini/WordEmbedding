package ie.atu.sw;

import java.io.IOException;
import java.util.Scanner;

/**
 * The Menu class handles the user interface for the application. It displays
 * options to the user and processes their input.
 */

public class Menu {

	private WordEmbeddingIO io;
	private Configuration config;
	private FileReaderUtility file;
	private EmbeddingAnalyzer analyzer;
	private Sort sort;
	private boolean menuRunning;
	private Scanner s;

	
	/*
	 * Constructor initiating the the class variables.
	 */
	
	public Menu(WordEmbeddingIO io, Configuration config, FileReaderUtility file, EmbeddingAnalyzer analyzer, Sort sort, Scanner s) {
		this.io = io;
		this.config = config;
		this.file = file;
		this.analyzer = analyzer;
		this.sort = sort;
		this.menuRunning = true;
		this.s = s;
	}

	/**
	 * Starts the menu loop, displaying options and processing user input.
	 */
	public void start() {

		while (menuRunning) {

			showOptions();

			try {
				int choice = s.nextInt();
				handleOptions(choice);
				//maybe add more exceptions here?
			} catch (Exception e) {
				System.out.println();
				System.out.println("******** The option selected is not valid. Please enter a valid option ******** ");
				System.out.println();
				s.nextLine();
			}

		}
		s.close();
	}

	/*
	 * The method displays the application header and available options to the user.
	 */
	private void showOptions() {
		System.out.println("*************************************************************");
		System.out.println("*                                                           *");
		System.out.println("*      ATU - Dept. of Computer Science & Applied Physics    *");
		System.out.println("*         Similarity Search with Word Embeddings            *");
		System.out.println("*                                                           *");
		System.out.println("*************************************************************");
		System.out.println();
		System.out.println("(1) Specify Embedding File - Default path is pre-filled");
		System.out.println("(2) Specify a Output File name - Default path is pre-filled");
		System.out.println("(3) Enter a word or a text");
		System.out.println("(4) Processed file");
		System.out.println("(5) Display Top close words");
		System.out.println("(6) Display Detailed Top close words");
		System.out.println("(7) Display the configuration options");
		System.out.println("(8) Quit");
		System.out.println("(9) Specify the number of closest words");
		System.out.println("(?) Optional Extras...");
		System.out.println();
		System.out.println(ConsoleColour.YELLOW + "Select Option [1 - 8] > " + ConsoleColour.RESET);
		System.out.println();
	}

	/*
	 * Method processes the user choice and handles tasks for other classes.
	 * 
	 * @param choice the user's menu selection.
	 */
	private void handleOptions(int choice) throws IOException {
		switch (choice) {
			case 1 -> io.setFilePath(); // Set the embedding file path
			case 2 -> io.setOutputFileName(); // Set the output file name
			case 3 -> io.setInputWord(); // Enter a word or a text
			case 4 -> io.getWordFile(); // Process and output file
			case 5 -> io.printWords(false); // Display top close words
			case 6 -> io.printWords(true); // Display detailed top close words
			case 7 -> io.configSettings(); // Show configuration settings
			case 8 -> menuRunning = false; // Quit the application
			case 9 -> io.setNumberOfCloseWords();
			default -> System.out.println("Invalid Selection"); // Handle invalid selections
		}
	}

}
