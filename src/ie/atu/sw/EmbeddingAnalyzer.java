package ie.atu.sw;

import java.io.IOException;

public class EmbeddingAnalyzer {

	private WordEmbeddingIO io;
	private FileReaderUtility utility;
	private Sort sort;

	public EmbeddingAnalyzer(WordEmbeddingIO io, FileReaderUtility utility, Sort sort) {
		this.utility = utility;
		this.io = io;
		this.sort = sort;
	}

	// TODO Create Regular Expression for the method split. Now it is just
	// identifying just the spaces. .
	// Method to handle the user word/ words input.
	private String[] getInputWordsArray() {
		return io.getInputWord().split(" ");
	}

	// Method to return the array index of the user inputed word.
	private int getWordIndex(String word, String[] wordArray) {

		for (int i = 0; i < wordArray.length; i++) {
			if (word.equals(wordArray[i])) {
				return i;
			}
		}
		System.out.println("The word: " + word + " is not present in the > " + io.getFilepath() + " < file.");
		return -1;
	}

	// Comment
	private Double[] getWordVector(int wordIndex, Double[][] embeddingVector) {

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

	private CosineDistance[] calculateCosineDistances(Double[] inputWordVector, Double[][] vectorArray) {

		CosineDistance[] cosine = new CosineDistance[vectorArray.length];

		for (int i = 0; i < vectorArray.length; i++) {
			CosineDistance s = new CosineDistance(computeCosineSimilarity(inputWordVector, vectorArray[i]), i);
			cosine[i] = s;
		}
		return cosine;
	}

	// TODO Comment
	private CosineDistance[] computeCosineDistances(String word, String fpath) throws IOException {

		// Get word array and vector array from the utility class
		String[] wordArray = utility.embeddingWordsArray(fpath); // TODO Maybe use wordArray as argument as it is needed
																	// again in runCosineDistances, doesn't looks like
																	// good have to run the method twice.
		Double[][] vectorArray = utility.embeddingVectorArray(fpath);

		// Select the index of user word input in the word array.
		int wordIndex = getWordIndex(word, wordArray);

		// Select the word vector array based on the wordIdex.
		Double[] inputWordVector = getWordVector(wordIndex, vectorArray);

		return calculateCosineDistances(inputWordVector, vectorArray);

	}

	// Sort the Cosine Distance processed array.
	private CosineDistance[] sortCosineDistances(CosineDistance[] unsortedArray) {
		return sort.mergeSort(unsortedArray);
	}

	// Print the first 10 words in the sorted array.
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
	}

	// The method return a sorted Cosine Distance Array.
	public CosineDistance[] processCosineDistances(String word, String fpath) throws IOException {

		System.out.println("Running cosine distances...");
		System.out.println("Processing word: " + word);
		CosineDistance[] cosineDistancesArray = computeCosineDistances(word, fpath);

		if (cosineDistancesArray.length == 0) {
			System.out.println("No cosine distances calculated for word: " + word);
		}
		return sortCosineDistances(cosineDistancesArray);
	}

	public void printWords(boolean detail) throws IOException {
		
		String[] inputWords = getInputWordsArray();
		String fpath = io.getFilepath();
		String[] wordArray = utility.embeddingWordsArray(fpath);

		for (String word : inputWords) {
			CosineDistance[] cosieDistances = processCosineDistances(word, fpath);
			if (detail) {
				printDetailCloseWords(cosieDistances, wordArray, 10);
			} else {
				printCloseWords(cosieDistances, wordArray, 10);
			}
		}
		System.out.println();
	}	
}