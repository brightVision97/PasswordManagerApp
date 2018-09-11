package com.rachev.passwordmanager.http.base;

import java.io.IOException;

public interface HttpRequester
{
    String get(String url) throws IOException;
    
    String post(String url, String bodyString) throws IOException;
    
    String delete(String url) throws IOException;
}
