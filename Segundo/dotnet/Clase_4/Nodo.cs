namespace Clase_4;
using System.Collections;
class Nodo{
    private Nodo? _nodoIzquierdo{ get; set; }
    private Nodo? _nodoDerecho{ get; set; }
    private int _dato{ get; set; }


    public Nodo(int dato){
        _dato=dato;
    }
    public Nodo(){}
    public void Insertar(int num){ // Inserta valor en el árbol descartándolo en caso que ya exista.
        if (num<_dato){
            if (_nodoIzquierdo==null)
                _nodoIzquierdo=new Nodo(num);
            else
                _nodoIzquierdo.Insertar(num);
        }
        else if (num>_dato){
            if (_nodoDerecho==null)//si no hay nodo creo el nodo, sino llamo de nuevo a insertar desde el nodo siguiente
                _nodoDerecho=new Nodo(num);
            else
                _nodoDerecho.Insertar(num);
        }
    }
    public List<int> GetInOrden(){
        List<int> valores = new List<int>();
        if (_nodoIzquierdo != null){
            valores.AddRange(_nodoIzquierdo.GetInOrden());
        }
        valores.Add(_dato);
        if (_nodoDerecho != null){
            valores.AddRange(_nodoDerecho.GetInOrden());
        }
        return valores;
    }
    public int GetAltura(){
        int alturaIzquierda = 0;
        int alturaDerecha = 0;
        if (_nodoIzquierdo != null){
            alturaIzquierda = _nodoIzquierdo.GetAltura() + 1;
        }
        if (_nodoDerecho != null){
            alturaDerecha = _nodoDerecho.GetAltura() + 1;
        }
        return Math.Max(alturaIzquierda, alturaDerecha);
    }

    public int GetCantNodos(){
        int cantNodosDerecha=0;
        int cantNodosIzquierda=0;
        if (_nodoIzquierdo != null){
            cantNodosIzquierda = _nodoIzquierdo.GetCantNodos();
        }
        if (_nodoDerecho != null){
            cantNodosDerecha = _nodoDerecho.GetCantNodos();
        }
        return cantNodosDerecha+cantNodosIzquierda+1;
    }
    public int GetValorMinimo(){
        if (_nodoIzquierdo==null){
            return _dato;
        }
        else{
            return _nodoIzquierdo.GetValorMinimo();
        }
    }
    public int GetValorMaximo(){
        if (_nodoDerecho==null){
            return _dato;
        }
        else{
            return _nodoDerecho.GetValorMaximo();
        }
    }
}