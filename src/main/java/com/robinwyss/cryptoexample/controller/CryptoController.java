package com.robinwyss.cryptoexample.controller;

import com.robinwyss.cryptoexample.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@Controller
public class CryptoController {


    @Autowired
    private EncryptionService encryptionService;

    @GetMapping("/")
    public String get(Model model) throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        int maxKeySize = javax.crypto.Cipher.getMaxAllowedKeyLength("AES");
        model.addAttribute("keySize", maxKeySize);

        encryptAES(model);
        return "index";
    }



    private void encryptAES(Model model) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        byte[] encryptedData = encryptionService.encrypt("hello crypto");
        model.addAttribute("encryptedData", encryptedData);

        byte[] decryptedData= encryptionService.decrypt(encryptedData);
        model.addAttribute("decryptedData", new String(decryptedData));
    }

}
