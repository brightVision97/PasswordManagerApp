package com.rachev.passwordmanager.views;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.rachev.passwordmanager.R;
import com.rachev.passwordmanager.utils.Constants;
import com.rachev.passwordmanager.views.about.AboutActivity;
import com.rachev.passwordmanager.views.login.LoginActivity;
import com.rachev.passwordmanager.views.passwordlist.PasswordsListActivity;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.annotations.Nullable;
import studios.codelight.smartloginlibrary.UserSessionManager;
import studios.codelight.smartloginlibrary.users.SmartFacebookUser;
import studios.codelight.smartloginlibrary.users.SmartGoogleUser;
import studios.codelight.smartloginlibrary.users.SmartUser;

public abstract class BaseDrawerActivity extends DaggerAppCompatActivity
{
    @Nullable
    @BindView(R.id.drawer_toolbar)
    Toolbar mToolbar;
    
    public BaseDrawerActivity()
    {
    }
    
    public void setupDrawer()
    {
        PrimaryDrawerItem listPasswordsItem = new PrimaryDrawerItem()
                .withIdentifier(PasswordsListActivity.IDENTIFIER)
                .withIcon(R.drawable.ic_lock_outline)
                .withName(Constants.LIST_ACTIVITY_TITLE);
        
        SecondaryDrawerItem accountItem = new SecondaryDrawerItem()
                .withIdentifier(LoginActivity.IDENTIFIER)
                .withName(Constants.ACC_ACTIVITY_TITLE);
        
        SecondaryDrawerItem aboutItem = new SecondaryDrawerItem()
                .withIdentifier(AboutActivity.IDENTIFIER)
                .withName(Constants.RATING_ACTIVITY_TITLE);
        
        SmartUser user = UserSessionManager.getCurrentUser(this);
        String name = "";
        String profilePicUrl = "";
        
        if (user != null)
        {
            if (user instanceof SmartGoogleUser)
            {
                name = user.getEmail();
                profilePicUrl = ((SmartGoogleUser) user).getPhotoUrl();
            } else if (user instanceof SmartFacebookUser)
            {
                name = ((SmartFacebookUser) user).getProfileName();
                profilePicUrl = Constants.FB_GRAPH_PROFILE_PIC_URL_PART1 + user.getUserId() +
                        Constants.FB_GRAPH_PROFILE_PIC_URL_PART2;
            }
        }
        
        if (profilePicUrl == null)
            profilePicUrl = Constants.NO_PROFILE_PIC_AVATAR_URL;
        
        AccountHeader header = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .withProfileImagesVisible(true)
                .addProfiles(new ProfileDrawerItem()
                        .withName(name)
                        .withIcon(profilePicUrl))
                .build();
        
        Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withAccountHeader(header)
                .addDrawerItems(
                        listPasswordsItem,
                        new DividerDrawerItem(),
                        accountItem,
                        new DividerDrawerItem(),
                        aboutItem)
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
            case LoginActivity.IDENTIFIER:
                intent = new Intent(this, LoginActivity.class);
                intent.putExtra(Constants.IS_OPENED_FROM_DRAWER, true);
                break;
            case AboutActivity.IDENTIFIER:
                intent = new Intent(this, AboutActivity.class);
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

