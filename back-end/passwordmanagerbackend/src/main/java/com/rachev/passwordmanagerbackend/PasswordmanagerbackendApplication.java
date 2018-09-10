package com.rachev.passwordmanagerbackend;

import com.rachev.passwordmanagerbackend.models.Password;
import com.rachev.passwordmanagerbackend.services.base.PasswordManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PasswordmanagerbackendApplication implements CommandLineRunner
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
    
    @Override
    public void run(String... args)
    {
        createPasswordTest();
    }
    
    private void createPasswordTest()
    {
        Password password = new Password(
                "Facebook",
                "bright",
                "123abc");
        Password passwordDb = passwordManagerService.createPassword(password);
        
        password = new Password(
                "Google",
                "momcheto",
                "stupidPass101");
        passwordDb = passwordManagerService.createPassword(password);
    }
}
