package Ej1;

import java.util.Iterator;


public class StackTest {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push("uno");
        stack.push("dos");
        stack.push("tres");
//        while (!stack.isEmpty()) {
//            System.out.println(stack.pop());
//        }
//        while (!stack.isEmpty()) {
//            System.out.println(stack.pop());
//        }
        Iterator iterator= stack.getIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }


    }
}
