package homeworkOne;
/**
 * Patrick Wamsley 
 * 110539155
 * CSE 214 (2) 
 * TA: Mingchen Zhang
 */
import java.util.HashSet;

/**
 * Singlely Linked List implementation. 
 * @author Patrick Wamsley
 */
public class UniLinkedList<E> {

	private Node<E>	head; 
	
	/**
	 * Keeps track of current position in the tail, as apposed to a "tail" node.
	 */
	private Cursor<E> addingCursor; 
	
	/**
	 * The pointer for accesing the list. 
	 */
	private Cursor<E> positionCursor; 

	/**
	 * Saved as a field so it doesn't need to be calculated.
	 * Changes size() from O(n) to O(1)
	 */
	private int size = 0; 

	public UniLinkedList() {
		this(null); 
	}

	protected UniLinkedList(Node<E> head) {
		this.head = head; 
	}

	/**
	 * @return The element this list's çursor is currently pointing to. 
	 */
	public E get() {

		if (positionCursor == null)
			resetPositionCursor();

		return positionCursor.next(); 
	}

	/**
	 * @return true if and only if one more iteration can be made. 
	 */
	public boolean hasNext() {	
		if (positionCursor == null)
			resetPositionCursor(); 
		return positionCursor.position != null; 
	}

	/**
	 * Resets the position cursor. Should be called every time the list is iterated through. 
	 */
	public void resetPositionCursor() {
		positionCursor = new Cursor<>(this); 
	}

	/**
	 * Clears the list. 
	 */
	public void clear() {
		head = null; 
	}

	/**
	 * @return head element, the first element in the list. Null if the list is empty. 
	 */
	public E head() {
		return head == null ? null : head.getData(); 
	}

	@Override
	public String toString() {

		if (head == null)
			return "()"; 

		String toReturn = "("; 

		Cursor<E> cursor = new Cursor<>(this); 
		
		while (cursor.hasNext()) 
			toReturn += cursor.next() + " -> "; 
		
		toReturn = toReturn.substring(0, toReturn.length() - 4); //get rid of the last space and arrow
		toReturn += ")"; 

		return toReturn; 
	}

	/**
	 * @return true if and only if <code>obj</code> is UniLinkedList 
	 * with equal size, with every element being equal and in the same order.
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (!(obj instanceof UniLinkedList))
			return false; 

		@SuppressWarnings("unchecked")
		UniLinkedList<E> other = (UniLinkedList<E>)obj; 

		if (size != other.size)
			return false; 

		Cursor<E> currNode = new Cursor<>(this); 
		Cursor<E> otherCurrNode = new Cursor<>(other); 

		while (currNode.hasNext()) 
			if (!(currNode.next().equals(otherCurrNode.next())))
				return false;

		return true; 
	}
	
	@Override
	public int hashCode() {
		return head != null ? head.hashCode() : 0; 
	}

	/**
	 * @return the size of the list
	 */
	public int size() {
		return size; 
	}
	
	/**
	 * @return true if and only if the list is empty. 
	 */
	public boolean isEmpty() {
		return head == null; 
	}

	/**
	 * @param element
	 * @return the index of the first occurance of <code>element</code>
	 */
	public int indexOf(E element) {

		Cursor<E> cursor = new Cursor<>(this);
		int index = 0; 

		while (cursor.hasNext()) {
			if (cursor.next().equals(element)) 
				return index; 
			index++; 
		}
		return -1; 
	}

	/**
	 * @param element
	 * @return true if and only if <code>element</code> is in the list. 
	 */
	public boolean contains(E element) {
		return indexOf(element) != -1; 
	}

	/**
	 * Adds <code>element</code> to the end of the list. 
	 */
	public boolean add(E element) {

		if (head == null) {
			head = new Node<E>(element); 
			addingCursor = new Cursor<>(this);
			size++; 
			return true; 
		} else {
			addingCursor.position.setNext(new Node<E>(element));
			addingCursor.next(); 
			size++; 
			return true;
		}
	}

	/**
	 * Adds {@code elementToAdd} directly after {@code mark}.
	 * @param mark
	 * @param elementToAdd
	 * @throws IllegalArgumentException if {@code mark} is not in the list.  
	 */
	public boolean addAfter(E mark, E elementToAdd) throws IllegalArgumentException {

		Cursor<E> cursor = new Cursor<>(this);  

		while (cursor.hasNext()) {
			if (cursor.position.data.equals(mark)) {
				Node<E> temp = cursor.position.next; 
				cursor.position.setNext(new Node<E>(elementToAdd));
				cursor.position.next.setNext(temp); 
				size++; 
				return true;
			}
			cursor.next(); 
		}
		
		throw new IllegalArgumentException("Element " + mark + " was not found in the list."); 
	}
	
	/**
	 * Adds the element as the first in the list. 
	 * @param element
	 */
	public boolean addFirst(E element) {

		//handles an empty list fine
		Node<E> temp = head;
		head = new Node<E>(element); 
		head.setNext(temp);
		size++; 
		return true;
	}

	/**
	 * Removes the first occurance of {@code element} from the list. 
	 * @param element
	 */
	public boolean remove(E element) {
		if (head == null)
			return false;
		
		if (element.equals(head.data)) {
			head = head.next; 
			size--; 
			return true; 
		}
	
		for (Node<E> e = head; e.next != null;) {
			if (e.next.data.equals(element)) {
				e.next = e.next.next; 
				size--; 
				return true; 
			} else 
				e = e.next; 
		}
		
		return false; 
	}

	/**
	 * Removes every occurance of {@code element} from the list. 
	 * @param element
	 */
	public boolean removeAll(E element) {

		boolean modified = false; 

		while (remove(element)) //bad O(n^2) solution 
			modified = true; 

		return modified; 
	}

	/**
	 * Removes every depuplicate from the list so that every element only occurs exactly once. 
	 */
	public void deduplicate() {

		if (head == null)
			return; 

		Cursor<E> cursor = new Cursor<>(this); 
		HashSet<E> uniqueOccurances = new HashSet<>(); 

		uniqueOccurances.add(head.getData()); 

		while (cursor.hasNext() && cursor.position.hasNext()) { //cursor points to one ahead of curr position, so this checks if curr and next is not null d
			if (uniqueOccurances.contains(cursor.position.getNext().data)) {
				cursor.position.setNext(cursor.position.getNext().getNext());
				size--; 
			} else {
				uniqueOccurances.add(cursor.position.getData()); 
				cursor.next(); 
			}
		}
	}

	/**
	 * @return a cloned version of this list. 
	 */
	public UniLinkedList<E> clone() {
		UniLinkedList<E> toReturn = new UniLinkedList<E>(); 
		Cursor<E> cursor = new Cursor<>(this); 

		while (cursor.hasNext())
			toReturn.add(cursor.next()); 

		return toReturn; 

	}
	
	public static class Cursor<E> {

		private Node<E> position; 

		private Cursor(UniLinkedList<E> list) {
			position = list.head; 
		}

		public boolean hasNext() {
			return position != null; 
			
		}

		public E next() {
			E e = position.data; 
			position = position.next; 
			return e;  
		}
	}

	private static class Node<E> {

		private Node<E> next; 
		private E data; 

		public Node(Node<E> next, E data) {
			this.next = next; 
			this.data = data; 
		}

		public Node(E data) {
			this(null, data); 
		}

		public void setNext(Node<E> next) {
			this.next = next; 
		}

		public E getData() {
			return data; 
		}

		public Node<E> getNext() {
			return next; 
		}

		public boolean hasNext() {
			return next != null; 
		}

		@Override
		public int hashCode() {
			int result = data != null ? data.hashCode() : 0; 
			return 31 * result + (next != null ? next.hashCode() : 0); 
		}
	}
}
