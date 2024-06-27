package ie.atu.sw;

import java.io.IOException;

/*
 * This class holds methods to analyze the word embedding file. 
 */

public class EmbeddingAnalyzer {

	private FileReaderUtility utility;
	private Sort sort;

	public EmbeddingAnalyzer(FileReaderUtility utility, Sort sort) {
		this.utility = utility;
		this.sort = sort;
	}

	/**
	 * Method to return the array index of the user inputed word.
	 * 
	 * @param word user inputed word
	 * @param word array
	 * @return user word index on word array
	 */
	private int getWordIndex(String word, String[] wordArray) {

		for (int i = 0; i < wordArray.length; i++) {
			if (word.equals(wordArray[i])) {
				return i;
			}
		}
		System.out.println("The word: " + word + " is not present.");
		return -1;
	}

	/**
	 * The method return the vector related to the user input word.
	 * 
	 * @param wordIndex       user inputed word index
	 * @param embeddingVector Embedding word vector two dimensional array
	 * @return The input word vector array
	 */
	private Double[] getWordVector(int wordIndex, Double[][] embeddingVector) {

		// Array to hold the input word vector.
		Double[] inputWordVector = new Double[embeddingVector[0].length];

		for (int i = 0; i < embeddingVector[0].length; i++) {
			inputWordVector[i] = embeddingVector[wordIndex][i];
		}
		return inputWordVector;
	}

	/**
	 * Method to perform the dot product calculation of two vectors.
	 *
	 * @param The     first vector as an array of Double.
	 * @param vectorB The second vector as an array of Double.
	 * @return The dot product of vectorA and vectorB. font =
	 *         https://www.geeksforgeeks.org/program-dot-product-cross-product-two-vector/
	 */
	private Double calculateDotProduct(Double vectorA[], Double vectorB[]) {

		double product = 0;

		for (int i = 0; i < vectorA.length; i++) {
			product = product + (vectorA[i] * vectorB[i]);
		}
		return product;
	}

	/**
	 * Method to calculate the magnitude between two vectors.
	 * 
	 * @param vectorA The first vector as an array of Double.
	 * @param vectorB The second vector as an array of Double.
	 * @return The the root sum of the squares of two vectors
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

	/**
	 * Method to calculate the Cosine Distance by computing the dot product of the
	 * two vectors and then dividing this number by product of the magnitudes.
	 * Return a value between 1, max similarity and 0.
	 * 
	 * @param vectorA The first vector as an array of Double.
	 * @param vectorB The second vector as an array of Double.
	 * @return the cosine similarity.
	 */
	private Double computeCosineSimilarity(Double vectorA[], Double vectorB[]) {

		double dotProduct = calculateDotProduct(vectorA, vectorB);
		double magnitude = calculateMagnitude(vectorA, vectorB);
		return dotProduct / magnitude; // Return the cosine distance.

	}
	
	
	/**
	 * The method calculate the cosine distance between a user input word vector and
	 * all the word vectors in the Embedded word file.
	 * 
	 * @param inputWordVector user input word vector double array.
	 * @param vectorArray two dimensional array with the file words vectors.
	 * @return A CosineDistance array that carries the Cosine Similarity between the
	 *         input word and each word in the file and a word index.
	 */
	private CosineDistance[] calculateCosineDistances(Double[] inputWordVector, Double[][] vectorArray) {

		CosineDistance[] cosine = new CosineDistance[vectorArray.length];

		for (int i = 0; i < vectorArray.length; i++) {

			// CosineDistance integer i is used as a word index tracker.
			CosineDistance s = new CosineDistance(computeCosineSimilarity(inputWordVector, vectorArray[i]), i);
			cosine[i] = s;
		}
		return cosine;
	}

	/**
	 * Method to compute the cosine distances between a user input word and all words in the embedding file.
	 */
	private CosineDistance[] computeCosineDistances(String word, String[] wordArray,
			Double[][] vectorArray) throws IOException {

		// Select the index of user word input in the word array.
		int wordIndex = getWordIndex(word, wordArray);
		
		// Select the word vector array based on the wordIdex.
		Double[] inputWordVector = getWordVector(wordIndex, vectorArray);

		return calculateCosineDistances(inputWordVector, vectorArray);

	}


	/**
	 * Method to sort a CosineDistance array in descending order by the cosine
	 * similarity.
	 * 
	 * @param unsortedArray The unsorted array of CosineDistance objects.
	 * @return A sorted array of CosineDistance objects in descending order by cosine similarity.
	 */
	private CosineDistance[] sortCosineDistances(CosineDistance[] unsortedArray) {
		return sort.mergeSort(unsortedArray);
	}

	/**
	 * The method return a sorted CosineDistance array, after compute the cosine
	 * distances between the inputed word an all the embedding words in the file.
	 * 
	 * @param user input word
	 * @param embedding word array file path
	 * @return CosineDistance array with sorted cosine distances
	 */
	public CosineDistance[] processCosineDistances(String word, String fpath) throws IOException {

		// Get word array and vector array from the utility class
		String[] wordArray = utility.embeddingWordsArray(fpath);
		Double[][] vectorArray = utility.embeddingVectorArray(fpath);

		System.out.println("Running cosine distances...");
		System.out.println("Processing word: " + word);
		CosineDistance[] cosineDistancesArray = computeCosineDistances(word, wordArray, vectorArray);

		if (cosineDistancesArray.length == 0) {
			System.out.println("No cosine distances calculated for word: " + word);
		}
		return sortCosineDistances(cosineDistancesArray);
	}
}
