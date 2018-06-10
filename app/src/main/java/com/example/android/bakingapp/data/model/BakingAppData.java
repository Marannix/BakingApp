package com.example.android.bakingapp.data.model;

import java.util.List;

// TODO: Rename to something better
public class BakingAppData {
  private int id;
  private String name;
  private List<Ingredients> ingredients;
  private List<Step> steps;
  private int servings;
  private String image;

  public BakingAppData(int id, String name, List<Ingredients> ingredients, List<Step> steps,
      int servings, String image) {
    this.id = id;
    this.name = name;
    this.ingredients = ingredients;
    this.steps = steps;
    this.servings = servings;
    this.image = image;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<Ingredients> getIngredients() {
    return ingredients;
  }

  public List<Step> getSteps() {
    return steps;
  }

  public int getServings() {
    return servings;
  }

  public String getImage() {
    return image;
  }
}
