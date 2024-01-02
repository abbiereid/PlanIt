package com.example.mobileappdevelopment;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class IndividualRecipeActivity extends AppCompatActivity {

    private dbHandlerRecipes db;
    private TextView ingredients;
    private ArrayList<Ingredient> ingredientsList;
    private ArrayList<Integer> initialIngredientAmounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_recipe);

        db = new dbHandlerRecipes(this);

        TextView name = findViewById(R.id.individualName);
        ingredients = findViewById(R.id.ingredients);
        TextView instructions = findViewById(R.id.instructions);

        Bundle extras = getIntent().getExtras();
        int id = extras.getInt("id");
        String recipeName = extras.getString("name");
        String recipeInstructions = extras.getString("instructions");

        ingredientsList = db.getIngredients(id);

        StringBuilder ingredientsStringBuilder = new StringBuilder();

        initialIngredientAmounts = new ArrayList<>();

        for (Ingredient ingredient : ingredientsList) {
            String ingredientName = ingredient.getName();
            int ingredientAmount = ingredient.getAmount();
            initialIngredientAmounts.add(ingredientAmount);
            String ingredientString = ingredientName + ": " + ingredientAmount + " g " + "\n";
            ingredientsStringBuilder.append(ingredientString);
        }

        name.setText(recipeName);
        ingredients.setText(ingredientsStringBuilder);
        instructions.setText(recipeInstructions);

        ImageView home = findViewById(R.id.homeButton);
        home.setOnClickListener(view -> {
            Intent intent = new Intent(IndividualRecipeActivity.this, MainActivity.class);
            startActivity(intent);
        });

        ImageView add = findViewById(R.id.addToMealPlan);
        add.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(IndividualRecipeActivity.this);
            builder.setTitle("Enter Portion Number");

            final EditText input = new EditText(IndividualRecipeActivity.this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            builder.setView(input);

            builder.setPositiveButton("Add Meal", (dialog, which) -> {
                String portionString = input.getText().toString();

                if (!portionString.isEmpty()) {
                    int portions = Integer.parseInt(portionString);

                    Recipe theRecipe = new Recipe(id, recipeName, recipeInstructions);

                    db.addMeal(theRecipe, portions);

                    Snackbar snackbar = Snackbar.make(view, "Meal added successfully", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else {
                    Snackbar snackbar = Snackbar.make(view, "Please enter a valid portion number", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

            builder.show();
        });

        NumberPicker portionPicker = findViewById(R.id.portionPicker);
        portionPicker.setMinValue(1);
        portionPicker.setMaxValue(30);
        portionPicker.setValue(1);


        portionPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            updateIngredientAmounts(newVal);
        });
    }

    private void updateIngredientAmounts(int portions) {
        StringBuilder updatedIngredientsStringBuilder = new StringBuilder();

        for (int i = 0; i < ingredientsList.size(); i++) {
            Ingredient ingredient = ingredientsList.get(i);
            int initialAmount = initialIngredientAmounts.get(i);
            int updatedAmount = initialAmount * portions;
            String ingredientName = ingredient.getName();
            String ingredientString = ingredientName + ": " + updatedAmount + " g " + "\n";
            updatedIngredientsStringBuilder.append(ingredientString);
        }

        ingredients.setText(updatedIngredientsStringBuilder);
    }
}
