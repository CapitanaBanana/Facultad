package Ej3;

import java.util.Iterator;

public class StringConverterTest {
    public static void main(String[] args) {
        StringConverterSet set= new StringConverterSet();

        set.add(1);
        set.add(new Persona());
        Iterator it= set.getIterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

    private static class Persona{
        private String nombre="pepe";
        private int edad=10;
        @Override
        public String toString(){
            return this.nombre +"is"+ this.edad+"anios";
        }

    }
}
