import java.util.*;
import java.net.*;
import java.io.IOException;

public class MyLList<E extends Comparable < ? super E > > implements ListInterface<E>{
    //Question 1B Represents the ADT List/Linked List
    private boolean integrityOK;
    protected ListNode firstNode; //Changed to Protected in order to be accessibly by the Iterator
    protected ListNode lastNode; //Changed to Protected in order to be accessibly by the Iterator
    private int numberOfEntries;

    public MyLList() { clear (); }
    public final void clear () {
        integrityOK = false;

        firstNode = null;
        lastNode = null;
        numberOfEntries = 0;

        integrityOK = true;
    }
    private void checkIntegrity() {
        if(!integrityOK)
            throw new SecurityException("Link List not functioning");
    }
    public boolean isEmpty() { return (firstNode == null); }
    public int getLength() { return numberOfEntries; }
    public boolean contains(E anEntry) {
        checkIntegrity();
        ListNode fn = firstNode;

        if(fn == null) return false;
        else if(fn.getData().equals(anEntry)) return true;
        else {
            do {
                fn = fn.next;
                if(anEntry.equals(fn.getData())) return true;
            } while (fn.next != null);
        }
        return false;
    }
    public void printAll() {
        Comparable[] arr = this.toArray();
        for(int i=0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
    public Comparable[] toArray() {
        if(firstNode == null) return null;

        E[] arr = (E[])new Comparable[numberOfEntries];

        int count =0;
        ListNode currentNode = firstNode;

        do {
            arr[count] = currentNode.getData();
            count++;
            if(currentNode.next == null) {
                break;
            }
            currentNode = currentNode.next;
        } while (!(currentNode.equals(lastNode)));

        arr[count] = lastNode.getData();
        return arr;
    }
    private ListNode getNodeAt(int givenPosition) {
        checkIntegrity();
        ListNode currentNode = firstNode;
        for (int counter = 1 ; counter < givenPosition ; counter++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }
    public E getEntry(int i) {
        Comparable[] arr = this.toArray();

        E element = null;
        if((i >0) && (i < arr.length)) {
            element = (E) arr[i-1];
        }
        return element;
    }
    public E replace(int position, E anEntry) {
        checkIntegrity();
        boolean success = false;
        if (!((position >= 1) && (position <= numberOfEntries))){
            return null;
        } else {
            ListNode theNode = this.getNodeAt(position - 1);
            theNode.setData(anEntry);
            success = true;
        }
        return anEntry;
    }
    public E remove(int position) {
        checkIntegrity();
        E result = null;
        if ((position >= 1) && (position <= numberOfEntries)) {
            if (position ==1) {
                result = firstNode.data;
                firstNode = firstNode.next;
            } else {
                ListNode nodeBefore = getNodeAt(position - 1);
                ListNode nodeToRemove = nodeBefore.next;
                ListNode nodeAfter = nodeToRemove.next;
                nodeBefore.next = nodeAfter;
                result = nodeToRemove.getData();
            }
            numberOfEntries --;
        } else {
            System.out.println(position + ": is out of range of the list with size: " + numberOfEntries);
        }
        return result;
    }
    public ListInterface<E> getAllLessThan(Comparable<E> elementToCompare) {
        ListInterface<E> resultList = new MyLList<>();
        ListNode currentNode = firstNode;
        boolean done = false;

        do {
            E currentElement = currentNode.getData();
            if (elementToCompare.compareTo(currentElement) > 0) resultList.add(currentElement);
            if (currentNode.getNextNode() == null) done = true;
            currentNode = currentNode.getNextNode();
        } while (!done);
        return resultList;
    }
    public void add(E anEntry) {
        checkIntegrity();
        ListNode newNode = new ListNode (anEntry);

        if (isEmpty ()) {
            firstNode = newNode;
        } else {
            ListNode lastNode = getNodeAt (numberOfEntries);
            lastNode.next = newNode;
        }
        lastNode = newNode;
        numberOfEntries ++;
    }
    public boolean add(int insertPosition, E anEntry) {
        ListNode nodeBefore, nodeAfter;

        checkIntegrity();

        boolean success = true;
        if ((insertPosition >= 1) && (insertPosition <= numberOfEntries +1)) {
            ListNode newNode = new ListNode (anEntry);
            if (isEmpty () || (insertPosition == 1)) {
                newNode.next = firstNode;
                firstNode = newNode;
                lastNode = newNode;
            } else {
                nodeBefore = getNodeAt (insertPosition - 1);
                if (nodeBefore.next == null) {
                    nodeBefore.next = newNode;
                    newNode.next = null;
                    lastNode = newNode;
                } else {
                    nodeAfter = nodeBefore.next;
                    newNode.next = nodeAfter;
                    nodeBefore.next = newNode;
                    lastNode = nodeAfter;
                }
            }
            numberOfEntries ++;
        } else success = false;

        return success;
    }
    public void printLinkedList() {
        int nodeCount = 1;
        ListNode currentNode = firstNode;
        E data = (E)currentNode.getData();

        System.out.println("Node[1]: " + data);

        while( currentNode.getNextNode() != null) {
            currentNode = currentNode.getNextNode();
            data = (E)currentNode.getData();
            System.out.println("Node[" + (++nodeCount) + "]: " + data);
        }
    }
    public class ListNode {
        public E data;
        public ListNode next;
        public ListNode(E anEntry) { this(anEntry, null); }
        public ListNode(E anEntry, ListNode n) {
            this.data = anEntry;
            this.next = n;
        }
        public E getData() { return data; }
        public ListNode getNextNode() { return this.next; }
        public void setNextNode(ListNode anEntry) { this.next = anEntry; }
        public void setData(E anEntry) { this.data = anEntry; }
    }
}
