package Algorithms_Java;

import java.io.*;
import java.util.Stack;

public class BST {
    public static class Node {
		int data;
		Node left;
		Node right;

		Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	public static class Pair {
		Node node;
		int state;

		Pair(Node node, int state) {
			this.node = node;
			this.state = state;
		}
	}

	public static Node construct(Integer[] arr) {
		Node root = new Node(arr[0], null, null);
		Pair rtp = new Pair(root, 1);

		Stack<Pair> st = new Stack<>();
		st.push(rtp);

		int idx = 0;
		while (st.size() > 0) {
			Pair top = st.peek();
			if (top.state == 1) {
				idx++;
				if (arr[idx] != null) {
					top.node.left = new Node(arr[idx], null, null);
					Pair lp = new Pair(top.node.left, 1);
					st.push(lp);
				} else {
					top.node.left = null;
				}

				top.state++;
			} else if (top.state == 2) {
				idx++;
				if (arr[idx] != null) {
					top.node.right = new Node(arr[idx], null, null);
					Pair rp = new Pair(top.node.right, 1);
					st.push(rp);
				} else {
					top.node.right = null;
				}

				top.state++;
			} else {
				st.pop();
			}
		}

		return root;
	}

	public static void display(Node node) {
		if (node == null) {
			return;
		}

		String str = "";
		str += node.left == null ? "." : node.left.data + "";
		str += " <- " + node.data + " -> ";
		str += node.right == null ? "." : node.right.data + "";
		System.out.println(str);

		display(node.left);
		display(node.right);
	}

	public static int size(Node node) {
		if (node == null) {
			return 0;
		}
		int leftSize = size(node.left);
		int rightSize = size(node.right);
		int mySize = leftSize + rightSize + 1;
		return mySize;
	}

	public static int sum(Node node) {
		if (node == null) {
			return 0;
		}
		int leftSum = sum(node.left);
		int rightSum = sum(node.right);
		int mySum = leftSum + rightSum + node.data;
		return mySum;
	}

	public static int max(Node node) {
		if (node.right != null) {
			return max(node.right);
		} else
			return node.data;
	}

	public static int min(Node node) {
		if (node.left != null) {
			return min(node.left);
		} else
			return node.data;
	}

	public static boolean find(Node node, int data) {
		if (node == null) {
			return false;
		}

		if (data > node.data) {
			return find(node.right, data);
		} else if (data < node.data) {
			return find(node.left, data);
		} else
			return true;
	}

	public static Node add(Node node, int data) {
		if (node == null) {
			Node nn = new Node(data, null, null);
			return nn;
		}
		if (data > node.data) {
			node.right = add(node.right, data);
		} else if (data < node.data)
			node.left = add(node.left, data);
		else {

		}
		return node;
	}

	public static Node remove(Node node, int data) {
		if (node == null) {
			return null;
		}
		if (data > node.data) {
			node.right = remove(node.right, data);
		} else if (data < node.data) {
			node.left = remove(node.left, data);
		} else {
			if (node.left != null && node.right != null) {
				int leftKaMax = max(node.left);
				node.data = leftKaMax;
				node.left = remove(node.left, leftKaMax);
				return node;
			} else if (node.left != null) {
				return node.left;
			} else if (node.right != null) {
				return node.right;
			} else {
				return null;
			}
		}
		return node;
	}

	static int sum = 0;

	public static void rwsol(Node node) {
		if (node == null)
			return;
		rwsol(node.right);
		int origData = node.data;
		node.data = sum;
		sum += origData;
		rwsol(node.left);
	}

	public static int lca(Node node, int d1, int d2) {
		if (node == null) {
			return 0;
		}
		if (node.data < d1 && node.data < d2) {
			return lca(node.right, d1, d2);
		} else if (node.data > d1 && node.data > d2) {
			return lca(node.left, d1, d2);
		} else
			return node.data;
	}

	public static void pir(Node node, int d1, int d2) {
		if (node == null) {
			return;
		}
		pir(node.left, d1, d2);
		if (node.data >= d1 && node.data <= d2) {
			System.out.println(node.data);
		}
		pir(node.right, d1, d2);
	}

	public static void printTargetPair(Node root, Node node, int target) {
		if (node == null) {
			return;
		}
		printTargetPair(root, node.left, target);
		int restValue = target - node.data;
		if (node.data < restValue) {
			if (find(root, restValue)) {
				System.out.println(node.data + " " + restValue);
			}
		}
		printTargetPair(root, node.right, target);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Integer[] arr = new Integer[n];
		String[] values = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			if (values[i].equals("n") == false) {
				arr[i] = Integer.parseInt(values[i]);
			} else {
				arr[i] = null;
			}
		}

		int data = Integer.parseInt(br.readLine());

		Node root = construct(arr);

		int size = size(root);
		int sum = sum(root);
		int max = max(root);
		int min = min(root);
		boolean found = find(root, data);

		System.out.println(size);
		System.out.println(sum);
		System.out.println(max);
		System.out.println(min);
		System.out.println(found);
	}
}
