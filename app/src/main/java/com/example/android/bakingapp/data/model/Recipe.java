package com.example.android.bakingapp.data.model;

import java.util.List;

public class Recipe {
  private int id;
  private String name;
  private List<Ingredients> ingredients;
  private List<Step> steps;
  private int servings;
  private String image;

  public Recipe() {

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

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setIngredients(List<Ingredients> ingredients) {
    this.ingredients = ingredients;
  }

  public void setSteps(List<Step> steps) {
    this.steps = steps;
  }

  public void setServings(int servings) {
    this.servings = servings;
  }

  public void setImage(String image) {
    this.image = image;
  }
}
