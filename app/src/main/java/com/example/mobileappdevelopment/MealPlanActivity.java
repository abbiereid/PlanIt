package com.example.mobileappdevelopment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MealPlanActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private dbHandlerRecipes db;
    private MealAdapter mealAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealplan_activity);

        db = new dbHandlerRecipes(this);

        recyclerView = findViewById(R.id.shoppingRecycler);
        mealAdapter = new MealAdapter(getData(db),this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mealAdapter);

    }

    public List<Recipe> getData(dbHandlerRecipes db) {
        List<Recipe> recipes = new ArrayList<>();
        List<String> meals = db.readMeals();

        for (int i = 0; i<meals.size(); i++) {
            String name = meals.get(i);
            recipes.add(db.readSpecificRecipe(name));
        }

        return recipes;
    }

}
