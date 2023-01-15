package com.sbipbackend.sbipeth.contract;

import com.sbipbackend.sbipeth.SbipEthApplication;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Numeric;

import javax.crypto.Cipher;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class SmartContractConfig {

    private final static String PRIVATE_KEY = System.getenv("TESTNET_PRIVATE_KEY");
    private final static String API_KEY = "https://sepolia.infura.io/v3/" + System.getenv("INFURA_API_KEY");
    private final static String CONTRACT_ADDRESS = "0x03dfdfec5b948845d5056877aa558898e65ceec9";

    public final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    public final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
    // private final static ContractGasProvider contractGasProvider = new StaticGasProvider(GAS_PRICE, GAS_LIMIT);
    private final static ContractGasProvider contractGasProvider = new DefaultGasProvider();

    public static Credentials getCredentialsFromPrivateKey() {
        return Credentials.create(PRIVATE_KEY);
    }

    public static Credentials getCredentialsFromWallet() throws IOException, CipherException {
        return WalletUtils.loadCredentials(
                "passphrase",
                "wallet/path"
        );
    }

    public static int generateIdentifier() {
        SecureRandom random = new SecureRandom();
        int id = random.nextInt(2000000000);
        if (id < 0) {
            id = id % 2000000000 + 2000000000;
        }
        return id;
    }

    public static String generateSignature(int id, String accountId, String params) throws Exception {

        long timestamp = System.currentTimeMillis();
        System.out.println("id: " + id + " timestamp: " + timestamp);
        System.out.println("Parameters: " + params);

        // encryption - use private key, decryption - use public key
        SecureRandom random = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048, random);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // update state database
        SbipEthApplication.getSmartContract().addPublicKeyRecords(accountId, publicKey.toString());

        // convert hexadecimal public key to bytes
        byte[] publicKeyBytes = Numeric.hexStringToByteArray(publicKey.toString());
        // encode bytes to base64 representation
        String base64PublicKey = Base64.getEncoder().encodeToString(publicKeyBytes);
        System.out.println("Public Key (Base64): " + base64PublicKey);
        String message = id + timestamp + params + base64PublicKey;

        byte[] originalMessage = message.getBytes();
        int fragmentSize = 245;
        int numberOfFragments = (int) Math.ceil((double) originalMessage.length / fragmentSize);

        List<byte[]> fragments = new ArrayList();
        for (int i = 0; i < numberOfFragments; i++) {
            int start = i * fragmentSize;
            int end = Math.min(start + fragmentSize, originalMessage.length);
            byte[] fragment = Arrays.copyOfRange(originalMessage, start, end);
            fragments.add(fragment);
        }

        byte[] encryptedFragments = new byte[0];
        Cipher cipher = Cipher.getInstance("RSA");
        // Encrypt the plaintext using private key
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        for (byte[] fragment : fragments) {
            byte[] encryptedFragment = cipher.doFinal(fragment);
            encryptedFragments = concatBytes(encryptedFragments, encryptedFragment);
        }

        // Encode output in base64 to get final signature
        String signature = Base64.getEncoder().encodeToString(encryptedFragments);

        return signature;
    }

    private static byte[] concatBytes(byte[] first, byte[] second) {
        byte[] result = new byte[first.length + second.length];
        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    public static String deployContract(Web3j web3j, TransactionManager transactionManager) throws Exception {
        return SmartContract.deploy(web3j, transactionManager, contractGasProvider, BigInteger.ZERO)
                .send()
                .getContractAddress();
    }

    public static SmartContract loadContract(String contractAddress, Web3j web3j, TransactionManager transactionManager) {
        return SmartContract.load(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static String getAPIKey() {
        return API_KEY;
    }

    public static String getContractAddress() { return CONTRACT_ADDRESS; }
}
