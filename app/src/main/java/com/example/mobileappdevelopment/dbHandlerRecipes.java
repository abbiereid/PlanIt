package com.example.mobileappdevelopment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    private static final String instructions = "instructions";
    private static final String mealplanTable = "meals";
    private static final String shoppingListTable = "shopping";
    private static final String individualIngredients = "ingredient";
    private static final String amount = "amount";
    private static final String ingredientsTable = "ingredients";
    private static final String ingredientName = "name";
    private static final String ingredientAmount = "amount";
    private static final String recipeID = "recipeID";

    public dbHandlerRecipes(Context context) { super(context, dbName, null, version);}

    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ tableName + " ("
                + id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + recipeName + " TEXT, "
                + instructions + " TEXT) ";

        db.execSQL(query);

        String query2 = "CREATE TABLE "+ ingredientsTable + " ("
                + recipeID + " INTEGER, "
                + ingredientName + " STRING, "
                + ingredientAmount + " INTEGER)";

        db.execSQL(query2);

        String query3 = "CREATE TABLE "+ mealplanTable + " ("
                + recipeName + " STRING)";

        db.execSQL(query3);

        String query4 = "CREATE TABLE "+ shoppingListTable + " ("+ individualIngredients+ " STRING, " + amount +" INTEGER)";

        db.execSQL(query4);

    }

    public void addRecipe(Recipe recipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(recipeName, recipe.getName());
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
                        cursor.getString(2)));

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
            String instructions = cursor.getString(2);

            recipe = new Recipe(id,name,instructions);
        }

        cursor.close();
        return recipe;

    }

    public ArrayList<Ingredient> getIngredients(int recipeID) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Ingredient> ingredients = new ArrayList<>();

        String selection = "recipeID = ?";
        String[] selectionArgs = { String.valueOf(recipeID) };

        Cursor cursor = db.query(ingredientsTable, null, selection, selectionArgs, null, null, null);

        int recipeIDIndex = cursor.getColumnIndex(String.valueOf(recipeID));
        int ingredientNameIndex = cursor.getColumnIndex(ingredientName);
        int ingredientAmountIndex = cursor.getColumnIndex(ingredientAmount);

        if (cursor.moveToFirst()) {
            do {
                int recipeIDValue = (recipeIDIndex >= 0) ? cursor.getInt(recipeIDIndex) : -1;
                String ingredientNameValue = (ingredientNameIndex >= 0) ? cursor.getString(ingredientNameIndex) : "N/A";
                int ingredientAmountValue = (ingredientAmountIndex >= 0) ? cursor.getInt(ingredientAmountIndex) : -1;

                ingredients.add(new Ingredient(recipeIDValue, ingredientNameValue, ingredientAmountValue));
            } while (cursor.moveToNext());
        }

        cursor.close();

        return ingredients;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }


    public void addMeal(Recipe recipe,int portions) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(recipeName, recipe.getName());

        db.insert(mealplanTable,null,values);

        ArrayList<Ingredient> ingredients = getIngredients(recipe.getID());

        for (Ingredient ingredient : ingredients) {
            addShoppingItem(ingredient,portions);
        }
    }
    public void deleteMeal(Recipe recipe) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(mealplanTable, recipeName + " = ?", new String[]{recipe.getName()});

        ArrayList<Ingredient> ingredients = getIngredients(recipe.getID());

        for (Ingredient ingredient : ingredients) {
            deleteShoppingItem(ingredient.getName());
        }
        db.close();
    }

    public void deleteMeals() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(mealplanTable, null,null);
        db.delete(shoppingListTable,null,null);
    }

    private void deleteShoppingItem(String itemName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(shoppingListTable, individualIngredients + " = ?", new String[]{itemName});
        db.close();
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

    public void addIngredient(Ingredient ingredient) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(recipeID, ingredient.getID());
        values.put(ingredientName, ingredient.getName());
        values.put(ingredientAmount, ingredient.getAmount());

        db.insert(ingredientsTable, null, values);
        db.close();
    }

    public void addShoppingItem(Ingredient ingredient, int portions) {
        SQLiteDatabase db = this.getWritableDatabase();

        String name = ingredient.getName();
        int theAmount = ingredient.getAmount();
        theAmount = theAmount*portions;

        Cursor cursor = db.query(shoppingListTable, null, individualIngredients + " = ?", new String[]{name}, null, null, null);

        if (cursor.moveToFirst()) {
            int currentAmountColumnIndex = cursor.getColumnIndex(amount);
            int currentAmount = (currentAmountColumnIndex != -1) ? cursor.getInt(currentAmountColumnIndex) : 0;
            theAmount += currentAmount;

            ContentValues updateValues = new ContentValues();
            updateValues.put(amount, theAmount);

            db.update(shoppingListTable, updateValues, individualIngredients + " = ?", new String[]{name});
        } else {
            ContentValues insertValues = new ContentValues();
            insertValues.put(individualIngredients, name);
            insertValues.put(amount, theAmount);

            db.insert(shoppingListTable, null, insertValues);
        }

        cursor.close();
        db.close();
    }



    public HashMap<String, Integer> readShopping(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor= db.rawQuery("SELECT * FROM " + shoppingListTable, null);

        HashMap<String,Integer> ingredients = new HashMap<>();

        if (cursor.moveToFirst()) {
            do {
                ingredients.put(cursor.getString(0),cursor.getInt(1));

            } while (cursor.moveToNext());
        }

        cursor.close();
        return ingredients;
    }

}
