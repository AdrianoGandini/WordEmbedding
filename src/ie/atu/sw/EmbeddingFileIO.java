package ie.atu.sw;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EmbeddingFileIO {


	/*
	 * Writes the contents of a specified array to a file. Each element of the array is written in a text file adding ", " to improve readability. 
	 * 
	 * @param words array of String
	 * @param fileName file name provided by user
	 * @throws IOException if an I/O error occurs while writing to the file
	 */

	public void fileWriter(String[] words, String fileName) throws IOException {

		BufferedWriter in = new BufferedWriter(new FileWriter(fileName));

		for (int i = 0; i < words.length; i++) {
			in.write(words[i]);
			
			if (i < words.length - 1) {
				in.write(", ");
				
			}
		}
		
		in.flush();
		in.close();

	}

	

}
