package com.rachev.passwordmanager.validators;

import com.rachev.passwordmanager.constants.Constants;
import com.rachev.passwordmanager.models.Password;
import com.rachev.passwordmanager.validators.base.Validator;

public class PasswordValidator implements Validator<Password>
{
    @Override
    public boolean isValid(Password object)
    {
        return object != null &&
                isUsernameValid(object) &&
                isPasswordValid(object) &&
                isTargetWebsiteValid(object);
    }
    
    private boolean isUsernameValid(Password object)
    {
        return object.getUsername().length() >= Constants.USERNAME_MIN_LENGTH &&
                object.getUsername().length() <= Constants.USERNAME_MAX_LENGTH;
    }
    
    private boolean isPasswordValid(Password object)
    {
        return object.getPassword().length() >= Constants.PASSWORD_MIN_LENGTH &&
                object.getPassword().length() <= Constants.PASSWORD_MAX_LENGTH;
    }
    
    private boolean isTargetWebsiteValid(Password object)
    {
        return object.getTargetWebsite().length() >= Constants.TARGET_WEBSITE_MIN_LENGTH &&
                object.getTargetWebsite().length() <= Constants.TARGET_WEBSITE_MAX_LENGTH;
    }
}
