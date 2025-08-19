package ejercicio5;
import ejercicio3.*;
public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vertice<String> v1 = new VerticeImplListAdy<String>("Buenos Aires");
		Vertice<String> v2 = new VerticeImplListAdy<String>("Santiago");
		Vertice<String> v3 = new VerticeImplListAdy<String>("Lima");
		Vertice<String> v4= new VerticeImplListAdy<String>("Montevideo");
		Vertice<String> v5= new VerticeImplListAdy<String>("Asuncion");
		Vertice<String> v6= new VerticeImplListAdy<String>("La Habana");
		Vertice<String> v7= new VerticeImplListAdy<String>("Caracas");

		Grafo<String> ciudades = new GrafoImplListAdy<String>();
		ciudades.agregarVertice(v1);
		ciudades.agregarVertice(v2);
		ciudades.agregarVertice(v3);
		ciudades.agregarVertice(v4);
		ciudades.agregarVertice(v5);
		ciudades.agregarVertice(v6);
		ciudades.agregarVertice(v7);

		ciudades.conectar(v1, v2);
		ciudades.conectar(v1, v3);
		ciudades.conectar(v1, v4);
		ciudades.conectar(v1, v5);
		ciudades.conectar(v3, v5); 
		ciudades.conectar(v2, v5);
		ciudades.conectar(v4, v5);
		ciudades.conectar(v2, v6);
		ciudades.conectar(v7, v6);
		ciudades.conectar(v7, v5);

		Recorridos<String> r=new Recorridos<String>();
		ListaGenerica<Vertice<String>> lista =r.dfs(ciudades);
		while(!lista.fin()) {
			System.out.println(lista.proximo().dato());
		}
		Vertice<String> RioCuarto = new VerticeImplListAdy<String>("Rio Cuarto");
        Vertice<String> VillaMaria= new VerticeImplListAdy<String>("Villa Maria");
        Vertice<String> CruzDelEje= new VerticeImplListAdy<String>("Cruz del Eje");
        Vertice<String> VillaGralBelgrano= new VerticeImplListAdy<String>("Villa Gral Belgrano");
        Vertice<String> JesusMaria= new VerticeImplListAdy<String>("Jesus Maria");
        Vertice<String> Tulumba= new VerticeImplListAdy<String>("Tulumba");
        Vertice<String> VillaDolores= new VerticeImplListAdy<String>("Villa Dolores");
        Vertice<String> Cordoba= new VerticeImplListAdy<String>("Cordoba");

        Grafo <String> ciudades2 = new GrafoImplListAdy<String>();

        ciudades2.agregarVertice(RioCuarto);
        ciudades2.agregarVertice(VillaMaria);
        ciudades2.agregarVertice(CruzDelEje);
        ciudades2.agregarVertice(VillaGralBelgrano);
        ciudades2.agregarVertice(JesusMaria);
        ciudades2.agregarVertice(Tulumba);
        ciudades2.agregarVertice(VillaDolores);
        ciudades2.agregarVertice(Cordoba);

        ciudades2.conectar(RioCuarto, VillaMaria,70);
        ciudades2.conectar(VillaMaria, RioCuarto,70);

        ciudades2.conectar(RioCuarto, VillaGralBelgrano,50);
        ciudades2.conectar(VillaGralBelgrano, RioCuarto,50);

        ciudades2.conectar(VillaMaria, JesusMaria,60);
        ciudades2.conectar(JesusMaria, VillaMaria,60);

        ciudades2.conectar(VillaMaria, CruzDelEje,80);
        ciudades2.conectar(CruzDelEje, VillaMaria,80);

        ciudades2.conectar(VillaGralBelgrano, CruzDelEje,85);
        ciudades2.conectar(CruzDelEje, VillaGralBelgrano,85);

        ciudades2.conectar(RioCuarto, VillaDolores,90);
        ciudades2.conectar(VillaDolores, RioCuarto,90);

        ciudades2.conectar(VillaDolores, Tulumba,70);
        ciudades2.conectar(Tulumba, VillaDolores,70);

        ciudades2.conectar(VillaDolores, JesusMaria,70);
        ciudades2.conectar(JesusMaria, VillaDolores,70);

        ciudades2.conectar(Tulumba, VillaGralBelgrano,90);
        ciudades2.conectar(VillaGralBelgrano, Tulumba,90);

        ciudades2.conectar(CruzDelEje, Cordoba,60);
        ciudades2.conectar(Cordoba, CruzDelEje,60);

        ciudades2.conectar(JesusMaria, Cordoba,90);
        ciudades2.conectar(Cordoba, JesusMaria,90);

        ciudades2.conectar(Tulumba, Cordoba,75);
        ciudades2.conectar(Cordoba, Tulumba,75);
				Parcial2017 p= new Parcial2017();
				System.out.println("hola");
				System.out.println(p.caminoDistanciaMaxima(ciudades2, "Rio Cuarto", "Cordoba", 30));
	}
}
