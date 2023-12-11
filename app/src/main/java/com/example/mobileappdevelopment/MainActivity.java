package com.example.mobileappdevelopment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView recipesButton = findViewById(R.id.recipesButton);

        recipesButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,RecipeActivity.class);
            startActivity(intent);
        });

        ImageView mealsButton = findViewById(R.id.mealplanButton);
        mealsButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,MealPlanActivity.class);
            startActivity(intent);
        });

        ImageView shoppingButton = findViewById(R.id.shoppingButton);
        shoppingButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,ShoppingActivity.class);
            startActivity(intent);
        });

    }
}