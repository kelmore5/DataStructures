package kelmore5.java.yeh.data_structures.hash_table;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class HashTable<T> {
 private T[] array;
 private int capacity;
 public static final int DEFAULT_CAPACITY = 10;

 @SuppressWarnings("unchecked")
 public HashTable(int _capacity) {
  capacity = _capacity;
  array = (T[]) new Object[capacity];
 }

 public HashTable() {
  this(DEFAULT_CAPACITY);
 }

 public int hash(T obj) {
  return obj.hashCode() % capacity;
 }

 public boolean add(T obj) {
  int code = hash(obj);
  array[code] = obj;
  return true;
 }

 @SuppressWarnings("unchecked")
 public Iterator<T> iterator() {
  return new MyIterator();
 }

 public void dump() {
  Iterator<T> iter = iterator();
  while(iter.hasNext()) {
   System.out.println(iter.next());
  }
 }
 
 public boolean delete(T obj) {
  int code = hash(obj);
  return delete(code);
 }
 
 public boolean delete(int index) {
  if(array[index] == null) {
   return false;
  }
  else {
   array[index] = null;
   return true;
  }
 }

 @SuppressWarnings("rawtypes")
 private class MyIterator implements Iterator{
  //next occupied bucket index
  private int nextIndex, removeIndex;

  private boolean  findNextIndex() {
   while(array[nextIndex] == null) {
    nextIndex++;
    if(nextIndex >= capacity) {
     return false;
    }
   }
   return true;
  }

  public MyIterator() {
   nextIndex = 0;
   removeIndex = -1;
   findNextIndex();
  }

  public boolean hasNext() {
   return nextIndex < capacity;
  }

  public T next() throws NoSuchElementException {
   if(!hasNext()) throw new NoSuchElementException();
   T obj = array[nextIndex];
   removeIndex = nextIndex;
   nextIndex++;
   findNextIndex();
   return obj;
  }

  public void remove() throws IllegalStateException {
   if(removeIndex >= 0) {
    delete(removeIndex);
    removeIndex = -1;
   }
   else {
    throw new IllegalStateException();
   }
  }
 }
}
