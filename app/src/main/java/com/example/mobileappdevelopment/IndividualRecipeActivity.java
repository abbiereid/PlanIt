package com.example.mobileappdevelopment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IndividualRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_recipe);

        TextView name = findViewById(R.id.individualName);
        TextView ingredients = findViewById(R.id.ingredients);
        TextView instructions = findViewById(R.id.instructions);

        Bundle extras = getIntent().getExtras();
        int id = extras.getInt("id");
        String recipeName = extras.getString("name");
        String recipeIngredients = extras.getString("ingredients");
        String recipeInstructions = extras.getString("instructions");

        name.setText(recipeName);
        ingredients.setText(recipeIngredients);
        instructions.setText(recipeInstructions);

        ImageView backToRecipes = findViewById(R.id.backToRecipe);
        backToRecipes.setOnClickListener(view -> {
            Intent intent = new Intent(IndividualRecipeActivity.this,RecipeActivity.class);
            startActivity(intent);
        });

        ImageView backToMealPlan = findViewById(R.id.backToMealPlan);
        backToMealPlan.setOnClickListener(view -> {
            Intent intent = new Intent(IndividualRecipeActivity.this,MealPlanActivity.class);
            startActivity(intent);
        });

        ImageView add = findViewById(R.id.addToMealPlan);
        add.setOnClickListener(view -> {
            Recipe theRecipe = new Recipe(id,recipeName,recipeIngredients,recipeInstructions);

            dbHandlerRecipes db = new dbHandlerRecipes(this);
            db.addMeal(theRecipe);

        });

    }
}
