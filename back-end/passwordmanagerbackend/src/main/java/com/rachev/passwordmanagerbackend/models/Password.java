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
    @Column(name = "social_user_acc_id")
    private String socialUserAccId;
    
    public Password(String targetWebsite, String username, String password, String socialUserAccId)
    {
        setTargetWebsite(targetWebsite);
        setUsername(username);
        setPassword(password);
        setSocialUserAccId(socialUserAccId);
    }
}
