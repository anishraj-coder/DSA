package Tree;

import java.util.LinkedList;
import java.util.Queue;

public class Tree {

	Node root;

	Tree(int n) {
		root = new Node(n);
	}

	void addLeft(Node root, int n) {
		Node nw = new Node(n);
		root.left = nw;
	}

	void addRight(Node root, int n) {
		Node nw = new Node(n);
		root.right = nw;
	}

	void view() {
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		while (q.size() > 0) {
			int n = q.size();
			for (int i = 0; i < n; i++) {
				Node rem = q.remove();
				System.out.print(rem.val + " ");
				if (rem.left != null)
					q.add(rem.left);
				if (rem.right != null)
					q.add(rem.right);
			}
			System.out.println();
		}
		System.out.println();
	}

}