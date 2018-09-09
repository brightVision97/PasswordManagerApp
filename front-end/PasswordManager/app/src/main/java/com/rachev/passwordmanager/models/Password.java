package com.rachev.passwordmanager.models;

import java.io.Serializable;

public class Password implements Serializable
{
    public int id;
    public String username;
    public String password;
    public String targetWebsite;
    
    public Password()
    {
    }
    
    public Password(String username, String password, String targetWebsite)
    {
        this.username = username;
        this.password = password;
        this.targetWebsite = targetWebsite;
    }
    
    public int getId()
    {
        return id;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public String getTargetWebsite()
    {
        return targetWebsite;
    }
}
