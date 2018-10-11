package com.rachev.passwordmanager.views.about;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.rachev.passwordmanager.R;
import com.rachev.passwordmanager.utils.Constants;
import com.rachev.passwordmanager.views.BaseDrawerActivity;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class AboutActivity extends BaseDrawerActivity implements RatingDialogListener
{
    public static final int IDENTIFIER = 296;
    
    @BindView(R.id.about_text)
    TextView mAboutTextView;
    
    @BindView(R.id.rating_button)
    Button mRatingButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        
        ButterKnife.bind(this, this);
        
        mAboutTextView.setText(String.format("%s%n%s",
                getString(R.string.app_version),
                getString(R.string.developer)));
        
        mRatingButton.setOnClickListener(v -> showRatingDialog());
    }
    
    private void showRatingDialog()
    {
        new AppRatingDialog.Builder()
                .setPositiveButtonText(Constants.POSITIVE_BUTTON_TEXT)
                .setNegativeButtonText(Constants.NEGATIVE_BUTTON_TEXT)
                .setNeutralButtonText(Constants.NEUTRAL_BUTTON_TEXT)
                .setNoteDescriptions(Arrays.asList(
                        Constants.NOTE_DESCRIPTION_VERY_BAD,
                        Constants.NOTE_DESCRIPTION_NOT_GOOD,
                        Constants.NOTE_DESCRIPTION_OK,
                        Constants.NOTE_DESCRIPTION_VERY_GOOD,
                        Constants.NOTE_DESCRIPTION_EXCELLENT))
                .setDefaultRating(2)
                .setTitle(Constants.RATING_DIALOG_TITLE)
                .setDescription(Constants.RATING_DIALOG_DESCRIPTION)
                .setCommentInputEnabled(true)
                .setStarColor(R.color.accent)
                .setNoteDescriptionTextColor(R.color.md_cyan_50)
                .setTitleTextColor(R.color.white)
                .setDescriptionTextColor(R.color.md_blue_grey_200)
                .setHint(Constants.RATING_COMMENT_HINT)
                .setHintTextColor(R.color.md_cyan_50)
                .setCommentTextColor(R.color.md_cyan_100)
                .setCommentBackgroundColor(R.color.colorPrimary)
                .setWindowAnimation(R.style.MyDialogSlideHorizontalAnimation)
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .create(this)
                .show();
    }
    
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
    
    @Override
    protected long getIdentifier()
    {
        return IDENTIFIER;
    }
    
    @Override
    public void onNegativeButtonClicked()
    {
        Toast.makeText(getApplicationContext(),
                Constants.RATING_CANCELLED_TOAST,
                Toast.LENGTH_SHORT)
                .show();
    }
    
    @Override
    public void onNeutralButtonClicked()
    {
    
    }
    
    @Override
    public void onPositiveButtonClicked(int i, @NotNull String s)
    {
        Toast.makeText(getApplicationContext(),
                Constants.RATING_SUBMITTED_TOAST,
                Toast.LENGTH_SHORT)
                .show();
    }
}
