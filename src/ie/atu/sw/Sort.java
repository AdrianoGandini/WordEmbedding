package ie.atu.sw;

public class Sort {

	public CosineDistance[] bubbleSort(CosineDistance[] scoreArray) {

		/*
		 * Bubble sort
		 * https://stackoverflow.com/questions/11644858/bubblesort-implementation
		 */

		for (int i = 0; i < scoreArray.length; i++) {
			for (int j = 0; j < scoreArray.length - i - 1; j++) {
				if (scoreArray[j].cosineDistance() < scoreArray[j + 1].cosineDistance()) {
					CosineDistance temp = scoreArray[j];
					scoreArray[j] = scoreArray[j + 1];
					scoreArray[j + 1] = temp;
				}
			}
		}
		return scoreArray;
	}
	
	/*
	 * Merge Sort
	 * https://www.programiz.com/dsa/merge-sort
	 * https://stackoverflow.com/questions/13727030/mergesort-in-java
	 */

	public CosineDistance[] mergeSort(CosineDistance[] scoreArray) {
		if (scoreArray.length <= 1) {
			return scoreArray;
		}

		int mid = scoreArray.length / 2;
		CosineDistance[] left = new CosineDistance[mid];
		CosineDistance[] right = new CosineDistance[scoreArray.length - mid];

		System.arraycopy(scoreArray, 0, left, 0, mid);
		System.arraycopy(scoreArray, mid, right, 0, scoreArray.length - mid);

		return merge(mergeSort(left), mergeSort(right));
	}

	private CosineDistance[] merge(CosineDistance[] left, CosineDistance[] right) {
		CosineDistance[] result = new CosineDistance[left.length + right.length];
		int i = 0, j = 0, k = 0;

		while (i < left.length && j < right.length) {
			if (left[i].cosineDistance() >= right[j].cosineDistance()) {
				result[k++] = left[i++];
			} else {
				result[k++] = right[j++];
			}
		}

		while (i < left.length) {
			result[k++] = left[i++];
		}

		while (j < right.length) {
			result[k++] = right[j++];
		}

		return result;
	}
}
