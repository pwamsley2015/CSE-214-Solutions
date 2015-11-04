package homeworkThree;
/**
 * Patrick Wamsley 
 * 110539155
 * CSE 214 (2) 
 * TA: Mingchen Zhang
 */
public interface BinaryTree<E> {

	public void add(E element); 
	
	public void remove(E element); 
	
	public boolean contains(E element); 
	
	public E min(); 
	
	public E max(); 
}
