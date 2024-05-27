package ie.atu.sw;

import java.util.Scanner;

public class Menu {

	/*
	 * TODO: show options; create a switch statement to access the options methods;
	 * create the options methods;
	 */
	
	//Class variable to track the menu state.
	private boolean menuRunnig = true;
	
	//Creating a new class Scanner object to take user input
	private Scanner s;
	
	//Contructor iniciating the Scanner s object.
	public Menu() {
		s = new Scanner(System.in);
	}
	
	
	//Method to containig ta swhitch spatement to process user input
	//TODO; a user exception to prevent the user to input a invalid input
	public void start() {
		
		while(menuRunnig) {
			
			options(); // Calling the private options method to print the user options.
			
			int choice = s.nextInt(); // Variable to hold the user input
			
			switch(choice) {
			
			case 1 -> filePath();
			case 2 -> outputFile();
			case 3 -> word();
			case 4 -> menuRunnig = false;
			default -> System.out.println("Invalid Selction");
			}
		}
	}
	
	public void filePath() {
		//TODO;
	}
	
	public void outputFile() {
		//TODO;
	}
	
	public void word() {
		//TODO;
	}
	
	//Method to print the application header and the options 
	private void options() {
		
		
		System.out.println("*************************************************************");
		System.out.println("*                                                           *");
		System.out.println("*      ATU - Dept. of Computer Science & Applied Physics    *");
		System.out.println("*         Similarity Search with Word Embeddings            *");
		System.out.println("*                                                           *");
		System.out.println("*************************************************************");
		System.out.println();
		System.out.println("(1) Specify Embedding File");
		System.out.println("(2) Specify a Outiput File");
		System.out.println("(3) Enter a word");
		System.out.println("(4) Quit");
	}

}
