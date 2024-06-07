package ie.atu.sw;

public class EmbeddingCalculator {

	FileReaderUtility file;
	WordEmbeddingIO in;
	
	public EmbeddingCalculator() {
		this.file = new FileReaderUtility();
		this.in = new WordEmbeddingIO();
	}
	
	
	
	public int inputWordIndex() {
		// TODO Method to return the array index of the user inputed word.
		return 0;
	}

	public int dotCalculation() {
		// TODO Method to perform the dot calculation. Product of two vectors.
		return 0;
	}

	public void euclideanDistance() {
		// TODO Method to calculate the Euclidean Distance.
	}

	public String[] compereVectors(String[] wordArray, Double[] embeddingVector) {

		// TODO Method to compare the inputed word vector with all words vectors in the
		// provided file. Return an array with the top 10 similar words

		return null;
	}

}
