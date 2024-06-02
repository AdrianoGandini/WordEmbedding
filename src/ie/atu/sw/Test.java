package ie.atu.sw;

import java.io.IOException;

public class Test {

	public static void main(String[] args) {

		
		EmbeddingUtility test = new EmbeddingUtility();

		int numberRows = test.arraySize(false);
		int numberColumns = test.arraySize(true);

		System.out.println("Number of rows: " + numberRows);
		System.out.println("Number of Columns: " +numberColumns);

		Double[][] vectors = null;

		try {
			vectors = test.embeddingVectorArray(numberRows, numberColumns);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (vectors != null) {
			for (int i = 0; i < vectors.length; i++) {
				for (int j = 0; j < vectors[i].length; j++) {
					System.out.print(vectors[i][j] + " ");
					
				}
				System.out.println();
			}
		}
	
	
	}
}
