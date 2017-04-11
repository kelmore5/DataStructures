package kelmore5.java.yeh.data_structures.linked_lists;

import kelmore5.java.yeh.data_structures.node.LinkedNode;

/**
 * <pre class="doc_header">
 * A Circular Linked List is a linked list that loops around to the
 * front of the list. So, if you're traveling through the list, going past
 * the end will bring you back to the root.
 * </pre>
 *
 * @param <T> the type parameter
 * @author kelmore5
 * @custom.date Fall 2012
 */
public class CircularLinkedList<T>  extends ListAdapter<T>
{
    private LinkedNode<T> first;
    private LinkedNode<T> last;

    /**
     * Instantiates a new Circular linked list. Sets nodes to null
     */
    CircularLinkedList() {
        first = null;
        last = null;
    }

    public boolean add(T element) {
        //Check special cases: If first or last is null, initialize
        //Otherwise, add node to end of last
        if(first == null) {
            first = new LinkedNode<>(element);
        }
        else if(last == null) {
            last = new LinkedNode<>(element);
            first.setNext(last);
        }
        else {
            LinkedNode<T> temp = new LinkedNode<>(element);
            last.setNext(temp);
            last = temp;
        }

        return true;
    }

    public void add(int position, T element) {
        //If position is greater than size, throw out of bounds exception
        if(position > size()) {
            throw new IndexOutOfBoundsException();
        }

        //Create new node with element
        LinkedNode<T> newNode = new LinkedNode<>(element);
        int counter = 0;

        //If position add, add to first. If 1, add to one after first
        //Otherwise, loop through list and find
        if(position == 0) {
            newNode.setNext(first);
            first = newNode;
        }
        else if(position == 1) {
            newNode.setNext(first.getNext());
            first.setNext(newNode);
        }
        else {
            LinkedNode<T> temp = first;
            while(temp != null) {
                if(counter == position-2) {
                    newNode.setNext(temp.getNext().getNext());
                    temp.getNext().setNext(newNode);
                }

                counter++;
                temp = temp.getNext();
            }
        }

    }

    public int size() {
        //If first is null, no size - return 0
        //Otherwise, loop through list and count to get size
        int counter = 0;
        if(first == null) {
            return 0;
        }
        else {
            LinkedNode<T> temp = first;

            while(temp != null) {
                counter++;

                temp = temp.getNext();
            }

            return counter;
        }
    }

    public boolean remove(Object element) {
        //If list is empty, return false.
        //Otherwise, loop through list to find element and remove
        if(isEmpty()) {
            return false;
        }
        else {
            LinkedNode<T> temp = first;

            if(temp.getDatum().equals(element)) {
                first = temp.getNext();
            }
            else {
                while(temp != null) {
                    //Return if reach end of loop (temp.next == false)
                    //Otherwise keep going through array
                    if(temp.getNext() == null) {
                        return false;
                    }
                    else {
                        if(temp.getNext().getDatum().equals(element)) {
                            temp.setNext(temp.getNext().getNext());
                            return true;
                        }
                        temp = temp.getNext();
                    }
                }
            }
            return false;
        }
    }

    public T remove(int index) {
        //If index greater than size, throw exception
        if(index > size()-1) {
            throw new IndexOutOfBoundsException();
        }

        T element = null;

        //If index zero, return first node datum
        if(index == 0) {
            element = first.getDatum();
            first = first.getNext();
        }
        else {
            int counter = 0;
            LinkedNode<T> temp = first;
            while(temp != null) {
                if(counter == index-1) {
                    element = temp.getNext().getDatum();
                    temp.setNext(temp.getNext().getNext());
                }

                counter++;

                temp = temp.getNext();
            }
        }

        return element;
    }

    public String toString() {
        String string = "[";
        LinkedNode<T> temp = first;

        while(temp != null) {
            string += temp.getDatum() + ", ";
            temp = temp.getNext();
        }

        string = string.substring(0, string.lastIndexOf(","));
        string += "]";

        return string;
    }

    public boolean isEmpty() {
        return first == null;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        LinkedList<String> yll = new LinkedList<>();
        for(int i = 0; i < 10; i++) {
            yll.add("" + i);
        }

        yll.remove("5");

        System.out.println(yll.toString());

        System.out.println("Size: " + yll.size());

        yll.add(5, "5");
        yll.add(0, "-1");
        yll.add(1, "2");
        yll.add(yll.size(), "10");
        System.out.println("Add 5: " + yll.toString());

        yll.remove(1);
        yll.remove(0);
        yll.remove(10);
        System.out.println("Remove 0: " + yll.toString());
    }
}
