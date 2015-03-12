package ku.util;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Factory for stacks.
 * @author jim
 *
 */
public class StackFactory {
	/** Known stack implementations. */
	private static List<Class<? extends Stack>> stackclasses ;
	/** index for selecting among known implementations. -1 means random selection. */
	private static int stacktype = 0;
	private static java.util.Random random;
	
	static {
		random = new java.util.Random( System.nanoTime() );
		stackclasses = new ArrayList<Class<? extends Stack>>( );
		register( ArrayStack.class );
		register( DefinitivelyDefectiveStack.class );
		register( LeakyStack.class );
	}

	/** Register a new type of Stack implementation. */
	public static void register(Class<? extends Stack> stackClass) {
		stackclasses.add( stackClass );
	}
	
	/** Specify which concrete stack to instantiate. 
	 *  @param type is 0, 1, ... Negative value means random stack.
	 */
	public static void setStackType(int type) {
		stacktype = type;
	}
	
	/** Get the index of stack type to create. */
	public static int getStackType() {
		return stacktype;
	}
	
	/**
	 * Return a new generic (untyped) Stack.
	 * @param capacity the capacity of new stack
	 * @return Stack object
	 */
	public static Stack makeStack(int capacity) {
		int index = stacktype;
		if (index < 0) index = random.nextInt(stackclasses.size());
		else index = index % stackclasses.size();
		
		Class<? extends Stack> clazz = stackclasses.get(index);
		try {
			// convoluted logic to invoke a parameterized constructor via reflection
			Class[] argTypes = { int.class };
			Constructor<? extends Stack> constructor = clazz.getDeclaredConstructor(argTypes);
			Object[] arguments = { new Integer(capacity) };
			Stack stack = constructor.newInstance( arguments );
			return stack;
		}
		catch(Exception ex) {
			// fall back to known stack
			System.err.println("Error instantiating stack: " + ex);
			return new ArrayStack(capacity);
		}
	}

	
	public static void main(String[] args) {
		// set stack type. 0 = ArrayStack, 1 = LeakyStack
		StackFactory.setStackType(0);
		Stack<String> s = (Stack<String>) StackFactory.makeStack( 2 );
		System.out.println(s.getClass().getName());
		System.out.println( "capacity = " + s.capacity() );
		s.push("hello");
		s.push("bye");
		s.push("overflow");
		while( ! s.isEmpty() ) {
			System.out.println( s.pop() );
		}
	}
}
