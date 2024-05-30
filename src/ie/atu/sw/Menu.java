package ie.atu.sw;


import java.util.Scanner;

public class Menu {
	
	private WordEmbeddingIO io;
	private EmbeddingUtility eu;
	private boolean menuRunnig;
	private Scanner s;
	
	
	
	// Constructor initiating the the class variables.
	public Menu() {
		this.io = new WordEmbeddingIO();
		this.eu = new EmbeddingUtility();
		this.s = new Scanner(System.in);
		this.menuRunnig = true;
	}

	// Method to containing a switch statement to process user input.
	// TODO; a user exception to prevent the user to input a invalid input.
	public void start() {

		while (menuRunnig) {

			options(); // Calling the private options method to print the user options.

			int choice = s.nextInt();

			switch (choice) {

			case 1 -> io.filePath();
			case 2 -> io.outputFile(eu.compereVectors());
			case 3 -> io.word();
			case 4 -> menuRunnig = false;
			default -> System.out.println("Invalid Selction");

			}
		}
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
