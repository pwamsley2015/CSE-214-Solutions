package homeworkFour;
/**
 * Patrick Wamsley 
 * 110539155
 * CSE 214 (2) 
 * TA: Mingchen Zhang
 */
import java.util.Comparator;

public interface Sorter<E> {

	public void sort(); 
	
	public void setComparator(Comparator<E> comparator); 
	
}
