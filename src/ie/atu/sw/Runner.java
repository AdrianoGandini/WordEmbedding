package ie.atu.sw;

public class Runner {
	
	
	public static void main(String[] args) {
		EmbeddingUtility test = new EmbeddingUtility();
	
		System.out.println(test.arraySize());
		
		Menu menu = new Menu();
		menu.start();
	}
}
