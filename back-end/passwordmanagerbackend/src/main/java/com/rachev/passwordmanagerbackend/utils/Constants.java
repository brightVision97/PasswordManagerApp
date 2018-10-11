package com.rachev.passwordmanagerbackend.utils;

public class Constants
{
    public static final String ENCRYPTING_ALGORITHM = "PBEWithMD5AndDES";
    public static final int ITERATION_COUNT = 19;
    
    public static final String PASSWORDS_ROOT_REQUEST_MAPPING = "/api/passwords";
    
    public static final String VALIDATION_FAILED_MSG = "Validation failed";
    public static final String ERR_DELIMITER = "; ";
    
    public static final String PASSWORDS_TABLE = "passwords";
    public static final String PASSWORDS_ID_COLUMN = "password_id";
    public static final String PASSWORDS_WEBSITE_COLUMN = "target_website";
    public static final String PASSWORDS_USERNAME_COLUMN = "username";
    public static final String PASSWORDS_PASSWORD_COLUMN = "password";
}
