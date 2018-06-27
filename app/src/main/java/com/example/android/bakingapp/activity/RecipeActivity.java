package com.example.android.bakingapp.activity;

import android.os.Bundle;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.activity.BaseActivity;
import com.example.android.bakingapp.presenter.RecipePresenter;

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
