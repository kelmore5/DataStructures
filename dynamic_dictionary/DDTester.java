package kelmore5.java.yeh.data_structures.dynamic_dictionary;

import java.util.ArrayList;
import java.util.Random;

/**
 * <pre class="doc_header">
 * <p>
 * </pre>
 *
 * @author kelmore5
 * @custom.date 3/19/17
 */
public class DDTester {
    public static void main(String[] args) {// Argument: number of inserts to be performed
        Integer[] a = new Integer[] {0, 1, 10, 100, 10000};
        ArrayList<Long> random = new ArrayList<>();
        ArrayList<Long> sorted = new ArrayList<>();
        ArrayList<Long> reverse = new ArrayList<>();


        for(int test: a) {
            for(int i = 0; i < 5; i++) {
                long time1 = System.nanoTime();

                BST_DD dict1 = new BST_DD();

                Random r = new Random();
                for (int l=0; l<test; l++){
                    int k = r.nextInt(2*test);
                    dict1.insert(k, "The Number is " + Integer.toString(k));
                }

                for (int l=0; l<test/2; l++) {
                    int k = r.nextInt(2*test);
                    dict1.remove(k);
                }

                random.add((System.nanoTime() - time1)/1000000);

                long time2 = System.nanoTime();

                BST_DD dict2 = new BST_DD();

                for (int l=0; l<test; l++) dict2.insert(l, "The Number is " + Integer.toString(l));
                for (int l=0; l<test/2; l++) dict2.remove(r.nextInt(test));

                sorted.add((System.nanoTime() - time2)/1000000);

                long time3 = System.nanoTime();
                BST_DD dict3 = new BST_DD();

                for (int l=0; l<test; l++) dict3.insert(test-l, "The Number is " + Integer.toString(test-l));
                for (int l=0; l<test/2; l++) dict3.remove(r.nextInt(test));

                reverse.add((System.nanoTime() - time3)/1000000);
            }
        }

        System.out.println("Random: ");
        for(long test: random) {
            System.out.println(test);
        }

        System.out.println("Sorted: ");
        for(long test: sorted) {
            System.out.println(test);
        }

        System.out.println("Reverse: ");
        for(long test: reverse) {
            System.out.println(test);
        }
    }


}
