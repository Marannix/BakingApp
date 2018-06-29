package com.example.android.bakingapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapter.RecipePagerAdapter;
import com.example.android.bakingapp.data.model.Ingredients;
import com.example.android.bakingapp.data.model.Recipe;
import com.example.android.bakingapp.data.model.Step;
import com.example.android.bakingapp.fragment.IngredientsPagerFragment;
import com.example.android.bakingapp.fragment.StepsDetailFragment;
import com.example.android.bakingapp.fragment.StepsPagerFragment;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.bakingapp.adapter.RecipeAdapter.EXTRA_MESSAGE;

public class RecipePagerActivity extends BaseActivity {

  private static final String RECIPE = "recipe";
  private static final String LAST_VIDEO_INDEX = "last_video_index";
  private static final int FIRST_VIDEO = 0;

  private static final String SAVED_POSITION = "position";
  private static final String SAVED_CURRENT_WINDOW = "current_window";
  private static final String SAVED_STATE = "state";

  @BindView(R.id.tabs) TabLayout tabs;
  @BindView(R.id.viewpager) ViewPager viewPager;
  @Nullable @BindView(R.id.stepsDetailFragment) FrameLayout stepDetailLayout;

  private IngredientsPagerFragment ingredientsPageFragment;
  private StepsPagerFragment stepsPageFragment;
  private StepsDetailFragment stepFragment;
  private Recipe recipe;
  private List<Ingredients> ingredients;
  private List<Step> steps;
  private RecipePagerAdapter recipePagerAdapter;
  private boolean isTablet;
  private int lastVideoIndex = FIRST_VIDEO;

  private long playbackPosition;
  private int currentWindow;
  private boolean playWhenReady = true;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.recipe_pager);
    ButterKnife.bind(this, getViewGroup());

    if (savedInstanceState != null) {
      recipe = savedInstanceState.getParcelable(RECIPE);
      lastVideoIndex = savedInstanceState.getInt(LAST_VIDEO_INDEX);
      playbackPosition = savedInstanceState.getLong(SAVED_POSITION);
      currentWindow = savedInstanceState.getInt(SAVED_CURRENT_WINDOW);
      playWhenReady = savedInstanceState.getBoolean(SAVED_STATE);
    } else {
      recipe = getIntent().getParcelableExtra(EXTRA_MESSAGE);
    }

    isTablet = stepDetailLayout != null;

    ingredients = recipe.getIngredients();
    steps = recipe.getSteps();
    recipePagerAdapter = new RecipePagerAdapter(getSupportFragmentManager());
    initPagerFragments();
    viewPager.setAdapter(recipePagerAdapter);
    tabs.setupWithViewPager(viewPager);

    Log.d("Joshua", "RecipePagerActivity onCreate: " + this);
    showTabletLayout(steps.get(lastVideoIndex), playbackPosition, currentWindow, playWhenReady);

    setTitle(recipe.getName());
  }

  public void showTabletLayout(Step position, long playbackPosition, int currentWindow,
      boolean playWhenReady) {
    if (isTablet) {
      Log.d("Joshua", "showTabletLayout: the playbackPosition is  " + playbackPosition);
      stepFragment = StepsDetailFragment.newStepInstance(position, playbackPosition, currentWindow, playWhenReady,
          isTablet);
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.stepsDetailFragment, stepFragment)
          .commit();
    }
  }

  private void initPagerFragments() {
    initIngredientsPagerFragment();
    initStepsPagerFragment();
  }

  private void initIngredientsPagerFragment() {
    ingredientsPageFragment =
        IngredientsPagerFragment.newIngredientsInstance((ArrayList<Ingredients>) ingredients);
    recipePagerAdapter.addFragment(ingredientsPageFragment, "Ingredients");
  }

  private void initStepsPagerFragment() {
    stepsPageFragment =
        StepsPagerFragment.newStepsInstance((ArrayList<Step>) steps, recipe.getName(), isTablet);
    recipePagerAdapter.addFragment(stepsPageFragment, "Steps");
  }

  public void onStepSelected(Step step) {
    stepFragment.updateSteps(step);
    lastVideoIndex = step.getId();
  }

  public void onFragmentDestroy(long playbackPosition, int currentWindow, boolean playWhenReady) {
    Log.d("joshua", "onFragmentDestroy: position is " + playbackPosition);
    this.playbackPosition = playbackPosition;
    this.currentWindow = currentWindow;
    this.playWhenReady = playWhenReady;

  }

  @Override protected void onSaveInstanceState(Bundle savedInstanceState) {
    super.onSaveInstanceState(savedInstanceState);
    savedInstanceState.putParcelable(RECIPE, recipe);
    savedInstanceState.putInt(LAST_VIDEO_INDEX, lastVideoIndex);
    savedInstanceState.putLong(SAVED_POSITION, playbackPosition);
    savedInstanceState.putLong(SAVED_CURRENT_WINDOW, currentWindow);
    savedInstanceState.putBoolean(SAVED_STATE, playWhenReady);
  }
}
