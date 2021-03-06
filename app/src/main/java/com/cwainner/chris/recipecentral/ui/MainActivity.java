package com.cwainner.chris.recipecentral.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cwainner.chris.recipecentral.Constants;
import com.cwainner.chris.recipecentral.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.aboutButton) Button aboutButton;
    @Bind(R.id.contactButton) Button contactButton;
    @Bind(R.id.getRecipesButton) Button getRecipesButton;
    @Bind(R.id.mainHeader) TextView mainHeader;
    @Bind(R.id.ingredientsText) EditText ingredientsText;
    @Bind(R.id.editTextView) TextView editTextView;
    @Bind(R.id.savedRecipesButton) Button savedRecipesButton;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        Typeface quicksandFont = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Bold.otf");
        mainHeader.setTypeface(quicksandFont);
        editTextView.setTypeface(quicksandFont);

        aboutButton.setOnClickListener(this);
        contactButton.setOnClickListener(this);
        getRecipesButton.setOnClickListener(this);
        savedRecipesButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();
        if(id == R.id.action_logout){
            logout();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
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
               String searched = ingredientsText.getText().toString();
               Intent intent = new Intent(MainActivity.this, RecipeListActivity.class);
               addToSharedPreferences(searched);
               Toast.makeText(MainActivity.this, "Searching for Recipes", Toast.LENGTH_SHORT).show();
               startActivity(intent);
           }
        }
        if(v == savedRecipesButton){
            Intent intent = new Intent(MainActivity.this, SavedRecipeListActivity.class);
            startActivity(intent);
        }
    }

    private void logout(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void addToSharedPreferences(String searched){
        editor.putString(Constants.PREFERENCES_SEARCHED_KEY, searched).apply();
    }
}
