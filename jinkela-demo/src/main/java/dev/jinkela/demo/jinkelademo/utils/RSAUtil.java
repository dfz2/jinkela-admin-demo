package dev.jinkela.demo.jinkelademo.utils;

import lombok.experimental.UtilityClass;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@UtilityClass
public final class RSAUtil {

    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); //512、1024、2048
        return keyPairGenerator.generateKeyPair();
    }


    public static String getPublicKeyString(KeyPair keyPair) {
        PublicKey publicKey = keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();
        return Base64Util.byteToBase64String(bytes);
    }

    public static String getPrivateKeyString(KeyPair keyPair) {
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();
        return Base64Util.byteToBase64String(bytes);
    }

    public static PublicKey getPublicKey(String publicKeyString) throws Exception {
        byte[] keyBytes = Base64Util.base64StringToBytes(publicKeyString);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }


    public static PrivateKey getPrivateKey(String privateKeyString) throws Exception {
        byte[] keyBytes = Base64Util.base64StringToBytes(privateKeyString);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    public static String encrypt(String content, String publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, RSAUtil.getPublicKey(publicKey));
        byte[] byteEncrypt = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
        return Base64Util.byteToBase64String(byteEncrypt);
    }

    public static String decrypt(String contentBase64, String privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, RSAUtil.getPrivateKey(privateKey));
        byte[] bytesDecrypt = cipher.doFinal(Base64Util.base64StringToBytes(contentBase64));
        return new String(bytesDecrypt, StandardCharsets.UTF_8);
    }


}