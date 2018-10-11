package com.rachev.passwordmanager.diconfig;

import com.rachev.passwordmanager.models.Password;
import com.rachev.passwordmanager.models.SocialUser;
import com.rachev.passwordmanager.parsers.GsonJsonParser;
import com.rachev.passwordmanager.parsers.base.JsonParser;
import dagger.Module;
import dagger.Provides;

@Module
public class ParsersModule
{
    @Provides
    public JsonParser<Password> passwordsJsonParser()
    {
        return new GsonJsonParser<>(Password.class, Password[].class);
    }
    
    @Provides
    public JsonParser<SocialUser> usersJsonParser()
    {
        return new GsonJsonParser<>(SocialUser.class, SocialUser[].class);
    }
}

