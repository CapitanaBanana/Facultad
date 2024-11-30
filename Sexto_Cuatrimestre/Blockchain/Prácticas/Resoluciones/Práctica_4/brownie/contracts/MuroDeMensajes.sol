// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;
import "./interfaces/IRegistroDeUsuarios.sol";
contract MuroDeMensajes {
    IRegistroDeUsuarios private registroDeUsuarios;
    Mensaje[] private muro;
    struct Mensaje {
        string usuario;
        string contenido;
    }
    constructor(address _registroDeUsuarios) {
        registroDeUsuarios = IRegistroDeUsuarios(_registroDeUsuarios);
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