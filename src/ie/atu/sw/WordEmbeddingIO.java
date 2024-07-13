package ie.atu.sw;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	 * Method set the number of close words to be computed in the application.
	 */
	public void setNumberOfCloseWords() {
		System.out.println(ConsoleColour.GREEN + "[INFO] Enter the number of close words to be computed > " + ConsoleColour.RESET);
		config.setNumberOfCloseWords(s.nextInt());
	}
	
	public int getNumberOfCloseWords() {
		return config.getNumberOfCloseWords();
	}
	

	/*
     * Sets the file path for the word embedding file.
     */
	public void setFilePath() {
		System.out.println(ConsoleColour.GREEN + "[INFO] Enter the Word Embedding file path (with extension) > " + ConsoleColour.RESET);
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
		System.out.print(ConsoleColour.GREEN + "[INFO] Enter a Word or phrase to be processed > " + ConsoleColour.RESET);
		config.setInputString(s.nextLine());	
	}
	
	/**
	 * Method to handle the inputed string and extract the words to be processed.
	 * 
	 * @return array with the words on the user inputed string.
	 */
	public String [] getInputWordArray() {
		
		if (config.getInputString() == null) {
			System.out.println(ConsoleColour.RED + "[ERROR] No word or phrase has been inputed." + ConsoleColour.RESET);
			System.out.println("Please select option number 3  to input a word or phrase");
			return null;
		}else
			return config.getInputString().toLowerCase().split("\\W+");	
		
	}
	
	/*
	 * Method set the output file name and add the text (txt) file extension. 
	 */
	public void setOutputFileName() {
		System.out.print(ConsoleColour.GREEN + "[INFO] Please provide the output file name > " + ConsoleColour.RESET);
		config.setOutputFileName(s.next());
	}
	
	/*
     * Returns the output file name.
     */
	public String getOutputFilename() {
		return config.getOutputFileName();
	}
	
	private void confiLineColorPatter(String info, String value) {
		System.out.println(ConsoleColour.GREEN + info + ConsoleColour.RESET + ConsoleColour.PURPLE + value + ConsoleColour.RESET);
	}
	
	private void confiLineColorPatter(String info, int value) {
		System.out.println(ConsoleColour.GREEN + info + ConsoleColour.RESET + ConsoleColour.PURPLE + value + ConsoleColour.RESET);
	}
	
	/*
	 * Color patter Details Close Words. 
	 */
	private void printDetailsColour(String word, int position, double cosineValue, int wordEmbeddingFileIndex ) {
		System.out.println(ConsoleColour.GREEN + "Word " 
				+ ConsoleColour.PURPLE +  word 
				+ ConsoleColour.GREEN + "; Position: " 
				+ ConsoleColour.PURPLE +  position + ConsoleColour.GREEN +  "; cosine: " 
				+ ConsoleColour.PURPLE +   cosineValue + ConsoleColour.GREEN 
				+  " index on word embedding file: " 
				+ ConsoleColour.PURPLE + wordEmbeddingFileIndex + ConsoleColour.RESET);
	}
	
	/*
	 * Method display the configuration settings.
	 */
	public void configSettings() {
		System.out.println();
		System.out.println();
		System.out.println("*************************************************************");
		System.out.println("*                   Configuration Settings                  *");
		System.out.println();
		confiLineColorPatter("        File path: ", getFilepath());
		confiLineColorPatter("        Input word or phrase: ", config.getInputString());
		confiLineColorPatter("        Output File Name: ", config.getOutputFileName());
		confiLineColorPatter("        Number of close words: ", config.getNumberOfCloseWords());
		System.out.println();
		System.out.println("*************************************************************");
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
		
		
		System.out.print(ConsoleColour.GREEN);
		for (int j = 0; j < numberOfCloseWords; j++) {
			CosineDistance cosine = sortedCosineArray[j + 1];
			System.out.print(wordArray[cosine.index()]);
			if (j < numberOfCloseWords - 1) {
				System.out.print(", " );
			}
		}
		System.out.println(ConsoleColour.RESET);
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
			CosineDistance cosine = sortedCosineArray[j + 1];
			
			//Round cosine distance value to four decimal cases.
			double value = cosine.cosineDistance();
			String formattedValue = String.format("%.4f", value);
			double roundValue = Double.parseDouble(formattedValue);
			
			//Printing the detailed information.
			printDetailsColour(wordArray[cosine.index()], j, roundValue, cosine.index());
		}
		System.out.println(ConsoleColour.RESET);
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
	 * The method is used to invoke printCloseWords or printDetailCloseWords.
	 * 
	 * @param detail boolean that will guide with method is invoked. If the boolean is true the words are printed in details.
	 */
	public void printWords(boolean detail) throws IOException {
		String[] inputWords = getInputWordArray();
		int numberOfCloseWords = config.getNumberOfCloseWords();
		
		//Check if the inputWords array is null.
		if (inputWords == null) {
			return;
		}
		
		String fpath = getFilepath();
		String[] wordArray = utility.embeddingWordsArray(fpath);

		for (String word : inputWords) {
			if (isStringInArray(wordArray, word) == false) {
				System.out.println(ConsoleColour.RED + "[ERROR]Unfortunatly the word " + word + " is not present on the word embedding file." + ConsoleColour.RESET);
				System.out.println();
				continue;
			}			
			CosineDistance[] cosieDistances = analyzer.processCosineDistances(word, fpath);
			if (detail) {		
				printDetailCloseWords(cosieDistances, wordArray, numberOfCloseWords); 
			} else {
				System.out.print(ConsoleColour.GREEN + "[ " + word + " ] = " + ConsoleColour.RESET);
				printCloseWords(cosieDistances, wordArray, numberOfCloseWords);
			}
			System.out.println();
		}
		
	}
	
	/**
     * Writes the closest words to the BufferedWriter.
     *
     * @param writer the BufferedWriter to write to.
     * @param cosineDistances array of CosineDistance objects.
     * @param wordsArray array of words corresponding to the CosineDistances index.
     * @param numberOfCloseWords the number of closest words to write.
     * @throws IOException if an I/O error occurs.
     */
    private void writeCloseWords(BufferedWriter writer, CosineDistance[] cosineDistances, String[] wordsArray, int numberOfCloseWords) throws IOException {
        for (int i = 0; i < numberOfCloseWords; i++) {
            CosineDistance cosine = cosineDistances[i + 1]; //The interaction starts from the second array position, as the first position of the Cosine Distance array is the inputed word itself (Cosine Distance equals 1).  
            writer.write(wordsArray[cosine.index()]);
            if (i < numberOfCloseWords - 1) {
                writer.write(", ");
            }
        }
        writer.write("\n"); 
    }
		
	/**
	 * The method create a file that contains the closest words.
	 * 
	 * @param fpath the word embedding file path.
	 * @param out the output file name.
	 * @param inputWordArray array that contains the user input.
	 * @param wordArray an array of words corresponding to the CosineDistances index.
	 */
	private void outputWordFile(String fpath, String out, String[] inputWordArray, String[] wordsArray, int numberOfCloseWords) throws IOException {
	    try (BufferedWriter in = new BufferedWriter(new FileWriter(out))) {
	        for (String inputWord : inputWordArray) {
	        	
	        	//Check if the input word is present on the embedding word file.
	        	if (isStringInArray(wordsArray, inputWord) == false) {
					System.out.println(ConsoleColour.RED + "[ERROR]Unfortunatly the word " + inputWord + " is not present on the word embedding file." + ConsoleColour.RESET);
					in.write("The word " + inputWord + " is not present in the embedding file.");
					System.out.println();
					in.write("\n");
					continue;
				}	
	            CosineDistance[] array = analyzer.processCosineDistances(inputWord, fpath);
	            in.write("[ " + inputWord + " ] = ");
	            writeCloseWords(in, array, wordsArray, numberOfCloseWords);
	           
	        } 
	    }
	}
	
	
	
	/**
	 * Creates a path for the output file.
	 * 
	 * @param outFileName the name for the output file.
	 * @return The output file path as String.
	 */
	private String getOutFilePath(String outFileName) {
		
		//Add the logic to create the file in the Download folder.
		String home = System.getProperty("user.home");
		Path path = Paths.get(home, "Downloads", outFileName + ".txt");
		
		return path.toString();
	}
	
	
	/**
	 * Method to invoke outputWordFile and write a specific number of words to a ".txt" file.
	 * 
	 * @throws IOExcepion is a I/O fail situation. 
	 */
	public void getWordFile() throws IOException {
		
		
		
		String fpath = getFilepath();
		String outFileName = getOutputFilename();
		int numberOfCloseWords = getNumberOfCloseWords();
		String [] inputWordArray = getInputWordArray();
		String [] embeddigWordsArray = utility.embeddingWordsArray(fpath); 
		
		String out = getOutFilePath(outFileName);
		
		outputWordFile(fpath, out, inputWordArray, embeddigWordsArray, numberOfCloseWords);
		
		System.out.println(ConsoleColour.PURPLE + "The file named " + outFileName + " containing the top " + numberOfCloseWords + " close words has been created in the Downloads folder." + ConsoleColour.RESET);

			
	}
	
}
