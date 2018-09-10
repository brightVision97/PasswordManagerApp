package com.rachev.passwordmanagerbackend.utils.crypto;

import com.rachev.passwordmanagerbackend.constants.Constants;
import com.rachev.passwordmanagerbackend.utils.crypto.base.CryptoUtils;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class CryptoUtilsImpl implements CryptoUtils
{
    private Cipher encryptCipher;
    private Cipher decryptCipher;
    private byte[] salt;
    private int iterationCount;
    
    public CryptoUtilsImpl()
    {
        encryptCipher = null;
        decryptCipher = null;
        
        salt = new byte[]
                {
                        (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
                        (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03
                };
        
        iterationCount = Constants.CRYPTO_ITERATION_COUNT;
    }
    
    @Override
    public String encrypt(String key, String password)
            throws NoSuchAlgorithmException,
            InvalidKeySpecException,
            NoSuchPaddingException,
            InvalidAlgorithmParameterException,
            InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException
    {
        KeySpec keySpec = new PBEKeySpec(key.toCharArray(), salt, iterationCount);
        SecretKey secretKey = SecretKeyFactory.getInstance(Constants.ENCRYPTING_ALGORITHM)
                .generateSecret(keySpec);
        
        AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
        
        encryptCipher = Cipher.getInstance(secretKey.getAlgorithm());
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
        
        byte[] out = encryptCipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
        
        return new String(Base64.getEncoder().encode(out));
    }
    
    @Override
    public String decrypt(String key, String encryptedPassword)
            throws NoSuchAlgorithmException,
            InvalidKeySpecException,
            NoSuchPaddingException,
            InvalidAlgorithmParameterException,
            InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException
    {
        KeySpec keySpec = new PBEKeySpec(key.toCharArray(), salt, iterationCount);
        SecretKey secretKey = SecretKeyFactory.getInstance(Constants.ENCRYPTING_ALGORITHM)
                .generateSecret(keySpec);
        
        AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
        
        decryptCipher = Cipher.getInstance(secretKey.getAlgorithm());
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
        
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
        byte[] utf8Bytes = decryptCipher.doFinal(decodedBytes);
        
        return new String(utf8Bytes, StandardCharsets.UTF_8);
    }
}
