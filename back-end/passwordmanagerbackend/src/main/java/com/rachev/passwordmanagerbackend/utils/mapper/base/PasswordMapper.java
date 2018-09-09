package com.rachev.passwordmanagerbackend.utils.mapper.base;

import com.rachev.passwordmanagerbackend.models.Password;
import com.rachev.passwordmanagerbackend.viewmodels.PasswordViewModel;

import java.util.List;

public interface PasswordMapper
{
    PasswordViewModel map(Password password);
    
    Password map(PasswordViewModel passwordViewModel);
    
    List<PasswordViewModel> mapMany(List<Password> passwords);
}
