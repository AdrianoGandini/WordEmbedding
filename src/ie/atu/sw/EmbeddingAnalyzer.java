package ie.atu.sw;

import java.io.IOException;

public class EmbeddingAnalyzer {

	private WordEmbeddingIO io;
	private FileReaderUtility utility;
	
	public EmbeddingAnalyzer(WordEmbeddingIO io, FileReaderUtility utility) {
		this.utility =utility;
		this.io = io;
	}
	
	// Method to return the array index of the user inputed word.
	private int inputWordIndex(String word, String[] wordArray) {

		int index = 0;
		
		for (int i = 0; i < wordArray.length; i++) {
			if (word.equals(wordArray[i])) {
				index = i;
				break;
			}
		}
		return index;
	}

	private int calculateDotProduct() {
		// TODO Method to perform the dot calculation. Product of two vectors.
		return 0;
	}

	private void calculateEucledeanDistance() {
		// TODO Method to calculate the Euclidean Distance.
	}

	public String[] compereVectors(String[] wordArray, Double[] embeddingVector) {

		// TODO Method to compare the inputed word vector with all words vectors in the
		// provided file. Return an array with the top 10 similar words

		return null;
	}
	
	public static void main(String[] args) {
		
	}
	public void test() {
		try {
			int index =inputWordIndex(io.getInputWord(), utility.embeddingWordsArray(io.getFilepath()));
			 System.out.println("The word " + io.getInputWord() + " index is: " + index);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
