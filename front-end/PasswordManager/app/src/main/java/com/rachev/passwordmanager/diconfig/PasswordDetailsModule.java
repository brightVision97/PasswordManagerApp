package com.rachev.passwordmanager.diconfig;

import com.rachev.passwordmanager.views.passworddetails.PasswordDetailsContracts;
import com.rachev.passwordmanager.views.passworddetails.PasswordDetailsFragment;
import com.rachev.passwordmanager.views.passworddetails.PasswordDetailsPresenter;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PasswordDetailsModule
{
    @FragmentScoped
    @ContributesAndroidInjector
    abstract PasswordDetailsFragment passwordDetailsFragment();
    
    @ActivityScoped
    @Binds
    abstract PasswordDetailsContracts.Presenter passwordsListPresenter(PasswordDetailsPresenter presenter);
}

