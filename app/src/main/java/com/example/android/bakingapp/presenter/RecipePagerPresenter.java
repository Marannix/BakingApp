package com.example.android.bakingapp.presenter;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import com.example.android.bakingapp.fragment.IngredientsPageFragment;
import com.example.android.bakingapp.fragment.StepsPageFragment;
import com.example.android.bakingapp.view.RecipePagerView;

public class RecipePagerPresenter {

  //TODO: Fix this when I have time
  private RecipePagerView recipePagerView;

  public void present(ViewGroup viewGroup, FragmentManager supportFragmentManager,
      IngredientsPageFragment ingredientFragment, StepsPageFragment stepFragment,
      ViewPager viewPager) {
    recipePagerView =
        new RecipePagerView(viewGroup, supportFragmentManager, ingredientFragment, stepFragment,
            viewPager);
  }
}
