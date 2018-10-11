package com.rachev.passwordmanager.views.passwordlist;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.rachev.passwordmanager.R;
import com.rachev.passwordmanager.utils.Constants;
import com.rachev.passwordmanager.models.Password;
import studios.codelight.smartloginlibrary.UserSessionManager;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressWarnings("deprecation")
public class PasswordsListFragment extends Fragment
        implements PasswordsAdapter.OnPasswordClickListener, PasswordsListContracts.View
{
    private PasswordsListContracts.Presenter mPresenter;
    private PasswordsListContracts.Navigator mNavigator;
    private LinearLayoutManager mPasswordsViewLayoutManager;
    private List<Password> mPasswords;
    private AlertDialog mAlertDialog;
    private String mUserId;
    
    @BindView(R.id.lv_passwords)
    RecyclerView mPasswordsView;
    
    @BindView(R.id.loading_view)
    ProgressBar mLoadingView;
    
    @BindView(R.id.fab)
    FloatingActionButton mAddPasswordFloatingActionButton;
    
    @Inject
    PasswordsAdapter mPasswordsAdapter;
    
    @Inject
    public PasswordsListFragment()
    {
        // Required empty public constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_passwords_list, container, false);
        
        ButterKnife.bind(this, view);
        
        mPasswordsAdapter.setOnPasswordClickListener(this);
        
        mPasswordsView.setAdapter(mPasswordsAdapter);
        mPasswordsViewLayoutManager = new LinearLayoutManager(getContext());
        mPasswordsView.setLayoutManager(mPasswordsViewLayoutManager);
        
        mAddPasswordFloatingActionButton.setOnClickListener(v ->
        {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
            
            @SuppressLint("InflateParams")
            View mView = getActivity().getLayoutInflater()
                    .inflate(R.layout.dialog_add_password, null);
            
            final EditText mUsername = mView.findViewById(R.id.tv_add_username);
            final EditText mPassword = mView.findViewById(R.id.tv_add_password);
            final EditText mTargetWebsite = mView.findViewById(R.id.tv_add_website);
            Button mAddButton = mView.findViewById(R.id.btn_add);
            
            mAddButton.setOnClickListener(v1 ->
            {
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                String targetWebsite = mTargetWebsite.getText().toString();
                String socialUserAccId = UserSessionManager.getCurrentUser(getContext()).getUserId();
                
                if (!username.isEmpty() && !password.isEmpty() && !targetWebsite.isEmpty())
                {
                    Password passwordToAdd = new Password(username, password, targetWebsite, socialUserAccId);
                    mPresenter.addPassword(passwordToAdd);
                    
                    Toast.makeText(getContext(),
                            Constants.PASSWORD_ADDED_TOAST,
                            Toast.LENGTH_SHORT)
                            .show();
                } else
                    Toast.makeText(getContext(),
                            Constants.PASSWORD_NOT_ALL_FIELDS_FILLED_TOAST,
                            Toast.LENGTH_SHORT)
                            .show();
            });
            
            mBuilder.setView(mView);
            mAlertDialog = mBuilder.create();
            mAlertDialog.show();
        });
        
        mPasswords = new ArrayList<>();
        
        return view;
    }
    
    @Override
    public void onResume()
    {
        super.onResume();
        
        mPresenter.subscribe(this);
        mPresenter.loadPasswords(UserSessionManager.getCurrentUser(getContext()).getUserId());
    }
    
    @Override
    public void setPresenter(PasswordsListContracts.Presenter presenter)
    {
        mPresenter = presenter;
    }
    
    @Override
    public void showPasswords(List<Password> passwords)
    {
        mPasswordsAdapter.clear();
        mPasswordsAdapter.addAll(passwords);
        mPasswordsAdapter.notifyDataSetChanged();
    }
    
    @Override
    public void showEmptyPasswordsList()
    {
        mPasswordsAdapter.clear();
        mPasswordsAdapter.notifyDataSetChanged();
        
        Toast.makeText(getContext(),
                Constants.NO_PASSWORDS_TOAST,
                Toast.LENGTH_LONG)
                .show();
    }
    
    @Override
    public void showError(Throwable ex)
    {
        Toast.makeText(getContext(),
                ex.getMessage(),
                Toast.LENGTH_LONG)
                .show();
    }
    
    @Override
    public void showLoading()
    {
        mPasswordsView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.VISIBLE);
    }
    
    @Override
    public void hideLoading()
    {
        mPasswordsView.setVisibility(View.VISIBLE);
        mLoadingView.setVisibility(View.GONE);
    }
    
    public void setNavigator(PasswordsListContracts.Navigator navigator)
    {
        mNavigator = navigator;
    }
    
    @Override
    public void showPasswordDetails(Password password)
    {
        mNavigator.navigateWith(password);
    }
    
    @Override
    public void navigateToHome()
    {
        if (mAlertDialog != null)
            mAlertDialog.dismiss();
        
        mNavigator.navigateToHome();
    }
    
    @Override
    public void onClick(Password password)
    {
        mPresenter.selectPassword(password);
    }
}
