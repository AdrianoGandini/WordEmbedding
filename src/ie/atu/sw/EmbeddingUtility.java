package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileReader;

/*
 * Class to hold all the logic used to work with the embedded file
 */

public class EmbeddingUtility {

	/*
	 * The method reads the file and add to a counter the number of lines.
	 * 
	 */

	public int arraySize() {

		int lineCounter = 0;

		// Change the text file hard code in the future.
		try (BufferedReader in = new BufferedReader(new FileReader("word-embeddings.txt"))) {


			while (in.readLine() != null) {
				lineCounter++;
			}
			in.close();

		} catch (Exception e) {
			System.err.println("[ERROR] Embeddings file not found");
		}

		return lineCounter;

	}

	public String[] embeddingWordsArray() {

		return null;
	}

	public String[] embeddingVectorsArray() {

		// TODO Create a function to read the file and copy the vectors into a array.

		return null;
	}

	public String[] compereVectors() {

		// TODO Method to compare the inputed word vector with all words vectors in the
		// provided file. Return an array with the top 10 similar words

		return null;
	}
}
