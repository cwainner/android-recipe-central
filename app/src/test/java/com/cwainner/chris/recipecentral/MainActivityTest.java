package com.cwainner.chris.recipecentral;

import android.content.Intent;
import android.os.Build;
import android.widget.TextView;

import com.cwainner.chris.recipecentral.ui.AboutActivity;
import com.cwainner.chris.recipecentral.ui.ContactActivity;
import com.cwainner.chris.recipecentral.ui.MainActivity;
import com.cwainner.chris.recipecentral.ui.RecipeListActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static org.junit.Assert.*;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)

public class MainActivityTest {
    private MainActivity activity;

    @Before
    public void setup(){
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void validateMainHeader(){
        TextView appNameTextView = (TextView) activity.findViewById(R.id.mainHeader);
        assertTrue("Welcome to Recipe Central!".equals(appNameTextView.getText().toString()));
    }

    @Test
    public void aboutActivityStarted(){
        activity.findViewById(R.id.aboutButton).performClick();
        Intent expectedIntent = new Intent(activity, AboutActivity.class);
        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }

    @Test
    public void contactActivityStarted(){
        activity.findViewById(R.id.contactButton).performClick();
        Intent expectedIntent = new Intent(activity, ContactActivity.class);
        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }

    @Test
    public void recipesActivityStarted(){
        activity.findViewById(R.id.getRecipesButton).performClick();
        Intent expectedIntent = new Intent(activity, RecipeListActivity.class);
        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }
}
