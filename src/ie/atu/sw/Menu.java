package ie.atu.sw;

import java.util.Scanner;

/**
 * The Menu class handles the user interface for the application.
 * It displays options to the user and processes their input.
 */

public class Menu {

	private WordEmbeddingIO io;
	private Configuration config;
	private FileReaderUtility file;
	private EmbeddingAnalyzer analyzer;
	private boolean menuRunnig;
	private Scanner s;

	/*
	 *  Constructor initiating the the class variables.
	 */
	public Menu() {
		this.config = new Configuration();
		this.io = new WordEmbeddingIO(config);
		this.file = new FileReaderUtility();
		this.analyzer = new EmbeddingAnalyzer(io, file);
		this.s = new Scanner(System.in);
		this.menuRunnig = true;
	}

	
	/**
     * Starts the menu loop, displaying options and processing user input.
     */
	public void start() {

		while (menuRunnig) {

			showOptions();
			
			int choice = s.nextInt();
			
			handleOptions(choice);

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
		System.out.println("(1) Specify Embedding File");
		System.out.println("(2) Specify a Output File name");
		System.out.println("(3) Enter a word or a text");
		System.out.println("(4) Processed file");	
		System.out.println("(5) Show configuration settings");
		System.out.println("(6) Quit");
		System.out.println("(?) Configure Options");
		System.out.println("(?) Optional Extras...");	
		
		
		System.out.println(ConsoleColour.BLACK_BACKGROUND_BRIGHT);
		System.out.println("Slelect Option [1 - 4 > ");
		System.out.println();
	}

	/*
	 * Method process the user choice and handle tasks for other classes.
	 * 
	 * @param choice the user's menu selection.
	 */
	private void handleOptions(int choice) {

		
	switch (choice) {

		case 1 -> io.setFilePath();
		case 2 -> io.setOutputFileName();
		case 3 -> io.setInputWord();
		case 4 -> io.outputFile(null);
		case 5 -> io.configSettings();
		case 6 -> menuRunnig = false;
		case 7 -> analyzer.test();	 
		default -> System.out.println("Invalid Selction");

		}
	}
	

}
