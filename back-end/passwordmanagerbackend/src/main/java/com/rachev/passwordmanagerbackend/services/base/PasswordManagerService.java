package com.rachev.passwordmanagerbackend.services.base;

import com.rachev.passwordmanagerbackend.models.Password;
import com.rachev.passwordmanagerbackend.models.SocialUser;

import java.util.List;

public interface PasswordManagerService
{
    List<Password> getAllPasswords();
    
    Password getPasswordById(int id);
    
    Password createPassword(Password password);
    
    void deletePasswordById(int id);
    
    List<SocialUser> getAllUsers();
    
    SocialUser addUser(SocialUser user);
}
