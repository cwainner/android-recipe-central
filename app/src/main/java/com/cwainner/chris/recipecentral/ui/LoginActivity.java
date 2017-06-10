package com.cwainner.chris.recipecentral.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.cwainner.chris.recipecentral.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.createAccountButton) ToggleButton createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        final FragmentManager fm = getSupportFragmentManager();
        LoginFragment initialLoginFragment = new LoginFragment();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.loginFrameLayout, initialLoginFragment);
        fragmentTransaction.commit();

        createAccountButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    // Load create account fragment
                    CreateAccountFragment createAccountFragment = new CreateAccountFragment();

                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.loginFrameLayout, createAccountFragment);
                    fragmentTransaction.commit();
                } else {
                    // Load login fragment
                    LoginFragment loginFragment = new LoginFragment();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.loginFrameLayout, loginFragment);
                    fragmentTransaction.commit();
                }
            }
        });
    }
}
