package kelmore5.java.yeh.data_structures.node;

/**
 * <pre class="doc_header">
 * A linked node is a node used in a linked list. It only has a datum and next node parameter.
 * A linked node uses the next parameter to link to the next nodes in a list.
 * </pre>
 *
 * @param <T> the type parameter
 * @author kelmore5
 * @custom.date Fall 2012
 */
public class LinkedNode<T> extends Node<T> {
    protected LinkedNode<T> next;

    /**
     * Instantiates a new Linked node<br />
     * Sets datum and next to null
     */
    public LinkedNode() {
        super();
        next = null;
    }


    /**
     * Instantiates a new Linked node with specified datum
     *
     * @param datum the datum
     */
    public LinkedNode(T datum) {
        super(datum);
        next = null;
    }

    /**
     * Instantiates a new Linked node with specified datum and next node
     * @param datum the datum of the node
     * @param next the next node
     */
    public LinkedNode(T datum, LinkedNode<T> next) {
        this(datum);
        this.next = next;
    }

    /**
     * Gets the next node in the series
     *
     * @return the next
     */
    public LinkedNode<T> getNext() {
        return next;
    }

    /**
     * Sets the next node
     *
     * @param next the next node in the series
     */
    public void setNext(LinkedNode<T> next) {
        this.next = next;
    }
}
