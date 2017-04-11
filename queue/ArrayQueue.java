package kelmore5.java.yeh.data_structures.queue;

import java.util.NoSuchElementException;

/**
 * <pre class="doc_header">
 * An implementation of a queue using only an array
 *
 * For the professor's implementation, see {@link YehArrayQueue}
 * </pre>
 *
 * @param <E> The type parameter
 * @author kelmore5
 * @custom.date Fall 2012
 */
@SuppressWarnings("unused")
public class ArrayQueue<E> implements Queue<E> {
    private E[] array;      //The backbone of the queue
    private int top;        //The top of the queue
    private int bottom;     //The bottom of the queue

    /**
     * Creates a new Array queue.
     * Works by instantiating an empty array of 100 elements whose size is increased
     * as more items are added to the queue.
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        array = (E[]) new Object[100];
        top = -1;
        bottom = 0;
    }

    public void push(E obj) {
        //Increment @top, add new object
        top++;
        array[top] = obj;
    }

    public E pop() {
        //Check if array is empty, otherwise get last element and decrement @bottom
        if(isEmpty()) throw new NoSuchElementException();
        return array[bottom++];
    }

    public E peek() {
        //Check if array is empty for peek, otherwise show top of queue
        if(isEmpty()) throw new NoSuchElementException();
        return array[top];
    }

    public boolean isEmpty() {
        return top < 0;
    }
}
