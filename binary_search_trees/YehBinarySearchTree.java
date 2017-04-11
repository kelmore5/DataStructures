package kelmore5.java.yeh.data_structures.binary_search_trees;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kelmore5.java.yeh.data_structures.node.LinkedNode;

/**
 * <pre class="doc_header">
 * <p>
 * </pre>
 *
 * @author kelmore5
 * @custom.date 3/18/17
 */
@SuppressWarnings({"unused", "ConstantConditions"})
public class YehBinarySearchTree<T extends Comparable<T>> implements Set<T> {
    /**
     * This class has one private variable, Node<T>[] root, that is the first array of the tree
     */
    private LinkedNode<T>[] root;

    /**
     * Sets root to null to show an empty list
     */
    public YehBinarySearchTree() {
        root = null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean contains(Object o) {
        // TODO Auto-generated method stub
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

    @Override
    public <t> t[] toArray(t[] a) {
        // TODO Auto-generated method stub
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean add(T e) {
        if(root == null) {
            root = new LinkedNode[4];
            root[0] = new LinkedNode<>(e);
            return true;
        }
        else {
            addHelper(root, e);
        }
        return true;
    }

    private void addHelper(LinkedNode<T>[] array, T datum) {
        for(LinkedNode<T> n: array) {
            if(n == null) {
                //noinspection UnusedAssignment
                n = new LinkedNode<>(datum);
                sort(array, 0, 3);
            }
        }
        for(LinkedNode<T> n: array) {
            if(datum.compareTo(n.getDatum()) < 0) {
                add(datum);
            }
        }
    }

    @Override
    public boolean remove(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        // TODO Auto-generated method stub
        return false;
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
        // TODO Auto-generated method stub

    }

    private void swap(LinkedNode<T>[] list, int first, int last) {
        LinkedNode<T> temp = list[last];
        list[last] = list[first];
        list[first] = temp;
    }

    private void sort(LinkedNode<T>[] list, int begin, int end) {
        int first = begin;
        int last = end;
        T pivot = list[begin + (end-begin)/2].getDatum();

        while (first <= last) {

            while(list[first].getDatum().compareTo(pivot) < 0) {
                first++;
            }

            while(list[last].getDatum().compareTo(pivot) > 0) {
                last--;
            }

            if (first <= last) {
                swap(list, first, last);
                first++;
                last--;
            }
        }
        if (begin < last)
            sort(list, begin, last);
        if (first < end)
            sort(list, first, end);
    }
}
