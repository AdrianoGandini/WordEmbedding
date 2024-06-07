package ie.atu.sw;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WordEmbeddingIO {
	private Scanner s;
	private static String filepath;
	private static String inputWord;
	private static String outputFileName;

	
	
	// Constructor 
	public WordEmbeddingIO() {
		this.s = new Scanner(System.in);
		filepath = null;
		inputWord = null;
		outputFileName = null;
	}

	
	private void fileWriter(String[] words, String fname) throws IOException {

		BufferedWriter in = new BufferedWriter(new FileWriter(fname));//TODO Check if the file path is already set.

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

		if (outputFileName == null) {
			System.out.println("Output file name not set");
			setOutputFileName();
		}		
		try {
			fileWriter(array, outputFileName);
		} catch (IOException e) {
			System.err.println("Error file not created");
			e.printStackTrace();
		}

	}

	
	public void setFilePath() {
		System.out.println("Enter the Word Embedding file path > ");
		filepath = s.next();
	}
	
	public String getFilepath() {
		return filepath;
	}

	public void setInputWord() {
		System.out.print("Enter the Word to be compared > ");
		inputWord = s.next();
	}
	
	public String getInputWord() {
		return inputWord.toLowerCase(); //Converting the user input to lower case.
	}
	
	public void setOutputFileName() {
		System.out.print("[INFO] Please provide the output file name > ");
		outputFileName = s.next() + ".txt"; // Add a text extension to the file name.
	}
	
	public String getOutputFilename() {
		return outputFileName;
	}
	
}
