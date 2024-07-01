package ie.atu.sw;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * The class handles all the application input / output.
 */

public class WordEmbeddingIO {
	private Scanner s;
	private Configuration config;
	private EmbeddingAnalyzer analyzer;
	private FileReaderUtility utility;
	
	
	public WordEmbeddingIO(Configuration config, EmbeddingAnalyzer analyzer, FileReaderUtility utility) {
		this.config = config;
		this.analyzer = analyzer;
		this.utility = utility;
		this.s = new Scanner(System.in);
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
	
	/**
	 * Method to handle the inputed string and extract the words to be processed.
	 * 
	 * @return array with the words on the user inputed string.
	 */
	public String [] getInputWordArray() {
		return config.getInputWord().toLowerCase().split("\\W+");// Converting the user input to lower case and using a regular expression to extract words. 
	}
	
	/*
	 * Method set the output file name and add the text (txt) file extension. 
	 */
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
	
	private void outputWordFile(String fpath, String out, String[] inputWordArray, String[] embeddingWordsArray) throws IOException {
	    try (BufferedWriter in = new BufferedWriter(new FileWriter(out))) {
	        for (String inputWord : inputWordArray) {
	            CosineDistance[] array = analyzer.processCosineDistances(inputWord, fpath);
	            
	            //TODO remove the hard code 10.
	            for (int j = 0; j < 10; j++) {
	                CosineDistance cosine = array[j];
	                in.write(embeddingWordsArray[cosine.index()]);
	                if (j < 10 - 1) {
	                    in.write(", ");
	                }
	            }
	            in.write("\n"); // Add a newline character after each set of 10 words
	        }
	    }
	}
	
	/*
	 * Method write the top closest words in a ".txt" file.
	 */
	public void getWordFile() throws IOException {
		
		String fpath = getFilepath();
		String outFileName = getOutputFilename();
		String [] inputWordArray = getInputWordArray();
		String [] embeddigWordsArray = utility.embeddingWordsArray(fpath); 
		
		try {
			outputWordFile(fpath, outFileName, inputWordArray, embeddigWordsArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
}
