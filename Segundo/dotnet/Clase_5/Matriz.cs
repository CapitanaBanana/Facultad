namespace Clase_5;

class Matriz{
    private int _filas{get;set;}
    private int _columnas{get;set;}
    private double[,] _matriz;
    public int GetFilas(){
        return _filas;
    }
    public int GetColumnas(){
        return _columnas;
    }
    public Matriz(int filas, int columnas){
        _columnas=columnas;
        _filas=filas;
        _matriz= new double[filas,columnas];
    }
    public double this[int fila, int columna]{
        get{
            return _matriz[fila,columna];
        }
        set{
            _matriz[fila, columna]=value;
        }
    }
    public Matriz(double[,] matriz){
        _matriz= matriz;
    } 
    public void imprimir(){
        for (int i=0; i< _filas; i++){
            for (int j=0; j<_columnas; j++){
                Console.Write(_matriz[i,j]+ " ");
            }
            Console.WriteLine();
        }
    }
/*    public void imprimir(string formatString){
        for (int i=0; i< _columnas; i++){
            for (int j=0; j<_filas; j++){
                Console.Write(_matriz[i,j].ToString(formatString)+ " ");
            }
            Console.WriteLine();
        }
    }*/
    public double[] GetFila(int fila){
        double[] aux= new double[_matriz.GetLength(1)];
        for (int i = 0; i < _matriz.GetLength(1); i++)
        {
            aux[i]=_matriz[fila,i];
        }
        return aux;
    }
    public double[] GetColumna(int columna){
        double[] aux= new double[_matriz.GetLength(0)];
        for (int i = 0; i < _matriz.GetLength(0); i++)
        {
            aux[i]=_matriz[i,columna];
        }
        return aux;
    }
    public double[] DiagonalPrincipal{
        get{
            double[] diagPrincipal= new double[_columnas];
            if (_filas!=_columnas){
                throw new ArgumentException();
            }  
                for (int i=0; i<_filas; i++){
                diagPrincipal[i]= _matriz[i,i];
            }
            return diagPrincipal;
        }
    }
    public double[] DiagonalSecundaria{
        get{
            double[] diagSecundaria= new double[_columnas];
            if (_filas!=_columnas){
                throw new ArgumentException();
            }  
                for (int i=0, j= _filas-1; i<_filas; i++, j--){
                    diagSecundaria[i]= _matriz[i,j];
            }
            return diagSecundaria;
        }
    }
    public double[][] getArregloDeArreglo(){
        double[][] arreglo= new double[_filas][];
        for (int i=0; i<_filas; i++){
            arreglo[i]= new double[_columnas];
            for (int j=0; j<_columnas; j++){
                arreglo[i][j]= _matriz[i,j];
            }
        }
        return(arreglo);
    }
    public void sumarle(Matriz m){
        int filasB= m.GetFilas();
        int columnasB= m.GetColumnas();

        if ((_filas!= filasB)|(_columnas!= columnasB)){
            throw new ArgumentException();
        }
        double[,] res= new double[_filas,_columnas];
        for (int i=0; i<_filas;i++){
            for(int j=0; j<_columnas;j++){
                res[i,j]= _matriz[i,j]+m[i,j];
            }
        }
        _matriz=res;
    }
    public void restarle(Matriz m){
        int filasB= m.GetFilas();
        int columnasB= m.GetColumnas();

        if ((_filas!= filasB)|(_columnas!= columnasB)){
            throw new ArgumentException();
        }
        double[,] res= new double[_filas,_columnas];
        for (int i=0; i<_filas;i++){
            for(int j=0; j<_columnas;j++){
                res[i,j]= _matriz[i,j]-m[i,j];
            }
        }
        _matriz=res;
    }
    public void multiplicarPor(Matriz m){
        int fA= _filas;
        int cA= _columnas;
        int fB= m.GetFilas();
        int cB= m.GetColumnas();
        double temp;
        double[,] result=new double[fA,cB];
        for (int i = 0; i < fA; i++){
            for(int j = 0; j < cB; j++){
            temp= 0;
            for(int k = 0; k < cA; k++){
                temp += _matriz[i, k] * m[k,j];
            }
            result[i,j]=temp;
            }
        }
        _matriz=result;
    }
}