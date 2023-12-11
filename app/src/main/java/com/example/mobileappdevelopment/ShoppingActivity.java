package com.example.mobileappdevelopment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class ShoppingActivity  extends AppCompatActivity {

    RecyclerView recyclerView;
    private dbHandlerRecipes db;
    private ShoppingAdapter shoppingAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_layout);

        db = new dbHandlerRecipes(this);

        recyclerView = findViewById(R.id.shoppingRecycler);
        shoppingAdapter = new ShoppingAdapter(getData(db),this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(shoppingAdapter);


    }

    private HashMap<String,String> getData(dbHandlerRecipes db) {
        HashMap<String,String> ingredients = db.readIngredients();
        return ingredients;
    }
}
