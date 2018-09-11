package com.rachev.passwordmanager.services;

import com.rachev.passwordmanager.constants.Constants;
import com.rachev.passwordmanager.models.Password;
import com.rachev.passwordmanager.repositories.base.Repository;
import com.rachev.passwordmanager.services.base.PasswordsService;
import com.rachev.passwordmanager.validators.base.Validator;

import java.util.List;

public class HttpPasswordsService implements PasswordsService
{
    private final Repository<Password> mPasswordsRepository;
    private final Validator<Password> mPasswordValidator;
    
    public HttpPasswordsService(
            Repository<Password> passwordsRepository,
            Validator<Password> passwordValidator)
    {
        mPasswordsRepository = passwordsRepository;
        mPasswordValidator = passwordValidator;
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
    
    @Override
    public Password createPassword(Password password) throws Exception
    {
        if (!mPasswordValidator.isValid(password))
            throw new IllegalArgumentException(Constants.INVALID_PASSWORD_ERR_MSG);
        
        return mPasswordsRepository.add(password);
    }
    
    @Override
    public Password deletePasswordById(int id) throws Exception
    {
        return mPasswordsRepository.deleteById(id);
    }
}
