package com.rachev.passwordmanagerbackend.services;

import com.rachev.passwordmanagerbackend.models.Password;
import com.rachev.passwordmanagerbackend.models.SocialUser;
import com.rachev.passwordmanagerbackend.repositories.PasswordsRepository;
import com.rachev.passwordmanagerbackend.repositories.UsersRepository;
import com.rachev.passwordmanagerbackend.services.base.PasswordManagerService;
import com.rachev.passwordmanagerbackend.utils.crypto.CryptoUtilsImpl;
import com.rachev.passwordmanagerbackend.utils.crypto.base.CryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordManagerDatabaseServiceImpl implements PasswordManagerService
{
    private static final String ENCRYPTION_KEY = "cRZsiLXIczVyczOp4wqgBlbre0tXSzYZ";
    
    private final PasswordsRepository passwordsRepository;
    private final UsersRepository usersRepository;
    private final CryptoUtils cryptoUtils;
    
    {
        cryptoUtils = new CryptoUtilsImpl();
    }
    
    @Autowired
    public PasswordManagerDatabaseServiceImpl(PasswordsRepository passwordsRepository,
                                              UsersRepository usersRepository)
    {
        this.passwordsRepository = passwordsRepository;
        this.usersRepository = usersRepository;
    }
    
    @Override
    public List<Password> getAllPasswords()
    {
        return passwordsRepository.findAll();
    }
    
    @Override
    public Password getPasswordById(int id)
    {
        Password password = null;
        
        if (passwordsRepository.findById(id).isPresent())
            password = passwordsRepository.findById(id).get();
        
        try
        {
            password.setPassword(cryptoUtils.decrypt(ENCRYPTION_KEY,
                    password.getPassword()));
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        
        return password;
    }
    
    @Override
    public Password createPassword(Password password)
    {
        try
        {
            password.setPassword(cryptoUtils.encrypt(ENCRYPTION_KEY,
                    password.getPassword()));
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        
        return passwordsRepository.save(password);
    }
    
    @Override
    public void deletePasswordById(int id)
    {
        passwordsRepository.deleteById(id);
    }
    
    @Override
    public List<SocialUser> getAllUsers()
    {
        return usersRepository.findAll();
    }
    
    @Override
    public SocialUser addUser(SocialUser user)
    {
        return usersRepository.save(user);
    }
}
