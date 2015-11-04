package homeworkThree;
/**
 * Patrick Wamsley 
 * 110539155
 * CSE 214 (2) 
 * TA: Mingchen Zhang
 */
import java.util.Comparator;
import java.util.Scanner;

/**
 * BinarySearchTree implementation
 * @author Patrick Wamsley
 */
public class BinarySearchTree<E extends Comparable> implements BinaryTree<E> {

	/**
	 * Root of the tree
	 */
	private BinaryTreeNode<E> root; 
	/**
	 * Comparator used to compare elements in the tree.
	 * Determines the structure of the BST. 
	 */
	private Comparator<E> comparator;

	/**
	 * Constructs an empty BST
	 * @param comparator
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator; 
	}

	/**
	 * Adds this element to the binary search tree.
	 * If the element is already found in the tree, the tree is not modified. 
	 */
	@Override
	public void add(E element) {
		add(new BinaryTreeNodeImpl<E>(element), root); 
	}

	private void add(BinaryTreeNode<E> node, BinaryTreeNode<E> currPlace) {

		if (root == null) {
			root = node; 
			return; 
		}
	
		if (currPlace.getData().equals(node.getData()))
			return; //already in the tree

		int compareVal = comparator.compare(node.getData(), currPlace.getData()); 
		
		if (compareVal > 0) {
			if (currPlace.getRight() == null) {
				node.setParent(currPlace);
				currPlace.setRight(node); 
				return;
			}
			add(node, currPlace.getRight()); 
		} else {
			if (currPlace.getLeft() == null) {
				node.setParent(currPlace);
				currPlace.setLeft(node);
				return;
			}
			add(node, currPlace.getLeft()); 
		}
	}

	/**
	 * Removes the element from the BST. 
	 */
	@Override
	public void remove(E element) {

		if (root == null)
			return; 

		BinaryTreeNode<E> node = findNodeOfElement(element); 
		if (node == null)
			throw new IllegalStateException("Element " + element + " was not in the tree."); 
		
		int numberOfChildren = 0; 
		if (node.getLeft() != null)
			numberOfChildren++; 
		if (node.getRight() != null)
			numberOfChildren++;

		switch (numberOfChildren) {
			case 0: //trivial case
				if (node.getParent().getLeft() == node)
					node.getParent().setLeft(null);
				else
					node.getParent().setRight(null);
				node.setParent(null); //not really needed because node can't be accessed from inside the BST anymore
				break; 
			case 1: //trivial 1 child case... just swap
				if (node.getLeft() != null)
					node.getLeft().setParent(node.getParent());
				if (node.getRight() != null)
					node.getRight().setParent(node.getParent());
				node.setParent(null); //again not really needed
				break; 
			case 2: // :( 
				BinaryTreeNode<E> successor = getSuccessor(node); 
				//delete successor from its current spot. 
				if (successor.getParent().getRight() == successor)
					successor.getParent().setRight(null);
				else if (successor.getParent().getLeft() == successor)
					successor.getParent().setLeft(null);
				//replace s and n
				node.setData(successor.getData());
				break; 
			default: 
				assert "pigs can fly" == "true"; 
		}

	}
	
	private BinaryTreeNode<E> findNodeOfElement(E element) {
		return findNodeOfElement(element, root); 
	}

	private BinaryTreeNode<E> findNodeOfElement(E element, BinaryTreeNode<E> currNode) {

		if (currNode == null)
			return null; 

		if (element.equals(currNode.getData()))
			return currNode; 

		int compareVal = comparator.compare(element, currNode.getData()); 

		if (compareVal < 0)
			return findNodeOfElement(element, currNode.getLeft());
		else 
			return findNodeOfElement(element, currNode.getRight()); 
	}

	private BinaryTreeNode<E> getSuccessor(BinaryTreeNode<E> node) {
		if (node == null)
			return null; 
		if (node.getRight() != null)
			return getMinNode(node.getLeft()); 
		while (node.getParent() != null && node.getParent().getRight() == node)
			node = node.getParent(); 
		return node.getParent(); 
	}

	@Override
	public boolean contains(E element) {
		return contains(element, root);
	}

	private boolean contains(E element, BinaryTreeNode<E> currNode) {

		int compareVal = comparator.compare(element, currNode.getData()); 

		if (compareVal == 0) {
			return true; 
		} else if (compareVal > 0) {
			if (currNode.getRight() == null) 
				return false;
			else 
				return contains(element, currNode.getRight()); 
		} else {
			if (currNode.getLeft() == null) 
				return false;
			else
				return contains(element, currNode.getLeft()); 
		}

	}

	private BinaryTreeNode<E> getMinNode(BinaryTreeNode<E> root) {
		if (root == null) //empty tree
			throw new IllegalStateException("Cannot find minimum of an empty tree");
		return (root.getLeft() == null) ? root : getMinNode(root.getLeft()); 
	}

	private BinaryTreeNode<E> getMaxNode(BinaryTreeNode<E> root) {
		if (root == null)
			throw new IllegalStateException("Cannot find max of an empty tree"); 
		return (root.getRight() == null) ? root : getMaxNode(root.getRight()); 
	}

	@Override
	public E min() {
		return getMinNode(root).getData();
	}

	@Override
	public E max() {
		return getMaxNode(root).getData(); 
	}

	private String searchForAnswer(E string, int iterations, BinaryTreeNode<E> currNode) {

		int compareVal = comparator.compare(string, currNode.getData()); 

		if (compareVal == 0) 
			return "String " + string + " found in " + iterations + " steps."; 
		else 
			iterations++; 
		if (compareVal > 0) {
			if (currNode.getRight() == null) 
				return "String not found in the list after " + iterations + " steps.";
			else 
				return searchForAnswer(string, iterations, currNode.getRight()); 
		} else {
			if (currNode.getLeft() == null) 
				return "String not found in the list after " + iterations + " steps.";
			else
				return searchForAnswer(string, iterations,  currNode.getLeft()); 
		}

	}

	/**
	 * Something something something something homework
	 */
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in); 

		BinarySearchTree<String> bst = new BinarySearchTree<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2); 
			}
		}); 

		System.out.println("Enter in CSV of Strings. Do not include commas in the Strings themselves and don't add spaces.");
		for (String s : scanner.nextLine().split(","))
			bst.add(s); 

		System.out.println("The root is the 0th step.");
		while (true) {
			System.out.println("Enter in a string to search for or type quit to exit");
			String string = scanner.nextLine(); 
			if (string.equalsIgnoreCase("quit"))
				System.exit(0);
			System.out.println(bst.searchForAnswer(string, 0, bst.root)); 
		}
	}
}
