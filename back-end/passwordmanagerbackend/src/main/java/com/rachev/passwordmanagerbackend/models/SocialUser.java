package com.rachev.passwordmanagerbackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "social_media_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SocialUser
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;
    
    @NotNull
    @Column(name = "social_media")
    private String socialMedia;
    
    @NotNull
    @Column(name = "user_id", unique = true)
    private String userId;
    
    public SocialUser(String socialMedia, String userId)
    {
        this.socialMedia = socialMedia;
        this.userId = userId;
    }
}
