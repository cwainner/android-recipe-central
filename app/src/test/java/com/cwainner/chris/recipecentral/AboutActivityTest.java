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

    @Test
    public void validateAboutText(){
        TextView aboutTextView = (TextView) activity.findViewById(R.id.aboutText);
        String aboutTextContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi egestas, ante sit amet facilisis auctor, risus arcu faucibus justo, at tempor odio est ut est. Sed sodales augue a egestas suscipit. Curabitur malesuada tellus dolor, quis laoreet ipsum suscipit tristique. Sed vel ex iaculis, sollicitudin ipsum ac, maximus augue. Phasellus ut dui sed sapien vulputate dapibus. Suspendisse sed neque nec dolor pulvinar iaculis. Suspendisse lorem nisi, tristique a sapien at, porta blandit eros. Nam ut tincidunt mauris, id sollicitudin nulla. Mauris sit amet tincidunt nulla. Etiam elit velit, rutrum sit amet velit in, placerat commodo augue. Curabitur vitae rhoncus velit. In porttitor dictum cursus. Mauris eu fermentum dolor. Phasellus laoreet, metus ut malesuada convallis, elit arcu porta urna, condimentum porttitor magna turpis eget dolor.";
        assertTrue(aboutTextContent.equals(aboutTextView.getText().toString()));
    }
}
