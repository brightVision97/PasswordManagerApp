package com.rachev.passwordmanagerbackend.dao;

import com.rachev.passwordmanagerbackend.models.Password;
import org.springframework.data.repository.CrudRepository;

public interface PasswordManagerDao extends CrudRepository<Password, Integer>
{
}
