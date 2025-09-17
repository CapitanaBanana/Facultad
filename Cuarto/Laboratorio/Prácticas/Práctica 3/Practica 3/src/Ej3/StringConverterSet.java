package Ej3;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;

public class StringConverterSet extends AbstractSet {
   private ArrayList set;

   public StringConverterSet(){
       set=new ArrayList();
   }

   public Object get(int i){
       return set.get(i);
   }
   public boolean add(Object o){
       return set.add(o);
   }

    @Override
    public Iterator iterator() {
        return new IteratorStringAdapter();
    }

    @Override
    public int size() {
        return set.size();
    }
    public Iterator getIterator(){
       return new IteratorStringAdapter();
    }
    private class IteratorStringAdapter implements Iterator{
       int i;
       public IteratorStringAdapter(){
           i=0;
       }

        @Override
        public boolean hasNext() {
            return i<set.size();
        }

        @Override
        public Object next() {
            Object element= set.get(i).toString();
            i++;
            return element;
        }
    }
}
