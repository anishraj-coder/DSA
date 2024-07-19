package Queue;

import java.util.LinkedList;
import java.util.Queue;

/*Implement a last-in-first-out (LIFO) stack using only two queues. 
 * The implemented stack should support all the functions of a normal
 *  stack (push, top, pop, and empty).

Implement the MyStack class:

void push(int x) Pushes element x to the top of the stack.
int pop() Removes the element on the top of the stack and returns it.
int top() Returns the element on the top of the stack.
boolean empty() Returns true if the stack is empty, false otherwise.
Notes:

You must use only standard operations of a queue, which means that only
 push to back, peek/pop from front, size and is empty operations are valid.
Depending on your language, the queue may not be supported natively.
 You may simulate a queue using a list or deque (double-ended queue)
  as long as you use only a queue's standard operations.
 

Example 1:

Input
["MyStack", "push", "push", "top", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 2, 2, false]

Explanation
MyStack myStack = new MyStack();
myStack.push(1);
myStack.push(2);
myStack.top(); // return 2
myStack.pop(); // return 2
myStack.empty(); // return False
 

Constraints:

1 <= x <= 9
At most 100 calls will be made to push, pop, top, and empty.
All the calls to pop and top are valid.*/

/*
 * Ideology and Method:
 * 
 * The problem asks to implement a Last-In-First-Out (LIFO) stack using only queue operations.
 * This solution presents an innovative approach using a single queue instead of two.
 * 
 * Key Concepts:
 * 1. Reverse Order Maintenance: The queue is maintained in reverse order of insertion,
 *    ensuring that the last element pushed onto the stack is always at the front of the queue.
 * 
 * 2. Queue Rotation: After each push operation, the queue is rotated to move the newly
 *    added element to the front, simulating the LIFO behavior of a stack.
 * 
 * 3. Efficient Top and Pop Operations: By keeping the top of the stack at the front of
 *    the queue, top and pop operations become O(1) time complexity.
 * 
 * Implementation Details:
 * 
 * 1. Push Operation (O(n) time complexity):
 *    - Add the new element to the back of the queue (O(1))
 *    - Rotate the queue by removing each element and adding it back,
 *      except for the newly added element (O(n))
 *    - This rotation ensures the new element ends up at the front
 * 
 * 2. Pop Operation (O(1) time complexity):
 *    - Simply remove and return the front element of the queue
 *    - This works because the front element is always the top of our simulated stack
 * 
 * 3. Top Operation (O(1) time complexity):
 *    - Return the front element without removing it
 * 
 * 4. Empty Operation (O(1) time complexity):
 *    - Check if the queue is empty
 * 
 * 5. Size Operation (O(1) time complexity):
 *    - Return the size of the queue
 * 
 * Advantages of this Approach:
 * - Uses only one queue instead of two, saving space
 * - Provides O(1) time complexity for pop, top, empty, and size operations
 * - Adheres to the constraint of using only standard queue operations
 * 
 * Trade-offs:
 * - Push operation has O(n) time complexity due to the rotation step
 * - This might not be ideal for applications with frequent push operations on large stacks
 * 
 * This implementation demonstrates a creative way to solve the problem, balancing the
 * requirements and constraints while providing efficient operations for most use cases.
 */

class MyStack {
	// Using a single queue to implement the stack
	Queue<Integer> q = new LinkedList<>();

	public MyStack() {
		// Constructor - no initialization needed
	}

	public void push(int x) {
		// Step 1: Add the new element to the back of the queue
		q.add(x);

		// Step 2: Rotate the queue to move the new element to the front
		for (int i = 0; i < q.size() - 1; i++) {
			q.add(q.remove());
		}
		// After this loop, the new element is at the front, simulating stack's top
	}

	public int pop() {
		// Remove and return the front element (top of the stack)
		return q.remove();
	}

	public int top() {
		// Return the front element without removing it
		return q.peek();
	}

	public boolean empty() {
		// Check if the queue (and thus the stack) is empty
		return q.isEmpty();
	}

	public int size() {
		// Return the size of the queue (which represents the stack's size)
		return q.size();
	}
}

public class QueueUsingStack {

	public static void main(String[] args) {
		MyStack s = new MyStack();

		// Demonstrating stack operations
		s.push(3);
		s.push(2);
		s.push(4);
		s.push(1);

		System.out.println("Top of the stack: " + s.top());
		System.out.println("Size of the stack before removing element: " + s.size());
		System.out.println("The deleted element is: " + s.pop());
		System.out.println("Top of the stack after removing element: " + s.top());
		System.out.println("Size of the stack after removing element: " + s.size());
		System.out.println("Is the stack empty? " + s.empty());
	}
}