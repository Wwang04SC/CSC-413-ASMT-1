//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Test");
        ListWithIteratorInterface<String> myList = new LinkedListWithIterator<>();
        myList.add("A");
        myList.add("B");
        myList.add("C");
        myList.add("D");

        System.out.println("Using ADT list operations hasNext and next:");

        displayList(myList);

        Iterator it = myList.getIterator();
        System.out.println("next() again");
        try{
            it.next();
            System.out.println("No exception");
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException thrown");
        }

        System.out.println("\nStart Iteration again:");
        it = myList.getIterator();
        System.out.println("next() returns " + it.next() + " (should be A)");
        System.out.println("next() returns " + it.next() + " (should be B)");
        System.out.println("next() returns " + it.next() + " (should be C)");
        System.out.println("next() returns " + it.next() + " (should be D)");
    }

    public static void displayList(ListWithIteratorInterface<String> aList){
        System.out.println("The list contains " + aList.getLength() + "string(s) running through iterator, as follows:");
        Iterator it = aList.getIterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
}