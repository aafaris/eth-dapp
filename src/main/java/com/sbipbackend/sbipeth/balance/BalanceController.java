package com.sbipbackend.sbipeth.balance;

import com.sbipbackend.sbipeth.SbipEthApplication;
import com.sbipbackend.sbipeth.contract.SmartContract;
import com.sbipbackend.sbipeth.contract.SmartContractConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.web3j.crypto.WalletUtils;
import org.web3j.utils.Convert;

import java.math.BigInteger;


@Controller
public class BalanceController {

    @RequestMapping("/get-balance")
    private String renderHTML() {
        return "balance";
    }

    @PostMapping("/get-balance")
    private String renderPostHTML(@RequestParam("accountId") String accountId, Model model) {
        try {
            if (WalletUtils.isValidAddress(accountId)) {
                String balance = getBalanceViaSmartContract(accountId);
                model.addAttribute("success", true);
                model.addAttribute("balance", balance);
            } else {
                model.addAttribute("success", false);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "balance";
    }

    private String getBalanceViaSmartContract(String accountId) throws Exception {

        SbipEthApplication.getWeb3j();
        SmartContract smartContract = SbipEthApplication.getSmartContract();

        int requestId = SmartContractConfig.generateIdentifier();
        BigInteger balance = smartContract.getBalance(accountId).send();

        String params = "account" + accountId;
        String signature = SmartContractConfig.generateSignature(requestId, accountId, params);
        System.out.println("Signature: " + signature);

        SbipEthApplication.getWeb3j().shutdown();

        return Convert.fromWei(balance.toString(), Convert.Unit.ETHER).toString();
    }
}

