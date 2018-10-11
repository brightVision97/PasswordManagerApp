package com.rachev.passwordmanager.diconfig;

import com.rachev.passwordmanager.views.about.AboutActivity;
import com.rachev.passwordmanager.views.login.LoginActivity;
import com.rachev.passwordmanager.views.passworddetails.PasswordDetailsActivity;
import com.rachev.passwordmanager.views.passwordlist.PasswordsListActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule
{
    @ActivityScoped
    @ContributesAndroidInjector
    abstract LoginActivity loginActivity();
    
    @ActivityScoped
    @ContributesAndroidInjector
    abstract AboutActivity aboutActivity();
    
    @ActivityScoped
    @ContributesAndroidInjector(modules = PasswordsListModule.class)
    abstract PasswordsListActivity passwordsListActivity();
    
    @ActivityScoped
    @ContributesAndroidInjector(modules = PasswordDetailsModule.class)
    abstract PasswordDetailsActivity passwordDetailsActivity();
}
