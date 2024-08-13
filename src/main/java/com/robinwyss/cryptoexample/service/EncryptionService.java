package com.robinwyss.cryptoexample.service;

import org.springframework.stereotype.Service;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@Service
public class EncryptionService {

    private final SecretKey secretKey;
    private final Cipher cipher;

    public EncryptionService() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
        // Generate a random AES key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES", "BC");
        keyGen.init(256);
        secretKey = keyGen.generateKey();
        // Create a cipher for encryption
        cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
    }

    public byte[] encrypt(String message) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        // Encrypt the data
        byte[] input = message.getBytes();
        byte[] encryptedData = cipher.doFinal(input);
        return encryptedData;
    }

    public byte[] decrypt(byte[] encryptedData) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        // Decrypt the data
        byte[] decryptedData = cipher.doFinal(encryptedData);
       return decryptedData;
    }
}
