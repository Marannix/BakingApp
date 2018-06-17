package com.example.android.bakingapp.database;

import android.content.Context;
import com.example.android.bakingapp.data.model.Ingredients;
import java.util.List;

public class DatabaseHelper {

  private RecipeDatabase recipeDatabase;

  private void retrieveDatabase(Context context) {
    recipeDatabase = AppModule.getAppDatabase(context);
  }

  public List<Ingredients> getAllIngredients(int widgetId) {
    return recipeDatabase.ingredientsDao().getAllIngredients(widgetId);
  }
}
