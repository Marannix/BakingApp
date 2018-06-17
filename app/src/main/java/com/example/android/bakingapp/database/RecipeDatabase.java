package com.example.android.bakingapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.example.android.bakingapp.data.model.Ingredients;

@Database(entities = { Ingredients.class }, version = 1) public abstract class RecipeDatabase
    extends RoomDatabase {

  abstract public IngredientsDao ingredientsDao();
}
