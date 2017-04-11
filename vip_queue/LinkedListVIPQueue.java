package kelmore5.java.yeh.data_structures.vip_queue;

/**
 * <pre class="doc_header">
 * <p>
 * </pre>
 *
 * @author kelmore5
 * @custom.date 3/19/17
 */
public class LinkedListVIPQueue implements IVIPQueue {
    /**
     * front The front of the queue
     * back The back of the queue
     * count How many items are in the queue
     * size The maximum size of the queue
     */
    private Node front;
    private Node back;
    private int count;
    private int size;

    /**
     * Constructor Creates queue with specified size
     * @param size Maximum size of the queue
     */
    LinkedListVIPQueue(int size) {
        front = null;
        back = null;
        count = 0;
        this.size = size;
    }

    /**
     * Checks if the queue is empty
     *
     * @return true if queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return front == null || front.datum == null;
    }

    /**
     * Checks if the queue is full
     *
     * @return true if queue is full, false otherwise
     */
    @Override
    public boolean isFull() {
        return count == size;
    }

    /**
     * Inserts an integer onto the end of the queue
     *
     * Pre-conditions: The queue is not full
     *
     * @param datum The datum to be inserted
     */
    @Override
    public void Enqueue(int datum) {
        if(front == null) {
            back = new Node();
            back.front = front;
            front = new Node(null, back, datum);
        }
        else if(back.datum == null) {
            back.datum = datum;
        }
        else {
            back.back = new Node(back, null, datum);
            back = back.back;
        }

        count++;
    }

    /**
     * Inserts an integer onto the front of the queue
     *
     * Pre-conditions: The queue is not full
     *
     * @param datum The datum to be inserted
     */
    @Override
    public void EnqueueVip(int datum) {
        if(front == null) {
            back = new Node();
            front = new Node(null, back, datum);
        }
        else {
            front.front = new Node(null, front, datum);
            front = front.front;
        }

        count++;
    }

    /**
     * Removes an integer from the front of the queue and returns it
     *
     * Pre-conditions: The queue is not empty
     *
     * @return The integer being removed
     */
    @Override
    public int Dequeue() {
        int temp = front.datum;
        front = front.back;
        count--;
        return temp;
    }

    /**
     *
     * @author Kyle Elmore
     *
     * Node class. Individual nodes in the linked list. Holds the datum and what comes next and before it
     */
    private static class Node {
        /**
         * front The node in front of this node
         * back The node behind this node
         * datum The integer this node holds
         */
        public Node front;
        public Node back;
        public Integer datum;

        /**
         * No-arg Constructor Creates the node class and initializes variables
         */
        public Node() {
            this(null, null, null);
        }

        /**
         * Constructor Creates the node class with specified datum
         *
         * @param front The node in front of this node
         * @param back The node behind this node
         * @param datum The Integer for the node
         */
        public Node(Node front, Node back, Integer datum) {
            this.front = front;
            this.back = back;
            this.datum = datum;
        }
    }
}
