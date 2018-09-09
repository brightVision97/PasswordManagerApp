package com.rachev.passwordmanager.diconfig;

import com.rachev.passwordmanager.async.AsyncSchedulerProvider;
import com.rachev.passwordmanager.async.base.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AsyncModule
{
    @Provides
    @Singleton
    public SchedulerProvider schedulerProvider()
    {
        return AsyncSchedulerProvider.getInstance();
    }
}
