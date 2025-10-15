public class UnServidor {
    @Servidor(
            direccion = 123,
            puerto=1,
            archivo= "un archivo"
    )
    public void crear(){

    }

    @Invocar
    public void loguearCliente(){}
}
