package com.rachev.passwordmanagerbackend.models;

import com.rachev.passwordmanagerbackend.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = Constants.USERS_TABLE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SocialUser
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.USERS_ID_COLUMN, unique = true)
    private int id;
    
    @NotNull
    @Column(name = Constants.USERS_SOCIAL_MEDIA_COLUMN)
    private String socialMedia;
    
    @NotNull
    @Column(name = Constants.USERS_USER_ID_COLUMN, unique = true)
    private String userId;
}
