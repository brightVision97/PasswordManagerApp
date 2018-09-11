package com.rachev.passwordmanager.views.passwordlist;

import com.rachev.passwordmanager.models.Password;

import java.util.List;

public interface PasswordsListContracts
{
    interface View
    {
        void setPresenter(Presenter presenter);
        
        void showPasswords(List<Password> passwords);
        
        void showEmptyPasswordsList();
        
        void showError(Throwable ex);
        
        void showLoading();
        
        void hideLoading();
        
        void showPasswordDetails(Password password);
        
        void navigateToHome();
    }
    
    interface Presenter
    {
        void subscribe(View view);
        
        void loadPasswords();
        
        void selectPassword(Password password);
        
        void addPassword(Password password);
    }
    
    interface Navigator
    {
        void navigateWith(Password password);
    
        void navigateToHome();
    }
}
