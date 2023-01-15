package com.sbipbackend.sbipeth.send;

import com.sbipbackend.sbipeth.SbipEthApplication;
import com.sbipbackend.sbipeth.contract.SmartContract;
import com.sbipbackend.sbipeth.contract.SmartContractConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

@Controller
public class SendController {

    @RequestMapping("/send")
    private String renderHTML() {
        return "send";
    }

    @PostMapping("/send")
    private String renderPostHTML(@RequestParam("from") String fromAccount,
                                   @RequestParam("to") String toAccount,
                                   @RequestParam("amount") double amount,
                                   Model model) {
        try {
            if (WalletUtils.isValidAddress(toAccount)) {
                sendEth(fromAccount, toAccount, amount);
                model.addAttribute("success", true);
                model.addAttribute("ethAmount", amount);
                model.addAttribute("toAccount", toAccount);
            }
            else {
                model.addAttribute("success", false);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "send";
    }

    private void sendEth(String fromAccount, String toAccount, double amount) throws Exception {

        SbipEthApplication.getWeb3j();
        SmartContract smartContract = SbipEthApplication.getSmartContract();

        int requestId = SmartContractConfig.generateIdentifier();
        BigInteger value = Convert.toWei(BigDecimal.valueOf(amount), Convert.Unit.ETHER).toBigInteger();
        TransactionReceipt transactionReceipt = smartContract.send(fromAccount, toAccount, value).send();

        String params = "from" + fromAccount + "to" + toAccount + "amount" + amount;
        String signature = SmartContractConfig.generateSignature(requestId, fromAccount, params);

        System.out.println("Transaction Hash: " + transactionReceipt.getTransactionHash());
        System.out.println("Signature: " + signature);

        SbipEthApplication.getWeb3j().shutdown();
    }
}
