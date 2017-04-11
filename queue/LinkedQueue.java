package kelmore5.java.yeh.data_structures.queue;

import kelmore5.java.yeh.data_structures.node.LinkedNode;

import java.util.NoSuchElementException;

/**
 * <pre class="doc_header">
 * An implementation of a queue through a linked list
 * <p>
 * For the professor's implementation, see {@link YehArrayQueue}
 * </pre>
 *
 * @param <E> The type parameter
 * @author kelmore5
 * @custom.date Fall 2012
 */
@SuppressWarnings("unused")
public class LinkedQueue<E> implements Queue<E> {
    private LinkedNode<E> front = null;   //The front of the queue
    private LinkedNode<E> back = null;    //The back of the queue

    public boolean isEmpty()
    {
        return front == null;
    }

    public void push(E obj) {
        LinkedNode<E> newNode = new LinkedNode<>();    //Create new node
        newNode.setDatum(obj);             //Set @node's element to new object

        //If queue is empty, @newNode is set to the front of the queue
        //Otherwise, it is added onto the back
        if(isEmpty()) {
            newNode.setNext(back);
            front = newNode;
            return;
        }
        else {
            if(back != null)
                back.setNext(newNode);
        }
        back = newNode;                     //Finally, keep track of back of queue
    }

    public E peek() throws NoSuchElementException { return back.getDatum(); }

    public E pop() throws NoSuchElementException {
        E temp = front.getDatum();              //Get @front node's element
        front.setNext(front.getNext());                //Decrement queue
        return temp;
    }
}
