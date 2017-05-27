package com.cwainner.chris.recipecentral;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;


public class MainActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
}
