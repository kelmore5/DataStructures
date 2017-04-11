package kelmore5.java.yeh.data_structures.binary_search_trees;

import kelmore5.java.yeh.data_structures.node.ParentedNode;
import kelmore5.java.yeh.data_structures.node.Node;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <pre>
 * A Splay Tree implementation.
 * A splay tree is a self-adjusting binary search tree which takes the most commonly accessed items
 * in the tree and moves them closer to the root node.
 *
 * Advantages: comparable performance to other binary search trees and small memory footprint
 * Disadvantages: The height can become linear if all elements are accessed in non-decreasing order,
 * 					causing O(n) performance rather than O(log n)
 *
 * Commonly used for caches and garbage collection
 *
 * </pre>
 *
 * @param <T> the type parameter
 * @author kelmore5
 * @custom.date Fall 2012
 * @see <a href="https://en.wikipedia.org/wiki/Splay_tree">Splay Tree</a>
 */
public class SplayTree<T extends Comparable<T>> {
	private ParentedNode<T> root;

	/**
	 * Instantiates a new Splay tree.
	 */
	public SplayTree() {
		root = null;
	}

	/**
	 * Checks whether the splay tree is empty or not
	 *
	 * @return True if the tree is empty, false otherwise
	 */
	private boolean isEmpty() {
		return root == null;
	}

	/**
	 * Add the element datum to the splay tree
	 *
	 * @param datum the datum to add to the tree
	 */
	public void add(T datum) {
		//If empty, create root/tree
		//Else, add the datum to the tree
		if(isEmpty()) {
			root = new ParentedNode<>(datum);
			return;
		}
		addHelper(root, datum);
	}

	/**
	 * Method to carry out the load of add
	 *
	 * @param node  the node currently being looked at
	 * @param datum the datum to add to the splay tree
	 */
	private void addHelper(ParentedNode<T> node, T datum) {
		if(node.getDatum().compareTo(datum) > 0) {
			if(node.getLeft() == null) {
				ParentedNode<T> left = new ParentedNode<>(datum, node);
				node.setLeft(left);
				//leftRotation(left);
				return;
			}
			addHelper(node.getLeft(), datum);
		}
		else {
			if(node.getRight() == null) {
				ParentedNode<T> right = new ParentedNode<>(datum, node);
				node.setRight(right);
				return;
			}
			addHelper(node.getRight(), datum);
		}
	}

	/**
	 * Splay.
	 *
	 * @param node the node
	 */
	@SuppressWarnings("unused")
	private void splay(ParentedNode<T> node) {
		if(node != root) {
			if (node.getParent() == root) {
				if (node == node.getParent().getLeft()) {
					root = rightRotation(node);
				} else {
					root = leftRotation(node);
				}
			} else {
				ParentedNode<T> parent = node.getParent();
				ParentedNode<T> grandpa = parent.getParent();

				if (node == parent.getLeft() && parent == grandpa.getLeft()) {
					root = rightRotation(grandpa);
					root = rightRotation(parent);
				} else if (node == parent.getRight() && parent == grandpa.getRight()) {
					root = leftRotation(grandpa);
					root = leftRotation(parent);
				} else if (node == parent.getRight() && parent == grandpa.getLeft()) {
					root = leftRotation(parent);
					root = rightRotation(grandpa);
				} else if (node == parent.getLeft() && parent == grandpa.getRight()) {
					root = rightRotation(parent);
					root = leftRotation(grandpa);
				}

				splay(node);
			}
		}
	}

	/**
	 * Performs a zig-zag step (right) rotation
	 *
	 * @param node the node to perform the rotation on
	 * @return the node replacing the node being rotated
	 */
	private ParentedNode<T> rightRotation(ParentedNode<T> node) {
		if(node.getLeft() == null) {
			node.setLeft(new ParentedNode<>(null));
		}
		ParentedNode<T> left = node.getLeft();
		node.setLeft(left.getRight());
		if(left.getRight() != null) {
			left.getRight().setParent(node);
		}

		left.setRight(node);

		if(node.getParent() != null) {
			if(node == node.getParent().getRight()) {
				node.getParent().setRight(left);
			}
			else {
				node.getParent().setLeft(left);
			}
		}

		left.setParent(node.getParent());

		node.setParent(left);
		if(node == root) {
			return left;
		}
		else {
			return root;
		}
	}

	/**
	 * Performs a zig-zig step (left) rotation
	 *
	 * @param node the node to perform the rotation on
	 * @return the replacement node for the node being rotated
	 */
	private ParentedNode<T> leftRotation(ParentedNode<T> node) {
		if(node.getRight() == null) {
			node.setRight(new ParentedNode<>(null));
		}
		ParentedNode<T> right = node.getRight();
		node.setRight(right.getLeft());
		if(right.getLeft() != null) {
			right.getLeft().setParent(node);
		}
		right.setLeft(node);

		if(node.getParent() != null) {
			if(node == node.getParent().getLeft()) {
				node.getParent().setLeft(right);
			}
			else {
				node.getParent().setRight(right);
			}
		}

		right.setParent(node.getParent());

		node.setParent(right);

		if(node == root) {
			return right;
		}
		else {
			return root;
		}
	}

	/**
	 * Creates an iterable class to step through the binary tree
	 *
	 * @return the iterator
	 */
	public Iterator<ParentedNode<T>> iterator() {
		return new BreadthFirstIterator();
	}

	private class BreadthFirstIterator implements Iterator<ParentedNode<T>> {
		/**
		 * A queue used to look through the array
		 */
		Queue<ParentedNode<T>> mainQueue;

		/**
		 * Creates a breadth first iterator
		 * So, the iterator will start at the tree root and return
		 * the values of neighboring roots first
		 *
		 * @see <a href="https://en.wikipedia.org/wiki/Breadth-first_search">Breadth-first search</a>
		 */
		BreadthFirstIterator() {
			mainQueue = new LinkedBlockingQueue<>();
			if(root != null) {
				mainQueue.add(root);
			}
		}

		@Override
		public boolean hasNext() {
			return !mainQueue.isEmpty();
		}

		@Override
		public ParentedNode<T> next() {
			if(!hasNext()) throw new NoSuchElementException();
			ParentedNode<T> temp = mainQueue.poll();
			ParentedNode<T> left = temp.getLeft();
			ParentedNode<T> right = temp.getRight();
			if(left != null) {
				left.setDepth(temp.getDepth()+1);
				mainQueue.add(left);
			}
			else {
				mainQueue.add(new ParentedNode<>(temp.getDepth()+1));
			}
			if(right != null) {
				right.setDepth(temp.getDepth()+1);
				mainQueue.add(right);
			}
			else {
				mainQueue.add(new ParentedNode<>(temp.getDepth()+1));
			}

			boolean allNull = true;
			for(Node<T> node: mainQueue) {
				if(node.getDatum() != null) {
					allNull = false;
				}
			}
			
			if(allNull) {
				mainQueue.clear();
			}

			return temp;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
		}
	}
}
