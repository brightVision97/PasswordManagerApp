package com.rachev.passwordmanagerbackend.repositories;

import com.rachev.passwordmanagerbackend.models.SocialUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<SocialUser, Integer>
{
}
