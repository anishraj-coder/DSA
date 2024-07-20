package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class MyTree<T> {
	@SuppressWarnings("hiding")
	class Node<T> {
		T val;
		Node<T> left;
		Node<T> right;

		Node(T n) {
			this.val = n;
			this.left = null;
			this.right = null;
		}
	}

	Node<T> root;

	MyTree(T n) {
		root = new Node<>(n);
	}

	void addLeft(Node<T> node, T n) {
		Node<T> new_node = new Node<>(n);
		node.left = new_node;
	}

	void addRight(Node<T> node, T n) {
		Node<T> new_node = new Node<>(n);
		node.right = new_node;
	}

	void view() {
		Queue<Node<T>> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			int n = q.size();
			for (int i = 0; i < n; i++) {
				Node<T> rem = q.remove();
				System.out.print(rem.val + " ");
				if (rem.left != null)
					q.add(rem.left);
				if (rem.right != null)
					q.add(rem.right);

			}
			System.out.println();
		}
	}

	int size(Node<T> root) {
		if (root == null)
			return 0;
		int x = size(root.left);
		int y = size(root.right);
		return x + y + 1;
	}

	ArrayList<T> reverseLevelOrder(Node<T> node) {
		// code here
		Stack<Node<T>> s = new Stack<Node<T>>();

		Queue<Node<T>> q = new LinkedList<>();

		ArrayList<T> t = new ArrayList<>();
		if (node != null) {
			q.add(node);
			s.push(node);
		}

		while (q.size() > 0) {
			Node<T> temp = q.remove();
			// t.Add(temp.data);

			if (temp.right != null) {
				q.add(temp.right);
				s.push(temp.right);
			}
			if (temp.left != null) {
				q.add(temp.left);
				s.push(temp.left);
			}

		}

		while (s.size() > 0) {
			Node<T> temp = s.pop();
			t.add(temp.val);
		}

		return t;
	}

	ArrayList<ArrayList<T>> zigZagTraverse(Node<T> root) {
		Stack<Node<T>> ms = new Stack<>();
		Stack<Node<T>> cs = new Stack<>();
		ArrayList<ArrayList<T>> ans = new ArrayList<>();
		ms.push(root);
		int level = 1;
		while (ms.size() > 0) {
			ArrayList<T> temp = new ArrayList<>();
			while (ms.size() > 0) {
				Node<T> rem = ms.pop();
				temp.add(rem.val);
				if (level % 2 != 0) {
					if (rem.left != null)
						cs.push(rem.left);
					if (rem.right != null)
						cs.push(rem.right);

				} else {
					if (rem.right != null)
						cs.push(rem.right);
					if (rem.left != null)
						cs.push(rem.left);
				}
			}
			ans.add(temp);
			level++;
			Stack<Node<T>> t = cs;
			cs = ms;
			ms = t;
		}
		return ans;
	}

}

public class Basic {

	public static void main(String[] args) {
		MyTree<Integer> ro = new MyTree<>(5);
		ro.addLeft(ro.root, 44);
		ro.addRight(ro.root, 4);
		ro.addLeft(ro.root.left, 6);
		ro.addRight(ro.root.left, 34);
		ro.addLeft(ro.root.right, 23);
		ro.addRight(ro.root.right, 29);
		ro.addLeft(ro.root.right.right, 56);
		ro.addRight(ro.root.right.right, 69);
		ro.view();
		System.out.println("Size:\t" + ro.size(ro.root));
		ArrayList<Integer> ans = ro.reverseLevelOrder(ro.root);
		for (Integer ele : ans) {
			System.out.print(ele + " ");
		}
		System.out.println("______________________________________");
		ArrayList<ArrayList<Integer>> ans2 = new ArrayList<>();
		ans2 = ro.zigZagTraverse(ro.root);
		for (int i = 0; i < ans2.size(); i++) {
			for (int j = 0; j < ans2.get(i).size(); j++)
				System.out.print(ans2.get(i).get(j) + " ");
			System.out.println();
		}
	}

}
