package homeworkFive;

/**
 * Patrick Wamsley 
 * 110539155
 * CSE 214 (2) 
 * TA: Mingchen Zhang
 */

/**
 * Direct Addressing Table implementation using hashing. 
 * @author Patrick Wamsley
 */
public class DirectAddressTable<V extends Hashable> implements Dictionary<V> {
	
	private int size = 0; 
	private Hashable[] table = new Hashable[31];  
	
	@Override
	public boolean isEmpty() {
		return size == 0; 
	}

	@Override
	public void insert(V value) {
		if (value == null)
			throw new NullPointerException(); 
		if (++size > table.length)
			throw new FullAddressTableException("Address Table is full."); 
		int hash = value.hash() % table.length; 
		table[hash] = value; 
	}

	@Override
	public V delete(V value) {
		if (value == null)
			throw new NullPointerException(); 
		int hash = value.hash() % table.length; 
		table[hash] = null; 
		size--; 
		return value;
	}

	@Override
	public V find(int key) {
		return table[key] == null ? null : (V)table[key];
	}
	
	@Override
	public String toString() {
		StringBuilder returnString = new StringBuilder(); 
		
		for (int i = 0; i < table.length; i++) 
			returnString.append(i + " –– " + table[i] + "\n"); 
		
		return returnString.toString(); 
	}
	
	/**
	 * Exception thrown when an element is trying to be added to a full address table
	 * @author Patrick Wamsley
	 */
	private static class FullAddressTableException extends RuntimeException {
		FullAddressTableException() {
			super(); 
		}
		FullAddressTableException(String s) {
			super(s); 
		}
	}

}
