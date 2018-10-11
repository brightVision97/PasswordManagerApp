package com.rachev.passwordmanagerbackend.controllers;

import com.rachev.passwordmanagerbackend.models.SocialUser;
import com.rachev.passwordmanagerbackend.services.base.PasswordManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersApiController
{
    private final PasswordManagerService passwordManagerService;
    
    @Autowired
    public UsersApiController(PasswordManagerService passwordManagerService)
    {
        this.passwordManagerService = passwordManagerService;
    }
    
    @GetMapping()
    public List<SocialUser> getAllUsers()
    {
        return passwordManagerService.getAllUsers();
    }
    
    @PostMapping
    public SocialUser addUser(@RequestBody SocialUser user)
    {
        return passwordManagerService.addUser(user);
    }
}
