package com.example.android.bakingapp;

import android.os.Bundle;
import com.example.android.bakingapp.activity.BaseActivity;
import com.example.android.bakingapp.presenter.RecipePresenter;

// TODO: Rename to RecipeActivity
public class RecipeActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    RecipePresenter recipePresenter = new RecipePresenter(getApplicationContext());
    recipePresenter.present(getViewGroup());

    //TODO: string res
    setTitle("Recipes");
  }
}
