package com.rachev.passwordmanagerbackend.utils.crypto.base;

public interface CryptoUtils
{
    String encrypt(String key, String password) throws Exception;
    
    String decrypt(String key, String encryptedPassword) throws Exception;
}