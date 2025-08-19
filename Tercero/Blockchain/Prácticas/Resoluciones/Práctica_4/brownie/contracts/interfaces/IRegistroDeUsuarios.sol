// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;
interface IRegistroDeUsuarios {
    function registrarse(string calldata nombre) external;

    function estaRegistrado(address sender) external view returns (bool);

    function getUsuario(address direccion) external view returns (string memory);
}