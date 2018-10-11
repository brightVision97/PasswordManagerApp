package com.rachev.passwordmanager.models;

import java.io.Serializable;

public class Password implements Serializable
{
    public int id;
    public String username;
    public String password;
    public String targetWebsite;
    public String socialUserAccId;
    
    public Password()
    {
    }
    
    public Password(String username, String password, String targetWebsite, String socialUserAccId)
    {
        setUsername(username);
        setPassword(password);
        setTargetWebsite(targetWebsite);
        setSocialUserAccId(socialUserAccId);
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getTargetWebsite()
    {
        return targetWebsite;
    }
    
    public void setTargetWebsite(String targetWebsite)
    {
        this.targetWebsite = targetWebsite;
    }
    
    public String getSocialUserAccId()
    {
        return socialUserAccId;
    }
    
    public void setSocialUserAccId(String socialUserAccId)
    {
        this.socialUserAccId = socialUserAccId;
    }
}
