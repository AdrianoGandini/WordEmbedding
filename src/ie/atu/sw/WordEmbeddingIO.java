package ie.atu.sw;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WordEmbeddingIO {
	private Scanner s;
	private Configuration config;
	

	
	
	// Constructor 
	public WordEmbeddingIO(Configuration config) {
		this.config = config;
		this.s = new Scanner(System.in);
		
	}

	
	private void fileWriter(String[] words, String fname) throws IOException {

		BufferedWriter in = new BufferedWriter(new FileWriter(fname));

		for (int i = 0; i < words.length; i++) {
			in.write(words[i]);

			if (i < words.length - 1) {
				in.write(", ");
			}
		}
		in.flush();
		in.close();
	}

	public void outputFile(String[] array) {

		if (config.getOutputFileName() == "SimilarWordsOut.txt") {
			System.out.println("The default file name SimilarWordsOut.txt will be used");
		}		
		try {
			fileWriter(array, config.getOutputFileName());
		} catch (IOException e) {
			System.err.println("Error file not created");
			e.printStackTrace();
		}

	}

	
	public void setFilePath() {
		System.out.println("[INFO] Enter the Word Embedding file path (with extension) > ");
		config.setFilePath(s.next());
	}
	
	public String getFilepath() {
		return config.getFilePath();
	}

	public void setInputWord() {
		System.out.print("Enter the Word to be compared > ");
		config.setInputWord(s.nextLine());
		
	}
	
	public String getInputWord() {
		return config.getInputWord().toLowerCase(); //Converting the user input to lower case.
	}
	
	public void setOutputFileName() {
		System.out.print("[INFO] Please provide the output file name > ");
		config.setOutputFileName(s.next() + ".txt"); // Add a text extension to the file name.
	}
	
	public String getOutputFilename() {
		return config.getOutputFileName();
	}
	
	public void configSettings() {
		System.out.println();
		System.out.println();
		System.out.println("************************************************");
		System.out.println("*            Configuration Settings            *");
		System.out.println("File path: " + config.getFilePath());
		System.out.println("Input word: " + config.getInputWord());
		System.out.println("Output File Name: " + config.getOutputFileName());
		System.out.println("************************************************");
		System.out.println();
		System.out.println();
	}
	
}
