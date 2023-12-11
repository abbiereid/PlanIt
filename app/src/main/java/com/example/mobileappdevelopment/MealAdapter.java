package com.example.mobileappdevelopment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {

    private List<Recipe> meals;
    private Context context;
    private int dayIndex = 0;

    public MealAdapter(List<Recipe> meals,Context context) {
        this.context = context;
        this.meals = meals;
    }

    @NonNull
    @Override
    public MealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.day, parent, false);
        return new MealAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealAdapter.ViewHolder holder, int position) {
        dayIndex++;
        Recipe recipe = meals.get(position);
        String text = "Day " + (dayIndex) + " : " + recipe.getName();
        holder.mealTextView.setText(text);

        holder.mealTextView.setOnClickListener(v -> {
            launchIndividualRecipe(recipe);
        });

    }

    public void launchIndividualRecipe(Recipe recipe) {
        Intent intent = new Intent(context, IndividualRecipeActivity.class);
        intent.putExtra("id",recipe.getID());
        intent.putExtra("name", recipe.getName());
        intent.putExtra("ingredients",recipe.getIngredients());
        intent.putExtra("instructions",recipe.getInstructions());
        context.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        return meals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mealTextView;
        ImageView deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealTextView = itemView.findViewById(R.id.mealTextView);
            deleteButton = itemView.findViewById(R.id.delete);
        }
    }

}
