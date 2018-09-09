package com.rachev.passwordmanager.async;

import com.rachev.passwordmanager.async.base.SchedulerProvider;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class SyncSchedulerProvider implements SchedulerProvider
{
    @Override
    public Scheduler background()
    {
        return Schedulers.trampoline();
    }
    
    @Override
    public Scheduler ui()
    {
        return Schedulers.trampoline();
    }
}
