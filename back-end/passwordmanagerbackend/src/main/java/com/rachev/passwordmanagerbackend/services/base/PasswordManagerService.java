package com.rachev.passwordmanagerbackend.services.base;

import com.rachev.passwordmanagerbackend.models.Password;

import java.util.List;

public interface PasswordManagerService
{
    List<Password> getAllPasswords();
    
    Password findPasswordById(int id);
    
    Password createPassword(Password password);
    
    void deletePasswordById(int id);
}
