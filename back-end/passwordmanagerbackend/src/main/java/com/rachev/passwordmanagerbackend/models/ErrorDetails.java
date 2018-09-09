package com.rachev.passwordmanagerbackend.models;

import java.time.LocalDateTime;

public class ErrorDetails
{
    private String timestamp;
    private String message;
    private String details;
    
    public ErrorDetails(LocalDateTime timestamp, String message, String details)
    {
        this.timestamp = timestamp.toString();
        this.message = message;
        this.details = details;
    }
    
    public String getTimestamp()
    {
        return timestamp;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    public String getDetails()
    {
        return details;
    }
}

