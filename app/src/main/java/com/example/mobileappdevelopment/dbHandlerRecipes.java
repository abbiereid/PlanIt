package com.example.mobileappdevelopment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class dbHandlerRecipes extends SQLiteOpenHelper {

    private static final String dbName = "recipesDB";
    private static final int version = 1;
    private static final String tableName = "recipes";
    private static final String id = "id";
    private static final String recipeName = "name";
    private static final String ingredients = "ingredients";
    private static final String instructions = "instructions";
    private static final String mealplanTable = "meals";
    private static final String shoppingListTable = "shopping";
    private static final String individualIngredients = "ingredient";
    private static final String amount = "amount";

    public dbHandlerRecipes(Context context) { super(context, dbName, null, version);}

    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ tableName + " ("
                + id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + recipeName + " TEXT, "
                + ingredients + " TEXT, "
                + instructions + " TEXT)";

        db.execSQL(query);

        String query2 = "CREATE TABLE "+ mealplanTable + " ("
                + recipeName + " STRING)";

        db.execSQL(query2);

        String query3 = "CREATE TABLE "+ shoppingListTable + " ("+ individualIngredients+ " STRING, " + amount +" STRING)";

        db.execSQL(query3);

    }

    public void addRecipe(Recipe recipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(recipeName, recipe.getName());
        values.put(ingredients, recipe.getIngredients());
        values.put(instructions, recipe.getInstructions());

        db.insert(tableName, null, values);
        db.close();
    }

    public void deleteRecipe(Recipe recipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, recipeName + " = ?",
                new String[]{recipe.getName()});
        db.close();
    }


    public ArrayList<Recipe> readRecipes() {

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor= db.rawQuery("SELECT * FROM " + tableName, null);

        ArrayList<Recipe> recipeList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                recipeList.add(new Recipe(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        return recipeList;
    }

    public Recipe readSpecificRecipe(String entry) {

        SQLiteDatabase db = this.getReadableDatabase();

        //What i want to select
        String[] projection = {
                id,
                recipeName,
                ingredients,
                instructions
        };

        //WHERE clause
        String selection = recipeName + " = ?";
        String[] selectionArgs = { entry };


        Cursor cursor = db.query(
                tableName,          //FROM
                projection,         //SELECT THIS
                selection,          // WHERE
                selectionArgs,      // name = name
                null,
                null,
                null
        );

        Recipe recipe = null;


        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String ingredients = cursor.getString(2);
            String instructions = cursor.getString(3);

            recipe = new Recipe(id,name,ingredients,instructions);
        }

        cursor.close();
        return recipe;

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }


    public void addMeal(Recipe recipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(recipeName, recipe.getName());

        db.insert(mealplanTable,null,values);

        addIngredient(recipe.getIngredients());
    }


    public ArrayList<String> readMeals() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor= db.rawQuery("SELECT * FROM " + mealplanTable, null);

        ArrayList<String> mealList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                mealList.add(cursor.getString(0));

            } while (cursor.moveToNext());
        }

        cursor.close();
        return mealList;
    }

    public void addIngredient(String ingredientEntry) {
        SQLiteDatabase db = this.getWritableDatabase();

        List<String> ingredientList = Arrays.asList(ingredientEntry.split(","));

        for (String ingredient : ingredientList) {
            String[] parts = ingredient.trim().split("\\s+", 2);
            String ingredientName = parts[0];
            String ingredientAmount = (parts.length > 1) ? parts[1] : "";


            ContentValues values = new ContentValues();
            values.put(individualIngredients, ingredientName);
            values.put(amount, ingredientAmount);
            db.insert(shoppingListTable, null, values);
        }

        db.close();
    }

    public HashMap<String, String> readIngredients() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor= db.rawQuery("SELECT * FROM " + shoppingListTable, null);

        HashMap<String,String> shoppingList = new HashMap<>();

        if (cursor.moveToFirst()) {
            do {
                shoppingList.put(cursor.getString(0),cursor.getString(1));

            } while (cursor.moveToNext());
        }

        cursor.close();
        return shoppingList;
    }
}
