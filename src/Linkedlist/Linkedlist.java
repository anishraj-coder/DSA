package Linkedlist;

class Node {
	int val;
	Node next;

	Node() {
		this.next = null;
	}

	Node(int n) {
		this.val = n;
		this.next = null;
	}

	Node(int n, Node node) {
		this.val = n;
		this.next = node;
	}
}

public class Linkedlist {

	Node head;

	Linkedlist() {
		this.head = null;
	}

	Linkedlist(int val) {
		this.addFront(val);
	}

	public void view() {
		Node temp = head;
		while (temp != null) {
			System.out.print(temp.val + ", ");

			temp = temp.next;
		}
		System.out.println();
	}

	public void addFront(int val) {

		Node new_node = new Node(val);
		new_node.next = head;
		head = new_node;
	}

	public void removeFront() {
		if (head == null)
			return;
		head = head.next;
	}

	public void addLast(int val) {
		Node temp = head;
		while (temp.next != null) {
			temp = temp.next;
		}
		Node new_node = new Node(val);
		temp.next = new_node;
	}

//	public void removeLast(Node head) {
//		Node temp=head;
//		while((temp.next).next!=0) {}
//		
//	}
	public static void main(String[] args) {
		Linkedlist l1 = new Linkedlist();
		l1.addFront(44);
		l1.addFront(88);
		l1.addFront(77);
		l1.addLast(66);
		l1.view();
	}
}
