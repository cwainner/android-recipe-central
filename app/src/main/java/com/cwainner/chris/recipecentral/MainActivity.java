package com.cwainner.chris.recipecentral;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.aboutButton) Button aboutButton;
    @Bind(R.id.contactButton) Button contactButton;
    @Bind(R.id.getRecipesButton) Button getRecipesButton;
    @Bind(R.id.mainHeader) TextView mainHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface quicksandFont = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Bold.otf");
        mainHeader.setTypeface(quicksandFont);

        aboutButton.setOnClickListener(this);
        contactButton.setOnClickListener(this);
        getRecipesButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v == aboutButton){
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        if(v == contactButton){
            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(intent);
        }
        if(v == getRecipesButton){
            Intent intent = new Intent(MainActivity.this, RecipesActivity.class);
            startActivity(intent);
        }
    }
}
