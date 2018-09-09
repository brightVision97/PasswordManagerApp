package com.rachev.passwordmanager.diconfig;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import com.rachev.passwordmanager.views.passwordlist.PasswordsAdapter;
import dagger.Module;
import dagger.Provides;

@Module
public class ViewsModule
{
    @Provides
    public RecyclerView.Adapter<PasswordsAdapter.PasswordViewHolder> passwordAdapter(Context context)
    {
        return new PasswordsAdapter();
    }
}

