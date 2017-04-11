package kelmore5.java.yeh.data_structures.binary_search_trees;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * <pre class="doc_header">
 * <p>
 * </pre>
 *
 * @author kelmore5
 * @custom.date 3/18/17
 */
@SuppressWarnings("ConstantConditions")
public class nAryTree<T extends Comparable<? super T>> implements Set<T>
{
    /**
     * This class has one private variable, Node<T>[] root, that is the first array of the tree
     */
    private Node<T>[] root;

    /**
     * Sets root to null to show an empty list
     */
    public nAryTree() {
        root = null;
    }

    /**
     * This is a private class that creates nodes for the arrays
     *
     * @author Teh anonymous hackzorz
     *
     * @param <t>
     */
    @SuppressWarnings("hiding")
    private class Node<t> {
        t cargo;
        public Node<t>[] left, right;

        Node(t datum) {
            cargo = datum;
            left = null;
            right = null;
        }
    }

    @Override
    public int size() {
        return sizeHelper(root);
    }

    private int sizeHelper(Node<T>[] node) {
        if(node == null) {
            return 0;
        }
        else {
            return count(node) + ((node[0] == null)? 0 : sizeHelper(node[0].left)) + ((node[1] == null? 0: sizeHelper(node[1].left) + sizeHelper(node[1].right)));
        }
    }

    private int count(Node<T>[] node) {
        return ((node[0] == null)? 0 : 1) + ((node[1] == null)? 0: 1);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean contains(Object o) {
        return containsHelper(root, o);
    }

    private boolean containsHelper(Node<T>[] node, Object o) {
        return nodeContains(node, o) || (node[0] != null && containsHelper(node[0].left, o)) || (node[1] != null && (containsHelper(node[1].left, o) || containsHelper(node[1].right, o)));
    }

    @SuppressWarnings("unchecked")
    private boolean nodeContains(Node<T>[] node, Object o) {
        return (node[0] != null && node[0].cargo.compareTo((T) o) == 0) || (node[1] != null && node[1].cargo.compareTo((T) o) == 0);
    }

    @Override
    public boolean remove(Object o) {
        return removeHelper(root, o);
    }

    private boolean removeHelper(Node<T>[] node, Object o) {
        return nodeRemove(node, o) || ((node[0] != null && removeHelper(node[0].left, o)) || ((node[1] != null && (removeHelper(node[1].left, o) || removeHelper(node[1].right, o)))));
    }

    @SuppressWarnings("unchecked")
    private boolean nodeRemove(Node<T>[] node, Object o) {
        if(node[0] != null) {
            if(node[0].cargo.compareTo((T) o) == 0) {
                node[0].cargo = null;
                return true;
            }
        }
        if(node[1] != null) {
            if(node[1].cargo.compareTo((T) o) == 0) {
                node[1].cargo = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object[] toArray() {
        // TODO Auto-generated method stub
        return null;
    }

    @SuppressWarnings("hiding")
    @Override
    public <t> t[] toArray(t[] a) {
        // TODO Auto-generated method stub
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean add(T e) {
        if(root == null) {
            root = new Node[2];
            root[0] = new Node<>(e);
            root[0].left = new Node[2];
            root[0].right = new Node[2];
        }
        else {
            addHelper(root, e);
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private boolean addHelper(Node<T>[] array, T datum) {
        for(int i = 0; i < 2; i++) {
            if(array[i] == null) {
                array[i] = new Node<>(datum);
                array[i].left = new Node[2];
                array[i].right = new Node[2];
                if(i == 1) {
                    if(array[0].cargo.compareTo(array[1].cargo) > 0) {
                        swap(array);
                    }
                }
                return true;
            }
        }
        for(Node<T> n: array) {
            if(datum.compareTo(n.cargo) < 0) {
                if(addHelper(n.left, datum))
                    return true;
            }
        }
        return addHelper(array[1].right, datum);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for(T t: c) {
            this.add(t);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public String toString() {
        return stringHelper(root, 0);
    }

    private String stringHelper(Node<T>[] node, int level) {
        return (node == null)? "" : (((node[0] == null) ? "" : stringHelper(node[0].left, level+1)) + stringRepeat(" ", level) + nodeString(node) +
                ((node[1] == null) ? "" : stringHelper(node[1].left, level+1) + stringHelper(node[1].right, level+1)));
    }

    private String nodeString(Node<T>[] node) {
        return ((node[0] == null && node[1] == null) ? "" : ("(" + ((node[0] == null)? "null" : "" + node[0].cargo) + ", " + ((node[1] == null)? "null" : "" + node[1].cargo) + ")\n"));
    }

    private String stringRepeat(String string, int repeat) {
        for(int i = 0; i < repeat; i++) {
            string += string;
        }
        return string;
    }

    @SuppressWarnings("unchecked")
    private void swap(Node<T>[] list) {
        Node<T> temp = list[1];
        list[0].right = temp.right;
        temp.right = new Node[2];
        list[1] = list[0];
        list[0] = temp;
    }

    public static void main(String[] args) {
        nAryTree<Integer> yt = new nAryTree<>();
        yt.add(8);
        yt.add(12);
        yt.add(9);
        yt.add(7);
        yt.add(10);
        yt.add(15);
        yt.add(5);
        yt.add(6);

        System.out.println(yt.toString());

        yt.remove(8);

        System.out.println(yt.toString());
    }
}