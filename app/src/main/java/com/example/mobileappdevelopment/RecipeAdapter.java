package com.example.mobileappdevelopment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    private List<Recipe> recipeList;
    private Context context;

    public RecipeAdapter(List<Recipe> recipeList,Context context) {
        this.context = context;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipes_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.recipeNameTextView.setText(recipe.getName());

        holder.recipeNameTextView.setOnClickListener(v -> {
            launchIndividualRecipe(recipe);
        });
    }

    public void launchIndividualRecipe(Recipe recipe) {
        Intent intent = new Intent(context, IndividualRecipeActivity.class);
        intent.putExtra("id",recipe.getID());
        intent.putExtra("name", recipe.getName());
        intent.putExtra("instructions",recipe.getInstructions());
        context.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView recipeNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeNameTextView = itemView.findViewById(R.id.recipeNameTextView);
        }
    }
}
