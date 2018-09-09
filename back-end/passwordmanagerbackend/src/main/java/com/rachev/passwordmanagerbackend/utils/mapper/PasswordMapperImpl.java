package com.rachev.passwordmanagerbackend.utils.mapper;

import com.rachev.passwordmanagerbackend.models.Password;
import com.rachev.passwordmanagerbackend.utils.mapper.base.PasswordMapper;
import com.rachev.passwordmanagerbackend.viewmodels.PasswordViewModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PasswordMapperImpl implements PasswordMapper
{
    @Override
    public PasswordViewModel map(Password passwordModel)
    {
        PasswordViewModel viewModel = new PasswordViewModel();
        viewModel.id = passwordModel.getId();
        viewModel.username = passwordModel.getUsername();
        viewModel.password = passwordModel.getPassword();
        viewModel.targetWebsite = passwordModel.getTargetWebsite();
        
        return viewModel;
    }
    
    @Override
    public Password map(PasswordViewModel passwordViewModel)
    {
        Password model = new Password();
        model.setUsername(passwordViewModel.username);
        model.setPassword(passwordViewModel.password);
        model.setTargetWebsite(passwordViewModel.targetWebsite);
        
        return model;
    }
    
    @Override
    public List<PasswordViewModel> mapMany(List<Password> models)
    {
        return models.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
