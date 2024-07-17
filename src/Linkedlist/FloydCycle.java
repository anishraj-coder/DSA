package Linkedlist;

public class FloydCycle {
	/**
	 * Floyd's Cycle Detection Algorithm (also known as "Tortoise and Hare"
	 * algorithm)
	 * 
	 * Ideology: 1. Two Pointer Approach: The algorithm uses two pointers moving at
	 * different speeds. This is based on the idea that if a cycle exists, a
	 * faster-moving pointer will eventually catch up to a slower-moving pointer.
	 * 
	 * 2. Cycle Detection: If there's a cycle, the fast pointer will lap the slow
	 * pointer, and they will meet at some point within the cycle. If there's no
	 * cycle, the fast pointer will reach the end of the list.
	 * 
	 * 3. Cycle Start Detection: Once a cycle is detected, the algorithm leverages
	 * the fact that the distance from the head of the list to the cycle start is
	 * equal to the distance from the meeting point to the cycle start when
	 * traversed at the same speed.
	 * 
	 * 4. Mathematical Basis: The correctness of this algorithm is rooted in modular
	 * arithmetic. If we consider the cycle length as 'C' and the distance to the
	 * cycle start as 'X', when the pointers meet, the slow pointer has traveled 'X
	 * + nC' distance, while the fast pointer has traveled 'X + mC' distance, where
	 * n and m are some integers and X + mC = 2(X + nC). This equation helps prove
	 * why resetting one pointer to the head and moving both at the same speed will
	 * lead to the cycle start.
	 * 
	 * Method: 1. Initialization: Start with two pointers, 'slow' and 'fast', both
	 * at the head of the list.
	 * 
	 * 2. Phase 1 - Cycle Detection: - Move 'slow' one step and 'fast' two steps at
	 * a time. - If 'fast' reaches null or its next is null, there's no cycle. - If
	 * 'slow' and 'fast' meet, a cycle is detected.
	 * 
	 * 3. Phase 2 - Finding Cycle Start: - Reset 'slow' to the head of the list. -
	 * Move both 'slow' and 'fast' one step at a time. - The point where they meet
	 * is the start of the cycle.
	 * 
	 * 4. Return: The node where the pointers meet in Phase 2 is the start of the
	 * cycle. If no cycle is detected, return null.
	 * 
	 * Time Complexity: O(n), where n is the number of nodes in the list. Space
	 * Complexity: O(1), as it uses only two pointers regardless of list size.
	 */
	public Node floyd(Node head) {
		// Handle edge cases: empty list, single node, or only two nodes
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}

		Node slow = head;
		Node fast = head;

		// Phase 1: Detect cycle
		while (fast != null && fast.next != null) {
			slow = slow.next; // Move slow pointer one step
			fast = fast.next.next; // Move fast pointer two steps

			// If slow and fast meet, a cycle is detected
			if (slow == fast) {
				// Move to Phase 2
				break;
			}
		}

		// If fast reached the end (or its next is null), no cycle exists
		if (fast == null || fast.next == null) {
			return null;
		}

		// Phase 2: Find the start of the cycle
		slow = head; // Reset slow pointer to the head
		while (slow != fast) {
			// Move both pointers one step at a time
			slow = slow.next;
			fast = fast.next;
		}

		// The point where slow and fast meet is the start of the cycle
		return slow;
	}

	/**
	 * Node class representing a node in the linked list. This should be defined
	 * either as an inner class or in a separate file.
	 */
	private static class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
			this.next = null;
		}
	}

	/**
	 * Main method to demonstrate the Floyd's Cycle Detection algorithm.
	 */
	public static void main(String[] args) {
		FloydCycle detector = new FloydCycle();

		// Create a linked list with a cycle
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		// Create cycle: 5 points back to 3
		head.next.next.next.next.next = head.next.next;

		Node cycleStart = detector.floyd(head);

		if (cycleStart != null) {
			System.out.println("Cycle detected! It starts at node with value: " + cycleStart.data);
		} else {
			System.out.println("No cycle detected.");
		}
	}
}