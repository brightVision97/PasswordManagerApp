package com.rachev.passwordmanager.views.passwordlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.rachev.passwordmanager.R;
import com.rachev.passwordmanager.utils.Constants;
import com.rachev.passwordmanager.models.Password;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class PasswordsAdapter extends RecyclerView.Adapter<PasswordsAdapter.PasswordViewHolder>
{
    private List<Password> mPasswords;
    private OnPasswordClickListener mOnPasswordClickListener;
    
    @Inject
    public PasswordsAdapter()
    {
        mPasswords = new ArrayList<>();
    }
    
    @NonNull
    @Override
    public PasswordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.password_item, viewGroup, false);
        
        int height = viewGroup.getMeasuredHeight() / Constants.PARENT_VIEW_HEIGHT_DIVIDER;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = height;

        view.setLayoutParams(layoutParams);
        view.setMinimumHeight(height);
        
        return new PasswordViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull PasswordViewHolder passwordViewHolder, int i)
    {
        passwordViewHolder.setOnClickListener(mOnPasswordClickListener);
        passwordViewHolder.bind(mPasswords.get(i));
    }
    
    @Override
    public int getItemCount()
    {
        return mPasswords.size();
    }
    
    public Password getItem(int pos)
    {
        return mPasswords.get(pos);
    }
    
    public void clear()
    {
        mPasswords.clear();
    }
    
    public void addAll(List<Password> passwords)
    {
        mPasswords.addAll(passwords);
    }
    
    public void setOnPasswordClickListener(OnPasswordClickListener onPasswordClickListener)
    {
        mOnPasswordClickListener = onPasswordClickListener;
    }
    
    public static class PasswordViewHolder extends RecyclerView.ViewHolder
    {
        private OnPasswordClickListener mOnClickListener;
        private Password mPassword;
        
        @BindView(R.id.tv_website_name)
        TextView mTargetWebsiteTextView;
        
        public PasswordViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        
        void bind(Password password)
        {
            mTargetWebsiteTextView.setText(password.targetWebsite);
            mPassword = password;
        }
        
        @OnClick
        public void onClick()
        {
            mOnClickListener.onClick(mPassword);
        }
        
        public void setOnClickListener(OnPasswordClickListener onClickListener)
        {
            mOnClickListener = onClickListener;
        }
    }
    
    interface OnPasswordClickListener
    {
        void onClick(Password password);
    }
}
