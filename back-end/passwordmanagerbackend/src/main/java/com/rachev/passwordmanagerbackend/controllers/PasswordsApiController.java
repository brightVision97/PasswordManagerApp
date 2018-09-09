package com.rachev.passwordmanagerbackend.controllers;

import com.rachev.passwordmanagerbackend.models.Password;
import com.rachev.passwordmanagerbackend.services.base.PasswordManagerService;
import com.rachev.passwordmanagerbackend.utils.mapper.base.PasswordMapper;
import com.rachev.passwordmanagerbackend.viewmodels.PasswordViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passwords")
public class PasswordsApiController
{
    private final PasswordManagerService passwordManagerService;
    private final PasswordMapper mapper;
    
    @Autowired
    public PasswordsApiController(PasswordManagerService passwordManagerService,
                                  PasswordMapper mapper)
    {
        this.passwordManagerService = passwordManagerService;
        this.mapper = mapper;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public List<PasswordViewModel> getAllPasswords()
    {
        List<Password> models = passwordManagerService.getAllPasswords();
        
        return mapper.mapMany(models);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PasswordViewModel getPasswordById(@PathVariable("id") int id)
    {
        Password model = passwordManagerService.findPasswordById(id);
        
        return mapper.map(model);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PasswordViewModel> createPassword(
            @RequestBody PasswordViewModel passwordViewModel)
    {
        Password model = mapper.map(passwordViewModel);
        Password password = passwordManagerService.createPassword(model);
        PasswordViewModel viewModelToReturn = mapper.map(password);
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(viewModelToReturn);
    }
}
