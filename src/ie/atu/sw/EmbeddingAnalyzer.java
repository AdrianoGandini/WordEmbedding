package ie.atu.sw;

import java.io.FileNotFoundException;
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
			}else {
				System.out.println("The word " + word + "is not present in the " + io.getFilepath() + " file.");
			}
		}
		return index;
	}

	/*
	 * //Method to perform the dot calculation. Product of two vectors.
	 * 
	 * font = https://www.geeksforgeeks.org/program-dot-product-cross-product-two-vector/
	 */
	private double calculateDotProduct(double vect_A[], double vect_B[]) {
		
		double product = 0;
		
		for (int i = 0; i < vect_A.length; i++) {
			product = product + (vect_A[i] * vect_B[i]);
		}
		return product;
	}
	
	/*
	 * Method to calculate the magnitude. The root sum of the squares of two vectors;
	 */
	private double calculateMagnitude(double vectorA[], double vectorB[]) {
		
		double SquaresSumVectorA = 0;
		double SquaresSumVectorB = 0;
		
			for (int i = 0; i < vectorA.length; i ++) {
				SquaresSumVectorA += vectorA[i] * vectorA[i];
				SquaresSumVectorB += vectorB[i] * vectorB[i];
			}
		
			return Math.sqrt(SquaresSumVectorA * SquaresSumVectorB);
	}

	/*
	 * Method to calculate the Cosine Distance can be calculated by computing the dot product of the two vectors and then dividing this number by product of the magnitudes
	 *  The method will return a value between 1, max similarity  and 0 max dissimilar. 
	 */
	private double calculateCosineDistance(double vect_A[], double vect_B[]) {
		
		double dotProduct = calculateDotProduct(vect_A, vect_B);		
		double magnitude = calculateMagnitude(vect_A, vect_B);		
		return dotProduct / magnitude; //Return the cosine distance.

	}
	
	
	
	// Method to compare the inputed word vector with all words vectors in the
	// provided file. Return an array with the top 10 similar words
	
	public String[] compereVectors() throws IOException {
		
		String word = io.getInputWord(); //Word from user input. //User word input
		String fpath = io.getFilepath();
		
		String[] wordArray = utility.embeddingWordsArray(fpath); // Get the word array from word embedding file.
		Double[][] vectorArray = utility.embeddingVectorArray(fpath); // Get the vectors array from word embedding file.
		
		int inputWordIndex = inputWordIndex(word, wordArray ); // Select the index of user word input in the word array.
		
		Double[] inputWordVector = inputWordVector(inputWordIndex, vectorArray); //Input word vector array.
		
		Double[] fileVector = new Double[vectorArray[0].length];
		
		for (int i = 0; i < vectorArray.length; i++) {
			for (int j = 0; j < vectorArray[0].length; j++) {
				fileVector[j] = vectorArray[i][j];
			}
		}
		

		return null;
	}
	
	
	
	public Double[] inputWordVector(int wordIndex, Double[][] embeddingVector) {
		
		//Array to hold the input word vector.
		Double[] inputWordVector = new Double[embeddingVector[0].length];
		
		for (int i = 0; i < embeddingVector[0].length; i ++) {
			inputWordVector[i] = embeddingVector[wordIndex][i];
		}
		return inputWordVector;
	}
	
	//Need to compare the input word vector array with all rows and return the result.
	
}
