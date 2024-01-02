package com.example.mobileappdevelopment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

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

        ImageView home = findViewById(R.id.homeButton);
        home.setOnClickListener(view -> {
            Intent intent = new Intent(ShoppingActivity.this,MainActivity.class);
            startActivity(intent);
        });

    }

    public HashMap<String,Integer> getData(dbHandlerRecipes db) {
        HashMap<String,Integer> ingredients = db.readShopping();
        return ingredients;
    }

}
