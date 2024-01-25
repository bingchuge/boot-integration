package org.example;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.*;
import java.util.Base64;

public class CryptoUtils {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    // AES 加密
    public static String encryptAES(String data, SecretKey key, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // AES 解密
    public static String decryptAES(String encryptedData, SecretKey key, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decrypted);
    }

    // 生成 AES 密钥
    public static SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES", "BC");
        keyGen.init(256);
        return keyGen.generateKey();
    }

    // 生成随机 IV
    public static IvParameterSpec generateIV() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    // RSA 加密
    public static String encryptRSA(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // RSA 解密
    public static String decryptRSA(String encryptedData, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decrypted);
    }

    // 生成 RSA 密钥对
    public static KeyPair generateRSAKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA", "BC");
        keyPairGen.initialize(2048);
        return keyPairGen.generateKeyPair();
    }

    public static void main(String[] args) {
        try {
            // AES 加密演示
            SecretKey aesKey = CryptoUtils.generateAESKey();
            IvParameterSpec iv = CryptoUtils.generateIV();
            String originalData = "Hello, World!";
            String encryptedDataAES = CryptoUtils.encryptAES(originalData, aesKey, iv);
            String decryptedDataAES = CryptoUtils.decryptAES(encryptedDataAES, aesKey, iv);
            System.out.println("原始数据: " + originalData);
            System.out.println("AES加密后: " + encryptedDataAES);
            System.out.println("AES解密后: " + decryptedDataAES);

            // RSA 加密演示
            KeyPair rsaKeyPair = CryptoUtils.generateRSAKeyPair();
            String encryptedDataRSA = CryptoUtils.encryptRSA(originalData, rsaKeyPair.getPublic());
            String decryptedDataRSA = CryptoUtils.decryptRSA(encryptedDataRSA, rsaKeyPair.getPrivate());
            System.out.println("RSA加密后: " + encryptedDataRSA);
            System.out.println("RSA解密后: " + decryptedDataRSA);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

