package com.example.mobileappdevelopment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.content.Intent;
import android.view.View;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Recipe> recipes = RecipeReader.fillRecipeArrayList();


        ImageView recipesButton = findViewById(R.id.recipesButton);
        recipesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RecipeActivity.class);
                startActivity(intent);
            }
        });

    }
}