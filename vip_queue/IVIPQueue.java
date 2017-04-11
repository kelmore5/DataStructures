package kelmore5.java.yeh.data_structures.vip_queue;

/**
 * <pre class="doc_header">
 * <p>
 * </pre>
 *
 * @author kelmore5
 * @custom.date 3/19/17
 */
public interface IVIPQueue {
    /**
     * Checks if the queue is empty
     * @return true if empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Checks if the queue if full
     * @return true if full, false otherwise
     */
    boolean isFull();

    /**
     * Inserts an integer onto the front of the queue
     *
     * Pre-conditions: The queue is not full
     *
     * @param datum The integer to be inserted
     */
    void Enqueue(int datum);

    /**
     * Inserts an integer onto the back of the queue
     *
     * Pre-conditions: The queue is not full
     *
     * @param datum The integer to be inserted
     */
    void EnqueueVip(int datum);

    /**
     * Removes an integer from the queue
     *
     * Pre-conditions: The queue is not empty
     *
     * @return The integer being removed
     */
    int Dequeue();
}
