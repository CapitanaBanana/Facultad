// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;
contract RegistroDeUsuarios {
    struct Usuario{
        address direccion;
        string usuario;
    }
    Usuario[] private usuarios;

    function registrarse(string memory nombre) public noRegistrado {
        Usuario memory user = Usuario(msg.sender, nombre);
        usuarios.push(user);
    } 

    modifier noRegistrado() {
        require(!estaRegistrado(msg.sender), "Ya estas registrado");
        _;  
    }
    function estaRegistrado(address sender) public view returns (bool){
        for (uint256 i=0; i<usuarios.length; i++) 
        {
            if (sender== usuarios[i].direccion){
                return true;
            }
        }
        return false;
    }
    function getUsuario(address direccion) public view returns (string memory nombre) {
        for (uint i = 0; i < usuarios.length; i++) {
            if (usuarios[i].direccion == direccion){
                return usuarios[i].usuario;
            }
        }
        return "Usuario no encontrado";
    }

}
contract MuroDeMensajes {
    RegistroDeUsuarios private registroDeUsuarios;
    Mensaje[] private muro;
    struct Mensaje {
        string usuario;
        string contenido;
    }
    constructor(address _registroDeUsuarios) {
        registroDeUsuarios = RegistroDeUsuarios(_registroDeUsuarios);
    }
    //esto tiene sentido pero no lo había pensado. Si llamo de una a esta registrado el msg.sender es muro de mensajes, no el que llama a la función posta
    modifier soloRegistrados() {
        require(registroDeUsuarios.estaRegistrado(msg.sender), "Tenes que registrarte para dejar un mensaje");
        _;
    }

    function dejarMensaje(string memory mensaje) public soloRegistrados{
        string memory user= registroDeUsuarios.getUsuario(msg.sender);
        Mensaje memory men = Mensaje(user, mensaje);
        muro.push(men);
    }
     function leerMensajes() public view returns (Mensaje[] memory) {
        return muro;
    }
    
}