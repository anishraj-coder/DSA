package Linkedlist;

public class Reverse {
	public static void reverseLinkedList(Linkedlist head) {
		Node curr = head.head, prev = null;
		while (curr != null) {
			Node currp1 = curr.next;
			curr.next = prev;
			prev = curr;
			curr = currp1;
		}
		head.head = prev;
	}

	public static void main(String[] args) {
		Linkedlist l1 = new Linkedlist();
		l1.addFront(44);
		l1.addFront(59);
		l1.addLast(60);
		l1.addLast(79);
		l1.view();

		reverseLinkedList(l1);
		l1.view();

	}
}
