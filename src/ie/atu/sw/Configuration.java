package ie.atu.sw;

/*
 * This class holds the application configuration.
 */

public class Configuration {

	private String filePath = "./word-embeddings.txt"; //Default file path.
	private String inputWord = null;
	private String outputFileName = "SimilarWordsOut"; //Default output file name.
	private int numberOfCloseWords = 10; //Default number of close words.
	
	public int getNumberOfCloseWords() {
		return numberOfCloseWords;
	}
	
	/**
	 * Sets the number of close words to be processed.
	 * 
	 * @return the number of close words.
	 */
	public void setNumberOfCloseWords(int numberOfCloseWords) {
		this.numberOfCloseWords = numberOfCloseWords;
	}

	/**
	 * Gets the Word Embedding file path
	 * 
	 * @return the file path of the word embedding.
	 */
	public String getFilePath() {
		return filePath;
	}
	
	/**
	 * Sets the Word Embedding file path.
	 * 
	 * @param filePath the file path to be set.
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	/**
	 * Gets the user input string.
	 * 
	 * @return the the user input string.
	 */
	public String getInputString() {
		return inputWord;
	}
	
	/**
	 * Sets the user input String.
	 * 
	 * @param inputWord the input word to be set.
	 */
	public void setInputString(String inputWord) {
		this.inputWord = inputWord;
	}
	
	/**
	 * Gets the output file name.
	 * 
	 * @return the output file name
	 */
	public String getOutputFileName() {
		return outputFileName;
	}
	
	/**
	 * Sets the output file name.
	 * 
	 * @param outputFileName the output file name to be set.
	 */
	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

}
