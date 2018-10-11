package com.rachev.passwordmanager.diconfig;

import com.rachev.passwordmanager.http.base.HttpRequester;
import com.rachev.passwordmanager.models.Password;
import com.rachev.passwordmanager.models.SocialUser;
import com.rachev.passwordmanager.parsers.base.JsonParser;
import com.rachev.passwordmanager.repositories.HttpRepository;
import com.rachev.passwordmanager.repositories.base.Repository;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class RepositoryModule
{
    @Provides
    @Singleton
    public Repository<Password> passwordsRepository(@Named("baseServerUrl") String baseServerUrl,
                                                    HttpRequester httpRequester,
                                                    JsonParser<Password> jsonParser)
    {
        return new HttpRepository<>(baseServerUrl + "/passwords", httpRequester, jsonParser);
    }
    
    @Provides
    @Singleton
    public Repository<SocialUser> usersRepository(@Named("baseServerUrl") String baseServerUrl,
                                                      HttpRequester httpRequester,
                                                      JsonParser<SocialUser> jsonParser)
    {
        return new HttpRepository<>(baseServerUrl + "/users", httpRequester, jsonParser);
    }
}
