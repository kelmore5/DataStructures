package kelmore5.java.yeh.data_structures.vip_queue;

/**
 * <pre class="doc_header">
 * <p>
 * </pre>
 *
 * @author kelmore5
 * @custom.date 3/19/17
 */
@SuppressWarnings("unused")
public class ArrayVIPQueue implements IVIPQueue {
    /**
     * front The index of the front of the queue
     * end The index of the end of the queue
     * queue The array of the queue
     */
    private int front;
    private int end;
    private Integer[] queue;

    /**
     * Constructor Specifies size of queue
     * @param size the size of the queue
     */
    ArrayVIPQueue(int size) {
        front = 0;
        end = 0;
        queue = new Integer[size];
    }

    /**
     * Checks if the queue is empty
     * @return true if queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return (front == end) && queue[front] == null;
    }

    /**
     * Checks if the queue is full
     * @return true if queue is full, false otherwise
     */
    public boolean isFull() {
        return (front == end) && queue[front] != null;
    }

    /**
     * Adds an integer to the end of the queue
     *
     * Pre-conditions: The queue is not full
     *
     * @param datum integer to add to queue
     */
    public void Enqueue(int datum) {
        queue[end++] = datum;
        if(end == queue.length) {
            end = 0;
        }
    }

    /**
     * Adds an integer to the front of the queue
     *
     * Pre-conditions: The queue is not full
     *
     * @param datum datum integer to add to the queue
     */
    public void EnqueueVip(int datum) {
        front--;
        if(front == -1) {
            front = queue.length-1;
        }
        queue[front] = datum;
    }

    /**
     * Removes an integer from the queue
     *
     * Pre-conditions: The queue is not empty
     *
     * @return the datum being removed
     */
    public int Dequeue() {
        int temp = queue[front];
        queue[front] = null;

        front++;
        if(front == queue.length) {
            front = 0;
        }

        return temp;
    }
}
