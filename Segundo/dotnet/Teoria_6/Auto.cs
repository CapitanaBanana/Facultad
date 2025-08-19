namespace Teoria_6;

class Auto: Automotor
{
   public TipoAuto Tipo;
   public override void Imprimir()
{
Console.Write($"Auto {Tipo} ");
base.Imprimir();
}
}
