package Linkedlist;

/*
Problem:
You are given the head of a singly linked-list. The list can be represented as:
L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:
L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.

Solution Ideology:
Certainly! Let's analyze the question and the solution:

Problem:
The problem asks to reorder a singly linked list from its original form:
L0 → L1 → L2 → ... → Ln-1 → Ln
to the following form:
L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → ...

Solution Ideology:
The solution uses a three-step approach:

1. Find the middle of the list
2. Reverse the second half of the list
3. Merge the first half and the reversed second half alternately

Method Used:

1. Finding the middle:
   - Use two pointers: slow (s) and fast (f)
   - Move slow one step at a time and fast two steps at a time
   - When fast reaches the end, slow will be at the middle

2. Reversing the second half:
   - Start from the node after the middle
   - Use three pointers (prev, curr, currp1) to reverse the links

3. Merging the two halves:
   - Use two pointers: left (for first half) and right (for reversed second half)
   - Alternately connect nodes from left and right until right is exhausted

Key Points:
- The solution modifies the list in-place, not creating any new nodes
- It handles edge cases (empty list or single node list) at the beginning
- The middle finding technique (slow-fast pointers) works for both odd and even length lists
- After reversing the second half, the original last node becomes the first node of the right half
- The merging process ensures that the original order is maintained while interleaving the second half

Time Complexity: O(n) where n is the number of nodes in the list
Space Complexity: O(1) as it uses only a constant amount of extra space

This solution efficiently reorders the list by manipulating the pointers, adhering to the constraint of not modifying the values in the nodes.

Time Complexity: O(n) where n is the number of nodes in the list
Space Complexity: O(1) as it uses only a constant amount of extra space
*/

public class Reorder {
	public static void reorder(Linkedlist h) {
		Node head = h.head;
		// Handle edge cases: empty list or single node list
		if (head == null || head.next == null)
			return;

		// Step 1: Find the middle of the list using slow and fast pointers
		Node s = head; // slow pointer
		Node f = head; // fast pointer
		while (f.next != null && f.next.next != null) {
			s = s.next;
			f = f.next.next;
		}
		// At this point, 's' is at the middle node

		// Step 2: Reverse the second half of the list
		Node prev = null;
		Node curr = s.next;
		while (curr != null) {
			Node currp1 = curr.next;
			curr.next = prev;
			prev = curr;
			curr = currp1;
		}
		// At this point, 'prev' is the new head of the reversed second half

		// Step 3: Merge the first half and the reversed second half alternately
		Node left = head;
		Node right = prev;
		s.next = null; // Break the link between first and second half
		while (right != null) {
			Node leftp1 = left.next;
			Node rightp1 = right.next;
			left.next = right;
			right.next = leftp1;
			left = leftp1;
			right = rightp1;
		}
	}

	public static void main(String[] args) {
		// Create and populate a linked list
		Linkedlist l1 = new Linkedlist();
		l1.addFront(44);
		l1.addFront(59);
		l1.addLast(60);
		l1.addLast(79);
		l1.addFront(78);
		l1.addFront(75);
		l1.addFront(55);

		// Display the original list
		System.out.println("Original list:");
		l1.view();

		// Reorder the list
		reorder(l1);

		// Display the reordered list
		System.out.println("Reordered list:");
		l1.view();
	}
}