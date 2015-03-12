package ku.util;


import java.util.EmptyStackException;
import java.util.Observable;

/**
 * A stack that is never full.
 * 
 * @author jim
 * @param <T> the type of items in the stack
 */
public class LeakyStack<T> extends ArrayStack<T> {
	
	/**
	 * A new stack with the given capacity.
	 * @param capacity maximum size of the stack, must be non-negative.
	 */
	public LeakyStack(int capacity) {
		super(capacity);
	}
	
	/* (non-Javadoc)
	 * @see ku.util.Stack#push(T)
	 */
	public void push(T obj) {
		if ( ! super.isFull() ) super.push(obj);
		else { 
			// replace top element
			super.pop();
			super.push(obj);
		}
	}
	
	/* (non-Javadoc)
	 * @see ku.util.Stack#isFull()
	 */
	public boolean isFull() { return false; }

}
