// SPDX-License-Identifier: MIT
pragma solidity 0.8.17;

contract SmartContract {

    constructor() payable {}
    receive() external payable {}

    struct BalanceRecord {
        address account;
        uint256 balance;
    }

    struct PublicKeyRecords {
        mapping(address => string[]) keys;
    }

    mapping(address => BalanceRecord) public balanceRecords;
    PublicKeyRecords publicKeyRecords;

    function updateBalanceRecords(address account, uint256 _balance) internal {
        balanceRecords[account] = BalanceRecord(account, _balance);
    }

    function addPublicKeyRecords(address account, string memory publicKey) public {
        publicKeyRecords.keys[account].push(publicKey);
    }

    function getPublicKeyRecords(address account) public view returns (string[] memory) {
        return publicKeyRecords.keys[account];
    }

    function send(address payable from, address payable to) public payable returns (string memory) {
        require(msg.sender == from && to != address(0), "You have entered the wrong address");
        require(msg.sender.balance >= msg.value, "Insufficient funds");

        (bool sent,) = to.call{value: msg.value}("");

        if (sent) {
            updateBalanceRecords(msg.sender, msg.sender.balance);
            updateBalanceRecords(to, to.balance);
            return "SUCCESS";
        } else {
            return "ERROR";
        }
    }

    function getBalance(address accountAddress) public view returns (uint256) {
        return address(accountAddress).balance;
    }

}