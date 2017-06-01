package com.cwainner.chris.recipecentral.ui;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.cwainner.chris.recipecentral.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {
    @Bind(R.id.aboutHeader) TextView aboutHeader;
    @Bind(R.id.aboutText) TextView aboutText;

    String aboutTextContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi egestas, ante sit amet facilisis auctor, risus arcu faucibus justo, at tempor odio est ut est. Sed sodales augue a egestas suscipit. Curabitur malesuada tellus dolor, quis laoreet ipsum suscipit tristique. Sed vel ex iaculis, sollicitudin ipsum ac, maximus augue. Phasellus ut dui sed sapien vulputate dapibus. Suspendisse sed neque nec dolor pulvinar iaculis. Suspendisse lorem nisi, tristique a sapien at, porta blandit eros. Nam ut tincidunt mauris, id sollicitudin nulla. Mauris sit amet tincidunt nulla. Etiam elit velit, rutrum sit amet velit in, placerat commodo augue. Curabitur vitae rhoncus velit. In porttitor dictum cursus. Mauris eu fermentum dolor. Phasellus laoreet, metus ut malesuada convallis, elit arcu porta urna, condimentum porttitor magna turpis eget dolor.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        Typeface quickSandBoldFont = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Bold.otf");
        aboutHeader.setTypeface(quickSandBoldFont);

        aboutText.setText(aboutTextContent);
    }
}
