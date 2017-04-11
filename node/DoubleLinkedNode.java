package kelmore5.java.yeh.data_structures.node;

/**
 * <pre class="doc_header">
 * A double linked node is similar to a {@link LinkedNode} where a node links to another
 * node in a series. The only difference is a double linked node also links to a previous
 * node, allowing a linked list to travel both forward and backward amongst its nodes.
 * </pre>
 *
 * @param <T> the type parameter
 * @author kelmore5
 * @custom.date Fall 2012
 */
public class DoubleLinkedNode<T> extends LinkedNode<T> {
    @SuppressWarnings("FieldCanBeLocal")
    private final String errorMessage = "Only another double linked node can be set as the next node";
    private DoubleLinkedNode<T> previous;

    /**
     * Instantiates a new double linked node. Sets previous to null and the datum.
     *
     * @param element the element
     */
    public DoubleLinkedNode(T element) {
        super(element);
        this.previous = null;
    }

    /**
     * Gets the previous node
     *
     * @return the previous node
     */
    public DoubleLinkedNode<T> getPrevious() {
        return previous;
    }

    /**
     * Sets the previous node
     *
     * @param _previous the previous node
     */
    public void setPrevious(DoubleLinkedNode<T> _previous) {
        previous = _previous;
    }

    public DoubleLinkedNode<T> getNext() { return (DoubleLinkedNode<T>) this.next; }

    @Override
    public void setNext(LinkedNode<T> n) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(errorMessage);
    }

    /**
     * Sets the next node
     *
     * @param next the next node in the series
     */
    public void setNext(DoubleLinkedNode<T> next) {this.next = next;}
}
