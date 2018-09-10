package com.rachev.passwordmanager.views.passwordlist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.rachev.passwordmanager.R;
import com.rachev.passwordmanager.constants.Constants;
import com.rachev.passwordmanager.models.Password;
import com.rachev.passwordmanager.views.BaseDrawerActivity;
import com.rachev.passwordmanager.views.passworddetails.PasswordDetailsActivity;

import javax.inject.Inject;

@SuppressWarnings("deprecation")
public class PasswordsListActivity extends BaseDrawerActivity
        implements PasswordsListContracts.Navigator
{
    public static final int IDENTIFIER = 217;
    
    @Inject
    PasswordsListFragment mPasswordsListFragment;
    
    @Inject
    PasswordsListContracts.Presenter mPasswordsListPresenter;
    
    private boolean doubleBackToExitPressedOnce = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwords_list);
        setTitle(Constants.LIST_ACTIVITY_TITLE);
        
        ButterKnife.bind(this);
        
        setSupportActionBar(getToolbar());
        
        mPasswordsListFragment.setNavigator(this);
        mPasswordsListFragment.setPresenter(mPasswordsListPresenter);
        
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mPasswordsListFragment)
                .commit();
    }
    
    @Override
    protected void onResume()
    {
        super.onResume();
        
        doubleBackToExitPressedOnce = false;
    }
    
    @Override
    public void onBackPressed()
    {
        if (doubleBackToExitPressedOnce)
        {
            super.onBackPressed();
            return;
        }
        
        doubleBackToExitPressedOnce = true;
        Toast.makeText(this,
                Constants.SECOND_BACK_PRESS_REMINDING_POPUP,
                Toast.LENGTH_SHORT)
                .show();
    }
    
    @Override
    protected long getIdentifier()
    {
        return IDENTIFIER;
    }
    
    @Override
    public void navigateWith(Password password)
    {
        Intent intent = new Intent(this, PasswordDetailsActivity.class);
        intent.putExtra(Constants.PASS_EXTRA_KEY, password);
        
        startActivity(intent);
    }
}
