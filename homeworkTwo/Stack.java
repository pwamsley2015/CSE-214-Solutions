package homeworkTwo;
/**
 * Patrick Wamsley 
 * 110539155
 * CSE 214 (2) 
 * TA: Mingchen Zhang
 */
import java.util.Arrays;

/**
 * Custom Stack implementation. 
 * @author Patrick Wamsley
 */
public class Stack<E> {

	/**
	 * The default, initial capacity of a stack. 
	 */
	public static final int DEFAULT_CAPACITY = 20; 
	
	/**
	 * Elements in the stack. 
	 */
	private Node<E>[] elements; 
	
	/**
	 * Index of element that is at the top of the stack. 
	 */
	private int indexOfLast; 
		
	/**
	 * Constructs a stack with the given capacity. 
	 * If this capacity is ever reached, the stack will automatically resize. 
	 * @throws IllegalArguementException if {@code initialCapacity} is less than 1.
	 */
	public Stack(int initialCapacity) {	 
		
		if (initialCapacity < 1)
			throw new IllegalArgumentException("Cannot Make a Stack with an initial Capacity less than 1."); 
		
		elements = new Node[initialCapacity];
		indexOfLast = -1; 
	}
	
	/**
	 * Constructs a stack with a default capacity of 20 elements. 
	 * If this capacity is ever reached, the stack will automatically resize. 
	 */
	public Stack() {
		this(DEFAULT_CAPACITY); 
	}
	
	/**
	 * @return true if and only if the stack has no elements. 
	 */
	public boolean isEmpty() {
		return indexOfLast == -1; 
	}
	
	/**
	 * Peeks the top element of the stack. 
	 * Does not modify the stack in any way. 
	 * @return element at the top of the stack. 
	 * @throws EmptyStackException of the stack is empty. 
	 */
	public E peek() {	
		checkEmptyCondition();
		return elements[indexOfLast].data;
	}
	
	/**
	 * Pops the element currently at the top of the stack. 
	 * @return the element. 
	 * @throws EmptyStackException if the stack is currently empty. 
	 */
	public E pop() {
		checkEmptyCondition(); 
		return elements[indexOfLast--].data; 
	}
	
	/**
	 * Pushes the element to the top of the stack. 
	 * @param element to add
	 * @return element added to the stack. 
	 * @throws NullPointerException if the added element was null. 
	 */
	public E push(E element) {
		
		if (element == null)
			throw new NullPointerException("Cannot add a null Element to the Stack."); 
		
		Node<E> nodeToAdd = new Node<E>(element); 
		
		if (++indexOfLast >= elements.length)
			doubleArrayLength(); 
		
		elements[indexOfLast] = nodeToAdd; 
		
		return element; 
	}
	
	/**
	 * @return the number of elements in the stack. 
	 */
	public int size() {
		return indexOfLast + 1; 
	}
	
	/**
	 * Doubles the capacity of the array using native Arrays.copyOf
	 */
	private void doubleArrayLength() {
		elements = Arrays.copyOf(elements, elements.length * 2); 
	}
	
	/**
	 * @throws EmptyStackException if the stack is empty. 
	 */
	private void checkEmptyCondition() {
		if (indexOfLast < 0)
			throw new EmptyStackException("Failed because stack is empty.");
	}
	
	/**
	 * Node class which holds elements in the stack. 
	 * @author Patrick Wamsley
	 */
	private static class Node<E> {
		
		private E data; 
		
		private Node(E data) {
			this.data = data; 
		}
		
		@Override
		public String toString() {
			return "Node in Stack: " + data; 
		}
		
		@Override
		public boolean equals(Object obj) {
			
			if (this == obj)
				return true; 
			
			if (!(obj instanceof Node))
				return false; 
		
			return data.equals(((Node)obj).data); 
		}
	}
	
	/**
	 * Exception which is thrown when an element is accessed from an empty stack.  
	 * @author Patrick Wamsley
	 */
	private static class EmptyStackException extends RuntimeException {
		
		private EmptyStackException() {
			super(); 
		}
		
		private EmptyStackException(String s) {
			super(s); 
		}
	}
}
