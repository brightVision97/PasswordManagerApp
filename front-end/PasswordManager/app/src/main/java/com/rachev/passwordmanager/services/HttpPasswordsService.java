package com.rachev.passwordmanager.services;

import com.rachev.passwordmanager.models.Password;
import com.rachev.passwordmanager.repositories.base.Repository;
import com.rachev.passwordmanager.services.base.PasswordsService;

import java.util.List;

public class HttpPasswordsService implements PasswordsService
{
    private final Repository<Password> mPasswordsRepository;
    
    public HttpPasswordsService(Repository<Password> passwordsRepository)
    {
        mPasswordsRepository = passwordsRepository;
    }
    
    @Override
    public List<Password> getAllPasswords() throws Exception
    {
        return mPasswordsRepository.getAll();
    }
    
    @Override
    public Password getDetailsById(int id) throws Exception
    {
        return mPasswordsRepository.getById(id);
    }
}
