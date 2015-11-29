package homeworkFour;
/**
 * Patrick Wamsley 
 * 110539155
 * CSE 214 (2) 
 * TA: Mingchen Zhang
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Class which will run a quicksort alg.
 * 
 * @author Patrick Wamsley
 */
public class Quicksorter<E> implements Sorter<E> {

	private Comparator<E> currentComparator; 
	private ArrayList<E> data; 

	public Quicksorter(Comparator<E> comparator, ArrayList<E> data) {
		this.currentComparator = comparator; 
		this.data = data;
	}

	@Override
	public void sort() {
		assert data != null && data.size() > 0: "empty list can't be sorted.";
		helperQuickSort(0, data.size() - 1);
	}

	private void helperQuickSort(int leftOfPivot, int rightOfPivot) {

		if (leftOfPivot >= rightOfPivot)
			return; 

		// Use last item as pivot point. 
		int partitionIndex = partition(leftOfPivot, rightOfPivot, data.get(rightOfPivot)); 

		helperQuickSort(0, partitionIndex - 1);
		helperQuickSort(partitionIndex + 1, rightOfPivot);
	}

	private int partition(int left, int right, E pivot) {

		int leftPointer = left - 1, rightPointer = right; 

		while (leftPointer < rightPointer) {

			//shift pointer positions...
			while (leftPointer < data.size() && currentComparator.compare(data.get(++leftPointer), pivot) < 0);
			while (rightPointer > 0 && currentComparator.compare(data.get(--rightPointer), pivot) > 0);

			//this can happen with the shifting above ^^^. If it does... we're done. 
			if (leftPointer >= rightPointer) 
				break; 
			else
				swap(leftPointer, rightPointer); 
		}	
		
		swap(leftPointer, right); 
		return leftPointer; 
	}

	private void swap(int indexOne, int indexTwo) {
		E temp = data.get(indexOne); 
		data.set(indexOne, data.get(indexTwo)); 
		data.set(indexTwo, temp); 
	}

	@Override
	public void setComparator(Comparator<E> comparator) {
		this.currentComparator = comparator; 
	}
}
