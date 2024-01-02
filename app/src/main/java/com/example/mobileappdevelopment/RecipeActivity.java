package com.example.mobileappdevelopment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;


public class RecipeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    private dbHandlerRecipes db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        db = new dbHandlerRecipes(this);

        if (db.readRecipes().isEmpty()) {
            addRecipes(db);
        }


        recyclerView = findViewById(R.id.recipeView);
        recipeAdapter = new RecipeAdapter(getRecipeData(db),this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recipeAdapter);


        ImageView home = findViewById(R.id.homeButton);
        home.setOnClickListener(view -> {
            Intent intent = new Intent(RecipeActivity.this,MainActivity.class);
            startActivity(intent);
        });

    }

    public List<Recipe> getRecipeData(dbHandlerRecipes db){
        List<Recipe> recipes = db.readRecipes();
        return recipes;
    }

    public void addRecipes(dbHandlerRecipes db) {

        Recipe recipe1 = new Recipe(1, "Spaghetti Bolognese", "Classic Italian pasta dish with a rich meat sauce.");
        Ingredient ingredient1_1 = new Ingredient(1, "spaghetti", 300);
        Ingredient ingredient1_2 = new Ingredient(1, "ground beef", 250);
        Ingredient ingredient1_3 = new Ingredient(1, "tomato sauce", 200);
        db.addRecipe(recipe1);
        db.addIngredient(ingredient1_1);
        db.addIngredient(ingredient1_2);
        db.addIngredient(ingredient1_3);


        Recipe recipe2 = new Recipe(2, "Chicken Alfredo", "Creamy chicken and pasta with a luscious Alfredo sauce.");
        Ingredient ingredient2_1 = new Ingredient(2, "fettuccine", 300);
        Ingredient ingredient2_2 = new Ingredient(2, "chicken breast", 300);
        Ingredient ingredient2_3 = new Ingredient(2, "heavy cream", 150);
        Ingredient ingredient2_4 = new Ingredient(2, "parmesan cheese", 50);
        db.addRecipe(recipe2);
        db.addIngredient(ingredient2_1);
        db.addIngredient(ingredient2_2);
        db.addIngredient(ingredient2_3);
        db.addIngredient(ingredient2_4);


        Recipe recipe3 = new Recipe(3, "Vegetarian Pizza", "Delicious pizza with assorted vegetables for a burst of flavors.");
        Ingredient ingredient3_1 = new Ingredient(3, "pizza dough", 400);
        Ingredient ingredient3_2 = new Ingredient(3, "tomato sauce", 150);
        Ingredient ingredient3_3 = new Ingredient(3, "bell peppers", 100);
        Ingredient ingredient3_4 = new Ingredient(3, "mozzarella cheese", 200);
        db.addRecipe(recipe3);
        db.addIngredient(ingredient3_1);
        db.addIngredient(ingredient3_2);
        db.addIngredient(ingredient3_3);
        db.addIngredient(ingredient3_4);


        Recipe recipe4 = new Recipe(4, "Caesar Salad", "Classic Caesar salad with grilled chicken for a satisfying meal.");
        Ingredient ingredient4_1 = new Ingredient(4, "romaine lettuce", 200);
        Ingredient ingredient4_2 = new Ingredient(4, "grilled chicken breast", 250);
        Ingredient ingredient4_3 = new Ingredient(4, "Caesar dressing", 50);
        Ingredient ingredient4_4 = new Ingredient(4, "croutons", 75);
        db.addRecipe(recipe4);
        db.addIngredient(ingredient4_1);
        db.addIngredient(ingredient4_2);
        db.addIngredient(ingredient4_3);
        db.addIngredient(ingredient4_4);


        Recipe recipe5 = new Recipe(5, "Beef Tacos", "Tasty beef tacos with fresh salsa for a quick and delightful dish.");
        Ingredient ingredient5_1 = new Ingredient(5, "ground beef", 300);
        Ingredient ingredient5_2 = new Ingredient(5, "taco shells", 150);
        Ingredient ingredient5_3 = new Ingredient(5, "tomato salsa", 100);
        Ingredient ingredient5_4 = new Ingredient(5, "cheddar cheese", 75);
        db.addRecipe(recipe5);
        db.addIngredient(ingredient5_1);
        db.addIngredient(ingredient5_2);
        db.addIngredient(ingredient5_3);
        db.addIngredient(ingredient5_4);


        Recipe recipe6 = new Recipe(6, "Mushroom Risotto", "Creamy mushroom risotto with Parmesan for a comforting meal.");
        Ingredient ingredient6_1 = new Ingredient(6, "arborio rice", 200);
        Ingredient ingredient6_2 = new Ingredient(6, "mushrooms", 150);
        Ingredient ingredient6_3 = new Ingredient(6, "Parmesan cheese", 50);
        Ingredient ingredient6_4 = new Ingredient(6, "vegetable broth", 300);
        db.addRecipe(recipe6);
        db.addIngredient(ingredient6_1);
        db.addIngredient(ingredient6_2);
        db.addIngredient(ingredient6_3);
        db.addIngredient(ingredient6_4);


        Recipe recipe7 = new Recipe(7, "Shrimp Scampi", "Garlic-infused shrimp with linguine for a flavorful seafood pasta.");
        Ingredient ingredient7_1 = new Ingredient(7, "shrimp", 250);
        Ingredient ingredient7_2 = new Ingredient(7, "linguine", 200);
        Ingredient ingredient7_3 = new Ingredient(7, "garlic", 30);
        Ingredient ingredient7_4 = new Ingredient(7, "lemon juice", 25);
        db.addRecipe(recipe7);
        db.addIngredient(ingredient7_1);
        db.addIngredient(ingredient7_2);
        db.addIngredient(ingredient7_3);
        db.addIngredient(ingredient7_4);


        Recipe recipe8 = new Recipe(8, "Caprese Salad", "Simple and refreshing Caprese salad with tomatoes, mozzarella, and basil.");
        Ingredient ingredient8_1 = new Ingredient(8, "tomatoes", 200);
        Ingredient ingredient8_2 = new Ingredient(8, "fresh mozzarella", 150);
        Ingredient ingredient8_3 = new Ingredient(8, "fresh basil", 20);
        Ingredient ingredient8_4 = new Ingredient(8, "balsamic glaze", 30);
        db.addRecipe(recipe8);
        db.addIngredient(ingredient8_1);
        db.addIngredient(ingredient8_2);
        db.addIngredient(ingredient8_3);
        db.addIngredient(ingredient8_4);


        Recipe recipe9 = new Recipe(9, "Vegetable Stir Fry", "Colorful mix of stir-fried vegetables for a quick and healthy meal.");
        Ingredient ingredient9_1 = new Ingredient(9, "broccoli", 150);
        Ingredient ingredient9_2 = new Ingredient(9, "carrots", 100);
        Ingredient ingredient9_3 = new Ingredient(9, "snow peas", 75);
        Ingredient ingredient9_4 = new Ingredient(9, "soy sauce", 50);
        db.addRecipe(recipe9);
        db.addIngredient(ingredient9_1);
        db.addIngredient(ingredient9_2);
        db.addIngredient(ingredient9_3);
        db.addIngredient(ingredient9_4);


        Recipe recipe10 = new Recipe(10, "Lemon Garlic Chicken", "Zesty lemon garlic marinated chicken for a burst of citrus flavor.");
        Ingredient ingredient10_1 = new Ingredient(10, "chicken thighs", 300);
        Ingredient ingredient10_2 = new Ingredient(10, "lemon juice", 50);
        Ingredient ingredient10_3 = new Ingredient(10, "garlic cloves", 25);
        Ingredient ingredient10_4 = new Ingredient(10, "olive oil", 30);
        db.addRecipe(recipe10);
        db.addIngredient(ingredient10_1);
        db.addIngredient(ingredient10_2);
        db.addIngredient(ingredient10_3);
        db.addIngredient(ingredient10_4);

    }

}