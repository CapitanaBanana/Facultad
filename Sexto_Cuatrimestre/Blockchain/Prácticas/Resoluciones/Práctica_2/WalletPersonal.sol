pragma solidity ^0.8.4;
contract WalletPersonal {
    address private owner;
    constructor() {
        owner = msg.sender; 
    }
    modifier onlyOwner() {
        require(msg.sender == owner, "Solo el propietario puede ejecutar esto");
        _;  
    }
    //no entiendo cómo depositarle a esto dado que la función no tiene input
    function deposit() public payable{
        require(msg.value>0, "Debe ingresar un valor");
    }
    //PREGUNTAR no entendí que son estos de abajo y si son necesarios
    //update cuando pongo en lo de low level interactions me dice que fallback no está definido
    //update 2 ok le tenía que agregar lo de payable
    //update 3 creo que esto debería pasarle eth al contrato pero como le pongo números chicos me chilla y si le pongo números grandes me da error??
//update 3 el campo value no me deja poner menos de 1 y las cuentas que tengo tienen menos de 1 ether :p

    receive() external payable { }
    fallback() external payable { }

    function withdraw(uint256 _amount) public onlyOwner {
        require(_amount <= address(this).balance, "Fondos insuficientes en el contrato");
        payable(owner).transfer(_amount); //tenés que ser owner para ejecutar la función y le va a pasar al owner la plata??? creo que no entendí
        //YA ENTENDÍ LE PASAS PLATA AL CONTRATO Y DESPUÉS RETIRAS DESDE EL CONTRATO
    }
    function balance() public view returns (uint256){
        return address(owner).balance;
    }


}