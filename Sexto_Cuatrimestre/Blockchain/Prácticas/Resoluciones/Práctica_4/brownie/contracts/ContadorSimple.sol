// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;
import "@openzeppelin/contracts/access/Ownable.sol";
contract ContadorSimple is Ownable{
    uint256 private counter=0;
    address[] private whiteList;

    function increment() public onlyWhiteList{
        counter+=1;
        emit counterModified(msg.sender, counter);
    }
    function decrement() public onlyWhiteList{
        require(counter > 0, "El contador ya es 0");
        counter-=1;
        emit counterModified(msg.sender, counter);
        
    }
    event counterModified(address by, uint256 counterNow);
    function getCounter() public onlyWhiteList view returns (uint256){
        return counter;
            
    }

    function isOnWhiteList() internal view returns (bool){
        for (uint i = 0; i < whiteList.length; i++) {
            if (whiteList[i] == msg.sender) {
                return true;  // la dirección está en la whiteList c:
            }
        }
        return false; //no ta
    }
    function addToWhiteList(address _address) public onlyOwner {
        require(_address != address(0), "Debe enviar una direccion valida"); 
        whiteList.push(_address);
    }

    modifier onlyWhiteList(){
        require(isOnWhiteList(), "La direccion no esta en la white list");
        _;
    }
    
    
}