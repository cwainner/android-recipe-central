package com.cwainner.chris.recipecentral.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cwainner.chris.recipecentral.R;


public class CreateAccountFragment extends Fragment implements View.OnClickListener{

    private EditText emailEditText;
    private EditText nameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button createAccountButton;

    private Context context;

    public CreateAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_account, container, false);

        context = view.getContext();

        emailEditText = (EditText) view.findViewById(R.id.emailEditText);
        nameEditText = (EditText) view.findViewById(R.id.nameEditText);
        passwordEditText = (EditText) view.findViewById(R.id.passwordEditText);
        confirmPasswordEditText = (EditText) view.findViewById(R.id.confirmPasswordEditText);
        createAccountButton = (Button) view.findViewById(R.id.createAccountSubmitButton);

        createAccountButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == createAccountButton){
            createNewUser();
        }
    }

    private void createNewUser(){
        final String name = nameEditText.getText().toString().trim();
        final String email = emailEditText.getText().toString().trim();
        final String password = passwordEditText.getText().toString().trim();
        final String confirmPassword = confirmPasswordEditText.getText().toString().trim();
        if((password.length() < 6) || !(password.equals(confirmPassword))){
            Toast.makeText(context, "Please enter a password that is at least 6 characters long. Both passwords much also match.", Toast.LENGTH_SHORT).show();
        }
    }
}