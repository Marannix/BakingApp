package com.example.android.bakingapp.presenter;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import com.example.android.bakingapp.fragment.IngredientsPagerFragment;
import com.example.android.bakingapp.fragment.StepsPagerFragment;
import com.example.android.bakingapp.view.RecipePagerView;

public class RecipePagerPresenter {

  //TODO: Fix this when I have time
  private RecipePagerView recipePagerView;

  public void present(ViewGroup viewGroup, FragmentManager supportFragmentManager,
      IngredientsPagerFragment ingredientFragment, StepsPagerFragment stepFragment,
      ViewPager viewPager) {
    recipePagerView =
        new RecipePagerView(viewGroup, supportFragmentManager, ingredientFragment, stepFragment,
            viewPager);
  }
}
