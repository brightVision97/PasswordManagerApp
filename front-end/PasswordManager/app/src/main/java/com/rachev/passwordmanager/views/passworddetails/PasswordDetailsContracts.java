package com.rachev.passwordmanager.views.passworddetails;

import com.rachev.passwordmanager.models.Password;

public interface PasswordDetailsContracts
{
    interface View
    {
        void showPassword(Password password);
        
        void setPresenter(Presenter presenter);
        
        void showError(Throwable e);
    }
    
    interface Presenter
    {
        void subscribe(View view);
        
        void loadPassword();
        
        void setPasswordId(int id);
    }
}
