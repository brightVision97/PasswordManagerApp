package com.rachev.passwordmanager.views.passworddetails;

import com.rachev.passwordmanager.async.base.SchedulerProvider;
import com.rachev.passwordmanager.models.Password;
import com.rachev.passwordmanager.services.base.PasswordsService;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

import javax.inject.Inject;

public class PasswordDetailsPresenter implements PasswordDetailsContracts.Presenter
{
    private final PasswordsService mPasswordsService;
    private final SchedulerProvider mSchedulerProvider;
    private PasswordDetailsContracts.View mView;
    private int mPasswordId;
    
    @Inject
    public PasswordDetailsPresenter(PasswordsService passwordsService,
                                    SchedulerProvider schedulerProvider)
    {
        mPasswordsService = passwordsService;
        mSchedulerProvider = schedulerProvider;
    }
    
    @Override
    public void subscribe(PasswordDetailsContracts.View view)
    {
        mView = view;
    }
    
    @Override
    public void loadPassword()
    {
        Disposable disposable = Observable
                .create((ObservableOnSubscribe<Password>) emitter ->
                {
                    Password password = mPasswordsService.getDetailsById(mPasswordId);
                    
                    emitter.onNext(password);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doOnError(mView::showError)
                .subscribe(mView::showPassword);
    }
    
    @Override
    public void setPasswordId(int id)
    {
        mPasswordId = id;
    }
}
