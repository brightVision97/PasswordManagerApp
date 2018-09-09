package com.rachev.passwordmanager.views.passworddetails;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.rachev.passwordmanager.R;
import com.rachev.passwordmanager.models.Password;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressWarnings("deprecation")
public class PasswordDetailsFragment extends Fragment implements PasswordDetailsContracts.View
{
    private PasswordDetailsContracts.Presenter mPresenter;
    
    @BindView(R.id.tv_username)
    TextView mUsernameTextView;
    
    @BindView(R.id.tv_password)
    TextView mPasswordTextView;
    
    @Inject
    public PasswordDetailsFragment()
    {
        // Required empty public constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_password_details, container, false);
        
        ButterKnife.bind(this, view);
        
        return view;
    }
    
    @Override
    public void onResume()
    {
        super.onResume();
        
        mPresenter.subscribe(this);
        mPresenter.loadPassword();
    }
    
    @Override
    public void showPassword(Password password)
    {
        mUsernameTextView.setText(password.getUsername());
        mPasswordTextView.setText(password.getPassword());
    }
    
    @Override
    public void setPresenter(PasswordDetailsContracts.Presenter presenter)
    {
        mPresenter = presenter;
    }
    
    @Override
    public void showError(Throwable e)
    {
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG)
                .show();
    }
}