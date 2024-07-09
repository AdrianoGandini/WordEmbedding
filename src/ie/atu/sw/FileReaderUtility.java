package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * Class to hold all the logic used to work with the embedded file.
 */

public class FileReaderUtility {
	
	
	/**
	 * Method to return the number of rows in the WordEmbedding file.
	 * 
	 * @param fpath The file path of the WordEmbedding file.
	 * @return The number of rows in the file.
	 * @throws FileNotFoundException If the file is not found.
	 * @throws IOException If an I/O error occurs.
	 */
	private int getNumberOfRows(String fpath) throws FileNotFoundException, IOException {

		int rowsCounter = 0; // Variable to hold the number of rows in the file.

		try (BufferedReader in = new BufferedReader(new FileReader(fpath))) {
			while (in.readLine() != null) {
				rowsCounter++;
			}
			in.close();
		}
		return rowsCounter;
	}

	/**
	 * Method to return the number of columns in the WordEmbedding file.
	 * 
	 * @param fpath The file path of the WordEmbedding file.
	 * @return The number of columns in the file.
	 * @throws FileNotFoundException If the file is not found.
	 * @throws IOException If an I/O error occurs.
	 */
	private int getNumberOfColumns(String fpath) throws FileNotFoundException, IOException {
		try (BufferedReader in = new BufferedReader(new FileReader(fpath))) {
			return in.readLine().split(",").length - 1; //The -1 represents the first word column 
		}
	}

	/**
	 * The method creates an array containing the embedding words.
	 * 
	 * @param fpath The file path of the WordEmbedding file.
	 * @return An String array of embedding words.
	 * @throws IOException If an I/O error occurs.
	 */
	public String[] embeddingWordsArray(String fpath) throws IOException {

		String[] wordArray = new String[getNumberOfRows(fpath)];
		BufferedReader in = new BufferedReader(new FileReader(fpath));

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

	/**
	 * The method creates a two-dimensional array that contains the vectors.
	 * 
	 * @param fpath The file path of the WordEmbedding file.
	 * @return A two-dimensional array of embedding vectors.
	 * @throws FileNotFoundException If the file is not found.
	 * @throws IOException If an I/O error occurs.
	 */
	public Double[][] embeddingVectorArray(String fpath) throws FileNotFoundException, IOException {

		int rows = getNumberOfRows(fpath);
		int columns = getNumberOfColumns(fpath);

		Double[][] vector = new Double[rows][columns]; // Two dimensional array to hold the vectors.

		BufferedReader in = new BufferedReader(new FileReader(fpath));

		String line; // Current BufferReader line
		int row = 0; // Variable to keep track of the current row

		while ((line = in.readLine()) != null) {
			String[] embeddingLine = line.split(",");

			for (int j = 0; j < columns; j++) {
				vector[row][j] = Double.parseDouble(embeddingLine[j + 1]); //Is the number one here to jump the first column, that contains the words?
			}
			row++;
		}
		in.close();
		return vector;
	}
}
