package ie.atu.sw;

public class Test {

	
	public static void main(String[] args) {
		
		EmbeddingUtility test = new EmbeddingUtility();
		
		int numberRows = test.arraySize(true);
		int numberColumns = test.arraySize(false);
		
		System.out.println(numberRows);
		System.out.println(numberColumns);
		
		
		
	}
	
}
