package com.rachev.passwordmanagerbackend.controllers;

import com.rachev.passwordmanagerbackend.models.Password;
import com.rachev.passwordmanagerbackend.services.base.PasswordManagerService;
import com.rachev.passwordmanagerbackend.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.PASSWORDS_ROOT_REQUEST_MAPPING)
public class PasswordsApiController
{
    private final PasswordManagerService passwordManagerService;
    
    @Autowired
    public PasswordsApiController(PasswordManagerService passwordManagerService)
    {
        this.passwordManagerService = passwordManagerService;
    }
    
    @GetMapping()
    public List<Password> getAllPasswords()
    {
        return passwordManagerService.getAllPasswords();
    }
    
    @GetMapping(value = "/{id}")
    public Password getPasswordById(@PathVariable("id") int id)
    {
        return passwordManagerService.getPasswordById(id);
    }
    
    @PostMapping
    public Password createPassword(@RequestBody Password password)
    {
        return passwordManagerService.createPassword(password);
        
    }
    
    @DeleteMapping(value = "/{id}")
    public void deletePasswordById(@PathVariable("id") int id)
    {
        passwordManagerService.deletePasswordById(id);
    }
}
