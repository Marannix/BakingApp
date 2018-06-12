package com.example.android.bakingapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.model.Recipe;
import com.example.android.bakingapp.presenter.RecipeDetailPresenter;

import static com.example.android.bakingapp.adapter.RecipeAdapter.EXTRA_MESSAGE;

public class RecipeDetailActivity extends BaseActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.recipe_info_layout);
    Recipe recipe = getIntent().getParcelableExtra(EXTRA_MESSAGE);
    RecipeDetailPresenter presenter = new RecipeDetailPresenter(getApplicationContext());
    presenter.present(getViewGroup(), recipe);
    setTitle(recipe.getName());
  }
}
