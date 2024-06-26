package ie.atu.sw;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WordEmbeddingIO {
	private Scanner s;
	private Configuration config;
	private EmbeddingAnalyzer analyzer;
	private FileReaderUtility utility;
	

	
	
	// Constructor 
	public WordEmbeddingIO(Configuration config, EmbeddingAnalyzer analyzer, FileReaderUtility utility) {
		this.config = config;
		this.analyzer = analyzer;
		this.utility = utility;
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
	
	//I have to change the method
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
	
	// TODO Create Regular Expression for the method split. Now it is just
	// identifying just the spaces. .
	// Method to handle the user word/ words input.
	public String [] getInputWordArray() {
		return config.getInputWord().toLowerCase().split("\\W+"); //Converting the user input to lower case.
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
		System.out.println();
		System.out.println("File path: " + config.getFilePath());
		System.out.println("Input word: " + config.getInputWord());
		System.out.println("Output File Name: " + config.getOutputFileName());
		System.out.println();
		System.out.println("************************************************");
		System.out.println();
		System.out.println();
	}
	
	
	private void printCloseWords(CosineDistance[] sortedCosineArray, String[] wordArray, int numberOfCloseWords) {

		System.out.println();
		for (int j = 0; j < numberOfCloseWords; j++) {
			CosineDistance cosine = sortedCosineArray[j];
			System.out.print(wordArray[cosine.index()]);
			if (j < numberOfCloseWords - 1) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}

	// Print each close word details.
	private void printDetailCloseWords(CosineDistance[] sortedCosineArray, String[] wordArray, int numberOfCloseWords) {
		for (int j = 0; j < numberOfCloseWords; j++) {
			CosineDistance cosine = sortedCosineArray[j];
			System.out.println("Position: " + j + "; cosine: " + cosine.cosineDistance() + " index: " + cosine.index()
					+ " word: " + wordArray[cosine.index()]);
		}
		System.out.println();
	}
	
	public void printWords(boolean detail) throws IOException {

		String[] inputWords = getInputWordArray();
		String fpath = getFilepath();
		
		String[] wordArray = utility.embeddingWordsArray(fpath);

		for (String word : inputWords) {
			CosineDistance[] cosieDistances = analyzer.processCosineDistances(word, fpath);
			if (detail) {
				printDetailCloseWords(cosieDistances, wordArray, 10); // Change the hard code "10".
			} else {
				printCloseWords(cosieDistances, wordArray, 10);
			}
		}
		System.out.println();
	}
	
	public void outputWordFile(String fpath, String out, String[] inputWordArray, String[] wordArray) throws IOException {
	    try (BufferedWriter in = new BufferedWriter(new FileWriter(out))) {
	        for (String inputWord : inputWordArray) {
	            CosineDistance[] array = analyzer.processCosineDistances(inputWord, fpath);
	            
	            //TODO remove the hard code 10.
	            for (int j = 0; j < 10; j++) {
	                CosineDistance cosine = array[j];
	                in.write(wordArray[cosine.index()]);
	                if (j < 10 - 1) {
	                    in.write(", ");
	                }
	            }
	            in.write("\n"); // Add a newline character after each set of 10 words
	        }
	    }
	}
	
	public void test() {
		try {
			outputWordFile(getFilepath(), getOutputFilename(), getInputWordArray(), utility.embeddingWordsArray(getFilepath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
}
