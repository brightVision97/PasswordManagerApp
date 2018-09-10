package com.rachev.passwordmanager.diconfig;

import com.rachev.passwordmanager.models.Password;
import com.rachev.passwordmanager.repositories.base.Repository;
import com.rachev.passwordmanager.services.HttpPasswordsService;
import com.rachev.passwordmanager.services.base.PasswordsService;
import com.rachev.passwordmanager.validators.base.Validator;
import dagger.Module;
import dagger.Provides;

@Module
public class ServicesModule
{
    @Provides
    public PasswordsService passwordsService(
            Repository<Password> repository,
            Validator<Password> validator)
    {
        return new HttpPasswordsService(repository, validator);
    }
}
