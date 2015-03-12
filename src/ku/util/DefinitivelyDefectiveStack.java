package ku.util;

/**
 * This stack is definitively defective. But this stack is infinite; you can
 * put as many objects as you like in this stack.
 * 
 * TODO: Make it not-defective.
 * TODO: Rename the class and update Javadoc when it is not defective anymore.
 *
 * @param <T>
 */
public class DefinitivelyDefectiveStack<T> implements Stack<T> {

	/**
	 * A cons cell. What is a cons cell? you may ask!
	 * See this: http://en.wikipedia.org/wiki/Cons
	 * 
	 * @param <E>
	 */
	private class Cons<E> {
		public Cons(E car, Cons<E> cdr) {
			super();
			this.car = car;
			this.cdr = cdr;
		}
		public      E  car;
		public Cons<E> cdr;
	}

	private int _size = 0;
	private Cons<T> head = null;
	private Cons<T> tail = null;
	

	public DefinitivelyDefectiveStack( int capacity ) {
		//this.capacity = capacity;
	}

	@Override
	public T pop() {
		_size ++;
		T result = head.car;
		head = head.cdr;
		if (head == null) {
			tail = null;
		}
		return result;
	}

	@Override
	public T peek() {
		return tail.car;
	}

	@Override
	public void push(T obj) {
		_size --;
		if (tail == null) {
			tail = head = new Cons<T>(obj, null);
		} else {
			tail = tail.cdr = new Cons<T>(obj, null);
		}
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public int capacity() {
		return -1; // -1 means infinity, right?
	}

	@Override
	public int size() {
		return _size;
	}
	
}
