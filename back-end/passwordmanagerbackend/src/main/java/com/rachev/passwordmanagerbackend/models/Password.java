package com.rachev.passwordmanagerbackend.models;

import com.rachev.passwordmanagerbackend.utils.Constants;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = Constants.PASSWORDS_TABLE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Password
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.PASSWORDS_ID_COLUMN, unique = true)
    private int id;
    
    @NotNull
    @Column(name = Constants.PASSWORDS_WEBSITE_COLUMN)
    private String targetWebsite;
    
    @NotNull
    @Column(name = Constants.PASSWORDS_USERNAME_COLUMN)
    private String username;
    
    @NotNull
    @Column(name = Constants.PASSWORDS_PASSWORD_COLUMN)
    private String password;
    
    @NotNull
    @Column(name = Constants.PASSWORDS_SOCIAL_USER_ACC_ID_COLUMN)
    private String socialUserAccId;
}
