package com.example.android.bakingapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapter.RecipePagerAdapter;
import com.example.android.bakingapp.data.model.Ingredients;
import com.example.android.bakingapp.data.model.Recipe;
import com.example.android.bakingapp.data.model.Step;
import com.example.android.bakingapp.fragment.IngredientsPageFragment;
import com.example.android.bakingapp.fragment.StepsPageFragment;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.bakingapp.adapter.RecipeAdapter.EXTRA_MESSAGE;

public class RecipePagerActivity extends BaseActivity {

  @BindView(R.id.tabs) TabLayout tabs;
  @BindView(R.id.viewpager) ViewPager viewPager;

  private IngredientsPageFragment ingredientsPageFragment;
  private StepsPageFragment stepsPageFragment;
  private Recipe recipe;
  private List<Ingredients> ingredients;
  private List<Step> steps;
  private RecipePagerAdapter recipePagerAdapter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.recipe_pager);
    ButterKnife.bind(this, getViewGroup());
    recipe = getIntent().getParcelableExtra(EXTRA_MESSAGE);
    ingredients = recipe.getIngredients();
    steps = recipe.getSteps();
    recipePagerAdapter = new RecipePagerAdapter(getSupportFragmentManager());
    initPagerFragments();
    viewPager.setAdapter(recipePagerAdapter);
    tabs.setupWithViewPager(viewPager);
    setTitle(recipe.getName());
  }

  private void initPagerFragments() {
    initIngredientsPagerFragment();
    initStepsPagerFragment();
  }

  private void initIngredientsPagerFragment() {
    ingredientsPageFragment =
        IngredientsPageFragment.newIngredientsInstance((ArrayList<Ingredients>) ingredients);
    recipePagerAdapter.addFragment(ingredientsPageFragment, "Ingredients");
  }

  private void initStepsPagerFragment() {
    stepsPageFragment = StepsPageFragment.newStepsInstance((ArrayList<Step>) steps);
    recipePagerAdapter.addFragment(stepsPageFragment, "Steps");
  }
}
