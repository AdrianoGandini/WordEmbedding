package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * Class to hold all the logic used to work with the embedded file
 */

public class FileReaderUtility {
	
	private WordEmbeddingIO in;
	
	
	// Constructor
	public FileReaderUtility() {
		this.in = new WordEmbeddingIO();
	}
	
	//Method to check if the embedding file path was provided.
	public String isFilePathProvided(String fpath) {
		String embeddingFilePath = fpath;
		
		if(fpath == null) {
			embeddingFilePath = in.getFilePath();
		}
		return embeddingFilePath;
		
	}


	/*
	 * Method to return the number of rows in the WordEmbedding file.
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
	
	/*
	 * Method to return the number of columns in the WordEmbedding file.
	 */
	private int getNumberOfColumns(String fpath) throws FileNotFoundException, IOException {
		try (BufferedReader in = new BufferedReader(new FileReader(fpath))) {
			return in.readLine().split(",").length - 1;
		}
				
	}

	/*
	 * The method creates an array containing the embedding words.
	 * 
	 * @param size the variable defines the word array size.
	 */

	private String[] embeddingWordsArray(int size) throws IOException {

		String[] wordArray = new String[size];

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

	/*
	 * The method creates an two dimensional array that contains the vectors
	 * 
	 *  @param rows number of rows in the array
	 *  @param columns number of columns in the array. 
	 */
	public Double[][] embeddingVectorArray(int rows, int columns) throws FileNotFoundException, IOException {
		
		Double[][] vector = new Double[rows][columns]; // Two dimensional array to hold the vectors.
		
		BufferedReader in = new BufferedReader(new FileReader(fpath));
		
		String line; // Current BufferReader line 
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

	/*
	 * Method to return the processed data
	 * TODO
	 */
	public String[] outputArray() {
		
	     // Return an empty array to avoid null pointer exceptions
	        return new String[0];    
	}
}
