package Ej_5;

public class retornar3 {
    private static int min= 999;
    private static int max= -1; 
    private static float prom= 0f; 


    public static void setMin(int unmin){
        min= unmin;
    }
    public static void setMax(int unMax){
        max= unMax;
    }
    public static void setProm(float unProm){
        prom=unProm;
    }
    public static int getMax(){
        return max;
    }
    public static int getMin(){
        return min;
    }
    public static float getProm(){
        return prom;
    }
}
