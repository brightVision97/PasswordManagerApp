package com.rachev.passwordmanager.services.base;

import com.rachev.passwordmanager.models.SocialUser;

import java.util.List;

public interface UsersService
{
    List<SocialUser> getAllUsers() throws Exception;
    
    SocialUser createUser(SocialUser user) throws Exception;
}
