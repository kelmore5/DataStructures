package kelmore5.java.yeh.data_structures.node;

/**
 * <pre class="doc_header">
 * A binarized node is a parented node with the ability to set a binary value of true or false for each node.
 * </pre>
 *
 * @param <T> the type parameter
 * @author kelmore5
 * @custom.date 3/13/17
 */
public class BinarizedNode<T> extends ParentedNode<T> {
    private final String errorMessage = "Only another binarized node can be set as the left or right node";
    private boolean binary;

    /**
     * Instantiates a new Binarized node with the datum as datum
     *
     * @param datum the datum to initialize
     */
    public BinarizedNode(T datum) {
        super(datum);
        initialize();
    }

    /**
     * Instantiates a new Binarized node with the depth _depth
     *
     * @param _depth the depth of the node
     */
    public BinarizedNode(int _depth) {
        super(_depth);
        initialize();
    }

    /**
     * Initialize binary to false
     */
    private void initialize() {
        binary = false;
    }

    /**
     * Gets the binary value for the node
     *
     * @return the binary, true or false
     */
    public boolean getBinary() {
        return binary;
    }

    /**
     * Sets the binary value of the node
     *
     * @param _binary the binary to set
     */
    public void setBinary(boolean _binary) {
        binary = _binary;
    }

    @Override
    public String toString(){
        return "" + this.datum;
    }

    @Override
    public void setLeft(ParentedNode<T> n) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(errorMessage);
    }

    @Override
    public void setRight(ParentedNode<T> n) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(errorMessage);
    }

    /**
     * Sets the left node of the binarized node
     *
     * @param node the node to set as the left node
     */
    public void setLeft(BinarizedNode<T> node) {this.left = node;}

    /**
     * Sets the right node of the binarized node
     *
     * @param node the node to be set as the right node
     */
    public void setRight(BinarizedNode<T> node) {this.right = node;}

    @Override
    public BinarizedNode<T> getLeft() {
        return (BinarizedNode<T>) this.left;
    }

    @Override
    public BinarizedNode<T> getRight() {
        return (BinarizedNode<T>) this.right;
    }
}
