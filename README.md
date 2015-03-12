## Stack Testing

Create JUnit tests for the Stack interface.

1. Create an IDE project and add ku/util/Stack.java to your project "src" tree.
2. In the source tree, create a dummy stack with do-nothing methods. Eclipse can quickly do this for you.  
    * Real stacks have a capacity set via the constructor. So you may add a `DummyStack(int capacity)` constructor to match this.
3. Create a separate source tree named "test" for test code (best practice).
4. In `test/ku/util` create a StackTest class that tests the Stack methods.  For now use your dummy stack.
    * Most of the tests will fail. This is usual.
5. Download and add `StackFactory.jar` to your project.
6. In StackTest, create a real stack using:
```
    Stack stack = StackFactory.makeStack( capacity );
```
7. StackFactory produces 3 kinds of Stack. The default is type 0 (ArrayStack). To test a different stack type, add this code:
```
@Before
public void before() {
     StackFactory.setStackType( 1 );  // type = 0, 1, or 2
}
```
