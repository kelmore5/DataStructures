package kelmore5.java.yeh.data_structures.priority_queue;

/**
 * An interface for a priority queue. A priority queue uses a first in, first out structure like queue,
 * but also gives each node in the queue a priority. Those with the highest priority will be moved
 * to the front of the queue to be removed first
 *
 * @param <T> the type parameter
 * @author kelmore5
 * @custom.date Fall 2012
 *
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/PriorityQueue.html">Priority Queue"</a>
 */
interface PriorityQueue<T> {
	/**
	 * Puts a new element onto the end of the queue with a given priority
	 *
	 * @param item     the item
	 * @param priority the priority
	 */
	void push(T item, int priority);

	/**
	 * Takes the element with the highest priority and latest time addition off the queue,
	 * with supremacy given to priority
	 *
	 * @return an element off the queue
	 */
	T pop();

	/**
	 * Checks to see if the queue is empty
	 *
	 * @return true if is empty, false otherwise
	 */
	boolean isEmpty();

	/**
	 * Returns the size of the queue
	 *
	 * @return the the size of the queue
	 */
	@SuppressWarnings("unused")
	int size();
}