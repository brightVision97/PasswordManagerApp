package com.rachev.passwordmanagerbackend.models;

import com.rachev.passwordmanagerbackend.constants.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = Constants.PASSWORDS_DB_TABLE_NAME)
public class Password
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = Constants.PASS_ID_DB_COLUMN_NAME, unique = true)
    private int id;
    
    @NotNull
    @Column(name = Constants.PASS_TARGET_WEBSITE_DB_COLUMN_NAME)
    private String targetWebsite;
    
    @NotNull
    @Column(name = Constants.USERNAME_DB_COLUMN_NAME)
    private String username;
    
    @NotNull
    @Column(name = Constants.PASS_DB_COLUMN_NAME)
    private String password;
    
    public Password()
    {
    }
    
    public Password(String targetWebsite, String password)
    {
        setTargetWebsite(targetWebsite);
        setPassword(password);
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getTargetWebsite()
    {
        return targetWebsite;
    }
    
    public void setTargetWebsite(String targetWebsite)
    {
        this.targetWebsite = targetWebsite;
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
}
