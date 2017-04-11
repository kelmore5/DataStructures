package kelmore5.java.yeh.data_structures.node;

/**
 * <pre class="doc_header">
 * A node class with placeholders for both depth and parent nodes
 * </pre>
 *
 * @param <T> the type parameter
 * @author kelmore5
 * @custom.date 3 /12/17
 */
public class ParentedNode<T> extends BranchedNode<T> {
    private final String errorMessage = "Only another parented node can be set as the left or right node";
    protected ParentedNode<T> parent;	//Parent branch
    private int depth;			        //How deep the node is in the tree

    /**
     * ParentedNode default constructor. Initializes all values to null
     */
    @SuppressWarnings("unused")
    ParentedNode() {
        super();
        initialize();
    }

    /**
     * Instantiates a new ParentedNode with datum _datum and depth zero
     *
     * @param datum the datum
     */
    public ParentedNode(T datum) {
        super(datum);
        initialize();
    }

    /**
     * Instantiates a new ParentedNode with depth _depth
     *
     * @param _depth the depth of the node
     */
    public ParentedNode(int _depth) {
        initialize();
        depth = _depth;
    }

    /**
     * Instantiates a new ParentedNode with the datum _datum and parent _parent
     *
     * @param _datum  the datum of the node
     * @param _parent the parent of the node
     */
    public ParentedNode(T _datum, ParentedNode<T> _parent) {
        this(_datum);
        parent = _parent;
    }

    /**
     * Initializes defaults into all class variables
     */
    private void initialize() {
        parent = null;
        depth = 0;
    }

    /**
     * Sets depth of the node
     *
     * @param _depth the depth
     */
    public void setDepth(int _depth) {
        depth = _depth;
    }

    /**
     * <pre>
     * Gets depth of the node
     * I.e. What level in a binary search tree the node is currently at
     * </pre>
     *
     * @return the depth
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Gets parent of the node
     *
     * @return the parent
     */
    public ParentedNode<T> getParent() {
        return parent;
    }

    /**
     * Sets parent of the node
     *
     * @param node the node
     */
    public void setParent(ParentedNode<T> node) {
        parent = node;
    }

    @Override
    public void setLeft(BranchedNode<T> n) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(errorMessage);
    }

    @Override
    public void setRight(BranchedNode<T> n) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(errorMessage);
    }

    /**
     * Sets the left node of the parented node
     *
     * @param node the node to set as the left node
     */
    public void setLeft(ParentedNode<T> node) {this.left = node;}

    /**
     * Sets the right node of the parented node
     *
     * @param node the node to be set as the right node
     */
    public void setRight(ParentedNode<T> node) {this.right = node;}

    @Override
    public ParentedNode<T> getLeft() {
        return (ParentedNode<T>) this.left;
    }

    @Override
    public ParentedNode<T> getRight() {
        return (ParentedNode<T>) this.right;
    }
}
