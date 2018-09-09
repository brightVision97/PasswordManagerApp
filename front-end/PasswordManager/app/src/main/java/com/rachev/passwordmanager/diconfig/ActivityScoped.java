package com.rachev.passwordmanager.diconfig;

import javax.inject.Scope;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScoped
{
}
