package com.rachev.passwordmanagerbackend.constants;

public class Constants
{
    public static final String VALIDATION_FAILED_ERR_MSG = "Validation failed!";
    public static final String PASSWORD_NOT_FOUND_ERR_MSG = "Password not found";
    
    public static final String JOINING_DELIMITER = "; ";
    
    public static final String ENCRYPTING_ALGORITHM = "PBEWithMD5AndDES";
    
    public static final int CRYPTO_ITERATION_COUNT = 19;
    
    public static final String PASSWORDS_DB_TABLE_NAME = "passwords_table";
    public static final String PASS_ID_DB_COLUMN_NAME = "id";
    public static final String PASS_TARGET_WEBSITE_DB_COLUMN_NAME = "website";
    public static final String USERNAME_DB_COLUMN_NAME = "username";
    public static final String PASS_DB_COLUMN_NAME = "password";
}
