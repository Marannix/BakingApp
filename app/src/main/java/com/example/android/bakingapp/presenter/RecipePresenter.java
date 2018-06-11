package com.example.android.bakingapp.presenter;

import android.content.Context;
import android.view.ViewGroup;
import com.example.android.bakingapp.data.model.Recipe;
import com.example.android.bakingapp.utils.RecipeLoader;
import com.example.android.bakingapp.view.RecipeView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public class RecipePresenter {

  private Context context;
  private RecipeView recipeView;
  private List<Recipe> recipeList;

  public RecipePresenter(Context context) {
    this.context = context;
    recipeList = loadJsonFromAsset();
  }

  public void present(ViewGroup rootView) {
    recipeView = new RecipeView(rootView);
    recipeView.setRecipeData(recipeList, context);
  }

  public List<Recipe> loadJsonFromAsset() {
    String json = RecipeLoader.loadRecipeJson(context);
    if (json == null) return null;
    Gson gson = new Gson();
    Type arrayOfRecipes = new TypeToken<Collection<Recipe>>() {
    }.getType();
    return gson.fromJson(json, arrayOfRecipes);
  }
}
