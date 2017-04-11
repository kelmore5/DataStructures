package kelmore5.java.yeh.data_structures.stack;

import java.util.EmptyStackException;
import java.util.Iterator;
import kelmore5.java.yeh.data_structures.node.LinkedNode;

/**
 * <pre class="doc_header">
 * A generic stack implementation
 * </pre>
 *
 * @author kelmore5
 * @custom.date Fall 2012
 */
public class GenericStack<T> implements Iterable<T> {
    private LinkedNode<T> top;

    private GenericStack()
    {
        top = null;
    }

    public void push(T newItem)
    {
        if(isEmpty())
        {
            top = new LinkedNode<>(newItem);
        }
        else
        {
            top = new LinkedNode<>(newItem, top);
        }
    }

    public T peek()
    {
        if(isEmpty())
            throw new EmptyStackException();
        return top.getDatum();
    }

    public T pop()
    {
        if(isEmpty())
            throw new EmptyStackException();
        T out = top.getDatum();
        top = top.getNext();
        return out;
    }

    public boolean isEmpty()
    {
        return top == null;
    }

    @SuppressWarnings("unused")
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
    public Iterator<T> iterator()
    {
        return new StackIterator();
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

    private class StackIterator implements Iterator<T>
    {
        LinkedNode<T> runner;

        StackIterator()
        {
            runner = top;
        }

        @Override
        public boolean hasNext()
        {
            return runner != null;
        }

        @Override
        public T next()
        {
            T output = runner.getDatum(); //fetch datum
            runner = runner.getNext(); //advance pointer
            return output; //return datum
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        //TODO: put test code here
        GenericStack<String> s = new GenericStack<>();
        s.push("finkelstein");
        s.push("qi");
        s.push("ball12d");
        for(String q: s)
        {
            System.out.println(q);
        }
        System.out.println(s);
    }

}
