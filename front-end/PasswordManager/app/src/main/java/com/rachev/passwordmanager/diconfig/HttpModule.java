package com.rachev.passwordmanager.diconfig;

import com.rachev.passwordmanager.utils.Constants;
import com.rachev.passwordmanager.http.OkHttpHttpRequester;
import com.rachev.passwordmanager.http.base.HttpRequester;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;

@Module
public class HttpModule
{
    @Provides
    public HttpRequester httpRequester()
    {
        return new OkHttpHttpRequester();
    }
    
    @Provides
    @Named("baseServerUrl")
    public String baseServerUrl()
    {
        return Constants.BASE_SERVER_URL;
    }
}
