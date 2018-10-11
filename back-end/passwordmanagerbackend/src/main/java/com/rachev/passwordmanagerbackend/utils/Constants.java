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
    public static final String PASSWORDS_SOCIAL_USER_ACC_ID_COLUMN = "social_user_acc_id";
    
    public static final String USERS_TABLE = "social_media_users";
    public static final String USERS_ID_COLUMN = "id";
    public static final String USERS_SOCIAL_MEDIA_COLUMN = "social_media";
    public static final String USERS_USER_ID_COLUMN = "user_id";
}
