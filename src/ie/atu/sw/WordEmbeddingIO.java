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

	/*
     * Sets the file path for the word embedding file.
     */
	public void setFilePath() {
		System.out.println("[INFO] Enter the Word Embedding file path (with extension) > ");
		config.setFilePath(s.next());
	}
	
	/*
     * Returns the file path for the word embedding file.
     */
	public String getFilepath() {
		return config.getFilePath();
	}

	/*
     * Sets the input word to be compared.
     */
	public void setInputWord() {
		System.out.print("Enter the Word to be compared > ");
		config.setInputString(s.nextLine());	
	}
	
	/**
	 * Method to handle the inputed string and extract the words to be processed.
	 * 
	 * @return array with the words on the user inputed string.
	 */
	public String [] getInputWordArray() {
		return config.getInputString().toLowerCase().split("\\W+");// Converting the user input to lower case and using a regular expression to extract words. 
	}
	
	/*
	 * Method set the output file name and add the text (txt) file extension. 
	 */
	public void setOutputFileName() {
		System.out.print("[INFO] Please provide the output file name > ");
		config.setOutputFileName(s.next() + ".txt"); // Add a text extension to the file name.
	}
	
	/*
     * Returns the output file name.
     */
	public String getOutputFilename() {
		return config.getOutputFileName();
	}
	
	/*
	 * Method display the configuration settings.
	 */
	public void configSettings() {
		System.out.println();
		System.out.println();
		System.out.println("************************************************");
		System.out.println("*            Configuration Settings            *");
		System.out.println();
		System.out.println("File path: " + config.getFilePath());
		System.out.println("Input word: " + config.getInputString());
		System.out.println("Output File Name: " + config.getOutputFileName());
		System.out.println();
		System.out.println("************************************************");
		System.out.println();
		System.out.println();
	}
	
	/**
	 * The method prints a specific number of words from a word array based on the proximity of the cosine distances.
	 * 
	 * @param sortedCosineArray an array of CosineDistances objects, sorted by their cosine distances.
	 * @param wordArray an array of words corresponding to the CosineDistances index.
	 * @param numberOfCloseWords the number of close words to be printed.
	 */
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

	/**
	 * The method prints a specific number of words details.
	 * 
	 * @param sortedCosineArray an array of CosineDistances objects, sorted by their cosine distances.
	 * @param wordArray an array of words corresponding to the CosineDistances index.
	 * @param numberOfCloseWords the number of close words to be printed.
	 */
	private void printDetailCloseWords(CosineDistance[] sortedCosineArray, String[] wordArray, int numberOfCloseWords) {
		for (int j = 0; j < numberOfCloseWords; j++) {
			CosineDistance cosine = sortedCosineArray[j];
			System.out.println("Position: " + j + "; cosine: " + cosine.cosineDistance() + " index: " + cosine.index()
					+ " word: " + wordArray[cosine.index()]);
		}
		System.out.println();
	}
	
	/**
	 * The method is used to invoke printCloseWords or printDetailCloseWords.
	 * 
	 * @param detail boolean that will guide with method is invoked. If the boolean is true the words are printed in details.
	 */
	public void printWords(boolean detail) throws IOException {

		String[] inputWords = getInputWordArray();
		String fpath = getFilepath();
		
		String[] wordArray = utility.embeddingWordsArray(fpath);

		for (String word : inputWords) {
			
			if (isStringInArray(wordArray, word) == false) {
				System.out.println("Unfortunatly the word " + word + " is not present on the word embedding file.");
				System.out.println();
				continue;
			}
			
			CosineDistance[] cosieDistances = analyzer.processCosineDistances(word, fpath);
			if (detail) {
				printDetailCloseWords(cosieDistances, wordArray, 10); // Change the hard code "10".
			} else {
				printCloseWords(cosieDistances, wordArray, 10);
			}
			System.out.println();
		}
		
	}
	
	/**
	 * Check if the word to be processed is part of the word embedding file.
	 * 
	 * @param wordArray string of words present in the word embedding file.
	 * @param inputWord user input word.
	 * @return boolean to flag if the word is present in the array.
	 */
	
	private boolean isStringInArray(String[] wordArray, String inputWord) {
		for (String word  : wordArray) {
			if (word.equals(inputWord)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * The method create a file that contains the closest words.
	 * 
	 * @param fpath the word embedding file path.
	 * @param out the output file name.
	 * @param inputWordArray array that contains the user input.
	 * @param wordArray an array of words corresponding to the CosineDistances index.
	 */
	private void outputWordFile(String fpath, String out, String[] inputWordArray, String[] wordsArray) throws IOException {
	    try (BufferedWriter in = new BufferedWriter(new FileWriter(out))) {
	        for (String inputWord : inputWordArray) {
	        	
	        	//Check if the input word is present on the embedding word file.
	        	if (isStringInArray(wordsArray, inputWord) == false) {
					System.out.println("Unfortunatly the word " + inputWord + " is not present on the word embedding file.");
					in.write("The word " + inputWord + " is not present in the embedding file.");
					System.out.println();
					in.write("\n");
					continue;
				}	
	        	
	            CosineDistance[] array = analyzer.processCosineDistances(inputWord, fpath);
	            
	            in.write("[ " + inputWord + " ] = ");
	            
	            //TODO remove the hard code 10.
	            //For loop starts form 1 to equal 10 in order to avoid the first item of the array. The first element is the inputWord. The processed word will result in cosine distance 1 when compared to itself. 
	            for (int j = 1; j <= 11; j++) {
	                CosineDistance cosine = array[j];
	                in.write(wordsArray[cosine.index()]);
	                if (j < 10 - 1) {
	                    in.write(", ");
	                }
	            }
	            in.write("\n"); // Add a newline character after each set of 10 words
	        }
	    }
	}
	
	/*
	 * Method to invoke outputWordFile and write a specific number of words to a ".txt" file.
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
