package kelmore5.java.yeh.data_structures.linked_lists;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * <pre class="doc_header">
 * A replica of the java.util.List interface. Only using this to define the layout
 * for linked lists implementations
 * </pre>
 *
 * @author kelmore5
 * @custom.date Fall 2012
 */
@SuppressWarnings("ConstantConditions")
public abstract class ListAdapter<E> implements List<E> {

    /**
     * Appends the specified element to the end of this list (optional operation).
     */
    public boolean add(E o) {
        return false;
    }

    /**
     * Inserts the specified element at the specified position in this list (optional operation).
     */
    public void add(int index, E element) {}

    /**
     * Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator (optional operation).
     */
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    /**
     * Inserts all of the elements in the specified collection into this list at the specified position (optional operation).
     */
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }


    /**
     * Removes all of the elements from this list (optional operation).
     */
    public void clear() {}

    /**
     * Returns true if this list contains the specified element.
     */
    public boolean contains(Object o)  {
        return false;
    }

    /**
     * Returns true if this list contains all of the elements of the specified collection.
     */
    public boolean containsAll(Collection<?> c)  {
        return false;
    }

    /**
     * Compares the specified object with this list for equality.
     */
    public boolean equals(Object o)  {
        return false;
    }

    /**
     * Returns the element at the specified position in this list.
     */
    public E get(int index) {
        return null;
    }

    /**
     * Returns the hash code value for this list.
     */
    public int hashCode() {
        return -1;
    }

    /**
     * Returns the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
     */
    public int indexOf(Object o) {
        return -1;
    }

    /**
     * Returns true if this list contains no elements.
     */
    public boolean isEmpty()  {
        return true;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     */
    public Iterator<E> iterator() { return null; }


    /**
     * Returns the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element.
     */
    public int lastIndexOf(Object o) {
        return -1;
    }


    /**
     * Returns a list iterator of the elements in this list (in proper sequence).
     */
    public ListIterator<E> listIterator() { return null; }


    /**
     * Returns a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
     */
    public ListIterator<E> listIterator(int index) {
        return null;
    }


    /**
     * Removes the element at the specified position in this list (optional operation).
     */
    public E remove(int index) {
        return null;
    }


    /**
     * Removes the first occurrence in this list of the specified element (optional operation).
     */
    public boolean remove(Object o)  {
        return false;
    }


    /**
     * Removes from this list all the elements that are contained in the specified collection (optional operation).
     */
    public boolean removeAll(Collection<?> c)  {
        return false;
    }


    /**
     * Retains only the elements in this list that are contained in the specified collection (optional operation).
     */
    public boolean retainAll(Collection<?> c)  {
        return false;
    }


    /**
     * Replaces the element at the specified position in this list with the specified element (optional operation).
     */
    public E set(int index, E element) {
        return null;
    }


    /**
     * Returns the number of elements in this list.
     */
    public int size() {
        return 0;
    }


    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
     */
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }


    /**
     * Returns an array containing all of the elements in this list in proper sequence.
     */
    public Object[] toArray() {
        return null;
    }


    /**
     * Returns an array containing all of the elements in this list in proper sequence; the runtime type of the returned array is that of the specified array.
     */
    public <T> T[] toArray(T[] a) {
        return null;
    }
}