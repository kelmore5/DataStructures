package kelmore5.java.yeh.data_structures.node;

/**
 * <pre class="doc_header">
 * <p>
 * </pre>
 *
 * @author kelmore5
 * @custom.date 3/14/17
 */
public class Token<T> extends ParentedNode<T> {
    private final String errorMessage = "Only another token node can be set as the left, right, or parent node";
    private T operator;

    public Token(T datum) {
        super(datum);
        operator = null;
    }

    public Token(int depth) {
        super(depth);
        operator = null;
    }

    public Token(Token<T> parent, T datum) {
        super(datum);
        this.parent = parent;
    }

    /**
     * Gets operator.
     *
     * @return the operator
     */
    public T getOperator() {
        return operator;
    }

    /**
     * Sets operator.
     *
     * @param operator the operator
     */
    public void setOperator(T operator) {
        this.operator = operator;
    }

    @Override
    public void setLeft(ParentedNode<T> n) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(errorMessage);
    }

    @Override
    public void setRight(ParentedNode<T> n) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(errorMessage);
    }


    @Override
    public void setParent(ParentedNode<T> node) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(errorMessage);
    }

    /**
     * Sets the left node of the parented node
     *
     * @param node the node to set as the left node
     * @return the argument being passed
     */
    public Token<T> setLeft(Token<T> node) {this.left = node; return node;}

    /**
     * Sets the right node of the parented node
     *
     * @param node the node to be set as the right node
     * @return the argument being passed
     */
    public Token<T> setRight(Token<T> node) {this.right = node; return node;}

    @Override
    public Token<T> getLeft() { return (Token<T>) this.left; }

    @Override
    public Token<T> getRight() { return (Token<T>) this.right; }

    /**
     * Sets parent of the node
     *
     * @param node the node
     * @return the argument being passed
     */
    public Token<T> setParent(Token<T> node) { this.parent = node; return node; }

    @Override
    public Token<T> getParent() { return (Token<T>) this.parent; }
}
