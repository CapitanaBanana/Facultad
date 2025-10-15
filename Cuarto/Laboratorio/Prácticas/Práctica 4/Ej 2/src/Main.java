//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Veterinaria <Animal> vet = new Veterinaria <Gato>();
        Veterinaria <?> vet2 = new Veterinaria<Gato>();
        vet2.setAnimal(new Gato());
        Veterinaria vet3 = new Veterinaria ();
        vet3.setAnimal(new Perro());
        Veterinaria vet4 = new Veterinaria <?>();
        Veterinaria <? extends Animal> vet5 = new Veterinaria<Gato>();


    }
}