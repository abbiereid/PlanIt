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

        Recipe macAndCheese = new Recipe(1, "Classic Macaroni and Cheese", "200g Elbow macaroni, 150g Cheddar cheese, 50g Butter, 300ml Milk, 30g Flour, Salt to taste, Pepper to taste", "Cook macaroni, make cheese sauce with 25g butter, 30g flour, 300ml milk, and 150g cheddar cheese, mix with pasta");
        Recipe spagBol = new Recipe(2, "Spaghetti Bolognese", "250g Spaghetti, 300g Ground beef, 400ml Tomato sauce, 1 Onion, 2 Garlic cloves", "Boil the spaghetti, cook the ground beef with 1 chopped onion and 2 minced garlic cloves, add tomato sauce");
        Recipe pancakes = new Recipe(3, "Pancakes", "200g Flour, 2 Eggs, 300ml Milk, 10g Baking powder, Pinch of Salt", "Mix the ingredients, pour onto a griddle, flip when bubbles form");
        Recipe chickenCurry = new Recipe(4, "Chicken Curry", "500g Chicken, 20g Curry powder, 1 Onion, 3 Garlic cloves, 400ml Coconut milk", "Cook chicken with 20g curry powder, 1 chopped onion, and 3 minced garlic cloves, add 400ml coconut milk, simmer");
        Recipe vegetableStirFry = new Recipe(5, "Vegetable Stir Fry", "150g Broccoli, 200g Carrots, 2 Bell peppers, 50ml Soy sauce, 10g Ginger", "Stir fry vegetables in 50ml soy sauce and 10g ginger");
        Recipe tunaSalad = new Recipe(6, "Tuna Salad", "1 can Tuna, Lettuce, Tomatoes, Cucumbers, Olive oil", "Mix tuna with chopped vegetables, drizzle with olive oil");
        Recipe tomatoSoup = new Recipe(7, "Tomato Soup", "500g Tomatoes, 1 Onion, 3 Garlic cloves, Basil to taste, 500ml Chicken broth", "Cook 500g tomatoes, 1 chopped onion, and 3 minced garlic cloves in 500ml chicken broth, add basil, blend");
        Recipe beefTacos = new Recipe(8, "Beef Tacos", "300g Ground beef, Taco seasoning to taste, Tortillas, Lettuce, Cheese", "Cook 300g beef with taco seasoning, fill tortillas, top with lettuce and cheese");
        Recipe spaghettiCarbonara = new Recipe(9, "Spaghetti Carbonara", "250g Spaghetti, 2 Eggs, 100g Bacon, 50g Parmesan cheese", "Cook spaghetti, mix with 2 beaten eggs, 100g bacon, and 50g parmesan cheese");
        Recipe grilledChicken = new Recipe(10, "Grilled Chicken", "4 Chicken breasts, Lemon, Olive oil, 3 Garlic cloves", "Marinate chicken breasts in lemon, olive oil, and 3 minced garlic cloves, grill until done");
        Recipe vegetableLasagna = new Recipe(11, "Vegetable Lasagna", "Lasagna noodles, 2 Zucchinis, 150g Spinach, 250g Ricotta cheese, Marinara sauce", "Layer noodles with vegetables, 250g ricotta, and marinara sauce, bake");
        Recipe shrimpScampi = new Recipe(12, "Shrimp Scampi", "300g Shrimp, 3 Garlic cloves, Lemon, 50g Butter, Fresh parsley", "Sauté 300g shrimp with 3 minced garlic cloves and 50g butter, squeeze lemon, garnish with fresh parsley");
        Recipe chickenCaesarSalad = new Recipe(13, "Chicken Caesar Salad", "300g Chicken, Romaine lettuce, Croutons, 50g Parmesan cheese, Caesar dressing", "Grill 300g chicken, toss with lettuce, croutons, 50g parmesan cheese, and caesar dressing");
        Recipe mushroomRisotto = new Recipe(14, "Mushroom Risotto", "300g Arborio rice, 200g Mushrooms, 1 Onion, White wine to taste, 1L Chicken broth", "Sauté 200g mushrooms and 1 chopped onion, add 300g rice, deglaze with white wine, slowly add 1L chicken broth");
        Recipe spinachSmoothie = new Recipe(15, "Spinach Smoothie", "Handful of Spinach, 1 Banana, 150g Greek yogurt, 200ml Almond milk, Honey to taste", "Blend ingredients until smooth");
        Recipe BBQChickenPizza = new Recipe(16, "BBQ Chicken Pizza", "Pizza dough, BBQ sauce, 300g Chicken, Red onion, 200g Mozzarella cheese", "Spread BBQ sauce on dough, top with 300g chicken, sliced red onion, and 200g mozzarella cheese, bake");
        Recipe vegetableQuinoaBowl = new Recipe(17, "Vegetable Quinoa Bowl", "200g Quinoa, 150g Broccoli, 200g Carrots, 1 can Chickpeas, Tahini dressing", "Cook 200g quinoa, roast 150g broccoli, 200g carrots, and chickpeas, toss with tahini dressing");
        Recipe chickenFajitas = new Recipe(18, "Chicken Fajitas", "400g Chicken, 2 Bell peppers, 1 Onion, Fajita seasoning to taste, Tortillas", "Cook 400g chicken with sliced bell peppers and 1 sliced onion, season with fajita mix, serve in tortillas");



        db = new dbHandlerRecipes(this);

       db.addRecipe(macAndCheese);
        db.addRecipe(spagBol);
        db.addRecipe(pancakes);
        db.addRecipe(chickenCurry);
        db.addRecipe(vegetableStirFry);
        db.addRecipe(tunaSalad);
        db.addRecipe(tomatoSoup);
        db.addRecipe(beefTacos);
        db.addRecipe(spaghettiCarbonara);
        db.addRecipe(grilledChicken);
        db.addRecipe(vegetableLasagna);
        db.addRecipe(shrimpScampi);
        db.addRecipe(chickenCaesarSalad);
        db.addRecipe(mushroomRisotto);
        db.addRecipe(spinachSmoothie);
        db.addRecipe(BBQChickenPizza);
        db.addRecipe(vegetableQuinoaBowl);
        db.addRecipe(chickenFajitas);


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

}