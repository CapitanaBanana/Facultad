// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;
import "../interfaces/IRegistroDeUsuarios.sol";

contract MockRegistroDeUsuarios is IRegistroDeUsuarios {
    mapping(address => bool) private usuariosRegistrados;
    mapping(address => string) private nombreDeUsuario;

    // Función de registro mock
    function registrarse(string memory nombre) public override {
        usuariosRegistrados[msg.sender] = true;
        nombreDeUsuario[msg.sender] = nombre;
    }

    // Verifica si la dirección está registrada
    function estaRegistrado(address sender) public override view returns (bool) {
        return usuariosRegistrados[sender];
    }

    // Devuelve el nombre de usuario si está registrado
    function getUsuario(address direccion) public override view returns (string memory nombre) {
        if (usuariosRegistrados[direccion]) {
            return nombreDeUsuario[direccion];
        } else {
            return "Usuario no encontrado";
        }
    }
}