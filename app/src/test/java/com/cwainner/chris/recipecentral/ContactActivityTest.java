package com.cwainner.chris.recipecentral;

import android.os.Build;
import android.widget.TextView;

import com.cwainner.chris.recipecentral.ui.ContactActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)

public class ContactActivityTest {
    private ContactActivity activity;

    @Before
    public void setup(){
        activity = Robolectric.setupActivity(ContactActivity.class);
    }

    @Test
    public void validateContactHeader(){
        TextView contactHeaderView = (TextView) activity.findViewById(R.id.contactHeader);
        assertTrue("Contact".equals(contactHeaderView.getText().toString()));
    }
}
