package kelmore5.java.yeh.data_structures.dynamic_dictionary;

/**
 * <pre class="doc_header">
 * <p>
 * </pre>
 *
 * @author kelmore5
 * @custom.date 3/19/17
 */
@SuppressWarnings("unused")
public class AVL_DD implements IDynamicDictionary {
    private Node root;
    private int count;

    AVL_DD() {
        root = null;
        count = 0;
    }

    /**
     * Inserts data into the dictionary with the specified key
     * @param key The key for the data
     * @param data The information that relates to the data
     */
    public void insert(int key, String data) {
        root = insertHelper(root, key, data);
        count++;
    }

    /**
     * Helps with insertion of a key/data pair
     *
     * @param node The node to check the key of
     * @param key The key to insert
     * @param data The data to be inserted
     */
    private Node insertHelper(Node node, int key, String data) {
        if(node == null) {
            return new Node(null, null, key, data);
        }
        if(node.getKey() == key) {
            node.setData(data);
            count--;
        }
        else if(key < node.getKey())  {
            node.setLeft(insertHelper(node.getLeft(), key, data));

            if(heightHelper(node.getLeft()) - heightHelper(node.getRight()) == 2) {
                if(key < node.getLeft().getKey()) {
                    return rotateLeftChild(node);
                }
                else {
                    return doubleRotateLeftChild(node);
                }
            }
        }
        else {
            node.setRight(insertHelper(node.getRight(), key, data));
            if(heightHelper(node.getRight()) - heightHelper(node.getLeft()) == 2) {
                if(key > node.getRight().getKey()) {
                    return rotateRightChild(node);
                }
                else {
                    return doubleRotateRightChild(node);
                }
            }
        }
        return node;
    }

    /**
     * Removes a key from the dictionary
     * @param key The key to remove
     */
    public void remove(int key) {
        removeHelper(key, root);
    }

    /**
     * Helper function to remove a node. Finds the node and then calls a remove function
     * @param key The key to be removed
     * @param node The node to check
     */
    private void removeHelper(int key, Node node) {
        if(node == null) return;
        else if(node.getKey() == key) {
            node.setData("");
            count--;
        }

        if(key < node.getKey()) removeHelper(key, node.getLeft());
        else removeHelper(key, node.getRight());
    }

    /**
     * @return True if the dictionary has no elements, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Retrieves the data for a specified key
     * @param key The key to look up the data for
     * @return The data the key is pointing to
     */
    public String find(int key) {
        return findHelper(key, root);
    }

    /**
     * Helper function to find an look up the data for a given key
     *
     * @param key The key to look up
     * @param node The node to look in for the key
     * @return The data the key is pointing to
     */
    private String findHelper(int key, Node node) {
        if(node == null) return null;
        else if(node.getKey() == key) return node.getData();
        if(key < node.getKey()) return findHelper(key, node.getLeft());
        else return findHelper(key, node.getRight());
    }

    /**
     * @return The height of the tree
     */
    public int height() {
        return heightHelper(root);
    }

    /**
     * Height helper function to determine height of tree
     * @param node The node to determine the height of
     * @return The height of the tree
     */
    private int heightHelper(Node node) {
        if(node == null) return 0;
        return 1 + Math.max(heightHelper(node.getLeft()), heightHelper(node.getRight()));
    }

    /**
     * @return The number of keys in the dictionary
     */
    public int count() {
        return count;
    }

    /**
     * Performs a left right case rotation
     * @param node The node to be rotated
     * @return The newly rotated node
     */
    private Node rotateLeftChild(Node node) {
        Node temp = node.getLeft();
        node.setLeft(temp.getRight());
        temp.setRight(node);
        return temp;
    }

    /**
     * Performs a right left case rotation
     * @param node The node to be rotated
     * @return The newly rotated node
     */
    private Node rotateRightChild(Node node) {
        Node temp = node.getRight();
        node.setRight(temp.getLeft());
        temp.setLeft(node);
        return temp;
    }

    /**
     * Performs a left left case rotation
     * @param node The node to be rotated
     * @return The newly rotated node
     */
    private Node doubleRotateLeftChild(Node node) {
        node.setLeft(rotateRightChild(node.getLeft()));
        return rotateLeftChild(node);
    }

    /**
     * Performs a right right case rotation
     * @param node The node to be rotated
     * @return The newly rotated node
     */
    private Node doubleRotateRightChild(Node node) {
        node.setRight(rotateLeftChild(node.getRight()));
        return rotateRightChild(node);
    }
}
