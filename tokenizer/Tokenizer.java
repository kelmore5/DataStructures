package kelmore5.java.yeh.data_structures.tokenizer;

import kelmore5.java.yeh.data_structures.node.Token;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * <pre>
 * A tokenizer class to take a mathematical function as a String argument,
 * decode and turn into a binary search tree, and then perform the operations.
 * Keeps track of operations through tokens in a binary search tree.
 * </pre>
 *
 * @author kelmore5
 * @custom.date Fall 2012
 */
public class Tokenizer implements Runnable{
	//Create the root, currentToken, and first token of the binary search tree
	private Token<String> root;
	private String token;

	/**
	 * Instantiates a new Tokenizer class
	 */
	public Tokenizer(String string) {
		token = string;
		root = tokenize(string);
		while(root.getParent() != null) {
			root = root.getParent();
		}
	}

	/**
	 * Takes a string and performs the math operations specified
	 *
	 * @param string the string
	 */
	private Token<String> tokenize(String string) {
		Token<String> token = new Token<>(null);

		for(int i = 0; i < string.length(); i++) {
			String newString = "";
			char c = string.charAt(i);
			char next = ' ';
			if(i < string.length()-1) {
				next = string.charAt(i+1);
			}
			newString += c;

			if(c == '(') {
				if(token.getLeft() == null) {
					token = token.setLeft(new Token<>(token, null));
				}
				else {
					token = token.setRight(new Token<>(token, null));
				}
			}
			else if(Character.isDigit(c)) {
				while(Character.isDigit(next)) {
					newString += next;
					i++;
					next = string.charAt(i+1);
				}
				if(token.getLeft() == null) {
					token.setLeft(new Token<>(token, newString));
				}
				else {
					token.setRight(new Token<>(token, newString));
				}
			}
			else if(c == ')') {
				token = token.getParent();
			}
			else {
				while(!Character.isDigit(next) && next != '(' && next != ')') {
					newString += next;
					i++;
					next = string.charAt(i+1);
				}

				if(token.getOperator() == null) {
					token.setOperator(newString);
				}
				else {
					/*Token<String> temp = new Token<String>(token, null);
					temp.setOperator(newString);
					temp.setLeft(token.getRight());
					token.getRight().setParent(temp);
					token = token.setRight(temp);*/
					Token<String> temp = new Token<>(token.getParent(), null);
					temp.setOperator(newString);
					temp.setLeft(token);
					if(token.getParent().getLeft() == token) {
						token.getParent().setLeft(temp);
					}
					else {
						token.getParent().setRight(temp);
					}
					token = token.setParent(temp);
				}
			}
		}
		return token;
	}

	public String getToken() {
		return token;
	}

	public void run() {
		String string = "";

		int oldDepth = 0;
		Iterator<Token<String>> iter = iterator();
		while(iter.hasNext()) {
			Token<String> node = iter.next();
			if(oldDepth != node.getDepth()) {
				string += "\n";
				oldDepth = node.getDepth();
			}

			string += stringRepeat(" ", (int) (10/(Math.pow(2, node.getDepth())+1)));
			if(node.getDatum() != null) {
				string += node.getDatum();
			}
			else {
				string += node.getOperator();
			}
		}

		System.out.println(string);
	}

	/**
	 * Simple method to repeat a string x times
	 *
	 * @param string the string to repeat
	 * @param repeat the number of times to be repeated
	 * @return the string x times
	 */
	public String stringRepeat(String string, int repeat) {
		for(int i = 0; i < repeat; i++) {
			string += string;
		}
		return string;
	}

	public double evaulator() {
		evaulatorHelper(root);
		return Double.parseDouble(root.getDatum());
	}

	private void evaulatorHelper(Token<String> token) {
		if(token.getLeft().getOperator() != null) {
			evaulatorHelper(token.getLeft());
		}

		if(token.getRight().getOperator() != null) {
			evaulatorHelper(token.getRight());
		}

		token.setDatum("" + eval(Double.parseDouble(token.getLeft().getDatum()), Double.parseDouble(token.getRight().getDatum()), token.getOperator()));
		token.setOperator(null);
	}

	private double eval(double left, double right, String op) {
		if(op.contains("+")) {
			return left + right;
		}
		else if(op.contains("**")) {
			return Math.pow(left, right);
		}
		else if(op.contains("-")) {
			return left - right;
		}
		else if(op.contains("*")) {
			return left * right;
		}
		else {
			return 0;
		}
	}

	/**
	 * Iterator to go through binary search tree of operators/operations
	 *
	 * @return the iterator
	 */
	public Iterator<Token<String>> iterator() {
		return new BreadthFirstIterator();
	}

	private class BreadthFirstIterator implements Iterator<Token<String>> {
		/**
		 * A temporary queue to store tokens as the iterator process them
		 */
		Queue<Token<String>> mainQueue;

		/**
		 * Instantiates a new Breadth first iterator.
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
		public Token<String> next() {
			if(!hasNext()) throw new NoSuchElementException();
			Token<String> temp = mainQueue.poll();
			Token<String> left = temp.getLeft();
			Token<String> right = temp.getRight();
			if(left != null) {
				left.setDepth(temp.getDepth()+1);
				mainQueue.add(left);
			}
			else {
				mainQueue.add(new Token<>(temp.getDepth() + 1));
			}
			if(right != null) {
				right.setDepth(temp.getDepth()+1);
				mainQueue.add(right);
			}
			else {
				mainQueue.add(new Token<>(temp.getDepth() + 1));
			}

			boolean allNull = true;
			for(Token<String> t: mainQueue) {
				if(t.getDatum() != null || t.getOperator() != null) {
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

		}

	}
}