package com.rachev.passwordmanager.validators.base;

public interface Validator<T>
{
    boolean isValid(T object);
}
