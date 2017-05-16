/**
 * 	PHUC PHAM N
 * 	Class 9:30 -> 10:30 
 */


import java.util.Iterator;

/**
 * Generic doubly linked list class.
 *
 * @param <T>
 */
public class DoublyLinkedList<T> implements Iterable<T> {
	/**
	 * Each element of the DoublyLinkedList class is a node that links to the
	 * next node and the previous node. There is no next node (next == null) if
	 * it's the last node (end of the list) and no previous node if it's the
	 * first node (start of the list)
	 *
	 * @param <T>
	 */
	private static class Node<T> {
		public T data; // Data within each node
		// Previous has a smaller index (closer to start)
		// Next has a larger index (closer to end)
		public Node<T> prev, next;
	}

	/**
	 * The conductor is capable of walking down the "train" of nodes. This
	 * allows it to follow Java's "iterator" interface.
	 *
	 * @param <T>
	 */
	private static class Conductor<T> implements Iterator<T> {
		private Node<T> current;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			// Retrieve passenger from current car
			T s = current.data;
			// Conductor advances to next car
			current = current.next;
			return s;
		}

		public Conductor(Node<T> n) {
			current = n;
		}

	}
	
	@Override
	public Iterator<T> iterator() {
		return new Conductor<T>(start);
	}

	// First and last nodes of the list.
	private Node<T> start, end;
	
	// Add field size of list
	private int size;
	/**
	 * Create an empty list.
	 */
	public DoublyLinkedList() {
		start = end = null;
		
		// init size of list
		this.size = 0;
	}

	/**
	 * Add an element to the end.
	 * 
	 * @param s
	 *            Data for that element
	 */
	public void add(T s) {
		if (start == null) {
			assert end == null;
			start = end = new Node<T>();
			start.data = s;
			this.size = 1;	// Increasing 1 to the size of list when add a new node
			// end == start so it doesn't need to be changed
			// start.next and start.prev are both null
		} else {
			// Add to the end
			// First, attaching a new boxcar to the end
			end.next = new Node<T>();
			// Next, building a doorway from the new last to the old last
			end.next.prev = end;
			// Then, setting up the data member of the new last boxcar
			end.next.data = s;
			// Finally, new last boxcar is the last boxcar!
			end = end.next;
			this.size = this.size + 1; // Increasing 1 to the size of list when add a new node
		}
	}

	/**
	 * Access element at index i (0 based). start has index 0. Runs in O(i) time
	 * (fast to access near the start, slow near the end)
	 * 
	 * @param i
	 * @return data in element at index i or null if i is not a valid index
	 */
	public T get(int i) {			
		int size = size();
		Node<T> n = null;
		if (i < 0 || i > size)
			throw new IndexOutOfBoundsException();			
		int k = (size - 1) - i;
		if(i < k){
			n = start;	
			for (int j = 0; j < i; j++) {
				n = n.next;
			}
		}else{	
			n = end;
			for (int j = 0; j < k; j++) {
				n = n.prev;
			}
		}
		return n.data;		
	}
	

	/**
	 * Remove an element from the list. Repairs the list so that it's connected
	 * but without that element. Runs in O(i) time.
	 * 
	 * @param i
	 * @return data in removed element or null if i is not a valid index
	 */
	public T remove(int i) {
		Node<T> n = start;
		if (i < 0)
			return null;
		try {
			for (int j = 0; j < i; j++) {
				n = n.next;
			}
			// NOTE: n could be null, but then we would
			// get caught by our null pointer catch clause

			// Let's say we have a list x-A-B-C-x
			// where next is to the right of each node
			// and we want to remove B (variable n)
			// The new list would be x-A-C-x
			// First we will connect A to C.
			if (n.prev != null) {
				// If there is such an A
				// Change its next variable to C
				n.prev.next = n.next;
			} else {
				// If there is no such A,
				// we must be the start of the list
				assert n == start;
				start = n.next;
			}
			// Then we will connect C to A
			if (n.next != null) {
				// If there is such a C
				// Change its prev variable to A
				n.next.prev = n.prev;
			} else {
				// If there is no such C,
				// we must be the end of the list
				assert n == end;
				end = n.prev;
			}
			this.size = this.size - 1; // decreasing 1 to the size of list when remove a node
			return n.data;
		} catch (NullPointerException e) {
			return null; // Not a valid index
		}
	}

	
	
	/**
	 * Function size of list
	 * 
	 * @return size
	 */
	public int size(){		
		return this.size;
	}
	
	
	/**
	 * Function reverse node of list
	 * 
	 * 
	 */
	public void reverse(){	
		Node<T> i = null;
		Node<T> current = start;
		
		while(current != null){
			i = current.prev;
			current.prev = current.next;
			current.next = i;
			current = current.prev;
		}
		
		if(i != null){
			start = i.prev;
		}
		
	}
	
	/**
	 * Add a new node at position i
	 * 
	 * @param i
	 * @param s
	 * @return true if added and false if could not add
	 */
	
	public boolean add(int i, T s){
		int size = size();
		if (i < 0 || i > size)
			return false;	
		if(i == 0){
			if(start == null){
				assert end == null;
				start = end = new Node<T>();
				start.data = s;
				this.size = 1;
			}else{
				Node<T> p = new Node<T>();
				p.data = s;
				p.next = start;
				start.prev = p;
				start = p;
				this.size = this.size + 1;
			}			
		}else if( i == size){
			if(start == null){
				assert end == null;
				start = end = new Node<T>();
				start.data = s;
				this.size = 1;
			}else{
				Node<T> p = new Node<T>();
				p.data = s;
				end.next = p;
				p.prev = end;
				end = p;
				this.size = this.size + 1;
			}			
		}else{			
			Node<T> n = null;				
			int k = (size - 1) - i;
			if(i < k){
				n = start;	
				for (int j = 1; j < i; j++) {
					n = n.next;
				}
			}else{	
				n = end;
				for (int j = 1; j < k; j++) {
					n = n.prev;
				}
			}
			Node<T> p = new Node<T>();			
			p.prev = n;
			p.next = n.next;			
			p.next.prev = p;
			n.next = p;
			p.data = s;
			this.size = this.size + 1;
		}
		return true;
	}	
	
}

