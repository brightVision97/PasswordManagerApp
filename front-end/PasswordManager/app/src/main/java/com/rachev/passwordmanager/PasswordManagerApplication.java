package com.rachev.passwordmanager;

import com.rachev.passwordmanager.diconfig.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class PasswordManagerApplication extends DaggerApplication
{
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector()
    {
        return DaggerAppComponent.builder()
                .application(this)
                .build();
    }
}
