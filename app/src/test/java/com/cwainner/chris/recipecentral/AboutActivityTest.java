package com.cwainner.chris.recipecentral;

import android.os.Build;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)

public class AboutActivityTest {
    private AboutActivity activity;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(AboutActivity.class);
    }

    @Test
    public void validateAboutHeader(){
        TextView aboutHeaderView = (TextView) activity.findViewById(R.id.aboutHeader);
        assertTrue("About".equals(aboutHeaderView.getText().toString()));
    }
}
