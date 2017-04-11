package kelmore5.java.yeh.data_structures.priority_queue.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import kelmore5.java.yeh.data_structures.node.BinarizedNode;
import kelmore5.java.yeh.data_structures.priority_queue.ArrayPriorityQueue;

/**
 * <pre>
 * This class takes an encoding phrase and returns a binary string of the encrypted binary search tree.
 * </pre>
 *
 * @author kelmore5
 * @custom.date Fall 2012
 */
class YehLetters {
	private ArrayPriorityQueue<BinarizedNode<String>> queue;	//A queue to put the tree in
	private BinarizedNode<String> root;							//The binary search tree to encode
	private String coded;										//The encoding string

	/**
	 * Instantiates a new Yeh letters class
	 *
	 * @param encoding the encoding string
	 */
	YehLetters(String encoding) {
		queue = new ArrayPriorityQueue<>();
		coded = encoding;
		getFrequency(encoding);			//Replace the tree with te encoding string (first run) and put into queue
		root = queue.pop();				//Get root of tree
		root.setBinary(false);
		createTree();					//Create preliminary tree

		encodeString();					//Encrypt the tree
	}

	/**
	 * Decode the encrypted binary tree using the encoding string
	 *
	 * @return a string representation of the binary tree
	 */
	String decodeString() {
		return decodeString(coded);
	}

	/**
	 * Get the encoding string
	 *
	 * @return the coding string
	 */
	String getCoding() {
		return coded;
	}

	/**
	 * Decode a binary search tree from storage
	 * @param coded The encryption string used to encode the tree
	 * @return The decoded tree
	 */
	private String decodeString(String coded) {
		String output = "";
		String[] code = coded.split(" ");

		for(String s: code) {
			if(s.length() == 1) {
				output += root.getDatum();
			}
			else {
				BinarizedNode<String> temp = root;
				for(char c: s.substring(1).toCharArray()) {
					if(c == '0') {
						temp = temp.getLeft();
					}
					else {
						temp = temp.getRight();
					}
				}
				output += temp.getDatum();
			}
		}
		
		return output;
	}

	/**
	 * Encode the binary string using the encryption key encoding
	 * @return A string representation of the encoded binary search tree
	 */
	private String encodeString() {
		String space = findSpace(root, "") + " ";
		coded = coded.replaceAll(" ", space);
		
		encodeHelper(root, "");
		
		return coded;
	}

	/**
	 * Used to recursively shift through the tree for encoding
	 * @param node	The current node
	 * @param code	The encoding string
	 */
	private void encodeHelper(BinarizedNode<String> node, String code) {
		//Return if null
		if(node == null) {
			return;
		}

		code += node.getBinary() ? "1" : "0";

		if(!node.getDatum().equals(" ")) {
			coded = coded.replaceAll(node.getDatum(), code + " ");
		}

		encodeHelper(node.getLeft(), code);
		encodeHelper(node.getRight(), code);
		
	}

	/**
	 * Recursive function to find all the empty spaces within the tree (so can ignore later when encoding)
	 * @param node The current node being looked at
	 * @param code The encoding string?
	 * @return A string representation of the tree with spaces
	 */
	private String findSpace(BinarizedNode<String> node, String code) {
		if(node == null) {
			return null;
		}

		code += node.getBinary() ? "1" : "0";

		if(node.getDatum().equals(" ")) {
			return code;
		}

		String code1 = findSpace(node.getLeft(), code);
		if(code1 != null) {
			return code1;
		}
		String code2 = findSpace(node.getRight(), code);
		if(code2 != null) {
			return code2;
		}

		return null;
	}

	private void getFrequency(String encoding) {
		Map<String, Integer> dict = new HashMap<>();
		for(char c: encoding.toCharArray()) {
			if(dict.keySet().contains("" + c)) {
				dict.put("" + c, dict.get("" + c)+1);
			}
			else {
				dict.put("" + c, 1);
			}
		}

		for(String c: dict.keySet()) {
			queue.push(new BinarizedNode<>(c), dict.get(c));
		}		
	}

	private void createTree() {
		Queue<BinarizedNode<String>> newNodes = new LinkedBlockingQueue<>();
		newNodes.add(root);

		while(!queue.isEmpty()) {
			BinarizedNode<String> newNode = queue.pop();
			newNodes.add(newNode);
			newNode.setParent(newNodes.peek());
			if(newNodes.peek().getLeft() == null) {
				newNodes.peek().setLeft(newNode);
				newNode.setBinary(false);
			}
			else {
				newNodes.poll().setRight(newNode);
				newNode.setBinary(true);
			}
		}
	}

	/**
	 * Get a breadth first iterator for the current binary search tree
	 *
	 * @return the iterator
	 */
	Iterator<BinarizedNode<String>> iterator() {
		return new BreadthFirstIterator<>(root);
	}

	private class BreadthFirstIterator<T> implements Iterator<BinarizedNode<T>> {
		/**
		 * The Main queue for the iterator
		 */
		Queue<BinarizedNode<T>> mainQueue;

		/**
		 * Instantiates a new Breadth first iterator.
		 *
		 * @param root the root of the tree
		 */
		BreadthFirstIterator(BinarizedNode<T> root) {
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
		public BinarizedNode<T> next() {
			if(!hasNext()) throw new NoSuchElementException();
			BinarizedNode<T> temp = mainQueue.poll();
			BinarizedNode<T> left = temp.getLeft();
			BinarizedNode<T> right = temp.getRight();
			if(left != null) {
				left.setDepth(temp.getDepth()+1);
				mainQueue.add(left);
			}
			else {
				mainQueue.add(new BinarizedNode<>(temp.getDepth()+1));
			}
			if(right != null) {
				right.setDepth(temp.getDepth()+1);
				mainQueue.add(right);
			}
			else {
				mainQueue.add(new BinarizedNode<>(temp.getDepth()+1));
			}

			boolean allNull = true;
			for(BinarizedNode<T> node: mainQueue) {
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
		public void remove() {}

	}
}
