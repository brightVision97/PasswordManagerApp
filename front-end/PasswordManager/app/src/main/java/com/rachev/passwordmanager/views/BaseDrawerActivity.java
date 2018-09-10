package com.rachev.passwordmanager.views;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.rachev.passwordmanager.R;
import com.rachev.passwordmanager.constants.Constants;
import com.rachev.passwordmanager.views.passwordlist.PasswordsListActivity;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseDrawerActivity extends DaggerAppCompatActivity
{
    @BindView(R.id.drawer_toolbar)
    Toolbar mToolbar;
    
    public BaseDrawerActivity()
    {
    }
    
    public void setupDrawer()
    {
        PrimaryDrawerItem listSuperheroesItem = new PrimaryDrawerItem()
                .withIdentifier(PasswordsListActivity.IDENTIFIER)
                .withIcon(R.drawable.ic_lock_outline)
                .withName(Constants.LIST_ACTIVITY_TITLE);
        
        AccountHeader header = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(new ProfileDrawerItem().withName(Constants.GUEST_USERNAME))
                .build();
        
        Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withAccountHeader(header)
                .addDrawerItems(
                        listSuperheroesItem,
                        new DividerDrawerItem())
                .withOnDrawerItemClickListener((view, position, drawerItem) ->
                {
                    int identifier = (int) drawerItem.getIdentifier();
                    
                    if (getIdentifier() == identifier)
                        return false;
                    
                    Intent intent = getNextIntent(identifier);
                    
                    if (intent == null)
                        return false;
                    
                    startActivity(intent);
                    return true;
                })
                .build();
    }
    
    private Intent getNextIntent(int identifier)
    {
        Intent intent = null;
        
        switch (identifier)
        {
            case PasswordsListActivity.IDENTIFIER:
                intent = new Intent(this, PasswordsListActivity.class);
                break;
        }
        
        return intent;
    }
    
    protected Toolbar getToolbar()
    {
        return mToolbar;
    }
    
    protected abstract long getIdentifier();
    
    @Override
    protected void onStart()
    {
        super.onStart();
        
        setupDrawer();
    }
}

