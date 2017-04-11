package kelmore5.java.yeh.data_structures.linked_lists.test;

import java.util.ArrayList;
import kelmore5.java.yeh.data_structures.linked_lists.DoubleCircularLinkedList;


public class DoubleCircularLinkedListTestCode {

	public static void main(String[] args) {
		DoubleCircularLinkedList<Integer> yll = new DoubleCircularLinkedList<Integer>();
		System.out.println("Size: " + yll.size());
		yll.add(1);
		System.out.println("Add 1: " + yll.toString());
		System.out.println("Size: " + yll.size());
		System.out.println("Contains(1): " + yll.contains(1));
		System.out.println("Get(0): " + yll.get(0));
		System.out.println("IndexOf(1): " + yll.indexOf(new Integer(1))); //Doesn't work
		yll.set(0, 4);
		System.out.println("Set(0, 4): " + yll.toString());
		//yll.remove(0); Infinite Loops
		yll.clear();
		System.out.println("isEmpty()" + yll.isEmpty());

		/*ListIterator<Integer> iter = yll.listIterator();
		iter.add(1); //Fails when nothing in list
		System.out.println("iter.add(1): " + yll.toString());
		iter.remove();
		System.out.println("iter.remove(): " + yll.toString());*/

		yll.add(0, 1);
		System.out.println("Add(0, 1): " + yll.toString());
		yll.remove(new Integer(0)); //Doesn't work
		System.out.println("Remove(new Integer(0)): " + yll.toString());
		yll.clear();

		ArrayList<Integer> test = new ArrayList<Integer>();
		for(int i = 0; i < 10; i++) {
			test.add(i);
		}

		yll.add(1);
		yll.addAll(test); //Doesn't work when nothing in list
		System.out.println("AddAll(test): " + yll.toString());
		System.out.println("ContainsAll(test): " + yll.containsAll(test));
		System.out.println("Get(yll.size()-1): " + yll.get(yll.size()-1));
		System.out.println("indexOf(8): " + yll.indexOf(8));
		/*yll.removeAll(test); //Infinitely Loops
		System.out.println("RemoveAll(test): " + yll.toString());*/
		yll.clear();
		yll.add(1);
		yll.addAll(1, test);
		System.out.println("AddAll(test): " + yll.toString());
		yll.removeAll(test);
		System.out.println("RemoveAll(test): " + yll.toString());
		yll.add(2);
		yll.addAll(1, test);
		System.out.println("AddAll(test): " + yll.toString());
		//yll.removeAll(test);
		//System.out.println("RemoveAll(test): " + yll.toString());
		yll.clear();
		yll.add(1);
		yll.addAll(test);
		System.out.println("AddAll(test): " + yll.toString());
		System.out.println("test");
		yll.retainAll(test);
		System.out.println("RetainAll(test): " + yll.toString());
		//yll.removeAll(test);

		//System.out.println("Equals(yll): " + yll.equals(yll)); //Infinitely loop

		yll.clear();
		yll.add(1);
		yll.add(1);
		System.out.println(yll.toString());
		System.out.println("indexOf(1): " + yll.indexOf(1));
		System.out.println("lastIndexOf(1): " + yll.lastIndexOf(1));
		yll.remove(1);
		yll.remove(1);

		yll.clear();
		System.out.println("Clear(): " + yll.toString());
		System.out.println("isEmpty(): " + yll.isEmpty());
	}
}
