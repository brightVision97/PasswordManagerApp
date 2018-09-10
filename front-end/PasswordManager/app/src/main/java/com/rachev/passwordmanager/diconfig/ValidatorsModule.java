package com.rachev.passwordmanager.diconfig;

import com.rachev.passwordmanager.models.Password;
import com.rachev.passwordmanager.validators.PasswordValidator;
import com.rachev.passwordmanager.validators.base.Validator;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ValidatorsModule
{
    @Provides
    @Singleton
    public Validator<Password> superheroValidator()
    {
        return new PasswordValidator();
    }
}
