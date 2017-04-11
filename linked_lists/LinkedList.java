package kelmore5.java.yeh.data_structures.linked_lists;

import java.util.NoSuchElementException;
import kelmore5.java.yeh.data_structures.node.LinkedNode;

/**
 * <pre class="doc_header">
 * An implementation of a Linked List. A Linked List is similar to an array structure as it
 * keeps track of a list of items, but instead of using an array, it uses a series of nodes
 * to store all its items.
 * </pre>
 *
 * @param <T> the type parameter
 * @author kelmore5
 * @custom.date Fall 2012
 * @see <a href="https://en.wikipedia.org/wiki/Linked_list">Linked List</a>
 */
public class LinkedList<T> extends ListAdapter<T> {
    /**
     * The root of the linked list
     */
    private LinkedNode<T> top;

    /**
     * Instantiates a new Linked list.
     */
    LinkedList()
    {
        top = null;
    }

    public boolean add(T newItem)
    {
        this.add(this.size(), newItem);
        return true;
    }

    public void add(int index, T newItem)
    {
        if(index == 0)
        {
            this.addFirst(newItem);
        }
        else if(index < 0)
            throw new IndexOutOfBoundsException();
        else
        {
            addHelper(top, top.getNext(), 1, index, newItem);
        }
    }

    /**
     * Helper method to add an element to the linked list at a specified index.
     * Trickles through the linked list until it reaches the place in the series
     * where the item is being added, and then adds it in
     * @param current The current node in the series
     * @param next The next node in the series
     * @param placeMarker The location of the current node
     * @param index Where to add the item in the series
     * @param newItem The new node
     */
    private void addHelper(LinkedNode<T> current, LinkedNode<T> next, int placeMarker, int index, T newItem)
    {
        //Check if at placemarker. If not, continue down list
        //If so, add item to list
        if(placeMarker != index)
        {
            if(current.getNext() == null)
                throw new IndexOutOfBoundsException();
            placeMarker++;
            addHelper(current.getNext(), next.getNext(), placeMarker, index, newItem);
        }
        else
        {
            LinkedNode<T> newLink = new LinkedNode<>(newItem, next);
            current.setNext(newLink);
        }
    }

    /**
     * Add item to the beginning of the list
     *
     * @param newItem the new item
     */
    private void addFirst(T newItem)
    {
        //If empty, make top the new item
        //Otherwise, ake the new item the top and then
        //make the current top node the second node in the series
        if(isEmpty()) {
            top = new LinkedNode<>(newItem);
        }
        else {
            top = new LinkedNode<>(newItem, top);
        }
    }

    public T remove(int index)
    {
        //If index is zero, increment top by one node
        //If there are no items in the series, throw error
        //Otherwise, use remove helper to remove
        if(index == 0) {
            top = top.getNext();
        }
        else if(index < 0)
            throw new IndexOutOfBoundsException();
        else {
            removeHelper(top, top.getNext(), 0, index);
        }
        return null;
    }

    public boolean remove(Object datum)
    {
        //Set temporary placeholder as root node
        LinkedNode<T> temp = top;
        LinkedNode<T> tempPrevious = null;

        //Go through node series and find the datu to remove
        while(temp != null && temp.getDatum() != datum) {
            tempPrevious = temp;
            temp = temp.getNext();
        }
        //If reach end and no datum (temp == null), return false
        //Otherwise, remove and return true
        if(temp == null) {
            return false;
        } else {
            if(tempPrevious == null) { top = top.getNext(); }
            else { tempPrevious.setNext(temp.getNext()); }
            return true;
        }
    }

    /**
     * Remove helper to remove a node at a specific index.
     * Goes through the series until it reaches the node, then removes
     * @param current The current node being looked at in the series
     * @param next The next node being looked at in the series
     * @param placeMarker The integer of the current node
     * @param index The index of the node to be removed
     */
    private void removeHelper(LinkedNode<T> current, LinkedNode<T> next, int placeMarker, int index)
    {
        //Go through series. If don't find it, throw index out of bounds
        //Once found, remove
        if(placeMarker != index-1) {
            if(current.getNext() == null)
                throw new IndexOutOfBoundsException();
            placeMarker++;
            removeHelper(current.getNext(), next.getNext(), placeMarker, index);
        }
        else {
            current.setNext(next.getNext());
        }
    }

    /**
     * Special case removal: remove the first node in the series
     *
     * @param quarry the quarry to remove
     * @return the remove object
     */
    private T removeFirst(T quarry) {
        //Get index of quarry. If not found, throw exception.
        //Otherwise, remove at index
        int index = this.indexOf(quarry);
        if(index == -1)
            throw new NoSuchElementException();
        else
            this.remove(index);
        return quarry;
    }

    /**
     * Special case removal: remove the last node in the series
     *
     * @param quarry the quarry to remove
     * @return the object being removed
     */
    private T removeLast(T quarry)
    {
        //Get index of quarry. If not found, throw exception
        //If not, remove from index
        int index = this.lastindexOf(quarry);
        if(index == -1)
            throw new NoSuchElementException();
        else
            this.remove(index);
        return quarry;
    }

    public T get(int index)
    {
        //Check special cases: index is root node or index out of bounds
        //Otherwise, use get helper
        if(index == 0)
            return top.getDatum();
        else if(index < 0)
            throw new IndexOutOfBoundsException();
        else
            return getHelper(top, top.getNext(), 0, index);
    }

    /**
     * Helper function to get a node from index. This function filters
     * through the series until it finds the node in the list
     * @param current The current node being looked at in the series
     * @param next The next node being looked at in the series
     * @param placeMarker The integer of the current node
     * @param index The index of the node to be removed
     * @return the node to get
     */
    private T getHelper(LinkedNode<T> current, LinkedNode<T> next, int placeMarker, int index)
    {
        //Check if index equals placemarker
        //If not, keep moving through the series
        if(placeMarker != index-1)
        {
            //If index out of bounds, throw exception
            if(current.getNext() == null)
                throw new IndexOutOfBoundsException();
            placeMarker++;
            //Otherwise, get node
            getHelper(current.getNext(), next.getNext(), placeMarker, index);
        }
        return next.getDatum();
    }

    public T set(int index, T newItem)
    {
        //Check special cases: If getting root node or index
        //out of bounds
        //Otherwise, use set helper to set node
        if(index == 0)
            top.setDatum(newItem);
        else if(index < 0)
            throw new IndexOutOfBoundsException();
        else
            setHelper(top, top.getNext(), 0, index, newItem);
        return newItem;
    }

    /**
     * Helper function to set a node in the series to a quarry
     * @param current The current node being looked at in the series
     * @param next The next node being looked at in the series
     * @param placeMarker The integer of the current node
     * @param index The index of the node to be set
     * @param quarry The quarry to set at the specified node
     */
    private void setHelper(LinkedNode<T> current, LinkedNode<T> next, int placeMarker, int index, T quarry) {
        //Check if index at placemarker
        //If not, keep going through series
        if(placeMarker != index-1) {
            //If index out of bounds, throw exception
            //Otherwise, get node and set
            if(current.getNext() == null)
                throw new IndexOutOfBoundsException();
            placeMarker++;
            getHelper(current.getNext(), next.getNext(), placeMarker, index);
        }
        next.setDatum(quarry);
    }

    public int indexOf(Object quarry)
    {
        //Check if node is empty
        if(isEmpty())
            return -1;

        //Otherwise, loop through node series until
        //quarry found
        LinkedNode<T> nav = top;
        int index = 0;
        while(!(nav == null)) {
            if(nav.getDatum().equals(quarry)) {
                return index;
            }
            nav = nav.getNext();
            index++;
        }
        return -1;
    }

    /**
     * Get the last index of the quarry
     *
     * @param quarry the quarry to find
     * @return the integer of the quarry
     */
    private int lastindexOf(T quarry)
    {
        //If node is empty, return
        if(isEmpty())
            return -1;

        //Otherwise, go through series to find node
        LinkedNode<T> nav = top;
        int latest = -1;
        int index = 0;
        while(!(nav == null)) {
            if(nav.getDatum().equals(quarry)) {
                latest = index;
            }
            nav = nav.getNext();
            index++;
        }
        return latest;
    }

    public boolean isEmpty()
    {
        return top == null;
    }

    public boolean contains(Object quarry)
    {
        return toQuarry(top, quarry);
    }

    /**
     * Recursive helper function for quarry. Take quarry and go thorugh series to find node
     * @param l The current node in the series
     * @param quarry The node being looked for
     * @return true if node is in list, false otherwise
     */
    private boolean toQuarry(LinkedNode<T> l, Object quarry) {
        while(!(l.getDatum().equals(quarry))) {
            l = l.getNext();
            if(l == null) {
                return false;
            }
            else {
                toQuarry(l, quarry);
            }
        }
        return true;
    }

    public void clear()
    {
        top = null;
    }

    private int toEnd(LinkedNode<T> l)
    {
        return l == null? 0: 1 + toEnd(l.getNext());
    }

    public int size()
    {
        return toEnd(top);
    }

    @Override
    public String toString()
    {
        if(isEmpty())
            return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        LinkedNode<T> l = top;
        for(; l.getNext() != null; l = l.getNext())
        {
            sb.append(l.getDatum()).append(", ");
        }
        sb.append(l.getDatum()).append("]");
        return sb.toString();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args)
    {
        LinkedList<String> s = new LinkedList<>();
        s.add("finkelstein");
        s.add("qi");
        s.add("ball12d");
        s.add("finkelstein");
        System.out.println("List: " + s);
        System.out.printf("s.size() = %s\n", s.size());
        System.out.println("s.lastindexOf(finkelstein) = " + s.lastindexOf("finkelstein"));
        System.out.println("s.lastindexOf(qi)" + s.lastindexOf("qi"));
        System.out.println("s.lastindexOf(pickles)" + s.lastindexOf("pickles"));
        System.out.println("s.contains(finkelstein) = " + s.contains("finkelstein"));
        System.out.println("s.contains(pickle) = " + s.contains("pickle"));
        System.out.println("s.indexOf(finkelstein) = " + s.indexOf("finkelstein"));
        System.out.println("s.indexOf(ball12d) = " + s.indexOf("ball12d"));
        System.out.println("s.indexOf(pickle) = " + s.indexOf("pickle"));
        s.add(0, "elmore");
        System.out.println("s.add(0) = " + s);
        s.add(2, "chiquito");
        System.out.println("s.add(2) = " + s);
        s.add(6, "johnson");
        System.out.println("s.add(6) = " + s);
        s.remove(0);
        System.out.println("s.remove(0) = " + s);
        s.remove(1);
        System.out.println("s.remove(1) = " + s);
        s.remove(4);
        System.out.println("s.remove(4) = " + s);
        s.removeFirst("qi");
        System.out.println("s.remove(qi) = " + s);
        s.removeLast("finkelstein");
        System.out.println("s.removeLast(finkelstein) = " + s);
        System.out.println("s.get(1) = " + s.get(1));
        System.out.println("s.get(0) = " + s.get(0));
        s.set(0, "qi");
        System.out.println("s.set(0, qi) = " + s);
        s.set(1, "chiquito");
        System.out.println("s.set(chiquito) = " + s);
		/*LinkedList<BigInteger> bigIntStack = new LinkedList<BigInteger>();
		bigIntStack.add(new BigInteger("32432432"));
		bigIntStack.add(BigInteger.valueOf(42));
		bigIntStack.add(BigInteger.ONE);
		System.out.println(bigIntStack);*/
    }
}
