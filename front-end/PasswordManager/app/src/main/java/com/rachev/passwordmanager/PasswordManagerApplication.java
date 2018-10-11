package com.rachev.passwordmanager;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.rachev.passwordmanager.diconfig.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class PasswordManagerApplication extends DaggerApplication
{
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector()
    {
        DrawerImageLoader.init(new AbstractDrawerImageLoader()
        {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder, String tag)
            {
                Glide.with(imageView.getContext()).load(uri).into(imageView);
            }
        });
        
        return DaggerAppComponent.builder()
                .application(this)
                .build();
    }
}
