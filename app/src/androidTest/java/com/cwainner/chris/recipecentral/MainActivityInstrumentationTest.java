package com.cwainner.chris.recipecentral;

import android.support.test.rule.ActivityTestRule;

import com.cwainner.chris.recipecentral.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class MainActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validateEditText(){
        onView(withId(R.id.ingredientsText)).perform(typeText("Brownies"))
                .check(matches(withText("Brownies")));
    }

    @Test
    public void textInputSentToRecipesActivity(){
        String textToInput = "Brownies";
        onView(withId(R.id.ingredientsText)).perform(typeText(textToInput));
        onView(withId(R.id.getRecipesButton)).perform(click());
//        onView(withId(R.id.recipeTypeView)).check(matches(withText("Recipe type: " + textToInput)));
    }
}
