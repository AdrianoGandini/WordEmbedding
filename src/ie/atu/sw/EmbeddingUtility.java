package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * Class to hold all the logic used to work with the embedded file
 */

public class EmbeddingUtility {

	private static final String FILE_PATH = "word-embeddings.txt";

	// Constructor
	public EmbeddingUtility() {

	}

	/*
	 * The method reads the file and return the number of rows or columns.
	 * 
	 * @param checkColumns boolean expression to determine if the method should
	 * return the number of columns or rows. If true, the method returns the number
	 * of columns in the first row. If false, the method returns the number of rows
	 * in the file.
	 */

	public int arraySize(boolean checkColumns) {

		int rowsCounter = 0; // Variable to hold the number of rows in the file.

		try (BufferedReader in = new BufferedReader(new FileReader(FILE_PATH))) {

			if (checkColumns)
				return in.readLine().split(",").length - 1; // Return the number of columns in a line.

			while (in.readLine() != null) {
				rowsCounter++;
			}
			in.close();
		} catch (Exception e) {
			System.err.println("[ERROR] Embeddings file not found");
		}
		return rowsCounter;
	}

	/*
	 * The method creates an array containing the embedding words.
	 * 
	 * @param size the variable defines the word array size.
	 */

	public String[] embeddingWordsArray(int size) throws IOException {

		String[] wordArray = new String[size];

		BufferedReader in = new BufferedReader(new FileReader(FILE_PATH));

		int counter = 0;

		String next;

		while ((next = in.readLine()) != null) {
			String[] line = next.split(",");
			wordArray[counter] = line[0]; // The vector word is in the index 0
			counter++;
		}
		in.close();
		return wordArray;

	}

	// Two dimensional array - The method is returning a bunch of nulls :(.
	// TODO Create a function to read the file and copy the vectors into a array.
	public Double[][] embeddingVectorArray(int rows, int columns) throws FileNotFoundException, IOException {
		
		Double[][] vector = new Double[rows][columns]; // Two dimensional array to hold the vectors.
		
		BufferedReader in = new BufferedReader(new FileReader(FILE_PATH));
		
		String line;
		int row = 0; // Variable to keep track of the current row

		while ((line = in.readLine()) != null) {
			String[] embeddingLine = line.split(",");
			
			for (int j = 0; j < columns; j++) {
				vector[row][j] = Double.parseDouble(embeddingLine[j + 1]);
			}
			
			row++;
		}
		
		in.close();
		return vector;
	}

	public String[] compereVectors() {

		// TODO Method to compare the inputed word vector with all words vectors in the
		// provided file. Return an array with the top 10 similar words

		return null;
	}
}
