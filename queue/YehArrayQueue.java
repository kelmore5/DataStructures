package kelmore5.java.yeh.data_structures.queue;

import java.util.NoSuchElementException;

/**
 * <pre class="doc_header">
 * Exactly how Professor Yeh implemented ArrayQueue
 * </pre>
 *
 * @param <E> The type parameter
 * @author kelmore5
 * @custom.date Fall 2012
 */

@SuppressWarnings("unused")
public class YehArrayQueue<E> implements Queue<E> {
    private E[] array;
    private int front, back;

    /**
     * Instantiates a new Yeh array queue.
     */
    public YehArrayQueue() throws ClassCastException {
        try { //noinspection unchecked
            array = (E[]) (new Object[100]); } catch(ClassCastException ex) {
            throw new ClassCastException("Something went wrong with creating the original array.");
        }
        front = back = 0;
    }

    public E peek() throws NoSuchElementException
    {
        if(isEmpty()) throw new NoSuchElementException();
        return array[front];
    }

    public E pop() throws NoSuchElementException {
        if(isEmpty()) throw new NoSuchElementException();
        return array[front++];
    }

    public boolean isEmpty() {
        return front == back;
    }

    public void push(E obj) {
        array[back++] = obj;
    }
}
