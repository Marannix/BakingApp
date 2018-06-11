package com.example.android.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.android.bakingapp.data.model.Recipe;
import com.example.android.bakingapp.utils.RecipeLoader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    List<Recipe> recipeList = loadJsonFromAsset();

    //TODO: Need to pass this to something... hmm.. adapter... hmm... idk
    for (int i = 0; i < recipeList.size(); i++) {
      Recipe recipe = recipeList.get(i);
    }
  }

  public List<Recipe> loadJsonFromAsset() {
    String json = RecipeLoader.loadRecipeJson(this);
    if (json == null) return null;
    Gson gson = new Gson();
    Type arrayOfRecipes = new TypeToken<Collection<Recipe>>(){}.getType();
    return gson.fromJson(json, arrayOfRecipes);
  }


}
