package kelmore5.java.yeh.data_structures.node;

/**
 * <pre class="doc_header">
 * A simple node class
 * </pre>
 *
 * @param <T> the type parameter
 * @author kelmore5
 * @custom.date 3 /12/17
 */
public class Node<T> {
    T datum;

    /**
     * Instantiates a new Node with a null element
     */
    Node() {
        datum = null;
    }

    /**
     * Instantiates a new Node with element _datum
     *
     * @param _datum the datum
     */
    Node(T _datum) {
        datum = _datum;
    }

    /**
     * Gets datum of the node
     *
     * @return the datum
     */
    public T getDatum() {
        return datum;
    }

    /**
     * Sets the datum of the node
     *
     * @param datum the new value of datum
     */
    public void setDatum(T datum) {this.datum = datum;}
}
