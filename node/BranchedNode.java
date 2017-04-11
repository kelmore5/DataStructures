package kelmore5.java.yeh.data_structures.node;

/**
 * <pre class="doc_header">
 * A node class with left and right branches
 * </pre>
 *
 * @param <T> the type parameter
 * @author kelmore5
 * @custom.date 3 /12/17
 */
public class BranchedNode<T> extends Node<T> {
    /**
     * The left node
     */
    BranchedNode<T> left;		    //Left branch
    /**
     * The right node
     */
    BranchedNode<T> right;		//Right branch

    /**
     * Instantiates a new branched Node with null values
     */
    BranchedNode() {
        initialize();
    }

    /**
     * Instantiates a new branched Node with datum datum
     *
     * @param datum the datum
     */
    BranchedNode(T datum) {
        super(datum);
        initialize();
    }

    /**
     * Initializes defaults into all class variables
     */
    private void initialize() {
        left = right = null;
    }

    /**
     * Gets the left branch of the node
     *
     * @return the left branching node
     */
    public BranchedNode<T> getLeft() {
        return left;
    }

    /**
     * Gets the right branch of the node
     *
     * @return the right branching node
     */
    public BranchedNode<T> getRight() {
        return right;
    }

    /**
     * Sets the left branch of the node to node n
     *
     * @param n The node to set as the left branch
     */
    @SuppressWarnings("unused")
    public void setLeft(BranchedNode<T> n) {
        left = n;
    }

    /**
     * Sets the right branch of the node to node n
     *
     * @param n The node to set as the right branch
     */
    @SuppressWarnings("unused")
    public void setRight(BranchedNode<T> n) {
        right = n;
    }
}
