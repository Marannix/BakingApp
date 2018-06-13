package com.example.android.bakingapp.presenter;

import android.content.Context;
import android.view.ViewGroup;
import com.example.android.bakingapp.data.model.Recipe;
import com.example.android.bakingapp.view.RecipeIngredientsView;
import com.example.android.bakingapp.view.RecipeStepsList;

public class RecipeDetailPresenter {

  private Context context;

  public RecipeDetailPresenter(Context context) {
    this.context = context;
  }

  public void present(ViewGroup viewGroup, Recipe recipe) {
    new RecipeIngredientsView(viewGroup, recipe, context);
    new RecipeStepsList(viewGroup, recipe.getSteps(), context);
  }
}
