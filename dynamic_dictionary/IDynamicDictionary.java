package kelmore5.java.yeh.data_structures.dynamic_dictionary;

/**
 * <pre class="doc_header">
 * <p>
 * </pre>
 *
 * @author kelmore5
 * @custom.date 3/19/17
 */
public interface IDynamicDictionary {
    /**
     * Inserts data into the dictionary with the specified key
     * @param key The key for the data
     * @param data The information that relates to the data
     */
    void insert(int key, String data);

    /**
     * Removes a key from the dictionary
     * @param key The key to remove
     */
    void remove(int key);

    /**
     * @return True if the dictionary has no elements, false otherwise
     */
    boolean isEmpty();

    /**
     * Retrieves the data for a specified key
     * @param key The key to look up the data for
     * @return The data the key is pointing to
     */
    String find(int key);

    /**
     * @return The height of the tree
     */
    int height();

    /**
     * @return The number of keys in the dictionary
     */
    int count();
}
