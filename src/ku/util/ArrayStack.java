package ku.util;


import java.util.EmptyStackException;
import java.util.Observable;

/**
 * A stack of items that provides last-in first-out (LIFO) behavior.
 * 
 * @author jim
 * @param <T> the type of items in the stack
 */
public class ArrayStack<T> implements Stack<T> {

	protected T[] items;
	protected int top; // the top element on the stack
	
	/**
	 * A new stack with the given capacity.
	 * @param capacity maximum size of the stack, must be non-negative.
	 */
	public ArrayStack(int capacity) {
		if (capacity < 0) throw new IllegalArgumentException("Stack capacity cannot be negative");
		items = (T[]) new Object[capacity];
		top = -1;
	}
	
	/**
	 * @see ku.util.Stack#pop()
	 */
	public T pop() {
		if ( top < 0 ) throw new EmptyStackException();		
		T result = items[top--];
		return result;
	}
	
	/**
	 * @see ku.util.Stack#peek()
	 */
	public T peek() {
		if ( top < 0 ) return null;
		return items[top];
	}
	
	/**
	 * @see ku.util.Stack#push(T)
	 */
	public void push(T obj) {
		if (obj == null) throw new IllegalArgumentException("cannot push null onto stack");
		if ( top < items.length - 1 ) {
			items[++top] = obj; 
		}
		// else do nothing when stack is full
	}
	
	/**
	 * @see ku.util.Stack#isEmpty()
	 */
	public boolean isEmpty() { return top < 0; }
	
	/**
	 * @see ku.util.Stack#isFull()
	 */
	public boolean isFull() { return top >= items.length - 1; }
	
	/**
	 * @see ku.util.Stack#capacity()
	 */
	public int capacity() { return items.length; }
	
	/**
	 * @see ku.util.Stack#size()
	 */
	public int size() { return top+1; }
}
