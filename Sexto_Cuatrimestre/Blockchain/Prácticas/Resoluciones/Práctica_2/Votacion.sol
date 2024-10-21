// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;
contract Votacion {
    int128[] private candidatos;
    address[] private participantes;
    uint256 private cant;

    constructor(uint128 _cantCandidatos) {
        cant=_cantCandidatos;
        for (uint256 i = 0; i < cant; i++) {
            candidatos.push(0);
        }
    }
    function votar(uint128 voto) public puedeVotar{
        require((voto>0 && voto<=cant), "Voto invalido");
        participantes.push(msg.sender);
        candidatos[voto-1]++;
    }
    function GetGanador() public view returns (uint256) {
        int128 max = -1;
        uint256 maxCandidato = 0; 

        for (uint256 i = 0; i < cant; i++) {
            if (candidatos[i] > max) {
                max = candidatos[i];
                maxCandidato = i+1;
            }
        }

        return maxCandidato;
    }
    modifier puedeVotar() {
        require(!yaVoto(), "Ya votaste! sali de aca");
        _;  
    }
    function yaVoto() internal view returns (bool){
        for (uint i = 0; i < participantes.length; i++) {
            if (participantes[i] == msg.sender) {
                return true;  // ya votó c:
            }
        }
        return false; //no votó
    }
}