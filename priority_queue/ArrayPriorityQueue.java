package kelmore5.java.yeh.data_structures.priority_queue;

import kelmore5.java.yeh.data_structures.node.PrioritizedNode;

import java.util.NoSuchElementException;


/**
 * An implementation of priority queue using an array of nodes.
 *
 * @param <T> the type parameter
 * @author kelmore5
 * @custom.date Fall 2012
 *
 * @see PriorityQueue
 */
public class ArrayPriorityQueue<T> implements PriorityQueue<T> {
	private PrioritizedNode<T>[] array;
	private int front, back;

	/**
	 * Instantiates a new Array priority queue
	 */
	@SuppressWarnings("unchecked")
	public ArrayPriorityQueue() {
		array = (PrioritizedNode<T>[])(new PrioritizedNode[100]);
		front = back = 0;
	}

	/**
	 * Checks for the first element of the queue and returns it without removing the node
	 *
	 * @return the next element out of the queue
	 * @throws NoSuchElementException if queue is empty, throws a no such element exception
	 */
	@SuppressWarnings("unused")
	public T peek() throws NoSuchElementException
	{
		//If empty, throw exception
		//Otherwise, return datum at front of queue without removing
		if(isEmpty()) throw new NoSuchElementException();
		return array[front].getDatum();
	}
	
	public T pop() throws NoSuchElementException {
		if(isEmpty()) throw new NoSuchElementException();
		return array[front++].getDatum();
	}
	
	public boolean isEmpty() {
		return front == back;
	}

	@Override
	public void push(T item, int priority) {
		//Push new item to the back of the queue
		array[back++] = new PrioritizedNode<>(item, priority);

		//Check if item is the first element. If so, return
		int temp = back-2;
		if(temp < 0) {
			return;
		}

		//Else, swap the new item to wherever it needs to be in the queue
		//based on priority
		while(array[temp].getPriority() < priority) {
			swap(array, temp, temp+1);
			temp--;
			//Break if the front of the queue is reached
			if(temp < 0) {
				break;
			}
		}
	}

	@Override
	public int size() {
		return back;
	}
	
	@Override
	public String toString() {
		String string = "[";
		int index = front;
		while(index < back) {
			string += "(" + array[index].getDatum() + ", " + array[index].getPriority() + "), ";
			index++;
		}
		
		string = string.substring(0, string.length()-2);
		string += "]";
		return string;
	}

	/**
	 * Method to swap two elements in an array
	 *
	 * @param list  the list to perform a swap on
	 * @param first the first element to be swapped
	 * @param last  the second element to be swapped
	 */
	private void swap(PrioritizedNode<T>[] list, int first, int last) {
		PrioritizedNode<T> temp = list[last];
		list[last] = list[first];
		list[first] = temp;
	}

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		//Push some elements to the queue for testing
		//and perform pops/printing
		ArrayPriorityQueue<String> q = new ArrayPriorityQueue<>();
		q.push("Homework", 9);
		q.push("Stuff", 10);
		q.push("Chiquito", 12);
		q.push("Lolz", 5);
		q.push("More stuff", 10);
		
		System.out.println(q.toString());
		
		System.out.println(q.pop());
		System.out.println(q);
	}
	
}