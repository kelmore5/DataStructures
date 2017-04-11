package kelmore5.java.yeh.data_structures.binary_search_trees;

import java.util.ArrayList;
import kelmore5.java.yeh.data_structures.node.BranchedNode;

/**
 * <pre class="doc_header">
 * An implementation of an AVL Tree. An AVL tree is a self-balancing tree. As nodes are
 * placed into the binary tree and it becomes unbalanced, the tree will self adjust towards
 * having an equal number of nodes on its branches.
 * </pre>
 *
 * @param <T> the type parameter
 * @author kelmore5
 * @custom.date 3 /14/17
 * @see <a href="https://en.wikipedia.org/wiki/AVL_tree">AVL Tree</a>
 */
@SuppressWarnings("unused")
public class AVLTree<T extends Comparable<T>>
{
    @SuppressWarnings({"FieldCanBeLocal", "MismatchedQueryAndUpdateOfCollection"})
    private ArrayList<T> realEntries;
    private BranchedNode<T> root;

    /**
     * Instantiates a new AVL tree. Sets root to null and instantiates an ArrayList
     */
    public AVLTree()
    {
        root = null;
        realEntries = new ArrayList<>();
    }

    /**
     * Checks whether the tree is empty or not
     *
     * @return True if empty, false otherwise
     */
    public boolean isEmpty()
    {
        return root == null;
    }

    /**
     * Tranverses through the tree through an in order operation<br />
     * E.g. From left to right
     *
     * @see <a href="https://en.wikipedia.org/wiki/Tree_traversal">Tree traversal</a>
     */
    public void showLDR()
    {
        showHelperLDR(root);
    }

    /**
     * Helper method for traveling through the tree (in order)
     * @param n The current position of the traversal
     */
    private void showHelperLDR(BranchedNode<T> n)
    {
        if(n != null)
        {
            showHelperLDR(n.getLeft());
            System.out.println(n.getDatum());
            showHelperLDR(n.getRight());
        }
    }

    /**
     * Tranverses through the tree through a post order operation<br />
     * E.g. From left bottom to right top
     *
     * @see <a href="https://en.wikipedia.org/wiki/Tree_traversal">Tree traversal</a>
     */
    public void showRDL()
    {
        showHelperRDL(root);
    }

    /**
     * Helper method for traveling through the tree (post order)
     * @param n The current position of the traversal
     */
    private void showHelperRDL(BranchedNode<T> n)
    {
        if(n != null)
        {
            showHelperRDL(n.getRight());
            System.out.println(n.getDatum());
            showHelperRDL(n.getLeft());
        }
    }

    /**
     * Gets the depth of the tree
     *
     * @return the depth of the tree
     */
    public int depth()
    {

        return depthHelper(root) ;
    }

    /**
     * Recursive helper method for getting the depth of the tree
     * @param n The current position of the traversal
     * @return The depth of the tree
     */
    private int depthHelper(BranchedNode<T> n)
    {
        if (n == null)
            return 0;
        int left = depthHelper(n.getLeft());
        int right = depthHelper(n.getRight());
        return 1 + left< right?  right: left;
    }

    /**
     * Gets the current size of the tree (how many items in total)
     *
     * @return the size of the tree
     */
    public int size()
    {
        return sizeHelper(root);
    }

    /**
     * Recursive helper method for determining the size of the tree
     * @param n The current node in the traversal
     * @return The size of the tree
     */
    private int sizeHelper(BranchedNode<T> n)
    {
        if(n == null)
            return 0;
        return 1 + sizeHelper(n.getLeft()) + sizeHelper(n.getRight()    );
    }
}
