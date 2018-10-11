package com.rachev.passwordmanager.models;

import java.io.Serializable;

public class SocialUser implements Serializable
{
    public int id;
    public String socialMedia;
    public String userId;
    
    public SocialUser()
    {
    }
    
    public SocialUser(String userId)
    {
        setSocialMedia(null);
        setUserId(userId);
    }
    
    @Override
    public boolean equals(Object obj)
    {
        return getUserId().equals(((SocialUser) obj).getUserId());
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getSocialMedia()
    {
        return socialMedia;
    }
    
    public void setSocialMedia(String socialMedia)
    {
        this.socialMedia = socialMedia;
    }
    
    public String getUserId()
    {
        return userId;
    }
    
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
}
