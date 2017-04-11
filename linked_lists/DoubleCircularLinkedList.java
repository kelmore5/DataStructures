package kelmore5.java.yeh.data_structures.linked_lists;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import kelmore5.java.yeh.data_structures.node.DoubleLinkedNode;

/**
 * <pre class="doc_header">
 * A Double Circular Linked List is similar to a {@link CircularLinkedList}. Where a Circular Linked List
 * allows the user to travel continuously through the linked list (looping from the back to the front of
 * the list), a double circular linked list has the additional ability to travel backward as well as forward.
 * </pre>
 *
 * @param <T> the type parameter
 * @author kelmore5
 * @custom.date Fall 2012
 */
@SuppressWarnings({"unused", "unchecked"})
public class DoubleCircularLinkedList<T>  extends ListAdapter<T> {
    private DoubleLinkedNode<T> first;

    /**
     * Instantiates a new Double circular linked list. Sets root node to null
     */
    public DoubleCircularLinkedList() {
        first = null;
    }

    public boolean add(T element) {
        //Check special cases
        //If first is null, set first. Then set next node to first
        //If first is set, fix first so series does not loop to first
        //Otherwise, add node to end of list and set last node to have first
        //  as next node
        if(first == null) {
            first = new DoubleLinkedNode<>(element);
            first.setPrevious(first);
            first.setNext(first);
        }
        else if(first.getNext() == first) {
            DoubleLinkedNode<T> temp = new DoubleLinkedNode<>(element);
            first.setPrevious(temp);
            temp.setPrevious(first);
            temp.setNext(first);
            first.setNext(temp);
        }
        else {
            DoubleLinkedNode<T> temp = new DoubleLinkedNode<>(element);
            temp.setPrevious(first.getPrevious());
            temp.setNext(first);
            first.getPrevious().setNext(temp);
            first.setPrevious(temp);
        }

        return true;
    }

    public void add(int position, T element) {
        //Check if position greater than size. Throw error if so
        if(position > size()) {
            throw new IndexOutOfBoundsException();
        }

        DoubleLinkedNode<T> newNode = new DoubleLinkedNode<>(element);
        int counter = 0;

        //If position first, set root node and set root node to have itself as the next node
        //Otherwise, set a new node
        if(position == 0) {
            newNode.setNext(first);
            newNode.setPrevious(first.getPrevious());
            first.getPrevious().setNext(newNode);
            first.setPrevious(newNode);
            first = newNode;
        }
        else {
            DoubleLinkedNode<T> temp = first;
            while(temp.getNext() != first) {
                if(counter == position-1) {
                    newNode.setPrevious(temp);
                    newNode.setNext(temp.getNext());
                    newNode.getNext().setPrevious(newNode);
                    newNode.getPrevious().setNext(newNode);
                }

                counter++;
                temp = temp.getNext();
            }
        }

    }

    public boolean addAll(Collection<? extends T> c) {
        //Call add function on entire collection
        Iterator temp = c.iterator();
        for(int i = 0; i < c.size(); i++) {
            this.add((T) temp.next());
        }
        return true;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        //Check if position greater than index
        if(index > size()) throw new IndexOutOfBoundsException();
        Iterator<T> citer = (Iterator<T>) c.iterator();

        //Otherwise, create two temporary double linked lists
        DoubleLinkedNode<T> temp = new DoubleLinkedNode<>(citer.next());
        DoubleLinkedNode<T> firstTemp = temp;

        //Create double linked list from collection
        for(int i = 0; i < c.size()-1; i++) {
            DoubleLinkedNode<T> temp2 = new DoubleLinkedNode<>(citer.next());
            temp.setNext(temp2);
            temp2.setPrevious(temp);
            temp = temp2;
        }

        //Finally, add new linked list to this linked list
        //Check if linked list empty (if so, set new one to this one)
        //Otherwise, loop through this linked list and put new linked list
        //  in between the two nodes at the index
        if(index == 0) {
            first.getPrevious().setNext(firstTemp);
            firstTemp.setPrevious(first.getPrevious());
            temp.setNext(first);
            first.setPrevious(temp);
            first = firstTemp;
        }
        else {
            DoubleLinkedNode<T> temp2 = first;
            for(int i = 0; i < index-1; i++) {
                temp2 = temp2.getNext();
            }

            temp.setNext(temp2.getNext());
            temp2.getNext().setPrevious(temp);
            temp2.setNext(firstTemp);
            firstTemp.setPrevious(temp2);
        }

        return true;
    }

    public void clear() {
        first = null;
    }

    public boolean contains(Object o) {
        //Check if empty
        //Otherwise, loop through array to find object
        if(isEmpty())
            return false;
        else {
            boolean contains = false;
            DoubleLinkedNode<T> temp = first;
            while(temp.getNext() != first) {
                if(temp.getDatum().equals(o)) {
                    contains = true;
                }
                temp = temp.getNext();
            }
            if(temp.getDatum().equals(o))
                contains = true;
            return contains;
        }
    }

    public boolean containsAll(Collection<?> c) {
        //Check if any of the items are not in the linked list
        //If not, return false. Otherwise, true
        for(Object e: c) {
            if(!this.contains(e))
                return false;
        }
        return true;
    }

    public boolean equals(Object o) {
        //Create temporary linked list
        DoubleCircularLinkedList<T> temp;

        //Make sure instanceof DoubleCircularLinkedList
        //If not, return false
        if(o instanceof DoubleCircularLinkedList)
            temp = (DoubleCircularLinkedList<T>) o;
        else
            return false;

        //Check if sizes equal. If not, return false
        if(temp.size() != size())
            return false;

        //Finally, loop through list and check if nodes
        //equal to each other
        ListIterator<T> tempI = temp.listIterator();
        ListIterator<T> iter = listIterator();
        for(int i = 0; i < size(); i++) {
            if(!(iter.next().equals(tempI.next()))) {
                return false;
            }
        }

        //Return true if all cases pass
        return true;
    }

    public T get(int index) {
        //Check size
        if(index >= size())
            throw new IndexOutOfBoundsException();

        //Loop through array to index. Get datum
        ListIterator<T> temp = listIterator();
        for(int i = 0; i < index; i++) {
            temp.next();
        }
        return temp.next();
    }

    public int indexOf(Object o) {
        //Set params
        boolean found = false;
        int counter = 0;

        //Loop through list to find object
        DoubleLinkedNode<T> temp = first;
        while(temp.getNext() != first) {
            if(temp.getDatum().equals(o)) {
                found = true;
                break;
            }
            counter++;
            temp = temp.getNext();
        }
        if(!(first.getPrevious().getDatum().equals(o) && !found)) {
            counter = -1;
        }
        return counter;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Iterator<T> iterator() {
        return new YehListIterator();
    }

    public int lastIndexOf(Object o) {
        //Set params
        int counter;
        boolean equalsFirst= false;

        //Check if object is in first datum
        //If not, loop backwards through list
        if(first.getDatum().equals(o)) {
            equalsFirst = true;
            counter = 0;
        }
        else {
            DoubleLinkedNode temp = first.getPrevious();
            counter = size()-1;
            while(temp.getPrevious() != first.getPrevious()) {
                if(temp.getDatum().equals(o)) {
                    break;
                }
                counter--;
                temp = temp.getPrevious();
            }
        }
        if(equalsFirst) {
            return 0;
        }
        return counter;
    }

    public ListIterator<T> listIterator() {
        return new YehListIterator();
    }

    public ListIterator<T> listIterator(int index) {
        //Check if index greater than size. If so, throw error
        //Otherwise, get iterator and then loop through until it reaches the index
        if(index > size()-1) throw new IndexOutOfBoundsException();
        ListIterator<T> iter = new YehListIterator();
        for(int i = 0; i < index; i++) {
            iter.next();
        }
        return iter;
    }

    public boolean remove(Object element) {
        //Check if list is empty
        //If not, loop through array
        if(isEmpty()) {
            return false;
        }
        else {
            DoubleLinkedNode<T> temp = first;

            //Check if found datum
            //If so, create new links between nodes to remove node
            //Otherwise, loop through array to find datum
            if(temp.getDatum().equals(element)) {
                temp.getNext().setPrevious(first.getPrevious());
                first.getPrevious().setNext(first.getNext());
                first = temp.getNext();
                return true;
            }
            else {
                while(temp.getNext() != first) {
                    if(temp.getNext().getDatum().equals(element)) {
                        temp.setNext(temp.getNext().getNext());
                        temp.getNext().setPrevious(temp);
                        return true;
                    }
                    temp = temp.getNext();
                }
            }
            return false;
        }
    }

    public T remove(int index) {
        //Check if index is greater than size
        if(index > size()-1) {
            throw new IndexOutOfBoundsException();
        }

        T element = null;

        //Check special case if index zero.
        //If so, remove first node
        //Otherwise, loop through list and then remove
        if(index == 0) {
            element = first.getDatum();
            first.getNext().setPrevious(first.getPrevious());
            first.getPrevious().setNext(first.getNext());
            first = first.getNext();
        }
        else {
            int counter = 0;
            DoubleLinkedNode<T> temp = first;
            while(temp.getNext() != first) {
                if(counter == index-1) {
                    element = temp.getNext().getDatum();
                    temp.setNext(temp.getNext().getNext());
                    temp.getNext().setPrevious(temp);
                    break;
                }

                counter++;

                temp = temp.getNext();
            }
        }

        return element;
    }

    public boolean removeAll(Collection<?> c) {
        //Call remove on all elements in collection (loop through and execute)
        Iterator<T> iter = (Iterator<T>) c.iterator();
        while(iter.hasNext()) {
            remove(iter.next());
        }
        return true;
    }

    public boolean retainAll(Collection<?> c) {
        removeIf(t -> !c.contains(t));
        return true;
    }

    public T set(int index, T element) {
        //Check if index out of bounds
        if(index > size()-1) throw new IndexOutOfBoundsException();

        //If not, get iterator, loop through to index, set datum of node
        ListIterator<T> iter = listIterator();
        T temp = null;
        for(int i = 0; i < index; i++) {
            temp = iter.next();
        }
        iter.set(element);
        return temp;
    }

    public int size() {
        //Get counter for size
        int counter = 0;

        //Check if list created. If not, return 0
        //Otherwise, loop through until have entire list (incrementing counter)
        //Return counter
        if(first == null) {
            return 0;
        }
        else {
            if(first.getNext() == first) {
                return 1;
            }
            else {
                DoubleLinkedNode<T> temp = first.getNext();
                counter++;

                while(temp != first) {
                    counter++;

                    temp = temp.getNext();
                }

                return counter;
            }
        }
    }

    public LinkedList<T> subList(int begin, int end) {
        //Check if end within size limits
        if(end > size()) throw new IndexOutOfBoundsException();

        //Create params
        LinkedList<T> temp = new LinkedList<>();
        ListIterator<T> iter = listIterator();

        //Loop through array to beginning
        //Once there, start adding nodes to temporary linked list
        //Loop to end, return list
        for(int i = 0; i < end; i++) {
            if(i >= begin) {
                temp.add(iter.next());
            }
            else {
                iter.next();
            }
        }
        return temp;
    }

    public Object[] toArray() {
        //Get size and create array
        int size = size();
        Object[] o = new Object[size];

        //Get iterator, loop through, add to array, return array
        ListIterator<T> iter = listIterator();
        for(int i = 0; i < size; i++) {
            o[i] = iter.next();
        }
        return o;
    }

    public String toString() {
        if(first == null)
            return "[]";
        else if(first.getNext() == first)
            return "[" + first.getDatum() + "]";

        String string = "[";
        DoubleLinkedNode<T> temp = first;

        string += temp.getDatum() + ", ";

        temp = temp.getNext();

        while(temp != first) {
            string += temp.getDatum() + ", ";
            temp = temp.getNext();
        }

        string = string.substring(0, string.lastIndexOf(","));
        //string += " - " + first.getPrevious().getNext().getElement();
        string += "]";

        return string;
    }

    private class YehListIterator implements ListIterator<T> {
        /**
         * The index of the current iterator
         */
        int index;
        /**
         * The current next node
         */
        DoubleLinkedNode<T> next, /**
         * The previous node
         */
        previous, /**
         * The current node
         */
        current;

        private YehListIterator() {
            //Set params
            index = 0;
            if(!isEmpty()) {
                next = first;
                previous = null;
                current = first;
            }
        }

        public void add(T element) {
            //Create temp node
            DoubleLinkedNode<T> temp = new DoubleLinkedNode<>(element);
            if(index == 0)
                first = temp;

            //Add between next and previous
            //Increment index
            temp.setNext(next);
            temp.setPrevious(previous);
            previous.setNext(temp);
            next.setPrevious(temp);
            current = null;
            index++;
        }

        public boolean hasNext() {
            return (next != null);
        }

        public boolean hasPrevious() {
            return (previous != null);
        }
        public T next() throws NoSuchElementException {
            //Check if iterator has next
            if(!hasNext()) throw new NoSuchElementException("");

            //Check if previous is null.
            //If so, set first to previous
            //Otherwise, set previous to previous.next
            if(previous == null)
                previous = first;
            else
                previous = previous.getNext();

            //Increment all other nodes in iterator
            next = next.getNext();
            current = next;

            //Check if next and first are equal
            //If so, set next to null
            if(next == first) {
                next = null;
            }

            //Check if current node equals first (root)
            //Set index to zero
            //Otherwise, increment index
            if(current == first) {
                index = 0;
            }
            else {
                index++;
            }
            return previous.getDatum();
        }

        public int nextIndex() {
            //Check if list is empty
            //If so, return zero
            if(!isEmpty()) {

                //Check if current node is equal to first's previous node
                //(e.g. one element in list)
                //If so, return zero
                //Otherwise, return index + 1
                if(current == first.getPrevious()) {
                    return 0;
                }
                else {
                    return index+1;
                }
            }
            else {
                return 0;
            }
        }

        public T previous() throws NoSuchElementException {
            //Check if has previous
            if(!hasPrevious()) throw new NoSuchElementException("");

            //Check if only one element in list
            //If so, set index to 1
            //Otherwise, decrement index and continue
            if(index == 0) {
                index = size()-1;
            }
            else {
                index--;
            }

            //Update class nodes to prevoius node
            next = next.getPrevious();
            current = previous;
            previous = previous.getPrevious();

            //Check if previous node is equal to first.previous
            //If so, set previous to null
            if(previous == first.getPrevious())
                previous = null;

            //Return datum
            return next.getDatum();
        }

        public int previousIndex() {
            //Check if list is empty
            //If so, return zero
            if(!isEmpty()) {

                //Check if index is at first element
                //If so, return 1. Otherwise, return index - 1
                if(index == 0) {
                    return size()-1;
                }
                else {
                    return index-1;
                }
            }
            else {
                return 0;
            }
        }

        public void remove() {
            //Check if current node is null (If so, no nodes in list and throw error)
            if(current != null) {

                //Check if only one element in list. If so, set first to its next node
                if(index == 0)
                    first = current.getNext();

                //Update current node's links to remove node
                //Decrement index
                current.getNext().setPrevious(current.getPrevious());
                current.getPrevious().setNext(current.getNext());
                index--;
                current = null;
            }
            else {
                throw new NullPointerException();
            }
        }

        public void set(T element) {
            //If current node does equal null, set datum
            //Otherwise, throw error
            if(current != null) {
                current.setDatum(element);
            }
            else {
                throw new NullPointerException();
            }
        }

    }
}
