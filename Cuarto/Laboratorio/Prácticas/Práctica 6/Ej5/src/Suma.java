public class Suma {
    public static void main(String[] args){
        int suma=0;
        for(int i=0;i<args.length;i++){

            try{
                suma+= Integer.parseInt(args[i]);
            }
            catch (Exception e){
                System.out.println((args[i])+" no es un número, error: "+e);
            };
        }
        System.out.print("La suma es:"+suma);
    }
}