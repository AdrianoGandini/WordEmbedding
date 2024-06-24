package ie.atu.sw;

public class Configuration {

	private String filePath = "./word-embeddings.txt";
	private String inputWord;
	private String outputFileName = "SimilarWordsOut.txt";

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getInputWord() {
		return inputWord;
	}

	public void setInputWord(String inputWord) {
		this.inputWord = inputWord;
	}

	public String getOutputFileName() {
		return outputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

}
