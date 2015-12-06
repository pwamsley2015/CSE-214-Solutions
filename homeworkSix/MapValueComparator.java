package homeworkSix;

/**
 * Patrick Wamsley 
 * 110539155
 * CSE 214 (2) 
 * TA: Mingchen Zhang
 */

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Comperator used to sort a map based off its values rather than keys. 
 * @author Patrick Wamsley
 */
public class MapValueComparator<V extends Comparable> implements Comparator<V> {

	private Map<?, V> preSortMap;
	private boolean reverseOrder = false;

	public MapValueComparator(Map<?, V> preSortMap) {
		this.preSortMap = preSortMap; 
	}
	
	public MapValueComparator(Map<String, V> preSortMap, boolean reverseOrder) {
		this.preSortMap = preSortMap;
		this.reverseOrder = reverseOrder; 
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compare(V o1, V o2) {
		int compare = preSortMap.get(o1).compareTo(preSortMap.get(o2));
		int returnVal = compare == 0 ? 1 : compare; //prevent merging
		return reverseOrder ? -1 * returnVal : returnVal; 
	}
}
