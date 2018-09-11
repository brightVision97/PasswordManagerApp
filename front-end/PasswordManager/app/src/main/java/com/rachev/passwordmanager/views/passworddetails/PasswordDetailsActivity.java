package com.rachev.passwordmanager.views.passworddetails;

import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.rachev.passwordmanager.R;
import com.rachev.passwordmanager.constants.Constants;
import com.rachev.passwordmanager.models.Password;
import com.rachev.passwordmanager.views.BaseDrawerActivity;
import com.rachev.passwordmanager.views.passwordlist.PasswordsListActivity;

import javax.inject.Inject;

@SuppressWarnings("deprecation")
public class PasswordDetailsActivity extends BaseDrawerActivity
        implements PasswordDetailsContracts.Navigator
{
    @Inject
    PasswordDetailsFragment mPasswordDetailsFragment;
    
    @Inject
    PasswordDetailsContracts.Presenter mPasswordDetailsPresenter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_details);
        
        ButterKnife.bind(this);
        
        Intent intent = getIntent();
        Password password = (Password) intent.getSerializableExtra(Constants.PASS_EXTRA_KEY);
    
        mPasswordDetailsFragment.setNavigator(this);
        mPasswordDetailsPresenter.setPasswordId(password.id);
        mPasswordDetailsFragment.setPresenter(mPasswordDetailsPresenter);
        
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content_details, mPasswordDetailsFragment)
                .commit();
    }
    
    @Override
    protected long getIdentifier()
    {
        return 0;
    }
    
    @Override
    public void navigateToHome()
    {
        Intent intent = new Intent(this, PasswordsListActivity.class);
        startActivity(intent);
        finish();
    }
}
