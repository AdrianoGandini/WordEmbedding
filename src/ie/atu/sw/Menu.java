package ie.atu.sw;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Menu class handles the user interface for the application. It displays
 * options to the user and processes their input.
 */

public class Menu {

	private WordEmbeddingIO io;
	
	private boolean menuRunning;
	private Scanner s;

	
	/*
	 * Constructor initiating the the class variables.
	 */
	
	public Menu(WordEmbeddingIO io,  Scanner s) {
		this.io = io;
		
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
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println(ConsoleColour.RED + "[ERROR]The option selected is not valid. Please select a optiion [1 - 9]" + ConsoleColour.RESET);
				System.out.println();
				s.nextLine();
			}catch (IOException e) {
				System.out.println();
				System.out.println(ConsoleColour.RED + "[ERROR]An I/O error occured. Error message: " + e.getMessage() + ConsoleColour.RESET);
				System.out.println();
			}

		}
		s.close();
	}

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
	    System.out.println("(3) Specify the number of closest words");
	    System.out.println("(4) Enter a word or a text");
	    System.out.println("(5) Display Top close words");
	    System.out.println("(6) Display Detailed Top close words");
	    System.out.println("(7) Get output Processed file");
	    System.out.println("(8) Display the configuration options");
	    System.out.println("(9) Quit");
	    System.out.println();
	    System.out.println(ConsoleColour.YELLOW + "Select Option [1 - 9] > " + ConsoleColour.RESET);
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
	        case 3 -> io.setNumberOfCloseWords(); // Set the number of closest words
	        case 4 -> io.setInputWord(); // Enter a word or a text
	        case 5 -> io.printWords(false); // Display top close words
	        case 6 -> io.printWords(true); // Display detailed top close words
	        case 7 -> io.getWordFile(); // Process and output file
	        case 8 -> io.configSettings(); // Show configuration settings
	        case 9 -> quit(); // Quit the application
	        default -> System.out.println(ConsoleColour.RED + "[ERROR]The option selected is not valid. Please select a optiion [1 - 9]" + ConsoleColour.RESET); // Handle invalid selections
	    }
	}
	
	private void quit() {
		menuRunning = false;
		System.out.println(ConsoleColour.GREEN + "The application has been closed. Thank you!!" + ConsoleColour.RESET);
	}


}
