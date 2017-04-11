package kelmore5.java.yeh.data_structures.stack;

import java.util.NoSuchElementException;
import kelmore5.java.yeh.data_structures.node.LinkedNode;

/**
 * An interface for a stack
 * @param <E>
 */
interface Stack<E> {
    void push(E obj);
    E pop();
    E peek();
    boolean isEmpty();
}

/**
 * <pre class="doc_header">
 * A stack implementation using a linked list
 * </pre>
 *
 * @author kelmore5
 * @custom.date 3/18/17
 */
@SuppressWarnings("unused")
public class LinkedListStack<E> implements Stack<E> {

    private LinkedNode<E> top = null;

    public boolean isEmpty()
    {
        return top == null;
    }

    public void push(E obj) {
        LinkedNode<E> newNode = new LinkedNode<>();
        newNode.setNext(top);
        newNode.setDatum(obj);
        top = newNode;
    }

    public E peek() throws NoSuchElementException {
        return top.getDatum();
    }

    public E pop() throws NoSuchElementException {
        E temp = top.getDatum();
        top = top.getNext();
        return temp;
    }
}

/**
 * A stack implementation using an array
 * @param <E> The type parameter
 */
@SuppressWarnings("unused")
class ArrayStack<E> implements Stack<E>
{
    private E[] array;
    private int top;

    public ArrayStack() {
        //noinspection unchecked
        array = (E[]) new Object[100];
        top = -1;
    }

    @Override
    public void push(E obj) {
        top++;
        array[top] = obj;
    }

    @Override
    public E pop() {
        if(isEmpty()) throw new NoSuchElementException();
        return array[top--];
    }

    @Override
    public E peek() {
        if(isEmpty()) throw new NoSuchElementException();
        return array[top];
    }

    @Override
    public boolean isEmpty() {
        return top < 0;
    }

}
