package com.example.android.bakingapp;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.example.android.bakingapp.activity.RecipeActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.contrib.RecyclerViewActions;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;

@RunWith(AndroidJUnit4.class) public class RecipeActivityBasicTest {

  private static final int FIRST_POSITION = 0;

  @Rule public ActivityTestRule<RecipeActivity> activityTestRule =
      new ActivityTestRule<>(RecipeActivity.class);

  @Test public void clickOnTheFirstRecipe() {
    onView(ViewMatchers.withId(R.id.recipeRecyclerView)).perform(
        RecyclerViewActions.actionOnItemAtPosition(FIRST_POSITION, click()));
  }
}
