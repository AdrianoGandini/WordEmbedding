package ie.atu.sw;

import java.io.IOException;

public class EmbeddingAnalyzer {

	private WordEmbeddingIO io;
	private FileReaderUtility utility;

	public EmbeddingAnalyzer(WordEmbeddingIO io, FileReaderUtility utility) {
		this.utility = utility;
		this.io = io;
	}

	// TODO part of take multiple words refactoring process.
	// Method to handle the user word/ words input.
	public String[] wordInputArray() {
		return io.getInputWord().split(" ");
	}

	// Method to return the array index of the user inputed word.
	private int inputWordIndex(String word, String[] wordArray) {

		for (int i = 0; i < wordArray.length; i++) {
			if (word.equals(wordArray[i])) {
				return i;
			}
		}

		System.out.println("The word: " + word + " is not present in the > " + io.getFilepath() + " < file.");
		return -1;
	}

	// Comment
	public Double[] inputWordVector(int wordIndex, Double[][] embeddingVector) {

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

	public CosineDistance[] calculateCosineDistances(Double[] inputWordVector, Double[][] vectorArray) {

		CosineDistance[] cosine = new CosineDistance[vectorArray.length];

		for (int i = 0; i < vectorArray.length; i++) {

			CosineDistance s = new CosineDistance(computeCosineSimilarity(inputWordVector, vectorArray[i]), i);
			cosine[i] = s;
		}
		return cosine;
	}

	public CosineDistance[] computeCosineDistances(String word, String fpath) throws IOException {

		// String word = io.getInputWord(); // Word from user input.
		// String fpath = io.getFilepath(); // Word Embedding file path.

		// Get word array and vector array from the utility class
		String[] wordArray = utility.embeddingWordsArray(fpath);
		Double[][] vectorArray = utility.embeddingVectorArray(fpath);

		// Select the index of user word input in the word array.
		int wordIndex = inputWordIndex(word, wordArray);

		// Select the index of user word input in the word array.
		Double[] inputWordVector = inputWordVector(wordIndex, vectorArray);

		return calculateCosineDistances(inputWordVector, vectorArray);

	}

	public CosineDistance[] bubbleSort(CosineDistance[] scoreArray) {

		// Bubble sort

		for (int i = 0; i < scoreArray.length; i++) {
			for (int j = 0; j < scoreArray.length - i - 1; j++) {
				if (scoreArray[j].cosineDistance() < scoreArray[j + 1].cosineDistance()) {
					CosineDistance temp = scoreArray[j];
					scoreArray[j] = scoreArray[j + 1];
					scoreArray[j + 1] = temp;
				}
			}
		}
		return scoreArray;
	}
	// Find perform Bubble sort to organize the data in descending order.
	// provided file. Return an array with the top 10 similar words

	public CosineDistance[] mergeSort(CosineDistance[] scoreArray) {
		if (scoreArray.length <= 1) {
			return scoreArray;
		}

		int mid = scoreArray.length / 2;
		CosineDistance[] left = new CosineDistance[mid];
		CosineDistance[] right = new CosineDistance[scoreArray.length - mid];

		System.arraycopy(scoreArray, 0, left, 0, mid);
		System.arraycopy(scoreArray, mid, right, 0, scoreArray.length - mid);

		return merge(mergeSort(left), mergeSort(right));
	}

	private CosineDistance[] merge(CosineDistance[] left, CosineDistance[] right) {
		CosineDistance[] result = new CosineDistance[left.length + right.length];
		int i = 0, j = 0, k = 0;

		while (i < left.length && j < right.length) {
			if (left[i].cosineDistance() >= right[j].cosineDistance()) {
				result[k++] = left[i++];
			} else {
				result[k++] = right[j++];
			}
		}

		while (i < left.length) {
			result[k++] = left[i++];
		}

		while (j < right.length) {
			result[k++] = right[j++];
		}

		return result;
	}

	public void test() {

		String[] myInputWordArray = wordInputArray();

		String filePath = io.getFilepath();

		for (int i = 0; i < myInputWordArray.length; i++) {

			try {
				String[] wordArray = utility.embeddingWordsArray(filePath);
				CosineDistance[] myTestArray = computeCosineDistances(myInputWordArray[i], filePath);

				CosineDistance[] myTestArraySorted = bubbleSort(myTestArray);
				// CosineDistance[] myTestArraySorted = mergeSort(myTestArray);

				for (int j = 0; j < 10; j++) {
					CosineDistance cosine = myTestArraySorted[j];
					System.out.println("Position: " + j + "; cosine: " + cosine.cosineDistance() + " index: "
							+ cosine.index() + " word: " + wordArray[cosine.index()]);
				}
				
				System.out.println();
				System.out.println("****************************************");
				System.out.println();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
