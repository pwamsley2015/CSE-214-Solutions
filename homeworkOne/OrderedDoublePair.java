package homeworkOne;
/**
 * Patrick Wamsley 
 * 110539155
 * CSE 214 (2) 
 * TA: Mingchen Zhang
 */

/**
 * Class which represents an ordered pair. 
 * @author Patrick Wamsley
 */
public class OrderedDoublePair {

	/**
	 * OrderedDoublePair object which represents the origin, (0,0)
	 */
	public static final OrderedDoublePair ORIGIN = new OrderedDoublePair(); 
	
	private double x, y; 
	
	/**
	 * Default Constructor, {@code (x, y)} is set to {@code (0,0)} by default
	 */
	public OrderedDoublePair() {
		this(0.0, 0.0); 
	}
	
	/**
	 * Constructs a new {@code OrderedDoublePair}
	 * @param x
	 * @param y
	 */
	public OrderedDoublePair(int x, int y) {
		this((double)x, (double)y); 
	}
	
	/**
	 * Constructs a new {@code OrderedDoublePair}
	 * @param x
	 * @param y
	 */
	public OrderedDoublePair(double x, double y) {
		this.x = x; 
		this.y = y;
	}
	
	/**
	 * @return the equality of the two ordered points. 
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj == this)
			return true; 

		if (!(obj instanceof OrderedDoublePair))
			return false; 
		
		OrderedDoublePair other = (OrderedDoublePair)obj; 
		
		return Math.abs(this.x - other.x) < 10e-8 && Math.abs(this.y - other.y) < 10e-8; 
	}
	/**
	 * @return true if the sets (x1, y1), (x2, y2) are equal.
	 */
	public boolean equalsIgnoreOrder(Object obj) {
		
		if (!(obj instanceof OrderedDoublePair))
			return false; 
		
		OrderedDoublePair other = (OrderedDoublePair)obj; 
		
		return equals(other) || (Math.abs(this.x - other.y) < 10e-8 && Math.abs(this.y - other.x) < 10e-8); 
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")"; 
	}
	
	@Override
	public int hashCode() {
		int result; 
		long temp = Double.doubleToLongBits(x); 
		result = (int) (temp ^ (temp >>> 32)); 
		temp = Double.doubleToLongBits(y); 
		return 31 * result + (int) (temp ^ (temp >>> 32)); 
	}
	
	public double getX() {
		return x; 
	}
	
	public double getY() {
		return y; 
	}
	
	public void setX(double newX) {
		x = newX; 
	}
	
	public void setY(double newY) {
		y = newY; 
	}
}
