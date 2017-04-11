package kelmore5.java.yeh.data_structures.queue;

/**
 * <pre class="doc_header">
 * An interface for a queue
 *
 * For condensed implementations, see {@link kelmore5.java.data.structures.queue.Queue}
 * </pre>
 *
 * @param <E> the type parameter
 * @author kelmore5
 * @custom.date Fall 2012
 *
 * @see <a href="https://en.wikipedia.org/wiki/Queue_(abstract_data_type)">Queue</a>
 */

@SuppressWarnings("unused")
interface Queue<E> {
	/**
	 * Remove the element at the front of the stack and return it.
	 *
	 * @return The element at the front of the stack
	 */
	E pop();

	/**
	 * Look at the first element in the stack without removing it.
	 *
	 * @return The first element in the stack
	 */
	E peek();

	/**
	 * Checks whether the stack is empty or not.
	 *
	 * @return True if stack is empty, false otherwise
	 */
	boolean isEmpty();

	/**
	 * Place an item on the end of the stack.
	 *
	 * @param obj The element to add to the stack
	 *
	 */
	void push(E obj);
}
