package homeworkFive;

/**
 * Patrick Wamsley 
 * 110539155
 * CSE 214 (2) 
 * TA: Mingchen Zhang
 */

/**
 * Class which represents an alphabetic character
 * @author Patrick Wamsley
 */
public class Alphabet implements Hashable {

	private char val; 
	
	public Alphabet(char val) {
		if (Character.getType(val) == Character.LOWERCASE_LETTER)
			this.val = val; 
		else
			throw new IllegalArgumentException("Invalid alphabet character"); 
	}
	
	/**
	 * Creates hash integer value by using the ASCII converstion 
	 * which is unique to the character. 
	 * @return Integer hash of the Alphabetic character. 
	 */
	@Override
	public int hash() {
		return (int)val; 
	}
	
	public String toString() {
		return "" + val; 
	}

}
