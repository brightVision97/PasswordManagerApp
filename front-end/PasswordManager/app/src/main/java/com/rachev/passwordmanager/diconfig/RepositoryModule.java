package com.rachev.passwordmanager.diconfig;

import com.rachev.passwordmanager.http.base.HttpRequester;
import com.rachev.passwordmanager.models.Password;
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
    public Repository<Password> superheroRepository(
            @Named("baseServerUrl") String baseServerUrl,
            HttpRequester httpRequester,
            JsonParser<Password> jsonParser)
    {
        String url = baseServerUrl + "/passwords";
        return new HttpRepository<>(url, httpRequester, jsonParser);
    }
}
