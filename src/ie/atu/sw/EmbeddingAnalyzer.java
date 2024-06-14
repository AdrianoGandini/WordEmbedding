package ie.atu.sw;

import java.io.IOException;

public class EmbeddingAnalyzer {

	private WordEmbeddingIO io;
	private FileReaderUtility utility;

	public EmbeddingAnalyzer(WordEmbeddingIO io, FileReaderUtility utility) {
		this.utility = utility;
		this.io = io;
	}

	// Method to return the array index of the user inputed word.
	private int inputWordIndex(String word, String[] wordArray) {

		int index = 0;

		for (int i = 0; i < wordArray.length; i++) {
			if (word.equals(wordArray[i])) {
				index = i;
				break;
			} else {
				System.out.println("The word " + word + "is not present in the " + io.getFilepath() + " file.");
			}
		}
		return index;
	}

	private Double[] inputWordVector(int wordIndex, Double[][] embeddingVector) {

		// Array to hold the input word vector.
		Double[] inputWordVector = new Double[embeddingVector[0].length];

		for (int i = 0; i < embeddingVector[0].length; i++) {
			inputWordVector[i] = embeddingVector[wordIndex][i];
		}
		return inputWordVector;
	}

	/*
	 * //Method to perform the dot calculation. Product of two vectors.
	 * 
	 * font =
	 * https://www.geeksforgeeks.org/program-dot-product-cross-product-two-vector/
	 */
	private Double calculateDotProduct(Double vect_A[], Double vect_B[]) {

		double product = 0;

		for (int i = 0; i < vect_A.length; i++) {
			product = product + (vect_A[i] * vect_B[i]);
		}
		return product;
	}

	/*
	 * Method to calculate the magnitude. The root sum of the squares of two
	 * vectors;
	 */
	private Double calculateMagnitude(Double vectorA[], Double vectorB[]) {

		double SquaresSumVectorA = 0;
		double SquaresSumVectorB = 0;

		for (int i = 0; i < vectorA.length; i++) {
			SquaresSumVectorA += vectorA[i] * vectorA[i];
			SquaresSumVectorB += vectorB[i] * vectorB[i];
		}

		return Math.sqrt(SquaresSumVectorA * SquaresSumVectorB);
	}

	/*
	 * Method to calculate the Cosine Distance can be calculated by computing the
	 * dot product of the two vectors and then dividing this number by product of
	 * the magnitudes The method will return a value between 1, max similarity and 0
	 * max dissimilar.
	 */
	private Double computeCosineSimilarity(Double vect_A[], Double vect_B[]) {

		double dotProduct = calculateDotProduct(vect_A, vect_B);
		double magnitude = calculateMagnitude(vect_A, vect_B);
		return dotProduct / magnitude; // Return the cosine distance.

	}

	private Double[] calculateCosineDistances(Double[] inputWordVector, Double[][] vectorArray) {

		Double[] cosineDistanceArray = new Double[vectorArray.length];

		for (int i = 0; i < vectorArray.length; i++) {

			cosineDistanceArray[i] = computeCosineSimilarity(inputWordVector, vectorArray[i]);
		}

		return cosineDistanceArray;
	}

	
	
  
	public Double[] computeCosineDistances() throws IOException {

		String word = io.getInputWord(); // Word from user input.
		String fpath = io.getFilepath(); // Word Embedding file path.

		// Get word array and vector array from the utility class
		String[] wordArray = utility.embeddingWordsArray(fpath);
		Double[][] vectorArray = utility.embeddingVectorArray(fpath);

		// Select the index of user word input in the word array.
		int wordIndex = inputWordIndex(word, wordArray);

		// Select the index of user word input in the word array.
		Double[] inputWordVector = inputWordVector(wordIndex, vectorArray);

		return calculateCosineDistances(inputWordVector, vectorArray);

	}

	// Find perform binary sort to organize the data in descending order. 
	// provided file. Return an array with the top 10 similar words
}
