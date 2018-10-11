package com.rachev.passwordmanagerbackend;

import com.rachev.passwordmanagerbackend.models.Password;
import com.rachev.passwordmanagerbackend.repositories.PasswordsRepository;
import com.rachev.passwordmanagerbackend.services.PasswordManagerDatabaseServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PasswordManagerDatabaseServiceTests
{
    @Mock
    PasswordsRepository mockRepository;
    
    @InjectMocks
    PasswordManagerDatabaseServiceImpl service;
    
    private List<Password> passwords;
    
    {
        passwords = new ArrayList<>();
        
        passwords.add(new Password(1,
                "Facebook",
                "username1",
                "password1",
                "asihf"));
        passwords.add(new Password(2,
                "Facebook",
                "username2",
                "password2",
                "asihfasdg"));
    }
    
    @Test
    public void getAllPasswordsSize_Should_ReturnTwo_When_TwoItemsInserted()
    {
        Mockito.when(mockRepository.findAll())
                .thenReturn(passwords);
        
        List<Password> passwords = service.getAllPasswords();
        
        Assert.assertEquals(2, passwords.size());
    }
    
    @Test
    public void getPasswordById_Should_NotThrowException_When_Exists()
    {
        Mockito.when(mockRepository.findById(2))
                .thenReturn(Optional.of(new Password(2,
                        "Facebook",
                        "username1",
                        "password1",
                        "aisfhji12")));
        
        Password password = service.getPasswordById(2);
        
        Assert.assertNotNull(password);
    }
    
    @Test
    public void createPassword_Should_CreatePassword_When_Added()
    {
        Password passwordToAdd = new Password();
        
        Mockito.when(mockRepository.save(passwordToAdd))
                .thenReturn(passwordToAdd);
        
        Password password = service.createPassword(passwordToAdd);
        
        Assert.assertEquals(passwordToAdd, password);
    }
    
    @Test
    public void deletePasswordFromService_Should_CallRepositoryMethod_When_Invoked()
    {
        service.deletePasswordById(0);
        
        verify(mockRepository).deleteById(0);
    }
}
