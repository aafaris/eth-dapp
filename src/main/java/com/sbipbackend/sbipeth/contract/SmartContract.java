package com.sbipbackend.sbipeth.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.2.
 */
@SuppressWarnings("rawtypes")
public class SmartContract extends Contract {
    public static final String BINARY = "60806040526107e7806100136000396000f3fe60806040526004361061004e5760003560e01c80631ea4122f1461005a5780634a1a5ed314610090578063b6d9d451146100ef578063be5f70ca1461010f578063f8b2cb4f1461013157600080fd5b3661005557005b600080fd5b34801561006657600080fd5b5061007a610075366004610477565b610167565b60405161008791906104e1565b60405180910390f35b34801561009c57600080fd5b506100d06100ab366004610477565b600060208190529081526040902080546001909101546001600160a01b039091169082565b604080516001600160a01b039093168352602083019190915201610087565b6101026100fd366004610543565b610256565b604051610087919061057c565b34801561011b57600080fd5b5061012f61012a3660046105a5565b6103df565b005b34801561013d57600080fd5b5061015961014c366004610477565b6001600160a01b03163190565b604051908152602001610087565b6001600160a01b0381166000908152600160209081526040808320805482518185028101850190935280835260609492939192909184015b8282101561024b5783829060005260206000200180546101be90610669565b80601f01602080910402602001604051908101604052809291908181526020018280546101ea90610669565b80156102375780601f1061020c57610100808354040283529160200191610237565b820191906000526020600020905b81548152906001019060200180831161021a57829003601f168201915b50505050508152602001906001019061019f565b505050509050919050565b6060336001600160a01b03841614801561027857506001600160a01b03821615155b6102d45760405162461bcd60e51b815260206004820152602260248201527f596f75206861766520656e7465726564207468652077726f6e67206164647265604482015261737360f01b60648201526084015b60405180910390fd5b343331101561031a5760405162461bcd60e51b8152602060048201526012602482015271496e73756666696369656e742066756e647360701b60448201526064016102cb565b6000826001600160a01b03163460405160006040518083038185875af1925050503d8060008114610367576040519150601f19603f3d011682016040523d82523d6000602084013e61036c565b606091505b5050905080156103ba57610381338031610415565b61039583846001600160a01b031631610415565b50506040805180820190915260078152665355434345535360c81b60208201526103d9565b505060408051808201909152600581526422a92927a960d91b60208201525b92915050565b6001600160a01b038216600090815260016020818152604083208054928301815583529091200161041082826106f1565b505050565b6040805180820182526001600160a01b039384168082526020808301948552600091825281905291909120905181546001600160a01b031916931692909217825551600190910155565b6001600160a01b038116811461047457600080fd5b50565b60006020828403121561048957600080fd5b81356104948161045f565b9392505050565b6000815180845260005b818110156104c1576020818501810151868301820152016104a5565b506000602082860101526020601f19601f83011685010191505092915050565b6000602080830181845280855180835260408601915060408160051b870101925083870160005b8281101561053657603f1988860301845261052485835161049b565b94509285019290850190600101610508565b5092979650505050505050565b6000806040838503121561055657600080fd5b82356105618161045f565b915060208301356105718161045f565b809150509250929050565b602081526000610494602083018461049b565b634e487b7160e01b600052604160045260246000fd5b600080604083850312156105b857600080fd5b82356105c38161045f565b9150602083013567ffffffffffffffff808211156105e057600080fd5b818501915085601f8301126105f457600080fd5b8135818111156106065761060661058f565b604051601f8201601f19908116603f0116810190838211818310171561062e5761062e61058f565b8160405282815288602084870101111561064757600080fd5b8260208601602083013760006020848301015280955050505050509250929050565b600181811c9082168061067d57607f821691505b60208210810361069d57634e487b7160e01b600052602260045260246000fd5b50919050565b601f82111561041057600081815260208120601f850160051c810160208610156106ca5750805b601f850160051c820191505b818110156106e9578281556001016106d6565b505050505050565b815167ffffffffffffffff81111561070b5761070b61058f565b61071f816107198454610669565b846106a3565b602080601f831160018114610754576000841561073c5750858301515b600019600386901b1c1916600185901b1785556106e9565b600085815260208120601f198616915b8281101561078357888601518255948401946001909101908401610764565b50858210156107a15787850151600019600388901b60f8161c191681555b5050505050600190811b0190555056fea26469706673582212205a19cb9598a81376d0c670097ce25c0d1059a7ccd58bd5d1e0e70fcf7d1b531f64736f6c63430008110033";

    public static final String FUNC_ADDPUBLICKEYRECORDS = "addPublicKeyRecords";

    public static final String FUNC_BALANCERECORDS = "balanceRecords";

    public static final String FUNC_GETBALANCE = "getBalance";

    public static final String FUNC_GETPUBLICKEYRECORDS = "getPublicKeyRecords";

    public static final String FUNC_SEND = "send";

    @Deprecated
    protected SmartContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SmartContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SmartContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SmartContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> addPublicKeyRecords(String account, String publicKey) {
        final Function function = new Function(
                FUNC_ADDPUBLICKEYRECORDS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.Utf8String(publicKey)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple2<String, BigInteger>> balanceRecords(String param0) {
        final Function function = new Function(FUNC_BALANCERECORDS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple2<String, BigInteger>>(function,
                new Callable<Tuple2<String, BigInteger>>() {
                    @Override
                    public Tuple2<String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getBalance(String accountAddress) {
        final Function function = new Function(FUNC_GETBALANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, accountAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<List> getPublicKeyRecords(String account) {
        final Function function = new Function(FUNC_GETPUBLICKEYRECORDS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> send(String from, String to, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SEND, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    @Deprecated
    public static SmartContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SmartContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SmartContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SmartContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SmartContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SmartContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SmartContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SmartContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SmartContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger initialWeiValue) {
        return deployRemoteCall(SmartContract.class, web3j, credentials, contractGasProvider, BINARY, "", initialWeiValue);
    }

    public static RemoteCall<SmartContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger initialWeiValue) {
        return deployRemoteCall(SmartContract.class, web3j, transactionManager, contractGasProvider, BINARY, "", initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<SmartContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployRemoteCall(SmartContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<SmartContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployRemoteCall(SmartContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }
}
