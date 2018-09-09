package com.rachev.passwordmanager.views.passwordlist;

import com.rachev.passwordmanager.async.base.SchedulerProvider;
import com.rachev.passwordmanager.models.Password;
import com.rachev.passwordmanager.services.base.PasswordsService;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

import javax.inject.Inject;
import java.util.List;

public class PasswordsListPresenter implements PasswordsListContracts.Presenter
{
    private PasswordsListContracts.View mView;
    private final PasswordsService mPasswordsService;
    private final SchedulerProvider mSchedulerProvider;
    private List<Password> mPasswords;
    
    @Inject
    public PasswordsListPresenter(
            PasswordsService passwordsService,
            SchedulerProvider schedulerProvider)
    {
        mPasswordsService = passwordsService;
        mSchedulerProvider = schedulerProvider;
    }
    
    @Override
    public void subscribe(PasswordsListContracts.View view)
    {
        mView = view;
    }
    
    @Override
    public void loadPasswords()
    {
        mView.showLoading();
        Disposable disposable = Observable
                .create((ObservableOnSubscribe<List<Password>>) emitter ->
                {
                    List<Password> passwords = mPasswordsService.getAllPasswords();
                    
                    emitter.onNext(passwords);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doOnError(mView::showError)
                .doFinally(mView::hideLoading)
                .subscribe(this::presentPasswordsToView);
    }
    
    @Override
    public void selectPassword(Password password)
    {
        mView.showPasswordDetails(password);
    }
    
    private void presentPasswordsToView(List<Password> passwords)
    {
        if (passwords.isEmpty())
            mView.showEmptyPasswordsList();
        else
            mView.showPasswords(passwords);
    }
    
}
