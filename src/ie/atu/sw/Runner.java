package ie.atu.sw;

import java.util.Scanner;

/*
 * Class responsible for run the application.
 */

public class Runner {

	public static void main(String[] args) {
		
		//Initializing dependencies
		Configuration config = new Configuration();
		FileReaderUtility file = new FileReaderUtility();
		Sort sort = new Sort();
		Scanner s = new Scanner(System.in);
		EmbeddingAnalyzer analyzer = new EmbeddingAnalyzer(file, sort);
		WordEmbeddingIO io = new WordEmbeddingIO(config, analyzer, file);
		
		//Create a menu instance and pass all dependencies
		Menu menu = new Menu(io, config, file, analyzer, sort, s);
		
		menu.start();
	}
}
