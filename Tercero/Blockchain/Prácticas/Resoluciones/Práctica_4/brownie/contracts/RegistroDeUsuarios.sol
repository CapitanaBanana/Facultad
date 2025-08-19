// SPDX-License-Identifier: MIT
import "./interfaces/IRegistroDeUsuarios.sol";
pragma solidity ^0.8.4;
contract RegistroDeUsuarios is IRegistroDeUsuarios{
    struct Usuario{
        address direccion;
        string usuario;
    }
    Usuario[] private usuarios;

    function registrarse(string memory nombre) public override noRegistrado {
        Usuario memory user = Usuario(msg.sender, nombre);
        usuarios.push(user);
    } 

    modifier noRegistrado() {
        require(!estaRegistrado(msg.sender), "Ya estas registrado");
        _;  
    }
    function estaRegistrado(address sender) public override view returns (bool){
        for (uint256 i=0; i<usuarios.length; i++) 
        {
            if (sender== usuarios[i].direccion){
                return true;
            }
        }
        return false;
    }
    function getUsuario(address direccion) public override view returns (string memory nombre) {
        for (uint i = 0; i < usuarios.length; i++) {
            if (usuarios[i].direccion == direccion){
                return usuarios[i].usuario;
            }
        }
        return "Usuario no encontrado";
    }

}
