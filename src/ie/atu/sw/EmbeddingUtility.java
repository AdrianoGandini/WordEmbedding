package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 * Class to hold all the logic used to work with the embedded file
 */

public class EmbeddingUtility {

	/*
	 * The method reads the file and add to a counter the number of lines.
	 * 
	 * TODO Observe what variables in the below methods should be class variables.
	 */

	public int arraySize() {

		int lineCounter = 0;

		// TODO Change the text file hard code in the future.
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

	/*
	 * The method creates an array containing the embedding words.
	 * 
	 * @param size the variable defines the word array size. 
	 */
	public String[] embeddingWordsArray(int size) throws IOException {
		
		String[] wordArray = new String[size];
		
		
			BufferedReader in = new BufferedReader(new FileReader("word-embeddings.txt"));
			
			int counter = 0;
			
			String next;
			
			while((next = in.readLine()) != null) {
				String[] line = next.split(",");
				wordArray[counter] = line[0]; // The vector word is in the index 0
				counter ++;
			}
			in.close();
			
		return wordArray;
	
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
