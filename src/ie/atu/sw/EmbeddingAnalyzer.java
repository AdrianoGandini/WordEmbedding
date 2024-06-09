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

	
	public String[] compereVectors(String[] wordArray, Double[] embeddingVector) {

		// TODO Method to compare the inputed word vector with all words vectors in the
		// provided file. Return an array with the top 10 similar words

		return null;
	}
	
	
	//Method created to test calculateCosineDistance 
	public void test() {
		
		double[] C = {
	            0.33614, 0.35956, 0.97398, -0.11536, 0.26715, 0.52664, -0.28079, -0.15934, -0.018939, -0.33067,
	            0.10573, -0.53051, 0.031862, -0.080079, 0.82261, -0.7917, -0.10958, -0.46814, -0.39095, -0.64602,
	            -0.021653, -0.76659, 0.79564, 0.59753, -0.12686, -0.69666, -0.88445, -1.0733, -0.3196, 0.12422,
	            4.0137, 0.14167, 0.79684, -0.4697, 0.70954, 0.15874, 0.64932, -0.014985, -0.67939, 0.29073,
	            -0.2803, -0.096077, 0.75959, 0.44819, -0.05046, -0.19157, -0.42044, 0.080226, -0.23491, 0.0
	        };

	        double[] D = {
	            -1.0468, 1.2666, -0.71321, -0.43586, 0.2787, 0.19846, 0.57062, -1.1086, -0.90585, 0.12501,
	            0.75718, 0.14679, -0.23584, -0.49588, -0.28049, -0.298, 0.12263, 1.009, -0.099603, 0.27757,
	            0.82543, 0.0031212, 0.11741, 0.33034, 0.64692, -0.64651, -0.56474, -1.1533, -1.0423, 0.26482,
	            2.0228, -0.62681, -0.37698, -1.7588, 0.53444, 0.11018, -0.291, 1.84, 0.73648, 0.98161,
	            0.3366, -0.75723, 0.087569, 1.214, -0.76718, -0.17217, 1.5288, 0.79911, -0.049526, 0.0
	        };

		
		
			System.out.println(calculateCosineDistance(C, D));
			
		
	}
	
}
