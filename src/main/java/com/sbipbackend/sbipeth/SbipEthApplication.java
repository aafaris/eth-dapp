package com.sbipbackend.sbipeth;

import com.sbipbackend.sbipeth.contract.SmartContract;
import com.sbipbackend.sbipeth.contract.SmartContractConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;

@SpringBootApplication
@Controller
public class SbipEthApplication {

	private static SmartContract smartContract;
	private static Web3j web3j;

	public static void main(String[] args) throws Exception {

		SpringApplication.run(SbipEthApplication.class, args);
		web3j = Web3j.build(new HttpService(SmartContractConfig.getAPIKey()));

		try {
			Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
			if (!web3ClientVersion.hasError()) {

				System.out.println("Connected to " + web3ClientVersion.getWeb3ClientVersion());
				TransactionManager transactionManager = new RawTransactionManager(
						web3j,
						SmartContractConfig.getCredentialsFromPrivateKey()
				);

				// DEPLOY CONTRACT
				// String deployedAddress = SmartContractConfig.deployContract(web3j, transactionManager);
				// System.out.println("Deployed contract address: " + deployedAddress);

				// LOAD CONTRACT
				smartContract = SmartContractConfig.loadContract(SmartContractConfig.getContractAddress(), web3j, transactionManager);
				System.out.println("Loaded contract address: " + SmartContractConfig.getContractAddress());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static SmartContract getSmartContract() {
		return smartContract;
	}

	public static Web3j getWeb3j() {
		return web3j;
	}

	@RequestMapping("/")
	public String renderHTML() {
		return "welcome";
	}
}
