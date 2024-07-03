package ie.atu.sw;

public class Sort {
	
	
	/*
	 * Sorts an array of Cosine Distances using the Bubble Sort algorithm.
	 * 
	 * https://stackoverflow.com/questions/11644858/bubblesort-implementation
	 * 
	 * The Bubble sort was implemented during the first application implementation.It is not used in the application anymore as it is slow (O(n^2)) compared to Merge Sort (O(n log n)).
	 */
	public CosineDistance[] bubbleSort(CosineDistance[] scoreArray) {

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
	
	/**
	 * Sorts an array of Cosine Distances using Merge Sort algorithm.
	 * 
	 * https://www.programiz.com/dsa/merge-sort
	 * https://stackoverflow.com/questions/13727030/mergesort-in-java
	 * 
	 * @param scoreArray unsorted CosineDistances array.
	 * @return a sorted array of CosineDistance objects, sorted by cosine distances.
	 */

	public CosineDistance[] mergeSort(CosineDistance[] scoreArray) {
		
		// Base condition to return arrays with one element.
		if (scoreArray.length <= 1) {
			return scoreArray;
		}

		int mid = scoreArray.length / 2;
		
		//Creating two arrays to divide the initial array in two parts, left and right.
		CosineDistance[] left = new CosineDistance[mid];
		CosineDistance[] right = new CosineDistance[scoreArray.length - mid];
		
		//Coping the items for the left and right arrays.
		System.arraycopy(scoreArray, 0, left, 0, mid);
		System.arraycopy(scoreArray, mid, right, 0, scoreArray.length - mid);
		
		//Recursively call mergeSort() for the left and right arrays.
		return merge(mergeSort(left), mergeSort(right));
	}

	/**
	 * Merge two arrays sorting the cosine distance in decreasing order.
	 * 
	 * @param left the left sub-array.
	 * @param right the right sub-array.
	 * @return a merged and sorted array of CosineDistance objects
	 */
	private CosineDistance[] merge(CosineDistance[] left, CosineDistance[] right) {
		CosineDistance[] result = new CosineDistance[left.length + right.length];
		
		//Variables to keep the track of the current indices in the left, right and result arrays. 
		int i = 0, j = 0, k = 0;
		
		// Merge the arrays while there are elements in both left and right.
		while (i < left.length && j < right.length) {
			if (left[i].cosineDistance() >= right[j].cosineDistance()) {
				result[k++] = left[i++];
			} else {
				result[k++] = right[j++];
			}
		}
		
		//Copy any remaining element of the left array.
		while (i < left.length) {
			result[k++] = left[i++];
		}
		
		//Copy any remaining element of the right array.
		while (j < right.length) {
			result[k++] = right[j++];
		}

		return result;
	}
}
