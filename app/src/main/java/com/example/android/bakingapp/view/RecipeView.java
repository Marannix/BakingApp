package com.example.android.bakingapp.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapter.RecipeAdapter;
import com.example.android.bakingapp.data.model.Recipe;
import java.util.List;

public class RecipeView {

  private ViewGroup parent;
  private RecipeAdapter recipeAdapter;
  private LinearLayoutManager layoutManager;

  @BindView(R.id.recipeRecyclerView) RecyclerView recyclerView;

  public RecipeView(ViewGroup parent) {
    ButterKnife.bind(this, parent);
    this.parent = parent;
    initRecipeAdapter();
  }

  public void setRecipeData(List<Recipe> recipes, Context context) {
    recipeAdapter.setRecipeData(recipes, context);
  }

  private void initRecipeAdapter() {
    recyclerView.setHasFixedSize(true);
    layoutManager = new LinearLayoutManager(parent.getContext());
    recyclerView.setLayoutManager(layoutManager);
    recipeAdapter = new RecipeAdapter();
    recyclerView.setAdapter(recipeAdapter);
  }

}
