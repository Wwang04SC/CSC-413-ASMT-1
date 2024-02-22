import java.util.*;
import java.util.Iterator;

public class LinkedListWithIterator<E extends Comparable<? super E>> extends MyLList<E> implements ListWithIteratorInterface<E> {
    public LinkedListWithIterator() { super(); }
    public Iterator<E> getIterator() { return new LLIterator(); }
    public Iterator<E> iterator() { return getIterator(); }
    public class LLIterator implements Iterator<E>{
        public ListNode cur; //Spent too much time trying to get cell to work but was scrapped for reusing ListNode

        LLIterator() { cur = firstNode; } //'Head' of the Node is firstNode. Thus Current.
        public boolean hasNext() {
            return cur != null;
        }
        public E next() {
            if (hasNext()) {
                E temp = cur.getData();
                cur = cur.getNextNode();
                return temp;
            } else {
                throw new NoSuchElementException("next() shouldn't be called. Iterator hit list limit");
            }
        }
    }

}
