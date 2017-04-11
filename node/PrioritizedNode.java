package kelmore5.java.yeh.data_structures.node;

import kelmore5.java.yeh.data_structures.priority_queue.ArrayPriorityQueue;

/**
 * <pre class="doc_header">
 * A prioritized node is a node with an int priority. The priority is how important the node is,
 * or in other words, at what point in a stack it should be accessed.
 * Nodes with higher priorities will be accessed before nodes with lower ones.
 * It is used by {@link ArrayPriorityQueue}
 * </pre>
 *
 * @param <T> the type parameter
 * @author kelmore5
 * @custom.date 3 /13/17
 */
public class PrioritizedNode<T> extends Node<T> {
    private int priority;

    /**
     * Instantiates a new Prioritized node
     */
    @SuppressWarnings("unused")
    public PrioritizedNode() {
        super();
        this.priority = 0;
    }

    /**
     * Instantiates a new Prioritized node with a defined datum and priority
     *
     * @param datum    the datum
     * @param priority the priority
     */
    public PrioritizedNode(T datum, int priority) {
        this.datum = datum;
        this.priority = priority;
    }

    /**
     * Gets the priority of the node
     *
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }
}
