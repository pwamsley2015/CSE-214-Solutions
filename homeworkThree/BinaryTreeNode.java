package homeworkThree;
/**
 * Patrick Wamsley 
 * 110539155
 * CSE 214 (2) 
 * TA: Mingchen Zhang
 */
/**
 * @author Patrick Wamsley
 */
public interface BinaryTreeNode<E extends Comparable> {

	public E getData(); 

	public void setData(E data); 
	
	public BinaryTreeNode<E> getParent();  

	public void setParent(BinaryTreeNode<E> parent); 

	public BinaryTreeNode<E> getLeft(); 

	public BinaryTreeNode<E> getRight(); 	

	public void setLeft(BinaryTreeNode<E> node);
	
	public void setRight(BinaryTreeNode<E> node); 

	public void removeFromParent(); 
}
