package com.rachev.passwordmanager.views.passwordlist;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.rachev.passwordmanager.R;
import com.rachev.passwordmanager.models.Password;

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
    private GridLayoutManager mPasswordsViewLayoutManager;
    private List<Password> mPasswords;
    
    @BindView(R.id.lv_passwords)
    RecyclerView mPasswordsView;
    
    @BindView(R.id.loading_view)
    ProgressBar mLoadingView;
    
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
        mPasswordsViewLayoutManager = new GridLayoutManager(getContext(), 2);
        mPasswordsView.setLayoutManager(mPasswordsViewLayoutManager);
        
        mPasswords = new ArrayList<>();
        
        return view;
    }
    
    @Override
    public void onResume()
    {
        super.onResume();
        
        mPresenter.subscribe(this);
        mPresenter.loadPasswords();
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
        Toast.makeText(getContext(), "No superheroes", Toast.LENGTH_LONG)
                .show();
    }
    
    @Override
    public void showError(Throwable ex)
    {
        Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_LONG)
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
    public void onClick(Password password)
    {
        mPresenter.selectPassword(password);
    }
}
