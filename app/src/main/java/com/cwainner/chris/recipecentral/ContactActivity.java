package com.cwainner.chris.recipecentral;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContactActivity extends AppCompatActivity {
    @Bind(R.id.contactHeader) TextView contactHeader;
    @Bind(R.id.contactText) TextView contactText;

    String contactTextContent = "Phone: XXX-XXX-XXXX\n" + "Email: xxxx@gmail.com\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);

        Typeface quicksandFont = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Bold.otf");
        contactHeader.setTypeface(quicksandFont);

        contactText.setText(contactTextContent);
    }
}
