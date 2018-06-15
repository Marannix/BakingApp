package com.example.android.bakingapp.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.example.android.bakingapp.adapter.RecipePagerAdapter;
import com.example.android.bakingapp.fragment.IngredientsPageFragment;
import com.example.android.bakingapp.fragment.StepsPageFragment;

public class RecipePagerView {

  public RecipePagerView(ViewGroup viewGroup, FragmentManager supportFragmentManager,
      IngredientsPageFragment ingredientFragment, StepsPageFragment stepFragment, ViewPager viewPager) {
    ButterKnife.bind(this, viewGroup);
    RecipePagerAdapter recipePagerAdapter = new RecipePagerAdapter(supportFragmentManager);
    recipePagerAdapter.addFragment(ingredientFragment, "Ingredients");
    recipePagerAdapter.addFragment(stepFragment, "Steps");
    viewPager.setAdapter(recipePagerAdapter);
  }
}
