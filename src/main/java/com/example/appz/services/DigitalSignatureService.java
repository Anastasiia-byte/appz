package com.example.appz.services;

import com.example.appz.dtos.Hashable;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

@Service
public class DigitalSignatureService {

    private PublicKey publicKey;

    private final PrivateKey privateKey;

    public DigitalSignatureService() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();

        publicKey = pair.getPublic();
        privateKey = pair.getPrivate();
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PublicKey setPublicKey(byte[] keyEncoded) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(keyEncoded));
    }

    public byte[] createDigitalSignature(Hashable dataObject) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String secretData = dataObject.getSecretData();
        byte[] dataHash = generateHash(secretData);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(dataHash);
    }

    public boolean verifySignature(byte[] digitalSignature, Hashable dataObject, PublicKey publicKey) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] decryptedMessageHash = cipher.doFinal(digitalSignature);

        byte[] hashedMessage = generateHash(dataObject.getSecretData());

        return Arrays.equals(decryptedMessageHash, hashedMessage);
    }

    private byte[] generateHash(String personalData) throws NoSuchAlgorithmException {
        byte[] dataBytes = personalData.getBytes();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(dataBytes);
    }
}
