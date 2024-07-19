package io.lokopay.util;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

public final class Security {

    private static SecretKey genSecretKey(byte[] keyBytes)
            throws Exception {
        MessageDigest hash = MessageDigest.getInstance("SHA-256");
        byte[] derivedKey = hash.digest(keyBytes);
        return new SecretKeySpec(derivedKey, 0, 16, "AES");
    }

    public static String AESEncrypt(String plaintext, String key)
            throws Exception {

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = genSecretKey(key.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String AESDecrypt(String ciphertext, String key)
            throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = genSecretKey(key.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decoded = Base64.getDecoder().decode(ciphertext);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted);
    }

    public static String HmacSignature(String data, String secretKey)
            throws Exception {

        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        mac.init(keySpec);
        byte[] rawHmac = mac.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(rawHmac);
    }
}
