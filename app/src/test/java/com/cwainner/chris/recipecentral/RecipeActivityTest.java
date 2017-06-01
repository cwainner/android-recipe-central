package com.cwainner.chris.recipecentral;

import android.os.Build;
import android.widget.TextView;

import com.cwainner.chris.recipecentral.ui.RecipesActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)

public class RecipeActivityTest {
    private RecipesActivity activity;

    @Before
    public void setup(){
        activity = Robolectric.setupActivity(RecipesActivity.class);
    }

    @Test
    public void validateRecipesHeader(){
        TextView recipesHeaderView = (TextView) activity.findViewById(R.id.recipesHeader);
        assertTrue("Recipes".equals(recipesHeaderView.getText().toString()));
    }
}
