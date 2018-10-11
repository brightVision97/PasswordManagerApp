package com.rachev.passwordmanagerbackend.repositories;

import com.rachev.passwordmanagerbackend.models.Password;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordsRepository extends JpaRepository<Password, Integer>
{
}
