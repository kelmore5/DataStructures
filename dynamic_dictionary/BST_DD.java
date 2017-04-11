package kelmore5.java.yeh.data_structures.dynamic_dictionary;

/**
 * <pre class="doc_header">
 * <p>
 * </pre>
 *
 * @author kelmore5
 * @custom.date 3/19/17
 */
public class BST_DD implements IDynamicDictionary {
    private Node root;
    private int count;

    BST_DD() {
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
        else if(key < node.getKey()) node.setLeft(insertHelper(node.getLeft(), key, data));
        else node.setRight(insertHelper(node.getRight(), key, data));
        return node;
    }

    /**
     * Removes a key from the dictionary
     * @param key The key to remove
     */
    public void remove(int key) {
        root = removeHelper(key, root);
    }

    /**
     * Helper function to remove a node
     * @param key The key to be removed
     * @param node The node to check
     */
    private Node removeHelper(int key, Node node) {
        if(node == null) return null;

        if(key == node.getKey()) {
            if(node.getLeft() == null) {
                return node.getRight();
            }
            else if(node.getRight() == null) {
                return node.getLeft();
            }
            else {
                node.setKey(findMax(node.getLeft()));

                node.setLeft(removeHelper(node.getKey(), node.getLeft()));
            }
        }
        else {
            if(key < node.getKey()) {
                node.setLeft(removeHelper(key, node.getLeft()));
            }
            else {
                node.setRight(removeHelper(key, node.getRight()));
            }
        }

        return node;
    }

    /**
     * Finds the max node below a sublet of the tree
     * @param node The node to start at
     * @return The maximum node
     */
    private int findMax(Node node) {
        if(node == null) return 0;

        Node temp = node.getRight();
        if(temp == null) return node.getKey();
        else return findMax(temp);
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
}
