class Cuenta
{
    private static int s_cuentas=0;
    private static int s_depositos=0;
    private static int s_extracciones=0;
    private static int s_denegadas=0;
    private static int s_tot_deposito=0;
    private static int s_tot_extraido=0;
    private static readonly List<Cuenta> s_lista_cuentas=new List<Cuenta>();
    private int _ID;
    private int _monto{get;set;}

    public Cuenta(){
        _monto=0;
        s_cuentas++;
        s_lista_cuentas.Add(this);
        _ID=s_cuentas;
        Console.WriteLine("Se creo la cuenta "+ _ID);
    }
    public static List<Cuenta> GetCuentas(){
        List<Cuenta> copia=new List<Cuenta>();//PARA QUE LAS MODIFICACIONES A LA LISTA NO CAMBIEN LA CUENTA, HAGO UNA COPIA
        foreach(Cuenta c in s_lista_cuentas){
            copia.Add(c);
        }
        return copia;
    }
    public Cuenta Depositar(int cantidad){
        _monto=_monto+cantidad;
        s_depositos++;
        s_tot_deposito+=cantidad;
        Console.WriteLine("Se depositaron "+ cantidad+" en la cuenta "+ _ID+ " (Saldo= "+ _monto+")");
        return this;
    }
    public Cuenta Extraer(int cantidad){
        if (_monto>=cantidad){
            _monto=_monto-cantidad;
            s_extracciones++;
            s_tot_extraido+=cantidad;
            Console.WriteLine("Se extrajeron "+ cantidad+" en la cuenta "+ _ID + " (Saldo= "+ _monto+")");
        }
        else {
            Console.WriteLine("Operación denegada - Saldo insuficiente");
            s_denegadas++;
        }
        return this;
    }
    public static void Imprimir(){
        Console.WriteLine("CUENTAS CREADAS:  "+ s_cuentas);
        Console.Write("DEPOSITOS      :  "+ s_depositos); Console.WriteLine("     Total depositado  :  "+ s_tot_deposito);
        Console.Write("EXTRACCIONES   :  "+ s_extracciones);Console.WriteLine("     Total extraido  :  "+ s_tot_extraido);
        Console.Write("Se denegaron " + s_denegadas+ " extracciones por falta de fondos.");
    }
}