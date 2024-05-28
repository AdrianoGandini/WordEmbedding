package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class EmbeddingFileIO {

	//TODO create a method to read the user input as a EnbeddingWord file path ; create a method to write the output to a file named by the user.
	
	//Class scanner variable
	private Scanner s;
	
	/*
	 * Method to get the user Embedding Word file path.
	 * Not sure if this method suppose to be here.
	 */
	public String getFilePath() {
		
		System.out.println("Enter the Word Embedding file path > ");
		String fpath = s.next();
	
		return fpath;
	}
	
	public void fileReader() throws FileNotFoundException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("file.txt")));
		
		//TODO
		
	}
	
}
