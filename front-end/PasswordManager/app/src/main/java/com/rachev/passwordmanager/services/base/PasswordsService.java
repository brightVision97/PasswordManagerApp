package com.rachev.passwordmanager.services.base;

import com.rachev.passwordmanager.models.Password;

import java.util.List;

public interface PasswordsService
{
    List<Password> getAllPasswords() throws Exception;
    
    Password getDetailsById(int id) throws Exception;
    
    Password createPassword(Password password) throws Exception;
    
    Password deletePassword(Password password) throws Exception;
}
