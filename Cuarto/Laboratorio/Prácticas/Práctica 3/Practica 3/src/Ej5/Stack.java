package Ej5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

public class Stack {
    private java.util.ArrayList items;
    public Stack(){
        items= new ArrayList();
    }
    public void push(Object item){
        items.addLast(item);
    }
    public Object pop(){
        Object item= items.getLast();
        items.removeLast();
        return item;
    }
    public boolean isEmpty(){
        return items.isEmpty();
    }
    public Iterator getIterator(){
        return new Iterator(){
            int i =items.size() -1;

            @Override
            public boolean hasNext() {
                return i>=0;
            }

            @Override
            public Object next() {
                Object item= items.get(i);
                i--;
                return item;
            }
        };
    }

}
