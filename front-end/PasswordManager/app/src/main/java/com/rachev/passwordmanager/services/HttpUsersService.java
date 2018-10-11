package com.rachev.passwordmanager.services;

import com.rachev.passwordmanager.models.SocialUser;
import com.rachev.passwordmanager.repositories.base.Repository;
import com.rachev.passwordmanager.services.base.UsersService;

import java.util.List;

public class HttpUsersService implements UsersService
{
    private final Repository<SocialUser> mUsersRepository;
    
    public HttpUsersService(Repository<SocialUser> usersRepository)
    {
        mUsersRepository = usersRepository;
    }
    
    @Override
    public List<SocialUser> getAllUsers() throws Exception
    {
        return mUsersRepository.getAll();
    }
    
    @Override
    public SocialUser createUser(SocialUser user) throws Exception
    {
        return mUsersRepository.add(user);
    }
}
