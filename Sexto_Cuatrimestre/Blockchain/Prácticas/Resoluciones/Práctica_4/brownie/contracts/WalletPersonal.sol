// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;
import "@openzeppelin/contracts/access/Ownable.sol";
contract WalletPersonal is Ownable{
    function deposit() public payable{
        require(msg.value>0, "Debe ingresar un valor");
    }
    receive() external payable { }
    fallback() external payable { }

    function withdraw(uint256 _amount) public onlyOwner {
        require(_amount <= address(this).balance, "Fondos insuficientes en el contrato");
        payable(owner()).transfer(_amount); 
  
    }
    function balance() public view returns (uint256){
        return address(this).balance;
    }


}