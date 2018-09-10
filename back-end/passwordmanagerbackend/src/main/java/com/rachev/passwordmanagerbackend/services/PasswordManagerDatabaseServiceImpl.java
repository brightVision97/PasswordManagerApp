package com.rachev.passwordmanagerbackend.services;

import com.rachev.passwordmanagerbackend.dao.PasswordManagerDao;
import com.rachev.passwordmanagerbackend.models.Password;
import com.rachev.passwordmanagerbackend.services.base.PasswordManagerService;
import com.rachev.passwordmanagerbackend.utils.crypto.CryptoUtilsImpl;
import com.rachev.passwordmanagerbackend.utils.crypto.base.CryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PasswordManagerDatabaseServiceImpl implements PasswordManagerService
{
    private static final String ENCRYPTION_KEY = "cRZsiLXIczVyczOp4wqgBlbre0tXSzYZ";
    
    private final PasswordManagerDao passwordManagerDao;
    
    @Autowired
    public PasswordManagerDatabaseServiceImpl(PasswordManagerDao passwordManagerDao)
    {
        this.passwordManagerDao = passwordManagerDao;
    }
    
    @Override
    public List<Password> getAllPasswords()
    {
        List<Password> passwords = new ArrayList<>();
        passwordManagerDao.findAll().forEach(passwords::add);
        
        return passwords;
    }
    
    @Override
    public Password findPasswordById(int id)
    {
        if (passwordManagerDao.findById(id).isPresent())
        {
            CryptoUtils decrypter = new CryptoUtilsImpl();
            
            Password encrpyptedPasswordObj =
                    passwordManagerDao.findById(id)
                            .get();
            
            try
            {
                encrpyptedPasswordObj.setPassword(
                        decrypter.decrypt(
                                ENCRYPTION_KEY,
                                encrpyptedPasswordObj.getPassword()));
                
                return encrpyptedPasswordObj;
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        return null;
    }
    
    @Override
    public Password createPassword(Password password)
    {
        CryptoUtils encrypter = new CryptoUtilsImpl();
        
        try
        {
            String encryptedPassword = encrypter.encrypt(
                    ENCRYPTION_KEY,
                    password.getPassword());
            
            password.setPassword(encryptedPassword);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return passwordManagerDao.save(password);
    }
    
    @Override
    public void deletePasswordById(int id)
    {
        passwordManagerDao.deleteById(id);
    }
}
