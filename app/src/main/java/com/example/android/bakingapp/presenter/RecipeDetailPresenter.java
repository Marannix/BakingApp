package com.example.android.bakingapp.presenter;

import android.content.Context;
import android.view.ViewGroup;
import com.example.android.bakingapp.data.model.Recipe;
import com.example.android.bakingapp.view.RecipeDetailView;

public class RecipeDetailPresenter {

  private Context context;

  public RecipeDetailPresenter(Context context) {
    this.context = context;
  }

  public void present(ViewGroup viewGroup, Recipe recipe) {
    new RecipeDetailView(viewGroup, recipe, context);
  }
}
