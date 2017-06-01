package com.cwainner.chris.recipecentral.ui;


import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cwainner.chris.recipecentral.R;


public class RecipeDetailFragment extends DialogFragment{
    private TextView recipeDetailHeader;
    private TextView recipeDetailBody;
    private Button recipeDetailCloseButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        recipeDetailHeader = (TextView) rootView.findViewById(R.id.recipeDetailHeader);
        recipeDetailBody = (TextView) rootView.findViewById(R.id.recipeDetailBody);
        recipeDetailCloseButton = (Button) rootView.findViewById(R.id.recipeDetailCloseButton);

        getDialog().setTitle("Recipe Detail");

        Bundle bundle = this.getArguments();
        String recipeDetailHeaderText = bundle.getString("recipe");
        recipeDetailHeader.setText(recipeDetailHeaderText);
        recipeDetailBody.setText("Recipe Body");

        recipeDetailCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return rootView;
    }
}