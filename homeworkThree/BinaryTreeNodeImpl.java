package homeworkThree;
/**
 * Patrick Wamsley 
 * 110539155
 * CSE 214 (2) 
 * TA: Mingchen Zhang
 */

/**
 * BinaryTreeNode Implementation which holds referances to parent and two children node. 
 * 
 * @author Patrick Wamsley
 */
public class BinaryTreeNodeImpl<E extends Comparable> implements BinaryTreeNode<E> {

	private E data; 
	private BinaryTreeNode<E> parent, left, right; 
	
	/**
	 * Constructs a node. 
	 * 
	 * @param data
	 * @param parent
	 */
	public BinaryTreeNodeImpl(E data, BinaryTreeNode<E> parent) {
		setData(data);
		setParent(parent);
	}
	
	/**
	 * Constructs a root node.
	 * @param data
	 */
	public BinaryTreeNodeImpl(E data) {
		this(data, null); 
	}
	
	/**
	 * @return This node's {@code data}. Returns {@code null} if {@code data} is {@code null}. 
	 */
	@Override
	public E getData() {
		return data; 
	}

	/**
	 * Sets this Node's {@code data}
	 */
	@Override
	public void setData(E data) {
		this.data = data; 
	}

	/**
	 * @return This Node's {@code parent} node
	 */
	@Override
	public BinaryTreeNode<E> getParent() {
		return parent;
	}

	/**
	 * Sets this Node's {@code parent} node
	 */
	@Override
	public void setParent(BinaryTreeNode<E> parent) {
		this.parent = parent; 
	}

	/**
	 * @return This node's {@code left} node
	 */
	@Override
	public BinaryTreeNode<E> getLeft() {
		return left; 
	}

	/**
	 * @return This node's {@code right} node
	 */
	@Override
	public BinaryTreeNode<E> getRight() {
		return right;
	}

	/**
	 * Sets this Node's {@code left} node
	 */
	@Override
	public void setLeft(BinaryTreeNode<E> node) {
		left = node; 
	}
	
	/**
	 * Sets this Node's {@code right} node
	 */
	@Override
	public void setRight(BinaryTreeNode<E> node) {
		right = node; 
	}

	/**
	 * Removes this node and all branching nodes from the parent node. 
	 */
	@Override
	public void removeFromParent() {
		
		if (this == parent.getLeft())
			parent.setLeft(null); 
		else
			parent.setRight(null);
		
		parent = null; 
	}

}
