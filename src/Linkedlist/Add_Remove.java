package Linkedlist;

class Add_Remove {
	public static void addlast(Node head, int val) {
		Node temp = head;
		while (temp.next != null) {
			temp = temp.next;
		}
		Node new_node = new Node(val);
		temp.next = new_node;
	}

	public static void removeLast(Node head) {
		if (head == null | head.next == null)
			return;
		Node temp = head;
		while (temp.next.next != null) {
			temp = temp.next;
		}
		temp.next = null;
	}

	public static void main(String[] args) {
		Linkedlist l1 = new Linkedlist();
		l1.addFront(44);
		l1.addFront(59);
		l1.addLast(60);
		l1.addLast(79);
		l1.view();
		removeLast(l1.head);
		l1.view();
	}
}
