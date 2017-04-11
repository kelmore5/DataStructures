package kelmore5.java.yeh.data_structures.vip_queue;

/**
 * <pre class="doc_header">
 * <p>
 * </pre>
 *
 * @author kelmore5
 * @custom.date 3/19/17
 */
public class Test  {
    public static void main(String args[]) {
        LinkedListVIPQueue Q = new LinkedListVIPQueue(10);
        for(int i = 0; i < 10; i++) {
            if(!Q.isFull()) Q.Enqueue(i);
            if(!Q.isFull()) Q.EnqueueVip(i*i);
        }

        while(!Q.isEmpty()) System.out.printf("->%d", Q.Dequeue());

        Q = new LinkedListVIPQueue(30);
        for(int i = 0; i < 33; i++) {
            for(int j = 0; j < i; j++) {
                if(!Q.isFull()) Q.Enqueue(i*j);
                if(j%3 == 0) {
                    if(!Q.isFull()) Q.EnqueueVip(i+j);
                }
                else {
                    if(!Q.isFull()) Q.Enqueue(i+j);
                }
            }

            while(!Q.isEmpty()) System.out.printf("->%d", Q.Dequeue());
            System.out.println("\n");
        }

        Q = new LinkedListVIPQueue(10000000);
        for(int i = 0; i < 10000001; i++) {
            if(!Q.isFull()) Q.Enqueue(i*i*i);
            if(!Q.isFull()) Q.EnqueueVip(i);
        }

        while(!Q.isEmpty()) Q.Dequeue();

        System.out.println("Finished");
    }
}
