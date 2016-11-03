import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sorts {

	private int countI;
	private int countM;
	private static int countQ;

	public static void main(String[] args) {
		int nrLists = 100;
		int lengthOfList = 100000;
		int randomRange = 10000;
		List<int[]> listInsertion = getRandomLists(nrLists, lengthOfList, randomRange);
		List<int[]> listQuick = getRandomLists(nrLists, lengthOfList, randomRange);
		List<int[]> listMerge = getRandomLists(nrLists, lengthOfList, randomRange);

		long startTime = System.nanoTime();
		for (int[] i : listInsertion)
			insertionSort(i);
		long stopTime = System.nanoTime();
		long sumTime = stopTime - startTime;
		long averageTime = sumTime / nrLists;
		System.out.println(nrLists + " Lists with " + lengthOfList + " random numbers between 0 and " + randomRange
				+ " sorted with InsertionSort in " + sumTime + " nanoseconds. Each sort took in average " + averageTime
				+ " nanoseconds.");

		startTime = System.nanoTime();
		for (int[] i : listQuick)
			quickSort(i, 0, i.length - 1);
		stopTime = System.nanoTime();
		sumTime = stopTime - startTime;
		averageTime = sumTime / nrLists;
		System.out.println(nrLists + " Lists with " + lengthOfList + " random numbers between 0 and " + randomRange
				+ " sorted with Quicksort in " + sumTime + " nanoseconds. Each sort took in average " + averageTime
				+ " nanoseconds.");

		startTime = System.nanoTime();
		for (int[] i : listMerge)
			mergeSort(i, 0, i.length - 1);
		stopTime = System.nanoTime();
		sumTime = stopTime - startTime;
		averageTime = sumTime / nrLists;
		System.out.println(nrLists + " Lists with " + lengthOfList + " random numbers between 0 and " + randomRange
				+ " sorted with MergeSort in " + sumTime + " nanoseconds. Each sort took in average " + averageTime
				+ " nanoseconds.");

	}

	private static List<int[]> getRandomLists(int nrLists, int lengthOfList, int randomRange) {
		Random rand = new Random();
		List<int[]> listOfLists = new ArrayList<int[]>();
		for (int i = 0; i < nrLists; i++) {
			listOfLists.add(new int[lengthOfList]);
		}
		for (int i = 0; i < nrLists; i++) {
			for (int j = 0; j < listOfLists.get(i).length; j++) {
				listOfLists.get(i)[j] = rand.nextInt(randomRange);
			}
		}
		return listOfLists;
	}

	public static void insertionSort(int[] A) {
		int i, key;
		for (int j = 1; j < A.length; j++) {
			key = A[j];
			i = j - 1;
			while (i >= 0 && A[i] > key) {
				A[i + 1] = A[i];
				i--;
			}
			A[i + 1] = key;
		}
	}

	public static void merge(int[] A, int al, int ar, int[] B, int bl, int br, int[] C) {
		int i = al, j = bl;
		for (int k = 0; k <= ar - al + br - bl + 1; k++) {
			if (i > ar) {
				C[k] = B[j++];
				continue;
			}
			if (j > br) {
				C[k] = A[i++];
				continue;
			}
			C[k] = (A[i] < B[j]) ? A[i++] : B[j++];
		}
	}

	public static void mergeSort(int[] A, int al, int ar) {
		if (ar > al) {
			int m = (ar + al) / 2;
			mergeSort(A, al, m);
			mergeSort(A, m + 1, ar);
			int[] B = new int[ar - al + 1];
			merge(A, al, m, A, m + 1, ar, B);
			for (int i = 0; i < ar - al + 1; i++)
				A[al + i] = B[i];
		}
	}

	public static void quickSort(int[] A, int al, int ar) {
		if (al < ar) {
			int pivot = A[al], i = al, j = ar + 1;
			while (true) {
				while (A[++i] < pivot && i < ar) {
				}
				while (A[--j] > pivot && j > al) {
				}
				if (i < j)
					swap(A, i, j);
				else
					break;
			}
			swap(A, j, al);

			quickSort(A, al, j - 1);
			quickSort(A, j + 1, ar);
		}
	}

	public static void swap(int[] A, int i, int j) {
		int t = A[i];
		A[i] = A[j];
		A[j] = t;
	}
}