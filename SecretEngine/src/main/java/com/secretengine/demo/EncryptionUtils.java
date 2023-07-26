package com.secretengine.demo;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class EncryptionUtils {

    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String ENCRYPTION_KEY = "0123456789abcdef0123456789abcdef";

    public String encrypt(String plainText) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), ENCRYPTION_ALGORITHM);
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String encryptedText) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), ENCRYPTION_ALGORITHM);
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
    
    public static byte[] decryptFile(byte[] encryptedData) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

