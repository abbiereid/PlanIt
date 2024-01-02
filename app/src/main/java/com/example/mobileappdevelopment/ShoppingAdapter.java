package com.example.mobileappdevelopment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHolder> {

    private HashMap<String, Integer> ingredients;
    private Context context;

    public ShoppingAdapter(HashMap<String, Integer> ingredients, Context context) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public ShoppingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.day, parent, false);
        return new ShoppingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingAdapter.ViewHolder holder, int position) {
        List<String> ingredientNames = new ArrayList<>(ingredients.keySet());
        String ingredientName = ingredientNames.get(position);
        Integer ingredientAmount = ingredients.get(ingredientName);

        String text =  ingredientName + " - " + ingredientAmount + " g ";
        holder.shoppingItemTextView.setText(text);

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView shoppingItemTextView;
        ImageView deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shoppingItemTextView = itemView.findViewById(R.id.mealTextView);
            deleteButton = itemView.findViewById(R.id.delete);
        }
    }
}
