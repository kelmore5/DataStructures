package kelmore5.java.yeh.data_structures.queue;

import java.util.NoSuchElementException;

/**
 * <pre class="doc_header">
 * Exactly how Professor Yeh implemented a LinkedQueue
 * </pre>
 *
 * @param <E> The type parameter
 * @author kelmore5
 * @custom.date Fall 2012
 */

@SuppressWarnings("unused")
public class YehLinkedQueue<E> implements Queue<E> {
    private Node<E> front;
    private Node<E> back;

    private class Node<T> {
        public Node next;
        private T cargo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E pop() throws NoSuchElementException {
        if(isEmpty()) throw new NoSuchElementException();
        E temp = front.cargo;
        front = front.next;
        return temp;
    }

    @Override
    public E peek() throws NoSuchElementException {
        if(isEmpty()) throw new NoSuchElementException();
        return front.cargo;
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public void push(E obj) {
        Node<E> newNode = new Node<>();
        newNode.cargo = obj;
        newNode.next = back;
        back = newNode;

    }
}
