package com.rachev.passwordmanager.diconfig;

import com.rachev.passwordmanager.views.passwordlist.PasswordsListContracts;
import com.rachev.passwordmanager.views.passwordlist.PasswordsListFragment;
import com.rachev.passwordmanager.views.passwordlist.PasswordsListPresenter;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PasswordsListModule
{
    @FragmentScoped
    @ContributesAndroidInjector
    abstract PasswordsListFragment passwordsListFragmentListFragment();
    
    @ActivityScoped
    @Binds
    abstract PasswordsListContracts.Presenter passwordsListPresenter(PasswordsListPresenter presenter);
}
