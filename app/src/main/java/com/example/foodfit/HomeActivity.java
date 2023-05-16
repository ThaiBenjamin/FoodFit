package com.example.foodfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void goalEventRunner(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void benefitsEventRunner(View view){
        Intent i = new Intent(this, BenefitsActivity.class);
        startActivity(i);
    }

    public void recipesEventRunner(View view){
        Intent i = new Intent(this, RecipesActivity.class);
        startActivity(i);
    }

    public void creditsEventRunner(View view){
        Intent i = new Intent(this, CreditsActivity.class);
        startActivity(i);
    }

    public void additionalResourcesEventRunner(View view){
        Intent i = new Intent(this, AdditionalResourcesActivity.class);
        startActivity(i);
    }

}