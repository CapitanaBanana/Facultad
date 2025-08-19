using Teoria_6;

Auto a = new Auto();
Colectivo c = new Colectivo();
a.Marca = "Ford";
a.Modelo = 2000;
c.Marca = "Mercedes";
c.Modelo = 2010;
c.CantPasajeros = 20;
a.Tipo = TipoAuto.Deportivo;
a.Imprimir();
c.Imprimir();
