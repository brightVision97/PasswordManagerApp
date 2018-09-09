package com.rachev.passwordmanager.diconfig;

import android.app.Application;
import com.rachev.passwordmanager.PasswordManagerApplication;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

import javax.inject.Singleton;

@Singleton
@Component(modules =
        {
                AndroidSupportInjectionModule.class,
                ActivityBindingModule.class,
                AppModule.class,
                ParsersModule.class,
                HttpModule.class,
                RepositoryModule.class,
                ServicesModule.class,
                AsyncModule.class,
                ViewsModule.class
        })
public interface AppComponent extends AndroidInjector<PasswordManagerApplication>
{
    @Component.Builder
    interface Builder
    {
        @BindsInstance
        AppComponent.Builder application(Application application);
        
        AppComponent build();
    }
}
