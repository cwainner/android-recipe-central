package com.cwainner.chris.recipecentral.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cwainner.chris.recipecentral.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.aboutButton) Button aboutButton;
    @Bind(R.id.contactButton) Button contactButton;
    @Bind(R.id.getRecipesButton) Button getRecipesButton;
    @Bind(R.id.mainHeader) TextView mainHeader;
    @Bind(R.id.ingredientsText) EditText ingredientsText;
    @Bind(R.id.editTextView) TextView editTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface quicksandFont = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Bold.otf");
        mainHeader.setTypeface(quicksandFont);
        editTextView.setTypeface(quicksandFont);

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
           if(ingredientsText.getText().toString().equals("")){
              Toast.makeText(MainActivity.this, "Please enter ingredients or recipe to search", Toast.LENGTH_SHORT).show();
           } else{
               String ingredients = ingredientsText.getText().toString();
               Intent intent = new Intent(MainActivity.this, RecipesActivity.class);
               intent.putExtra("ingredients", ingredients);
               Toast.makeText(MainActivity.this, "Searching for Recipes", Toast.LENGTH_SHORT).show();
               startActivity(intent);
           }
        }
    }
}
