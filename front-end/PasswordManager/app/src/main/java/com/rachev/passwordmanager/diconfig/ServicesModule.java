package com.rachev.passwordmanager.diconfig;

import com.rachev.passwordmanager.models.Password;
import com.rachev.passwordmanager.repositories.base.Repository;
import com.rachev.passwordmanager.services.HttpPasswordsService;
import com.rachev.passwordmanager.services.base.PasswordsService;
import dagger.Module;
import dagger.Provides;

@Module
public class ServicesModule
{
    @Provides
    public PasswordsService superheroesService(Repository<Password> repository)
    {
        return new HttpPasswordsService(repository);
    }
}
