package com.rachev.passwordmanager.views.passwordlist;

import com.rachev.passwordmanager.async.base.SchedulerProvider;
import com.rachev.passwordmanager.models.Password;
import com.rachev.passwordmanager.models.SocialUser;
import com.rachev.passwordmanager.services.base.PasswordsService;
import com.rachev.passwordmanager.services.base.UsersService;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class PasswordsListPresenter implements PasswordsListContracts.Presenter
{
    private PasswordsListContracts.View mView;
    private final PasswordsService mPasswordsService;
    private final UsersService mUsersService;
    private final SchedulerProvider mSchedulerProvider;
    
    @Inject
    public PasswordsListPresenter(PasswordsService passwordsService,
                                  UsersService usersService,
                                  SchedulerProvider schedulerProvider)
    {
        mPasswordsService = passwordsService;
        mUsersService = usersService;
        mSchedulerProvider = schedulerProvider;
    }
    
    @Override
    public void subscribe(PasswordsListContracts.View view)
    {
        mView = view;
    }
    
    @Override
    public void loadPasswords(String userId)
    {
        mView.showLoading();
        Disposable disposable = Observable
                .create((ObservableOnSubscribe<List<Password>>) emitter ->
                {
                    List<Password> passwords = mPasswordsService.getAllPasswords()
                            .stream()
                            .filter(p -> p.getSocialUserAccId().equals(userId))
                            .collect(Collectors.toList());
                    
                    emitter.onNext(passwords);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(this::presentPasswordsToView,
                        e -> mView.showError(e));
    }
    
    @Override
    public void selectPassword(Password password)
    {
        mView.showPasswordDetails(password);
    }
    
    @Override
    public void addPassword(Password password)
    {
        Disposable disposable = Observable
                .create((ObservableOnSubscribe<Password>) emitter ->
                {
                    Password createdPassword = mPasswordsService.createPassword(password);
                    
                    emitter.onNext(createdPassword);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(p -> mView.navigateToHome(),
                        e -> mView.showError(e));
    }
    
    @Override
    public void addUser(SocialUser user)
    {
        Disposable disposable = Observable
                .create((ObservableOnSubscribe<SocialUser>) emitter ->
                {
                    if (!mUsersService.getAllUsers().contains(user))
                        mUsersService.createUser(user);
                    
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(p -> mView.navigateToHome(),
                        e -> mView.showError(e));
    }
    
    private void presentPasswordsToView(List<Password> passwords)
    {
        if (passwords.isEmpty())
            mView.showEmptyPasswordsList();
        else
            mView.showPasswords(passwords);
    }
    
}
