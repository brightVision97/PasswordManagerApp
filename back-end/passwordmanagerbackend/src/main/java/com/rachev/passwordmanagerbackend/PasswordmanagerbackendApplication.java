package com.rachev.passwordmanagerbackend;

import com.rachev.passwordmanagerbackend.services.base.PasswordManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PasswordmanagerbackendApplication
{
    private final PasswordManagerService passwordManagerService;
    
    @Autowired
    public PasswordmanagerbackendApplication(PasswordManagerService passwordManagerService)
    {
        this.passwordManagerService = passwordManagerService;
    }
    
    public static void main(String[] args)
    {
        SpringApplication.run(PasswordmanagerbackendApplication.class, args);
    }
}
